package main;

import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.validator.*;

import main.abstracts.AArtifact;
import main.abstracts.AHero;
import main.models.artifact.AArtifactFactory;
import main.models.hero.*;

public class Main extends JFrame {
	private Main() {
		super("My first application");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		contentPane.add(new JButton("Push me"));
		contentPane.add(new JButton("Click meeeeeeee"));
		contentPane.add(new JCheckBox("Check me"));
		contentPane.add(new JTextField("edit me"));
	}

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		//SwLog.log.setLevel(Level.OFF);
		String name = "Tripouille";
		AHero me = AHero.find(name);
		if (me == null) {
		 	System.out.println("is null");
		 	me = AHeroFactory.create(name, "Voleur");
		 	me.save();
		}
		AHero enemy = AHeroFactory.create("enemy", "Voleur");
		System.out.println(enemy);
		me.attack(enemy);
		System.out.println(enemy);

		//me.save();
		//AArtifact rare = AArtifactFactory.create("Weapon", 777, "Rare");
		//me.equipWeapon(rare);
		//25me.save();

		//System.out.println(me);
		
		
		//AArtifact weapon = AArtifactFactory.create("Weapon", 10);
		//weapon.save();
		//System.out.println(weapon);
		//AArtifact other = AArtifactFactory.create("Weapon", 10);
		//weapon.copy(other);
		//weapon.save();
		//System.out.println(weapon);

		//entityManager.persist(me.weapon);
		//entityManager.persist(me);
		//entityManager.getTransaction().commit();
		//AHero me = entityManager.find(AHero.class, "astrid");
		//System.out.println(me);
		
		//entityManager.getTransaction().commit();

		//try {
			//entityManager.persist(me.weapon);
			//me.level = 70;
			//entityManager.remove(me.weapon);
			//entityManager.remove(me);
			//System.out.println(me);
			//transactionOk = true;
		//} finally {
			//if(transactionOk)
			//else
				//System.out.println("coucou2");
		//}
	}
}

// UIManager.setLookAndFeel(new NimbusLookAndFeel());
// App window = new App();
// Properties props = new Properties();
// try (FileInputStream fis = new FileInputStream("conf.properties")) {
// props.load(fis);
// }
// Connection connection = DriverManager.getConnection(props.getProperty("url"),
// props);

// Character c = new Character("Astrid");
// c.coucou();
// ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
// Validator validator = factory.getValidator();
// Set<ConstraintViolation<Character>> constraintViolations =
// validator.validate(c);
// if (constraintViolations.size() > 0 ) {
// System.out.println("Impossible de valider les donnees du bean : ");
// for (ConstraintViolation<Character> contraintes : constraintViolations) {
// System.out.println(contraintes.getRootBeanClass().getSimpleName()+
// "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());
// }
// } else {
// System.out.println("Les donnees du bean sont valides");
// }

// EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
// EntityManager entityManager = emf.createEntityManager();
// entityManager.getTransaction().begin();
// boolean transactionOk = false;
// try {
// entityManager.persist(c);
// transactionOk = true;
// } finally {
// if(transactionOk)
// entityManager.getTransaction().commit();
// else
// System.out.println("coucou2");
// }
// }
