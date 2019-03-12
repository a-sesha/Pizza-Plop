import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Customer {
    private int arrivalTime;
    private Topping[] order;
    private boolean[] toppingsSatisfied;

    public Customer() {
        this.arrivalTime = (int)(System.currentTimeMillis()/1000);

        this.order = new Topping[3];
        this.toppingsSatisfied = new boolean[3];

        for (int i=0; i<3; i++) {
            while (order[i] == null || !order[i].isEdible()) {
                order[i] = Topping.getList()[(int)(Math.random()*Topping.getList().length)];
            }
        }
    }

    public Topping[] getOrder() {
        return order;
    }

    public abstract String packageName();

    public String imageString() {
        String imageString = "Assets/CustomerImages/" + packageName() + "/";

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

    public double getPatience() {
        return Math.max(1 - (double)((int)(System.currentTimeMillis()/1000) - arrivalTime)/150, 0);
    }

    public boolean isAngry() {
        return getPatience() < 0.3;
    }

    public boolean addTopping(Topping newTopping) { //returns false if life is lost
        if (!newTopping.isEdible()) {
            return false;
        }

        for (int i=0; i<3; i++) {
            if (order[i].toString().equals(newTopping.toString()) && !toppingsSatisfied[i]) {
                toppingsSatisfied[i] = true;
                return true;
            }
        }

        toppingsSatisfied = new boolean[3];

        return true;
    }

    public Topping[] satisfiedToppings() {
        Topping[] satisfiedToppings = new Topping[3];

        for (int i=0; i<3; i++) {
            if (toppingsSatisfied[i]) {
                satisfiedToppings[i] = order[i];
            }
        }

        return satisfiedToppings;
    }

    public static Customer[] getList() {
        return new Customer[] {new PonytailGirl(), new StraightHairGirl()};
    }
}
