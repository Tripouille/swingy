package main.models.artifact;

import java.util.HashMap;

import main.abstracts.AArtifact;

public class AArtifactFactory {
	private final static Factory factory = new Factory();

	private interface CreateAArtifact {
		AArtifact create(long baseValue, String rarity);
		AArtifact create(long baseValue);
	}

	private static class Factory {
		private HashMap<String, CreateAArtifact> recipe = new HashMap<String, CreateAArtifact>();

		Factory() {
			recipe.put("Weapon", new CreateAArtifact() {
				public AArtifact create(long baseValue, String rarity) {
					return (new Weapon(baseValue, rarity));
				}
				public AArtifact create(long baseValue) {
					return (new Weapon(baseValue));
				}
			});
		}
	}

	public static AArtifact create(String ArtifactType, long baseValue, String rarity) {
		if (factory.recipe.containsKey(ArtifactType))
			return (factory.recipe.get(ArtifactType).create(baseValue, rarity));
		return (null);
	}

	public static AArtifact create(String ArtifactType, long baseValue) {
		if (factory.recipe.containsKey(ArtifactType))
			return (factory.recipe.get(ArtifactType).create(baseValue));
		return (null);
	}
}
