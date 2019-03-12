public class Mushroom extends Topping {
    public Mushroom() {
        super(1.5, true);
    }

    public String imageString() {
        return "Assets/ToppingImages/mushroom.png";
    }

    public String toString() {
        return "Mushroom";
    }
}