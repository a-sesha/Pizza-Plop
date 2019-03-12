import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Topping {
    private double xPos, yPos, xSpeed, ySpeed, speedRatio;
    private final double xMin = 50;
    private final double xMax = 550;
    private boolean isEdible;

    public Topping(double speedRatio, boolean isEdible) {
        this.speedRatio = speedRatio;
        this.isEdible = isEdible;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public void setPosition(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void collision(double angle) {
        double speed = Math.sin(angle) * 10;
        this.xSpeed = speed * Math.cos(angle);
        this.ySpeed = speed * Math.sin(angle);
    }

    public void freefall() {
        this.ySpeed += 0.5;
    }

    public void update(double difficultyModifier) {
        this.xPos += xSpeed * speedRatio * difficultyModifier;

        if (xPos < xMin) {
            xPos = xMin;
        }
        else if (xPos > xMax) {
            xPos = xMax;
        }

        this.yPos += ySpeed * speedRatio * difficultyModifier;
    }

    public abstract String imageString();

    public BufferedImage getImage() {
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("src/" + imageString()));
        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        return img;
    }

    public static Topping[] getList() {
        return new Topping[] {new Avocado(), new Bacon(), new Broccoli(), new Ham(), new Mushroom(), new Pepper(), new Pineapple(), new Poison(), new Zucchini()};
    }
}
