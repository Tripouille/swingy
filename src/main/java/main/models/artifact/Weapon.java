package main.models.artifact;

import main.abstracts.AArtifact;

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
