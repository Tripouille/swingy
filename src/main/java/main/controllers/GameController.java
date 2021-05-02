package main.controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Min;
import main.abstracts.AHero;
import main.models.game.Game;
import main.models.hero.AHeroFactory;
import main.views.GameView;

public class GameController {
	private Game model;
	private GameView view = new GameView();
	private Scanner scanner = new Scanner(System.in);
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public GameController(Game model) {
		this.model = model;
	}

	private Integer getValidIndexFromInput(Integer maxIndex) {
		Integer index = null;

		try {
			index = Integer.parseInt(this.scanner.nextLine());
		} catch (NumberFormatException | NullPointerException e) {
			return (null);
		}
		return (index < 0 || index > maxIndex ? null : index);
	}

	public void selectHero() {
		System.out.println("> Select Hero <");
		ArrayList<String> heroes = model.getHeroesFromDB();
		int createIndex = heroes.size();
		int deleteIndex = createIndex + 1;
		Integer heroIndex = null;

		do {
			if (model.getMode().equals("CONSOLE"))
				view.renderConsoleSelection(heroes);
			else
				view.renderGuiSelection(heroes);
		} while ((heroIndex = getValidIndexFromInput(createIndex == 0 ? createIndex : deleteIndex)) == null);
		if (heroIndex == createIndex)
			createHero(heroes);
		else if (heroIndex == deleteIndex)
			deleteHero(heroes);
		else
			loadHero(heroes.get(heroIndex));
	}

	private void createHero(ArrayList<String> heroesAlreadyInDB) {
		System.out.println("> Create New Hero <");
		ArrayList<String> availableClass = AHeroFactory.getAllAvailableClass();
		Integer classIndex = askClassIndex(availableClass);
		String heroClass = availableClass.get(classIndex);
		Set<ConstraintViolation<AHero>> constraintViolations;
		String heroName = null;
		AHero newHero = null;

		do {
			if (model.getMode().equals("CONSOLE"))
				view.renderConsoleCreateHeroName(heroClass);
			else
				view.renderGuiCreateHeroName(heroClass);
			heroName = this.scanner.nextLine();
			newHero = AHeroFactory.create(heroName, heroClass);
			constraintViolations = validator.validate(newHero);
			if (constraintViolations.size() > 0)
				for (ConstraintViolation<AHero> contraintes : constraintViolations)
					System.out.println(contraintes.getRootBeanClass().getSimpleName() +
						"." + contraintes.getPropertyPath() + " " + contraintes.getMessage());
			else if (heroesAlreadyInDB.contains(heroName))
				System.out.println("This name is already use.");
		} while (heroesAlreadyInDB.contains(heroName) || constraintViolations.size() > 0);
		newHero.save();
		selectHero();
	}

	private void deleteHero(ArrayList<String> heroesAlreadyInDB) {
		System.out.println("> Delete Hero <");
		Integer heroIndex = null;

		do {
			if (model.getMode().equals("CONSOLE"))
				view.renderConsoleDeleteHero(heroesAlreadyInDB);
			else
				view.renderGuiDeleteHero(heroesAlreadyInDB);
		} while ((heroIndex = getValidIndexFromInput(heroesAlreadyInDB.size() - 1)) == null);
		model.importHero(heroesAlreadyInDB.get(heroIndex));
		model.deleteHero();
		selectHero();
	}
	
	private Integer askClassIndex(ArrayList<String> availableClass) {
		Integer classIndex = null;

		do {
			if (model.getMode().equals("CONSOLE"))
				view.renderConsoleCreateHeroClass(availableClass);
			else
				view.renderGuiCreateHeroClass(availableClass);
		} while ((classIndex = getValidIndexFromInput(availableClass.size() - 1)) == null);
		return (classIndex);
	}

	private void loadHero(String heroName) {
		System.out.println("Loading hero " + heroName);
		model.importHero(heroName);
	}

	public void start() {
		System.out.println("> Game Start <");
	}
}
