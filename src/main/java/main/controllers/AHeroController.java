package main.controllers;

import main.abstracts.AArtifact;
import main.abstracts.AHero;
import main.views.AHeroView;

public class AHeroController {
	private AHero model;
	private AHeroView view = new AHeroView();

	public void render(String mode) {
		if (mode.equals("CONSOLE"))
			view.renderCLI(model);
		else
			view.renderGUI(model);
	}

	public void equipWeapon(AArtifact newWeapon) {
		System.out.println(this.model.getName() + " equip " + newWeapon);
		this.model.updateWeapon(newWeapon);
	}

	public void loadModel(AHero hero) {
		model = hero;
	}

	public AHero getHeroFromDB(String heroName) {
		return AHero.find(heroName);
	}

	public void removeModel() {
		model.remove();
	}
}
