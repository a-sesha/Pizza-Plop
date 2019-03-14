import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel
        implements ActionListener {
    private JavaArcade game;
    private GameStats gStats;
    private JButton startButton, pauseButton, stopButton, instructionsButton, creditsButton;

    // Constructor
    public ControlPanel(JavaArcade t, GameStats g) {
        game = t;
        gStats = g;

        instructionsButton = new JButton("Instructions");
        instructionsButton.setBorderPainted(false);
        instructionsButton.setFocusPainted(false);
        instructionsButton.setFocusPainted(false);
        instructionsButton.setBorderPainted(false);
        instructionsButton.addActionListener(this);
        add(instructionsButton);
        //add(Box.createHorizontalStrut(60));
        startButton = new JButton("Start");
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setForeground(Color.GREEN.darker());
        startButton.addActionListener(this);

        add(startButton);

        pauseButton = new JButton("Pause");
        pauseButton.setForeground(Color.BLUE);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setBorderPainted(false);
        pauseButton.addActionListener(this);
        add(pauseButton);
        stopButton = new JButton("Stop");
        stopButton.setForeground(Color.RED);
        stopButton.setBorderPainted(false);
        stopButton.setFocusPainted(false);
        stopButton.setFocusPainted(false);
        stopButton.setBorderPainted(false);
        stopButton.addActionListener(this);
        add(stopButton);
        //add(Box.createHorizontalStrut(60));
        creditsButton = new JButton("Credits");
        creditsButton.setBorderPainted(false);
        creditsButton.setFocusPainted(false);
        creditsButton.setFocusPainted(false);
        creditsButton.setBorderPainted(false);
        creditsButton.addActionListener(this);
        add(creditsButton);

    }

    // Called when the start button is clicked
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        if (button == startButton) {

            if (!game.running()) {

                ((JPanel) (game)).requestFocus(); //need to provide the JPanel focus
                game.startGame();
                gStats.update(0);
                gStats.repaint();
            }
        } else if (button == pauseButton) {
            game.pauseGame();
            if (pauseButton.getText().equals("Pause") && game.running()) pauseButton.setText("Resume");
            else pauseButton.setText("Pause");
            repaint();

        } else if (button == stopButton) {
            game.stopGame();
            gStats.gameOver(game.getPoints());
            gStats.repaint();
            startButton.setEnabled(true);
            startButton.setText("Restart");
            pauseButton.setText("Pause");
            repaint();
        } else if (button == creditsButton) {
            String credits = game.getCredits();
            JOptionPane.showMessageDialog(this, credits, "Game Credits", JOptionPane.PLAIN_MESSAGE);

        } else if (button == instructionsButton) {
            String instructions = game.getInstructions();
            JOptionPane.showMessageDialog(this, instructions, "Game Rules", JOptionPane.PLAIN_MESSAGE);

        }
        ((JPanel) (game)).requestFocus();
    }

}
