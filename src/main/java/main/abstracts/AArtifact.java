package main.abstracts;

import java.lang.module.ModuleDescriptor.Modifier;
import java.util.HashMap;
import java.util.Set;

public abstract class AArtifact {
	protected long baseValue;
	protected String rarity;
	protected long totalValue;
	private final static RarityManager rarityManager = new RarityManager();
	
	private static class RarityManager {
		private HashMap<String, Integer> modifier = new HashMap<String, Integer>(4);
		
		RarityManager() {
			modifier.put("Normal", 0);
			modifier.put("Rare", 50);
			modifier.put("Epique", 225);
			modifier.put("Legendaire", 350);
		}

		Integer getModifier(String rarity) {
			return (modifier.get(rarity));
		}

		String getRandomRarity() {
			return (String)(modifier.keySet().toArray()[(int)(Math.random() * 100) % modifier.size()]);
		}
	}

	public AArtifact(long baseValue, String rarity) {
		this.baseValue = baseValue;
		this.rarity = rarity;
		this.totalValue = baseValue + (baseValue * rarityManager.getModifier(rarity) / 100);
	}

	public AArtifact(long baseValue) {
		this.baseValue = baseValue;
		this.rarity = rarityManager.getRandomRarity();
		this.totalValue = baseValue + (baseValue * rarityManager.getModifier(rarity) / 100);
	}

	@Override
	public String toString() {
		return (": baseValue => [" + this.baseValue + "] Rarity => ["
				+ this.rarity + " +" + rarityManager.getModifier(rarity)
				+ "%] totalValue => [" + this.totalValue + "]");
	}
}
