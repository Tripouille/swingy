package main;

import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import main.character.Character;
import org.hibernate.validator.*;

public class Main extends JFrame {
    private Main() {
        super("My first application");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        JPanel contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(new JButton("Push me"));
        contentPane.add(new JButton("Click meeeeeeee"));
        contentPane.add(new JCheckBox("Check me"));
        contentPane.add(new JTextField("edit me"));
    }
    public static void main(String[] args) throws Exception {
        //UIManager.setLookAndFeel(new NimbusLookAndFeel());
        //App window = new App();
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("conf.properties")) {
            props.load(fis);
        }
        Connection connection = DriverManager.getConnection(props.getProperty("url"), props);

        Character c = new Character("");
        c.coucou();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
    }
}
