package main.views;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			if (((JButton)event.getSource()).getText().equals("Create hero")) {
				dispose();
				controller.createHeroConsole();
			}
			else if (((JButton)event.getSource()).getText().equals("Delete hero")) {
				dispose();
				controller.deleteHeroConsole();
			}
			else if (((JButton)event.getSource()).getText().equals("Switch Mode")) {
				dispose();
				controller.switchMode();
				controller.selectHero();
			}
			else if (((JButton)event.getSource()).getText().equals("Quit")) {
				dispose();
			}
			else {
				dispose();
				String content = ((JButton)event.getSource()).getText();
				Pattern pattern = Pattern.compile("\\((.*)\\)");
				Matcher result = pattern.matcher(content);
				result.find();
				controller.loadHero(Integer.parseInt(result.group(1)));
				controller.start();
			}
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
	private JPanel createOptionPanel(Window window, int optionNumber, boolean canDeleteHero) {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(optionNumber, 1));
		JButton createButton = new JButton("Create hero");
		createButton.addActionListener(window);
		optionsPanel.add(createButton);
		if (canDeleteHero) {
			JButton deleteButton = new JButton("Delete hero");
			deleteButton.addActionListener(window);
			optionsPanel.add(deleteButton);
		}
		JButton switchModeButton = new JButton("Switch Mode");
		switchModeButton.addActionListener(window);
		optionsPanel.add(switchModeButton);
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(window);
		optionsPanel.add(quitButton);
		return (optionsPanel);
	}
	private JScrollPane createHeroesPanel(Window window, ArrayList<AHero> heroes) {
		JPanel heroesPanel = new JPanel();
		heroesPanel.setLayout(new GridLayout(heroes.size(), 1));
		for (int i = 0; i < heroes.size(); ++i) {
			JButton b = new JButton("<html> (" + i + ") - " + heroes.get(i).toString().replaceAll("\\n", "<br>") + "</html>");
			b.addActionListener(window);
			heroesPanel.add(b);
		}
		JScrollPane heroesPane = new JScrollPane(heroesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return (heroesPane);
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
		mainPane.add(createHeroesPanel(window, heroes));
		mainPane.add(createOptionPanel(window, optionNumber, heroes.size() > 0));
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
