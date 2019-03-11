import java.util.ArrayList;

public class ToppingDispenser {
    private ArrayList<Topping> queue;
    private final int size = 10;

    public ToppingDispenser() {
        this.queue = new ArrayList<Topping>();
    }

    public void refill() {
        while (queue.size() < size) {
            queue.add(Topping.getList()[(int)(Math.random()*Topping.getList().length)]);
        }
    }

    public Topping dispense() {
        Topping temp = queue.remove(0);

        refill();

        return temp;
    }
}
