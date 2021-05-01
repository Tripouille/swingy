package main.models.artifact.game;

import main.controllers.AHeroController;

public class Game {
	String mode;
	AHeroController heroController;

	public Game(String mode) {
		this.mode = mode;
		System.out.println("Starting game in mode [" + mode + "]");
	}

	public void chooseHero() {
		System.out.println("Select your hero.");
	}
}
