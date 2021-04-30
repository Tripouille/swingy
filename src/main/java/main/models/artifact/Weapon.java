package main.models.artifact;

import javax.persistence.Entity;
import javax.persistence.Table;

import main.abstracts.AArtifact;

@Entity
@Table(name="aartifact")
class Weapon extends AArtifact {
	public Weapon(long baseValue, String rarity) {
		super(baseValue, rarity);
	}

	public Weapon(long baseValue) {
		super(baseValue);
	}

	@Override
	public String toString() {
		return ("Weapon" + super.toString());
	}
}
