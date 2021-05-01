package main.views;

import main.abstracts.AHero;

public class AHeroView {

	public void renderCLI(AHero model) {
		System.out.println("CLI " + model);
	}

	public void renderGUI(AHero model) {
		System.out.println("GUI " + model);
	}
}
