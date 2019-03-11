import java.awt.*;
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
        g.setColor(Color.BLACK);
        g.fillRect(543, 3, 44, 404);

        g.setColor(Color.GRAY);
        g.fillRect(545, 5, 40, 400);

        for (Topping topping : queue) {
            if (queue.indexOf(topping) > 0) {
                g.drawImage(topping.getImage(), 550, 10 + queue.indexOf(topping) * 40, 30, 30, null);
            }
        }


    }
}
