import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Topping {
    private double xPos, yPos, xSpeed, ySpeed, speedRatio;
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

    public void changeAngle(double angle) {
        double totalSpeed = Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);

        this.xSpeed = totalSpeed * Math.cos(angle);
        this.ySpeed = totalSpeed * -Math.sin(angle);
    }

    public void accelerate(double xAccel, double yAccel) {
        this.xSpeed += xAccel;
        this.ySpeed += yAccel;
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
        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        return img;
    }

    public static Topping[] getList() {
        return new Topping[] {new Avocado(), new Bacon(), new Broccoli(), new Ham(), new Mushroom(), new Pepper(), new Pineapple(), new Poison(), new Zucchini()};
    }
}
