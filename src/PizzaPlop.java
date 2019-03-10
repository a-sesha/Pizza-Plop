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

public class PizzaPlop extends JPanel implements JavaArcade {

  public PizzaPlop() {
    setPreferredSize(new Dimension(400, 600));
    setBackground(Color.WHITE);
  }

  public boolean running() {
    return true;
  }

  public void startGame() {

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

  }

  public int getPoints() {
    return 0;
  }
}
