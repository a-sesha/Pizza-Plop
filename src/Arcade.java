import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Arcade extends JFrame {
    public Arcade() {
        super("AP Java Arcade");

        setResizable(false);

        JavaArcade game = new PizzaPlop();

        GameStats display = new GameStats(game); //passing in a JavaArcade, therefore I know I can call getHighScore(), getScore()


        ControlPanel controls = new ControlPanel(game, display); //Also passing in JavaArcade to ControlPanel, I know you will respond to buttons

        ((PizzaPlop) game).setDisplay(display); //optional, provides game ability to update display

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.add(display, BorderLayout.NORTH);
        panel.add((JPanel) game, BorderLayout.CENTER);
        panel.add(controls, BorderLayout.SOUTH);

        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Arcade window = new Arcade();
        window.setBounds(100, 100, 600, 800);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
