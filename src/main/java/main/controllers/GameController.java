package main.controllers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.swing.UnsupportedLookAndFeelException;

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
	private GameView view = new GameView(this);
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
		ArrayList<AHero> heroes = model.getHeroesFromDB();
		if (model.getMode().equals("CONSOLE"))
			selectHeroConsole(heroes);
		else
			selectHeroGui(heroes);
	}
	private void selectHeroConsole(ArrayList<AHero> heroes) {
		System.out.println("> Select Hero <");
		int createIndex = heroes.size();
		int deleteIndex = createIndex + 1;
		int switchIndex = createIndex == 0 ? createIndex + 1 : createIndex + 2;
		int quitIndex = switchIndex + 1;
		Integer index = null;

		do {
			view.renderSelectionConsole(heroes);
		} while ((index = getValidIndexFromInput(quitIndex)) == null);

		if (index == quitIndex)
			System.exit(0);
		else if (index == switchIndex) {
			switchMode();
			selectHero();
		}
		else if (index == createIndex)
			createHeroConsole(heroes);
		else if (index == deleteIndex)
			deleteHeroConsole(heroes);
		else
			loadHero(heroes.get(index));
	}
	private void selectHeroGui(ArrayList<AHero> heroes) {
		view.renderSelectionGui(heroes);
	}

	private void createHeroConsole(ArrayList<AHero> heroesAlreadyInDB) {
		System.out.println("> Create New Hero <");
		ArrayList<AHero> availableClass = AHeroFactory.getAllAvailableClass();
		Integer classIndex = askClassIndexConsole(availableClass);
		String heroClass = availableClass.get(classIndex).getName();
		Set<ConstraintViolation<AHero>> constraintViolations;
		String heroName = null;
		AHero newHero = null;
		ArrayList<String> alreadyUsedName = new ArrayList<String>();
		for (AHero hero : heroesAlreadyInDB) alreadyUsedName.add(hero.getName());

		do {	
			view.renderCreateHeroNameConsole(heroClass);
			heroName = this.scanner.nextLine();
			newHero = AHeroFactory.create(heroName, heroClass);
			constraintViolations = validator.validate(newHero);
			if (constraintViolations.size() > 0)
				for (ConstraintViolation<AHero> contraintes : constraintViolations)
					System.out.println(contraintes.getRootBeanClass().getSimpleName() +
										"." + contraintes.getPropertyPath() + " " + contraintes.getMessage());
			else if (alreadyUsedName.contains(heroName))
				System.out.println("This name is already use.");
		} while (alreadyUsedName.contains(heroName) || constraintViolations.size() > 0);
		newHero.save();
		selectHero();
	}

	private void deleteHeroConsole(ArrayList<AHero> heroesAlreadyInDB) {
		System.out.println("> Delete Hero <");
		Integer heroIndex = null;

		do {
			view.renderDeleteHeroConsole(heroesAlreadyInDB);
		} while ((heroIndex = getValidIndexFromInput(heroesAlreadyInDB.size() - 1)) == null);
		heroesAlreadyInDB.get(heroIndex).remove();
		selectHero();
	}
	
	private Integer askClassIndexConsole(ArrayList<AHero> availableClass) {
		Integer classIndex = null;

		do {
			view.renderCreateHeroClassConsole(availableClass);
		} while ((classIndex = getValidIndexFromInput(availableClass.size() - 1)) == null);
		return (classIndex);
	}

	private void loadHero(AHero hero) {
		model.importHero(hero);
	}

	public void start() {
		System.out.println("> Game Start <");
		model.showHero();
	}

	public void switchMode() {
		model.switchMode();
	}
}
