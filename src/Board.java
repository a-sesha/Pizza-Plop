import java.awt.*;
import java.util.ArrayList;

public class Board {
    private ToppingQueue queue;
    private ToppingDispenser dispenser;
    private Platform[] platforms;
    private ArrayList<Topping> fallingToppings;
    private Table table;

    public Board() {
        this.queue = new ToppingQueue();
        this.dispenser = new ToppingDispenser();
        this.platforms = new Platform[] {new Platform(200, 300, 200),
                new Platform(400, 300, 200)};
        this.fallingToppings = new ArrayList<Topping>();
        this.table = new Table();
    }

    public void toppingDispensed(Topping topping, double xPos) {
        topping.setPosition(xPos, 75);
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

        ArrayList<Topping> despawnedToppings = new ArrayList<Topping>();

        for (Topping topping : fallingToppings) {
            boolean isInFreefall = true;
            for (Platform platform : platforms) {
                double theoreticalYPos = Math.tan(platform.getAngle()) * (topping.getX() - platform.getX()) + platform.getY();
                if (topping.getX() > platform.getXLeft() && topping.getX() < platform.getXRight() &&
                        topping.getY() - 30 < theoreticalYPos && topping.getY() + 10 > theoreticalYPos) {
                    topping.setPosition(topping.getX(), theoreticalYPos - 10);
                    topping.collision(platform.getAngle());
                    isInFreefall = false;
                    break;
                }
            }

            if (isInFreefall) {
                topping.freefall();
            }

            topping.update(difficultyModifier);

            if (topping.getY() > 450) {
                table.addTopping(topping, (int)topping.getX() / 200);
                despawnedToppings.add(topping);
            }
        }

        for (Topping topping : despawnedToppings) {
            fallingToppings.remove(topping);
        }

        for (Platform platform : platforms) {
            platform.update(difficultyModifier);
        }

        table.update();
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

    public int getScore() {
        return table.getScore();
    }

    public boolean isGameOver() {
        return table.isGameOver();
    }

    public void draw(Graphics g) {
        queue.draw(g);
        dispenser.draw(g);

        for (Platform platform : platforms) {
            platform.draw(g);
        }

        table.draw(g);

        for (Topping topping : fallingToppings) {
            if (topping != null) {
                g.drawImage(topping.getImage(), (int) topping.getX() - 15, (int) topping.getY() - 15, 30, 30, null);
            }
        }
    }
}
