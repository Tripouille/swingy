package main.models.artifact;

import javax.persistence.Entity;
import javax.persistence.Table;

import main.abstracts.AArtifact;

@Entity
@Table(name="aartifact")
public class Weapon extends AArtifact {
	public Weapon(long baseValue, String rarity) {
		super(baseValue, rarity);
	}

	public Weapon(long baseValue) {
		super(baseValue);
	}

	public Weapon() {
		super(10);
	}

	@Override
	public String toString() {
		return ("Weapon" + super.toString());
	}
}
