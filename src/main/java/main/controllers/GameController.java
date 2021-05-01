package main.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import main.models.game.Game;
import main.models.hero.AHeroFactory;
import main.views.GameView;

public class GameController {
	private Game model;
	private GameView view = new GameView();
	private Scanner scanner = new Scanner(System.in);

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
		Integer heroIndex = null;

		do {
			if (model.getMode().equals("CONSOLE"))
				view.renderConsoleSelection(heroes);
			else
				view.renderGuiSelection(heroes);
		} while ((heroIndex = getValidIndexFromInput(createIndex)) == null);
		if (heroIndex == createIndex)
			createNewHero();
		else
			loadHero(heroes.get(heroIndex));
	}

	private void createNewHero() {
		System.out.println("Create New Hero");
		Set<String> availableHeroes = AHeroFactory.getAllAvailableHeroes();
		Integer heroIndex = null;

		do {
			if (model.getMode().equals("CONSOLE"))
				view.renderConsoleCreation(availableHeroes);
			else
				view.renderGuiCreation(availableHeroes);
		} while ((heroIndex = getValidIndexFromInput(availableHeroes.size() - 1)) == null);
		System.out.println(heroIndex); // <--- change Set to ArrayList pour instancier facilement
	}

	private void loadHero(String heroName) {
		System.out.println("Loading hero " + heroName);
		model.importHero(heroName);
	}

	public void start() {
		System.out.println("> Game Start <");
	}
}
