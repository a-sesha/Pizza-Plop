/*
* User Panel
*
* @author David Grossman
*
* @version 2019/3/7
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PizzaPlop extends JPanel implements JavaArcade, KeyListener, ActionListener {
    private Timer timer;
    private boolean isRunning;
    private Board board;

    public double difficultyModifier = 1;

    public PizzaPlop() {
        this.isRunning = false;
        this.board = new Board();
        this.difficultyModifier = 1;

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);

        setFocusable(true);
        setBackground(Color.WHITE);
    }

    public boolean running() {
        return isRunning;
    }

    public void startGame() {
        isRunning = true;
    }

    public String getGameName() {
    return "Pizza Plop";
    }

    public void pauseGame() {

    }

    public String getInstructions() {
    return "";
    }

    public String getCredits() {
    return "";
    }

    public String getHighScore() {
    return "0";
    }

    public void stopGame() {
        isRunning = false;
    }

    public int getPoints() {
        return 0;
    }

    public void actionPerformed (ActionEvent e) { //invoked when timer expires every 5ms
        board.update(difficultyModifier);

        repaint(); //ensures PaintComponent is called
    }

    public void keyTyped(KeyEvent e) { }

    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
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
        switch(e.getKeyCode()) {
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

    //draws everything

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        board.draw(g);



        //g.drawString("You have 3 lives to kill the enemy", 100, 200);

        /*//Draw heroes
        myHero.draw(g);


        //Draw enemies
        enemyFast.draw(g);
        enemySlow.draw(g);

        g.drawString("Points: " + points, 20, getHeight()-30);

        if(!start){//shows instructions in the beginning
            g.drawString("Instructions: Drag the ship with the mouse", (getWidth() /2) - 100, getHeight()/2 + 20);
            g.drawString("(Inactive) Press enter to shoot .", (getWidth() /2) - 100, getHeight()/2 + 40);
            g.drawString("You have 3 lives to kill the enemy", (getWidth() /2) - 100, getHeight()/2+ 60);
        }*/





    }
}
