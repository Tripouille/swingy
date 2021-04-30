package main.models.hero;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import main.abstracts.ACharacter;
import main.abstracts.AHero;

@Entity
@Table(name="ahero")
public class Voleur extends AHero {
	public Voleur(String name) {
		super(name, 10, 60, 23);
	}

	public Voleur() {
		super("Tripouille", 10, 60, 23);
	}

	@Override
	public void doDamage(ACharacter target) {
		target.takeDamage(this.attack * 2);
	}
}
