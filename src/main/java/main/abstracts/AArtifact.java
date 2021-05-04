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
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.TransactionRequiredException;
import java.util.ArrayList;

import org.hibernate.PersistentObjectException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity @Getter
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
		private ArrayList<Rarity> rarity = new ArrayList<Rarity>(4);
		@AllArgsConstructor
		private class Rarity {
			private String name;
			private Integer modifier;
			private Double dropRate;
		}
		RarityManager() {
			rarity.add(new Rarity("Legendaire", 350, 0.05));
			rarity.add(new Rarity("Epique", 225, 0.15));
			rarity.add(new Rarity("Rare", 50, 0.30));
			rarity.add(new Rarity("Normal", 0, 1.0));
		}

		Integer getModifier(String name) {
			for (Rarity r : rarity)
				if (name.equals(r.name))
					return (r.modifier);
			return (0);
		}

		String getRandomRarity() {
			Double random = Math.random();
			for (Rarity r : rarity)
				if (random <= r.dropRate)
					return (r.name);
			return ("");
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
	
	public void remove() {
		em.getTransaction().begin();
		em.remove(this);
		em.getTransaction().commit();
	}

	static public AArtifact find(long id) {
		em.getTransaction().begin();
		AArtifact result = em.find(AArtifact.class, id);
		em.getTransaction().commit();
		return (result);
	}
}
