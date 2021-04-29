package main.character;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Character {
	@Id
	private String name;

	public Character(@Min(500) @NotBlank String name) {
		this.name = name;
	}

	public void coucou() {
		System.out.println("coucou");
	}
}
