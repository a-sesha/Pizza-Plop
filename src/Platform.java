import java.awt.*;

public class Platform {
    private double xPos, yPos, angle;
    private Color color;
    private final double length;

    public Platform(double xPos, double yPos, double length, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = 0;
        this.color = color;
        this.length = length;
    }

    public void rotate(boolean clockwise) {
        if (clockwise) {
            angle -= Math.PI/360;
        }
        else {
            angle += Math.PI/360;
        }
    }

    public void moveUp() {
        yPos--;
    }

    public void moveDown() {
        yPos++;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(color);
        g2.rotate(angle, xPos, yPos);

        g2.fillRect((int)(xPos-length/2), (int)yPos-2, (int)length, 4);
    }
}
