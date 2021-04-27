package tripouille;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.hibernate.validator.*;
import character.Character;
public class App extends JFrame {
    private App() {
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

        Character c = new Character(null);
        c.coucou();
    }
}
