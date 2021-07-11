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

import org.apache.log4j.net.SyslogAppender;

import jakarta.validation.ConstraintViolation;

import javax.imageio.ImageIO;
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
import java.io.FileInputStream;
import java.net.SocketPermission;

@AllArgsConstructor
public class GameView {
	private GameController	controller;

	private class Window extends JFrame {
		public Window(String name) {
			super(name);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(500, 500);
			this.setLocationRelativeTo(null);
		}
	}

	private class SelectionWindow extends Window implements ActionListener {
		public SelectionWindow() {
			super("Swingy");
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			if (((JButton)event.getSource()).getText().equals("Create hero")) {
				dispose();
				controller.createHero();
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

	private class HeroCreationWindow extends Window implements ActionListener {
		private JPanel mainPane;
		private JPanel classPanel;
		
		public HeroCreationWindow(ArrayList<AHero> availableClass) {
			super("Swingy");
			mainPane = (JPanel)this.getContentPane();

			classPanel = new JPanel();
			classPanel.setLayout(new GridLayout(availableClass.size(), 1));
			for (int i = 0; i < availableClass.size(); ++i) {
				JButton b = new JButton("<html> (" + i + ") - " + availableClass.get(i).toString().replaceAll("\\n", "<br>")
										+ "<br>" + AHeroFactory.getHeroInfos(availableClass.get(i).getName()) + "</html>");
				b.addActionListener(this);
				classPanel.add(b);
			}
			JScrollPane classPane = new JScrollPane(classPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
													JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			mainPane.setLayout(new GridLayout(2, 1));
			mainPane.add(classPane);

			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(this);
			mainPane.add(cancelButton);
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			if (((JButton)event.getSource()).getText().equals("Cancel")) {
				dispose();
				controller.selectHero();
			}
			else {
				dispose();
				String content = ((JButton)event.getSource()).getText();
				Pattern pattern = Pattern.compile("\\((.*)\\)");
				Matcher result = pattern.matcher(content);
				result.find();
				renderCreateHeroNameGui(AHeroFactory.getAllAvailableClass().get(Integer.parseInt(result.group(1))).getName());
			}
		}
	}

	private class HeroNameWindow extends Window implements ActionListener {
		private JPanel		mainPane;
		private JTextField	nameField = new JTextField();
		private String		heroClass;

		public HeroNameWindow(String heroClass) {
			super("Swingy");
			this.heroClass = heroClass;
			mainPane = (JPanel)this.getContentPane();
			mainPane.setLayout(new GridLayout(2, 1));

			nameField.setHorizontalAlignment(JTextField.CENTER);
			mainPane.add(nameField);

			JPanel buttonPanel = new JPanel();
			JButton validButton = new JButton("Valid");
			validButton.addActionListener(this);
			buttonPanel.add(validButton);
			buttonPanel.setLayout(new GridLayout(1, 2));
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(this);
			buttonPanel.add(cancelButton);
			mainPane.add(buttonPanel);
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			if (((JButton)event.getSource()).getText().equals("Valid")) {
				AHero newHero = AHeroFactory.create(nameField.getText(), heroClass);
				Set<ConstraintViolation<AHero>> constraintViolations = controller.getValidator().validate(newHero);
				if (constraintViolations.size() == 0) {
					newHero.save();
					dispose();
					controller.selectHero();
				} else {
					String message = new String();
					for (ConstraintViolation<AHero> contraintes : constraintViolations)
						message += contraintes.getRootBeanClass().getSimpleName() +
							"." + contraintes.getPropertyPath() + " " + contraintes.getMessage() + System.lineSeparator();
					JOptionPane.showMessageDialog(this, message);
				}
			}
			else if (((JButton)event.getSource()).getText().equals("Cancel")) {
				dispose();
				controller.selectHero();
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
	private JPanel createOptionPanel(SelectionWindow window, int optionNumber, boolean canDeleteHero) {
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
	private JScrollPane createHeroesPanel(SelectionWindow window, ArrayList<AHero> heroes) {
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
		SelectionWindow window = new SelectionWindow();
		JPanel mainPane = (JPanel)window.getContentPane();
		mainPane.setLayout(new GridLayout(2, 1));
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
	public void renderCreateHeroClassGui(ArrayList<AHero> availableClass) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println(e);
			System.exit(1);
		}
		HeroCreationWindow window = new HeroCreationWindow(availableClass);
		window.setVisible(true);
	}

	public void renderCreateHeroNameConsole(String heroClass) {
		System.out.println("Please enter your " + heroClass + " name");
	}
	public void renderCreateHeroNameGui(String heroClass) {
		HeroNameWindow window = new HeroNameWindow(heroClass);	
		window.setVisible(true);
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
