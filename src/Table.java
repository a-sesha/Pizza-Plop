import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Table {
    private Customer[] customers;
    private Pizza[] pizzas;
    private Lives lives;
    private int score;
    private final int numCustomers = 2;

    public Table() {
        this.customers = new Customer[numCustomers];
        this.pizzas = new Pizza[numCustomers];

//        for (Pizza pizza : pizzas) {
//            pizza = new Pizza();
//        }

        this.lives = new Lives();

        refill();
    }


    public void refill() {
        for (int i = 0; i < numCustomers; i++) {
            if (customers[i] == null) {
                customers[i] = Customer.getList()[(int) (Math.random() * Customer.getList().length)];
            }
        }
    }

    public void addTopping(Topping topping, int position) {
        if (position >= 0 && position < numCustomers) {
            if (!customers[position].addTopping(topping)) {
                lives.decrease();
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void update() {
        for (int i = 0; i < numCustomers; i++) {
            if(customers[i].isSatisfied()) {
                score += 50 + (int) (15 * customers[i].getPatience());

                customers[i].reset();
            }
            if (customers[i].getPatience() == 0) {
                customers[i] = null;
                lives.decrease();
            }
        }

        refill();
    }

    public void draw(Graphics g) {
        BufferedImage tableImg = null;
        BufferedImage trashImg = null;
        BufferedImage pizzaImg = null;

        try {
            tableImg = ImageIO.read(new File("src/Assets/table.png"));
            trashImg = ImageIO.read(new File("src/Assets/trash.png"));
            pizzaImg = ImageIO.read(new File("src/Assets/pizza.png"));
        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        g.drawImage(tableImg, 25, 480, 320, 196, null);
        g.drawImage(trashImg, 380, 550, 120, 120, null);

        for (int i = 0; i < numCustomers; i++) {
            if (customers[i] != null) {
                g.drawImage(customers[i].getImage(), i * 155 + 40, 580, 50, 50, null);
                g.drawImage(pizzaImg, i * 155 + 63, 490, 90, 70, null);

                for (int j = 0; j < 3; j++) {
                    g.drawImage(customers[i].getOrder()[j].getImage(), i * 155 + 95 + j * 25, 590, 20, 20, null);
                }

                Topping[] satisfiedToppings = customers[i].satisfiedToppings();

                for (int j = 0; j < 6; j++) {
                    if (satisfiedToppings[j % 3] != null) {
                        g.drawImage(satisfiedToppings[j % 3].getImage(), i * 155 + 100 + (int) (Math.cos(Math.PI * j / 3) * 27), 518 + (int) (Math.sin(Math.PI * j / 3) * 21), 15, 15, null);
                    }
                }

                g.setColor(Color.BLACK);
                g.fillRect(i * 155 + 58, 640, 100, 20);

                g.setColor(new Color((int) (255 - customers[i].getPatience() * 255), (int) (customers[i].getPatience() * 255), 0));
                g.fillRect(i * 155 + 60, 642, (int) (96 * customers[i].getPatience()), 16);
            }
        }

        lives.draw(g);
    }
}
