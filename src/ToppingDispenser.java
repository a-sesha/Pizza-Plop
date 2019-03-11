import java.awt.*;

public class ToppingDispenser {
    private Topping topping;
    private double xPos;
    private boolean isDispencing;
    private double nextDropPos;
    private final int xMin = 150;
    private final int xMax = 450;

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
            xPos -= 3 * difficultyModifier;
        } else {
            xPos += 6 * difficultyModifier;
        }
    }

    public Topping checkForDrop() {
        if (nextDropPos > xPos) {
            isDispencing = false;

            Topping temp = topping;
            topping = null;
            return temp;
        }
        else {
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
        this.nextDropPos = Math.random() * (xMax - xMin - 20) + xMin + 10;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(xMin - 50, 5, xMax - xMin + 100, 45);
        g.fillRect((int) xPos - 25, 50, 50, 50);

        if (topping != null) {
            g.drawImage(topping.getImage(), (int) xPos - 15, 60, 30, 30, null);
        }
    }
}
