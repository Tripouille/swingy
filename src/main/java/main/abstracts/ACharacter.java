package main.abstracts;

public abstract class ACharacter {
	protected long attack;
	protected long defense;
	protected long hitPoints;

	public ACharacter(long attack, long defense, long hitPoints) {
		this.attack = attack;
		this.defense = defense;
		this.hitPoints = hitPoints;
	}

	@Override
	public String toString() {
		return (": Attack => [" + this.attack + "] Defense => ["
				+ this.defense + "] HP => [" + this.hitPoints + "]");
	}

	public void takeDamage(long damage) {
		damage = defense < damage ? damage - defense : 0;
		damage = damage < this.hitPoints ? damage : this.hitPoints;
		this.hitPoints -= damage;
	}

	public void attack(ACharacter target) {
		target.takeDamage(this.attack);
	}

	public boolean isAlive() {
		return (this.hitPoints > 0);
	}
}