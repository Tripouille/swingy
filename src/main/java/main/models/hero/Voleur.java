package main.models.hero;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import main.abstracts.ACharacter;
import main.abstracts.AHero;
import main.models.artifact.AArtifactFactory;

@Entity
@Table(name="ahero")
public class Voleur extends AHero {
	@Transient
	private double criticalRate = 0.25;
	public Voleur(String name) {
		super(name, 10, 2, 23);
		this.weapon = AArtifactFactory.create("Weapon", 10, "Normal");
	}

	public Voleur() {
		super("Tripouille", 10, 2, 23);
		this.weapon = AArtifactFactory.create("Weapon", 10, "Normal");
	}

	@Override
	public void attack(ACharacter target) {
		long totalDamage = attack + weapon.getTotalValue();
		if (Math.random() < criticalRate) totalDamage *= 2;
		target.takeDamage(totalDamage);
	}
}
