package main;

import java.awt.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.hibernate.validator.*;
import character.Character;
import java.sql.*;
import org.postgresql.*;
import org.postgresql.Driver;

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

        System.out.println("coucou");
    }
}
