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
	
	public void renderConsoleCreateHeroClass(ArrayList<String> availableClass) {
		int i = 0;

		for (i = 0; i < availableClass.size(); ++i)
			System.out.println(i + " - " + availableClass.get(i) + " "
								+ AHeroFactory.getHeroInfos(availableClass.get(i)));
		System.out.println("Please enter a number beetween 0 and " + (i - 1));
	}
	public void renderGuiCreateHeroClass(ArrayList<String> availableClass) {
		
	}

	public void renderConsoleCreateHeroName(String heroClass) {
		System.out.println("Please enter your " + heroClass + " name");
	}
	public void renderGuiCreateHeroName(String heroClass) {
		
	}
}
