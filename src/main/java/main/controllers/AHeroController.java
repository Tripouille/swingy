package main.controllers;

import main.abstracts.AArtifact;
import main.abstracts.AHero;
import main.views.AHeroView;

public class AHeroController {
	private AHero model;
	private AHeroView view = new AHeroView();

	public void render(String mode) {
		if (mode == "CLI")
			view.renderCLI(model);
		else if (mode == "GUI")
			view.renderGUI(model);
	}

	public void equipWeapon(AArtifact newWeapon) {
		System.out.println(this.model.getName() + " equip " + newWeapon);
		this.model.updateWeapon(newWeapon);
	}

	public void loadModel(String heroName) {
		model = AHero.find(heroName);
	}

	public AHero getHero(String heroName) {
		return AHero.find(heroName);
	}

	public void removeModel() {
		model.remove();
	}
}
