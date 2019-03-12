import java.awt.*;

public class Platform {
    private double xPos, yPos, angle;
    private Color color;
    private RotationInput rotationInput;
    private TranslationInput translationInput;
    private final double length;

    public enum RotationInput {
        NONE, CLOCKWISE, COUNTERCLOCKWISE
    }

    public enum TranslationInput {
        NONE, UP, DOWN
    }

    public Platform(double xPos, double yPos, double length, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = 0;
        this.color = color;
        this.rotationInput = RotationInput.NONE;
        this.translationInput = TranslationInput.NONE;
        this.length = length;
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
                angle -= Math.PI/180 * difficultyModifier;
                break;
            case COUNTERCLOCKWISE:
                angle += Math.PI/180 * difficultyModifier;
        }

        switch (translationInput) {
            case UP:
                yPos -= 2 * difficultyModifier;
                break;
            case DOWN:
                yPos += 2 * difficultyModifier;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(color);
        g2.rotate(angle, xPos, yPos);

        g2.fillRect((int)(xPos-length/2), (int)yPos-2, (int)length, 4);
    }
}
