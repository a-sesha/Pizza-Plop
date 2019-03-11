import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Topping {
  private double xPos, yPos, xSpeed, ySpeed, speedRatio;

  public Topping(double speedRatio) {
    this.speedRatio = speedRatio;
  }

  public void setPosition(double xPos, double yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  public void setSpeeds(double xSpeed, double ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  public void changeSpeed(double xAccel, double yAccel) {
    this.xSpeed += xAccel/30;
    this.ySpeed += yAccel/30;
  }

  public void update(double difficultyModifier) {
    this.xPos += xSpeed * speedRatio * difficultyModifier;
    this.yPos += ySpeed * speedRatio * difficultyModifier;
  }

  public abstract String imageString();

  public BufferedImage getImage() {
    BufferedImage img = null;

    try {
      img = ImageIO.read(new File("src/" + imageString()));
    }
    catch (IOException e) {
      System.out.println("Picture could not load");
    }

    return img;
  }

  public static Topping[] getList() {
    return new Topping[] {new Avocado(), new Bacon(), new Pineapple()};
  }
}
