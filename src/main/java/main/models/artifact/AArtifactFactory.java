package main.models.artifact;

import java.util.HashMap;

import main.abstracts.AArtifact;

public class AArtifactFactory {
	private final static Factory factory = new Factory();

	private interface CreateAArtifact {
		AArtifact create(long baseValue, String rarity);
	}

	private static class Factory {
		private HashMap<String, CreateAArtifact> recipe = new HashMap<String, CreateAArtifact>();

		Factory() {
			recipe.put("Weapon", new CreateAArtifact() {
				public AArtifact create(long baseValue, String rarity) {
					return (new Weapon(baseValue, rarity));
				}
			});
		}
	}

	public static AArtifact create(String ArtifactType, long baseValue, String rarity) {
		if (factory.recipe.containsKey(ArtifactType))
			return (new Weapon(10, "Normal"));
		return (null);
	}
}
