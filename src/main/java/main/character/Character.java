package main.character;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Character {
	@Min(500)
	private String name;

	public Character(@Min(500) @NotBlank String name) {
		this.name = name;
	}

	public void coucou() {
		System.out.println("coucou");
	}
}
