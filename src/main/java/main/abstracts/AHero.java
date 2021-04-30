package main.abstracts;

import javax.persistence.Entity;
import javax.persistence.Id;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import main.models.artifact.AArtifactFactory;
import main.abstracts.AArtifact;

@Entity
public abstract class AHero extends ACharacter {
	@Id @Min(3) @Max(20) 
	protected String name;
	protected int level = 1;
	protected long experience;
	protected String heroClass;
	protected AArtifact weapon = AArtifactFactory.create("Weapon", 10);

	public AHero(String name, long attack, long defense, long hitPoints) {
		super(attack, defense, hitPoints);
		this.name = name;
	}

	@Override
	public String toString() {
		return (this.name + super.toString() + System.lineSeparator()
				+ weapon);
	}
}
