import java.awt.*;
import java.util.ArrayList;

public class Board {
    private Platform[] platforms;
    private ArrayList<Topping> fallingToppings;

    public Board() {
        this.platforms = new Platform[] {new Platform(200, 300, 150, Color.RED),
                new Platform(400, 300, 150, Color.BLUE)};
        this.fallingToppings = new ArrayList<Topping>();
    }

    public void toppingDispensed(Topping topping, double xPos) {
        topping.setPosition(xPos, 125);
        fallingToppings.add(topping);
    }

    public void draw(Graphics g) {
        for (Topping topping : fallingToppings) {
            g.drawImage(topping.getImage(), (int)topping.getX()-15, (int)topping.getY()-15, 30, 30, null);
        }
    }
}
