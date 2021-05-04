package main.abstracts;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import main.models.artifact.AArtifactFactory;
import main.abstracts.AArtifact;

@Entity @Getter
public abstract class AHero extends ACharacter {
	@Id @Pattern(regexp = "^[a-zA-Z]{3,20}$")
	protected String name;
	protected int level = 1;
	protected long experience;
	@OneToOne 
	protected AArtifact weapon = null;
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	private final static EntityManager em = emf.createEntityManager();

	public AHero(String name, long attack, long defense, long hitPoints) {
		super(attack, defense, hitPoints);
		this.name = name;
	}

	@Override
	public String toString() {
		return (this.name + super.toString() + System.lineSeparator()
				+ weapon);
	}

	public void save() {
		weapon.save();
		em.getTransaction().begin();
		em.persist(this);
		em.getTransaction().commit();
	}

	static public AHero find(String name) {
		em.getTransaction().begin();
		AHero result = em.find(AHero.class, name);
		em.getTransaction().commit();
		if (result != null)
			result.weapon = AArtifact.find(result.weapon.id);
		return (result);
	}

	public void updateWeapon(AArtifact newWeapon) {
		this.weapon.copy(newWeapon);
		this.weapon.save();
	}

	public void remove() {
		weapon.remove();
		em.getTransaction().begin();
		em.remove(this);
		em.getTransaction().commit();
	}
}
