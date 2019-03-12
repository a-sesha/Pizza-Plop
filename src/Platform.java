import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Platform {
    private double xPos, yPos, yMin, yMax, angle;
    private RotationInput rotationInput;
    private TranslationInput translationInput;
    private final double length;

    public enum RotationInput {
        NONE, CLOCKWISE, COUNTERCLOCKWISE
    }

    public enum TranslationInput {
        NONE, UP, DOWN
    }

    public Platform(double xPos, double yPos, double length) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.yMin = yPos - 125;
        this.yMax = yPos + 25;
        this.angle = 0;
        this.rotationInput = RotationInput.NONE;
        this.translationInput = TranslationInput.NONE;
        this.length = length;
    }

    public double getX() {
        return xPos;
    }

    public double getXLeft() {
        return xPos - length / 2;
    }

    public double getXRight() {
        return xPos + length / 2;
    }

    public double getY() {
        return yPos;
    }

    public double getAngle() {
        return angle;
    }

    public RotationInput getRotationInput() {
        return rotationInput;
    }

    public TranslationInput getTranslationInput() {
        return translationInput;
    }

    public void setRotationInput(RotationInput input) {
        this.rotationInput = input;
    }

    public void setTranslationInput(TranslationInput input) {
        this.translationInput = input;
    }

    public void update(double difficultyModifier) {
        switch (rotationInput) {
            case CLOCKWISE:
                angle -= Math.PI / 15 * difficultyModifier;
                break;
            case COUNTERCLOCKWISE:
                angle += Math.PI / 15 * difficultyModifier;
        }

        switch (translationInput) {
            case UP:
                yPos -= 10 * difficultyModifier;
                break;
            case DOWN:
                yPos += 10 * difficultyModifier;
        }

        if (yPos < yMin) {
            yPos = yMin;
        } else if (yPos > yMax) {
            yPos = yMax;
        }
    }

    public void draw(Graphics g) {
        BufferedImage platformImg = null;

        try {
            platformImg = ImageIO.read(new File("src/Assets/platform.png"));
        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        Graphics2D g2 = (Graphics2D) g.create();

        g2.rotate(angle, xPos, yPos + length / 20);

        g2.drawImage(platformImg, (int) (xPos - length / 2), (int) yPos, (int) length, (int) (length / 10), null);
    }
}
