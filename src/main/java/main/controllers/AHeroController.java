package main.controllers;

import main.abstracts.AArtifact;
import main.abstracts.AHero;
import main.views.AHeroView;

public class AHeroController {
	private AHero model;
	private AHeroView view;

	public AHeroController(AHero model) {
		this.model = model;
		this.view = new AHeroView(model);
	}

	public void render(String mode) throws Exception {
		if (mode == "CLI")
			view.renderCLI();
		else if (mode == "GUI")
			view.renderGUI();
	}

	public void equipWeapon(AArtifact newWeapon) {
		System.out.println(this.model.getName() + " equip " + newWeapon);
		this.model.updateWeapon(newWeapon);
	}
}
