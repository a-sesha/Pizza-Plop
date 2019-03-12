import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lives {
    private int num;

    public Lives() {
        this.num = 3;
    }

    public void decrease() {
        num--;
    }

    public boolean isGameOver() {
        return num <= 0;
    }

    public void draw(Graphics g) {
        BufferedImage heartImg = null;

        try {
            heartImg = ImageIO.read(new File("src/Assets/heart.png"));
        } catch (IOException e) {
            System.out.println("Picture could not load");
        }

        for (int i=0; i<num; i++) {
            g.drawImage(heartImg, 550, 630 - i * 40, 30, 30, null);
        }
    }
}
