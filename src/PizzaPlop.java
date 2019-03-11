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
    private boolean isRunning;

    public PizzaPlop() {
        this.isRunning = false;

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

    public void actionPerformed (ActionEvent e){ //invoked when timer expires every 5ms
        repaint(); //ensures PaintComponent is called
    }

    public void keyTyped(KeyEvent e) { }

    public void keyReleased(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {
            /*case KeyEvent.VK_ENTER://actions performed if enter key is pressed

                break;
            case KeyEvent.VK_SPACE://actions performed if enter key is pressed



                break;
            case KeyEvent.VK_LEFT://actions performed if enter key is pressed

                myHero.moveLeft();

                break;
            case KeyEvent.VK_RIGHT://actions performed if enter key is pressed

                myHero.moveRight();

                break;
            case KeyEvent.VK_UP://actions performed if enter key is pressed

                myHero.moveNorth();

                break;
            case KeyEvent.VK_DOWN://actions performed if enter key is pressed

                myHero.moveSouth();

                break;

            case KeyEvent.VK_ESCAPE://actions performed if escape key is pressed
                System.exit(0);

                break;
            default:*/

        }


    }

    //draws everything

    public void paintComponent(Graphics g){

        super.paintComponent(g); //a call to JPanel's paintComponent

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
