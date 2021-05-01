package main.models.hero;

import java.util.HashMap;
import java.util.Set;

import main.abstracts.AHero;
import main.models.hero.*;

public abstract class AHeroFactory {
	private final static Factory factory = new Factory();

	private interface CreateAHero {
		AHero create(String name);
		String infos();
	}

	private static class Factory {
		private HashMap<String, CreateAHero> recipe = new HashMap<String, CreateAHero>();

		Factory() {
			recipe.put("Voleur", new CreateAHero() {
				public AHero create(String name) {
					return (new Voleur(name));
				}
				public String infos() {
					return ("has 25% chance to deal double damage.");
				}
			});
		}
	}

	public static AHero create(String heroName, String heroClass) {
		if (factory.recipe.containsKey(heroClass))
			return (factory.recipe.get(heroClass).create(heroName));
		return (null);
	}

	public static Set<String> getAllAvailableHeroes() {
		return (factory.recipe.keySet());
	}

	public static String getHeroInfos(String heroClass) {
		if (factory.recipe.containsKey(heroClass))
			return (factory.recipe.get(heroClass).infos());
		return (null);
	}
}
