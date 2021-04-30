package main.abstracts;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import main.models.artifact.AArtifactFactory;
import main.abstracts.AArtifact;

@MappedSuperclass
public abstract class AHero extends ACharacter {
	@Id @Min(3) @Max(20) 
	protected String name;
	@NotBlank
	protected String heroClass;
	protected int level = 1;
	protected long experience;
	@OneToOne
	public AArtifact weapon = null;

	public AHero(String name, String heroClass ,long attack, long defense, long hitPoints) {
		super(attack, defense, hitPoints);
		this.name = name;
		this.heroClass = heroClass;
	}

	@Override
	public String toString() {
		return (this.name + super.toString() + System.lineSeparator()
				);
	}
}
