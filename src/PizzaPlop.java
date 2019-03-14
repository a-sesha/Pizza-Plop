/*
 * User Panel
 *
 * @author David Grossman
 *
 * @version 2019/3/7
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PizzaPlop extends JPanel implements JavaArcade, KeyListener, ActionListener {
    private Timer timer;
    private boolean isRunning;
    private boolean isPaused;
    private Board board;
    private GameStats gStats;
    private int highScore;

    public double difficultyModifier = 1;

    public PizzaPlop() {
        this.isRunning = false;
        this.isPaused = false;

        initializeGame();

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);

        setFocusable(true);
        setBackground(Color.WHITE);
    }

    public void initializeGame() {
        this.board = new Board();
        this.difficultyModifier = 1;
    }

    public boolean running() {
        return isRunning;
    }

    public void startGame() {
        isRunning = true;
        isPaused = false;
        initializeGame();
    }

    public String getGameName() {
        return "Pizza Plop";
    }

    public void pauseGame() {
        isPaused = !isPaused;
    }

    public String getInstructions() {
        return "In Pizza Plop, the goal of the game is to put the correct toppings on each pizza, keeping the customers satisfied. By\n" +
                "controlling the two platforms, which you can either move up and down or rotate (left platform: WASD, right platform: Arrow Keys),\n" +
                "direct the falling toppings onto one of the pizzas or into the trash. However, make sure not to put a topping that isn't on\n" +
                "the customer's order (shown below the pizza), as this will mean you'll have to throw away the pizza and start over. Make sure\n" +
                "to not waste too much time, because the customer will eventually grow impatient (shown by the bar and the face), resulting in\n" +
                "a lost life. Additionally, putting poison in a customer's pizza will automatically result in a lost life as well. Your score\n" +
                "is determined by how many pizzas you complete and how quickly you make them.";
    }

    public String getCredits() {
        return "David Grossman\nAnuj Sesha\nAshwin Ganesh";
    }

    public String getHighScore() {
        return "" + highScore;
    }

    public void stopGame() {
        isRunning = false;
        isPaused = false;

        initializeGame();
    }

    public int getPoints() {
        return board.getScore();
    }

    public void actionPerformed(ActionEvent e) { //invoked when timer expires every 5ms
        if (isRunning && !isPaused) {
            difficultyModifier += 0.002;

            System.out.println(difficultyModifier);

            board.update(difficultyModifier);

            if (gStats != null) {
                gStats.update(getPoints());
            }

            if (board.isGameOver()) {
                if (getPoints() > highScore) {
                    highScore = getPoints();
                }

                gStats.gameOver(getPoints());
                stopGame();
            }
        }
        repaint(); //ensures PaintComponent is called

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                if (board.getRotationInput(0) == Platform.RotationInput.CLOCKWISE) {
                    board.updateInput(0, Platform.RotationInput.NONE);
                }
                break;
            case KeyEvent.VK_D:
                if (board.getRotationInput(0) == Platform.RotationInput.COUNTERCLOCKWISE) {
                    board.updateInput(0, Platform.RotationInput.NONE);
                }
                break;
            case KeyEvent.VK_W:
                if (board.getTranslationInput(0) == Platform.TranslationInput.UP) {
                    board.updateInput(0, Platform.TranslationInput.NONE);
                }
                break;
            case KeyEvent.VK_S:
                if (board.getTranslationInput(0) == Platform.TranslationInput.DOWN) {
                    board.updateInput(0, Platform.TranslationInput.NONE);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (board.getRotationInput(1) == Platform.RotationInput.CLOCKWISE) {
                    board.updateInput(1, Platform.RotationInput.NONE);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (board.getRotationInput(1) == Platform.RotationInput.COUNTERCLOCKWISE) {
                    board.updateInput(1, Platform.RotationInput.NONE);
                }
                break;
            case KeyEvent.VK_UP:
                if (board.getTranslationInput(1) == Platform.TranslationInput.UP) {
                    board.updateInput(1, Platform.TranslationInput.NONE);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (board.getTranslationInput(1) == Platform.TranslationInput.DOWN) {
                    board.updateInput(1, Platform.TranslationInput.NONE);
                }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (isRunning && !isPaused) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    board.updateInput(0, Platform.RotationInput.CLOCKWISE);
                    break;
                case KeyEvent.VK_D:
                    board.updateInput(0, Platform.RotationInput.COUNTERCLOCKWISE);
                    break;
                case KeyEvent.VK_W:
                    board.updateInput(0, Platform.TranslationInput.UP);
                    break;
                case KeyEvent.VK_S:
                    board.updateInput(0, Platform.TranslationInput.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    board.updateInput(1, Platform.RotationInput.CLOCKWISE);
                    break;
                case KeyEvent.VK_RIGHT:
                    board.updateInput(1, Platform.RotationInput.COUNTERCLOCKWISE);
                    break;
                case KeyEvent.VK_UP:
                    board.updateInput(1, Platform.TranslationInput.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    board.updateInput(1, Platform.TranslationInput.DOWN);
            }
        }
    }

    public void setDisplay(GameStats gStats) {
        this.gStats = gStats;
    }

    //draws everything

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (isRunning) {
            board.draw(g);
            if (isPaused) {
                g.setColor(new Color(200, 100, 100, 210));
                g.fillRect(0, 0, 600, 800);
                g.setColor(Color.BLACK);
                g.setFont(new Font("sansserif", Font.BOLD, 38));
                g.drawString("Paused", 235, 320);
            }
        } else {
            g.setFont(new Font("sansserif", Font.BOLD, 32));
            g.setColor(Color.RED);
            g.drawString("Press the Start button to begin.", 65, 360);
            BufferedImage pizzaImg = null;

            try {
                pizzaImg = ImageIO.read(new File("src/Assets/pizza.png"));
            } catch (IOException e) {
                System.out.println("Picture could not load");
            }
            g.drawImage(pizzaImg, 180, 30, 270, 270, null);
        }
    }

}

