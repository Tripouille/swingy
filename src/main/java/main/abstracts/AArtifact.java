package main.abstracts;

import java.lang.module.ModuleDescriptor.Modifier;
import java.util.HashMap;
import java.util.Set;

public abstract class AArtifact {
	protected long baseValue;
	protected String rarity;
	private final static RarityModifier rarityModifier = new RarityModifier();
	
	private static class RarityModifier {
		private HashMap<String, Double> modifier = new HashMap<String, Double>(4);
		
		RarityModifier() {
			modifier.put("Normal", 1.0);
			modifier.put("Rare", 1.5);
			modifier.put("Epique", 2.25);
			modifier.put("Legendaire", 3.37);
		}

		public Double getModifier(String rarity) {
			return (modifier.get(rarity));
		}

		public Set<String> getRaritySet() {
			return (modifier.keySet());
		}
	}

	public AArtifact(long baseValue, String rarity) {
		this.baseValue = baseValue;
		this.rarity = rarity;
		System.out.println(rarityModifier.getRaritySet().toArray().length);
	}

	@Override
	public String toString() {
		return (": baseValue => [" + this.baseValue + "] Rarity => ["
				+ this.rarity + "] totalValue => [" + this.baseValue * rarityModifier.getModifier(rarity) + "]");
	}
}
