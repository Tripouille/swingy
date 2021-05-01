package main.views;

import main.abstracts.AHero;

public class AHeroView {
	private AHero hero;

	public AHeroView(AHero hero) {
		this.hero = hero;
	}

	public void renderCLI() {
		System.out.println("CLI " + hero);
	}

	public void renderGUI() {
		System.out.println("GUI " + hero);
	}
}
