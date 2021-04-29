package main.character;

import jakarta.validation.constraints.*;

public class Character {
	@Min(5)
	private String name;

	public Character(@Min(5) String name) {
		this.name = name;
	}

	public void coucou() {
		System.out.println("coucou");
	}
}
