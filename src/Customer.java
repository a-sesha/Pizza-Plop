import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Customer {
    private int arrivalTime;
    private Topping[] order;

    public Customer() {
        this.arrivalTime = (int)(System.currentTimeMillis()/1000);

        this.order = new Topping[(int)(Math.random() * 3) + 1];

        for (Topping topping : order) {
            while (topping == null || !topping.isEdible()) {
                topping = Topping.getList()[(int)(Math.random()*Topping.getList().length)];
            }
        }
    }

    public abstract String packageName();

    public String imageString() {
        String imageString = "CustomerImages/" + packageName() + "/";

        if (isAngry()) {
            imageString += "angry.png";
        }
        else {
            imageString += "happy.png";
        }

        return imageString;
    }

    public BufferedImage getImage() {
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("src/" + imageString()));
        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        return img;
    }

    public boolean isAngry() {
        return (int)(System.currentTimeMillis()/1000) - arrivalTime > 20;
    }

    public void draw(Graphics g) {

    }
}
