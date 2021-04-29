package main.models.hero;

import java.util.HashMap;

import main.abstracts.AHero;
import main.models.hero.*;

public abstract class AHeroFactory {
	private final static Factory factory = new Factory();

	private interface CreateAHero {
		AHero create(String name);
	}

	private static class Factory {
		private HashMap<String, CreateAHero> recipe = new HashMap<String, CreateAHero>();

		Factory() {
			recipe.put("Voleur", new CreateAHero() {
				public AHero create(String name) {
					return (new Voleur(name));
				}
			});
		}
	}

	public static AHero create(String heroName, String heroClass) {
		if (factory.recipe.containsKey(heroClass))
			return (new AHero(heroName, 10, 11, 12));
		return (null);
	}

}
