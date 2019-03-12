import java.awt.*;

public class Table {
    private Customer[] customers;
    private Pizza[] pizzas;
    private final int numCustomers = 3;

    public Table() {
        this.customers = new Customer[numCustomers];
        this.pizzas = new Pizza[numCustomers];

        for (Pizza pizza : pizzas) {
            pizza = new Pizza();
        }

        refill();
    }

    public void refill() {
        for (int i=0; i<numCustomers; i++) {
            if (customers[i] == null) {
                customers[i] = Customer.getList()[(int)(Math.random()*Customer.getList().length)];
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(140, 123, 84));
        g.fillRect(50, 400, 400, 200);

        g.setColor(new Color(221, 185, 99));
        g.fillPolygon(new int[] {50, 450, 400, 100}, new int[] {400, 400, 350, 350}, 4);

        for (int i=0; i<3; i++) {
            if (customers[i] != null) {
                g.drawImage(customers[i].getImage(), i * 100 + 50, 300, 50, 50, null);
            }
        }
    }
}
