import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ToppingQueue {
    private ArrayList<Topping> queue;
    private final int size = 10;

    public ToppingQueue() {
        this.queue = new ArrayList<Topping>();

        refill();
    }

    public void refill() {
        while (queue.size() < size) {
            queue.add(Topping.getList()[(int)(Math.random()*Topping.getList().length)]);
        }
    }

    public Topping fillDispenser() {
        Topping temp = queue.remove(0);

        refill();

        return temp;
    }

    public void draw(Graphics g) {
        BufferedImage queueImg = null;

        try {
            queueImg = ImageIO.read(new File("src/Assets/queue.png"));
        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        g.drawImage(queueImg, 540, 50, 50, 410, null);

        for (Topping topping : queue) {
            g.drawImage(topping.getImage(), 550, 60 + queue.indexOf(topping) * 40, 30, 30, null);
        }
    }
}
