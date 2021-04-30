package main.models.hero;

import main.abstracts.ACharacter;
import main.abstracts.AHero;

public class Voleur extends AHero {
	public Voleur(String name) {
		super(name, 10, 60, 23);
		this.heroClass = "Voleur";
	}

	@Override
	public void doDamage(ACharacter target) {
		target.takeDamage(this.attack * 2);
	}
}
