import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ToppingDispenser {
    private Topping topping;
    private double xPos;
    private boolean isDispencing;
    private double nextDropPos;
    private final int xMin = 50;
    private final int xMax = 550;

    public ToppingDispenser() {
        this.topping = null;
        this.xPos = (xMin + xMax) / 2;
    }

    public double getX() {
        return xPos;
    }

    public void update(double difficultyModifier) {
        if (isDispencing && xPos < xMin) {
            isDispencing = false;
            xPos = xMin;
        } else if (!isDispencing && xPos > xMax) {
            isDispencing = true;
            xPos = xMax;
        } else if (isDispencing) {
            xPos -= 8 * difficultyModifier;
        } else {
            xPos += 15 * difficultyModifier;
        }
    }

    public Topping checkForDrop() {
        if (nextDropPos > xPos) {
            isDispencing = false;

            Topping temp = topping;
            topping = null;
            return temp;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return topping == null;
    }

    public boolean isReadyToBeFilled() {
        return isEmpty() && xPos == xMax;
    }

    public void insertTopping(Topping topping) {
        this.topping = topping;
        this.nextDropPos = Math.random() * (xMax - xMin - 200) + xMin + 100;
    }

    public void draw(Graphics g) {
        //g.setColor(Color.GRAY);
        //g.fillRect(xMin - 50, 55, xMax - xMin + 100, 45);
        //g.fillRect((int) xPos - 25, 100, 50, 50);

        BufferedImage conveyorBeltImg = null;
        BufferedImage clawImg = null;

        try {
            conveyorBeltImg = ImageIO.read(new File("src/Assets/conveyorBelt.png"));

            if (isDispencing) {
                clawImg = ImageIO.read(new File("src/Assets/closedClaw.png"));
            } else {
                clawImg = ImageIO.read(new File("src/Assets/openClaw.png"));
            }

        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        g.drawImage(conveyorBeltImg, 10, 10, 580, 41, null);

        if (topping != null) {
            g.drawImage(topping.getImage(), (int) xPos - 15, 60, 30, 30, null);
        }

        g.drawImage(clawImg, (int) xPos - 25, 40, 50, 50, null);
    }
}
