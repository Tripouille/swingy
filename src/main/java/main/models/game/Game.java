package main.models.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;
import main.abstracts.AHero;
import main.controllers.AHeroController;

@Getter @Setter
public class Game {
	private String mode;
	private AHeroController heroController = new AHeroController();
	private Properties props = new Properties();

	public Game(String mode) {
		this.mode = mode;
		System.out.println("Starting game in mode [" + mode + "]");
		try (FileInputStream fis = new FileInputStream("conf.properties")) {
			props.load(fis);
		} catch (IOException e) {
			System.err.println("conf.properties not found.");
			System.exit(1);
		}
	}

	public ArrayList<AHero> getHeroesFromDB() {
		ArrayList<AHero> heroes = new ArrayList<AHero>();

		try (Connection connection = DriverManager.getConnection(props.getProperty("url"), props);
		Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT name FROM ahero");
			while (resultSet.next()) {
				heroes.add(heroController.getHero(resultSet.getString("name")));
			}
		} catch (SQLException e) {
			System.err.println("Invalid conf.properties.");
			System.exit(1);
		}
		return (heroes);
	}

	public void deleteHero() {
		heroController.removeModel();
	}

	public void importHero(String heroName) {
		heroController.loadModel(heroName);
	}
}
