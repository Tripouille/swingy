package main.views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import lombok.NoArgsConstructor;
import main.models.game.Game;
import main.models.hero.AHeroFactory;
import main.abstracts.AHero;


public class GameView {

	public void renderSelectionConsole(ArrayList<AHero> heroes) {
		int i;
		
		for (i = 0; i < heroes.size(); ++i)
			System.out.println(i + " - " + heroes.get(i));
		System.out.println(i + " - Create new hero");
		if (i > 0)
			System.out.println(++i + " - Delete hero");
		System.out.println("Please enter a number beetween 0 and " + i);
	}
	public void renderSelectionGui(ArrayList<AHero> heroes) {
		
	}
	
	public void renderCreateHeroClassConsole(ArrayList<String> availableClass) {
		int i = 0;

		for (i = 0; i < availableClass.size(); ++i)
			System.out.println(i + " - " + availableClass.get(i) + " "
								+ AHeroFactory.getHeroInfos(availableClass.get(i)));
		System.out.println("Please enter a number beetween 0 and " + (i - 1));
	}
	public void renderCreateHeroClassGui(ArrayList<String> availableClass) {
		
	}

	public void renderCreateHeroNameConsole(String heroClass) {
		System.out.println("Please enter your " + heroClass + " name");
	}
	public void renderCreateHeroNameGui(String heroClass) {
		
	}

	public void renderDeleteHeroConsole(ArrayList<AHero> heroes) {
		int i;
		
		for (i = 0; i < heroes.size(); ++i)
			System.out.println(i + " - " + heroes.get(i));
		System.out.println("Please enter a number beetween 0 and " + (i - 1));
	}
	public void renderDeleteHeroGui(ArrayList<AHero> heroes) {
		
	}
}
