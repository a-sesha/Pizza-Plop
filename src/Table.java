import java.awt.*;

public class Table {
    private Customer[] customers;
    private Pizza[] pizzas;

    public Table() {
        this.customers = new Customer[3];
        this.pizzas = new Pizza[3];

        //TEST
        customers[0] = new StraightHairGirl();
        customers[1] = new PonytailGirl();
        customers[2] = new PonytailGirl();
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
