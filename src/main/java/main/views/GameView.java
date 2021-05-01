package main.views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import lombok.NoArgsConstructor;
import main.models.game.Game;
import main.models.hero.AHeroFactory;


public class GameView {

	public void renderConsoleSelection(ArrayList<String> heroes) {
		int i;
		
		for (i = 0; i < heroes.size(); ++i) {
			System.out.println(i + " - " + heroes.get(i));
		}
		System.out.println(i + " - Create new hero");
		System.out.println("Please enter a number beetween 0 and " + i);
	}

	public void renderGuiSelection(ArrayList<String> heroes) {
		
	}
	
	public void renderConsoleCreation(Set<String> availableHeroes) {
		int i = 0;

		for (String hero : availableHeroes)
			System.out.println(i++ + " - " + hero + " " + AHeroFactory.getHeroInfos(hero));
		System.out.println("Please enter a number beetween 0 and " + (i - 1));
	}
	
	public void renderGuiCreation(Set<String> availableHeroes) {
		
	}
}
