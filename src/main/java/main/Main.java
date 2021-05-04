package main;

import main.abstracts.AHero;
import main.controllers.AHeroController;
import main.controllers.GameController;
import main.models.game.Game;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.ArgumentResolver;

public class Main {
	private Main() {
		// super("My first application");
		// this.setVisible(true);
		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// this.setSize(600, 400);
		// this.setLocationRelativeTo(null);
		
		// JPanel contentPane = (JPanel) this.getContentPane();
		// contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		// contentPane.add(new JButton("Push me"));
		// contentPane.add(new JButton("Click meeeeeeee"));
		// contentPane.add(new JCheckBox("Check me"));
		// contentPane.add(new JTextField("edit me"));
	}

	private static void checkArgs(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: swingy.jar {mode}");
			System.exit(1);
		} else if (args[0].toUpperCase().equals("CONSOLE") && args[0].toUpperCase().equals("GUI")) {
			System.err.println("Invalid mode, must be console or gui.");
			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		checkArgs(args);
		GameController gameController = new GameController(new Game(args[0].toUpperCase()));
		gameController.selectHero();
		gameController.start();



		// BasicConfigurator.configure();
		// heroController.render("GUI");
		// heroController.equipWeapon(AArtifactFactory.create("Weapon", 777, "Legendaire"));


		// if (me == null) {
		//  	System.out.println("is null");
		//  	me = AHeroFactory.create(name, "Voleur");
		//  	me.save();
		// }
		// AHero enemy = AHeroFactory.create("enemy", "Voleur");
		// System.out.println(enemy.getWeapon());

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
