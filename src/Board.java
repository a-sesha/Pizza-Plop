import java.awt.*;
import java.util.ArrayList;

public class Board {
    private ToppingQueue queue;
    private ToppingDispenser dispenser;
    private Platform[] platforms;
    private ArrayList<Topping> fallingToppings;

    public Board() {
        this.queue = new ToppingQueue();
        this.dispenser = new ToppingDispenser();
        this.platforms = new Platform[] {new Platform(200, 300, 150, Color.RED),
                new Platform(400, 300, 150, Color.BLUE)};
        this.fallingToppings = new ArrayList<Topping>();
    }

    public void toppingDispensed(Topping topping, double xPos) {
        topping.setPosition(xPos, 160);
        fallingToppings.add(topping);
    }

    public void update(double difficultyModifier) {
        dispenser.update(difficultyModifier);

        if (dispenser.isReadyToBeFilled()) {
            dispenser.insertTopping(queue.fillDispenser());
        }

        Topping droppedTopping = dispenser.checkForDrop();

        if (droppedTopping != null) {
            toppingDispensed(droppedTopping, dispenser.getX());
        }

        for (Topping topping : fallingToppings) {
            topping.accelerate(0, 0.1);
            topping.update(difficultyModifier);
        }

        for (Platform platform : platforms) {
            platform.update(difficultyModifier);
        }
    }

    public Platform.RotationInput getRotationInput(int index) {
        return platforms[index].getRotationInput();
    }

    public Platform.TranslationInput getTranslationInput(int index) {
        return platforms[index].getTranslationInput();
    }

    public void updateInput(int index, Platform.RotationInput input) {
        platforms[index].setRotationInput(input);
    }

    public void updateInput(int index, Platform.TranslationInput input) {
        platforms[index].setTranslationInput(input);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(3, 53, 504, 504);

        g.setColor(Color.WHITE);
        g.fillRect(5, 55, 500, 500);

        queue.draw(g);
        dispenser.draw(g);

        for (Platform platform : platforms) {
            platform.draw(g);
        }

        for (Topping topping : fallingToppings) {
            g.drawImage(topping.getImage(), (int)topping.getX()-15, (int)topping.getY()-15, 30, 30, null);
        }
    }
}
