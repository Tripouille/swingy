package main.views;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.*;
import javax.swing.event.AncestorListener;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import main.models.game.Game;
import main.models.hero.AHeroFactory;
import main.abstracts.AHero;
import main.controllers.GameController;

import java.awt.*;
import java.awt.event.*;

@AllArgsConstructor
public class GameView {
	private GameController controller;

	private class Window extends JFrame implements ActionListener {
		public Window() {
			super("Swingy");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(500, 500);
			this.setLocationRelativeTo(null);
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			if (((JButton)event.getSource()).getText().equals("Switch Mode")) {
				dispose();
				controller.switchMode();
				controller.selectHero();
			}
			else
				System.out.println("prout");
		}
	}

	public void renderSelectionConsole(ArrayList<AHero> heroes) {
		int i;
		
		for (i = 0; i < heroes.size(); ++i)
			System.out.println(i + " - " + heroes.get(i));
		System.out.println(i + " - Create new hero");
		if (i > 0)
			System.out.println(++i + " - Delete hero");
		System.out.println(++i + " - Switch Mode");
		System.out.println(++i + " - Quit");
		System.out.println("Please enter a number beetween 0 and " + i);
	}
	public void renderSelectionGui(ArrayList<AHero> heroes) {
		int optionNumber = heroes.size() > 0 ? 4 : 3;
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println(e);
			System.exit(1);
		}
		Window window = new Window();
		JPanel mainPane = (JPanel)window.getContentPane();
		mainPane.setLayout(new GridLayout(2, 1, 3, 3));
		JPanel heroesPanel = new JPanel();
		heroesPanel.setLayout(new GridLayout(heroes.size(), 1));
		for (AHero hero : heroes) {
			JButton b = new JButton("<html>" + hero.toString().replaceAll("\\n", "<br>") + "</html>");
			b.setSize(new Dimension(500, 50));
			b.addActionListener(window);
			heroesPanel.add(b);
		}
		JScrollPane heroesPane = new JScrollPane(heroesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPane.add(heroesPane);
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(optionNumber, 1));
		optionsPanel.add(new JButton("Create hero"));
		if (heroes.size() > 0)
			optionsPanel.add(new JButton("Delete hero"));
		JButton switchModeButton = new JButton("Switch Mode");
		switchModeButton.addActionListener(window);
		optionsPanel.add(switchModeButton);

		optionsPanel.add(new JButton("Quit"));
		mainPane.add(optionsPanel);
		window.setVisible(true);
	}
	
	public void renderCreateHeroClassConsole(ArrayList<AHero> availableClass) {
		int i = 0;

		for (i = 0; i < availableClass.size(); ++i)
			System.out.println(i + " - " + availableClass.get(i) + System.lineSeparator()
								+ AHeroFactory.getHeroInfos(availableClass.get(i).getName()));
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
