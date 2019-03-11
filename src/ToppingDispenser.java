import java.awt.*;

public class ToppingDispenser {
    private Topping topping;
    private double xPos;
    private boolean isDispencing;
    private final int xMin = 100;
    private final int xMax = 500;

    public ToppingDispenser() {
        this.topping = null;
        this.xPos = (xMin + xMax) / 2;
    }

    public void update(double difficultyModifier) {
        if (xPos < xMin || xPos > xMax) {
            isDispencing = !isDispencing;
        }

        if (isDispencing) {
            xPos -= difficultyModifier;
        }
        else {
            xPos += 2*difficultyModifier;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(xMin-50, 5, xMax-xMin+100, 45);
        g.fillRect((int)xPos-25, 50, 50, 50);

        if (topping != null) {
            g.drawImage(topping.getImage(), (int) xPos - 25, 60, 30, 30, null);
        }
    }
}
