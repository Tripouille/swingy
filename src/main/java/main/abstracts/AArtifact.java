package main.abstracts;

import java.lang.module.ModuleDescriptor.Modifier;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

@Entity
public abstract class AArtifact {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	protected long baseValue;
	protected String rarity;
	protected long totalValue;
	private final static RarityManager rarityManager = new RarityManager();
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	private final static EntityManager em = emf.createEntityManager();
	
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

	public void copy(AArtifact src) {
		this.baseValue = src.baseValue;
		this.rarity = src.rarity;
		this.totalValue = src.totalValue;
	}

	public void save() {
		em.getTransaction().begin();
		em.persist(this);
		em.getTransaction().commit();
	}

	public AArtifact find(long id) {
		em.getTransaction().begin();
		return (em.find(AArtifact.class, id));
	}
}
