package main.abstracts;

import javax.persistence.Entity;

import jakarta.validation.constraints.Min;

@Entity
public class AHero extends ACharacter {
	@Min(3)
	protected String name;
	protected int level;
	protected long experience;

	public AHero(String name, long attack, long defense, long hitPoints) {
		super(attack, defense, hitPoints);
		this.name = name;
	}

	@Override
	public String toString() {
		return (this.name + super.toString());
	}
}
