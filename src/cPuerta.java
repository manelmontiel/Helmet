import com.sun.tools.javac.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class cPuerta {
    public static boolean abierta = false;
    private Game game;

    Color color;


    Graphics g;
    Graphics2D g2d = (Graphics2D) g;


    private Timer timer = new Timer("myTimer");
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {


            if (color == Color.red) {
                abierta = true;
                color = Color.green;
            }
            else {
                abierta = false;
                color = Color.red;

                //g2d.setColor(Color.BLACK);
                //g2d.fillRect(30, 30, 30, 30);
            }

        }
    };

    int y_puerta = 570;
    int x_puerta = 750;
    int width = 70;
    int height = 10;
    int random = 3000;

    public cPuerta(Game game) {
        this.game = game;
        timer.scheduleAtFixedRate(timerTask, 0, random);

    }




    public void pintarPuerta(Graphics2D g){

        g.setColor(color);
        g.fillRect(x_puerta, y_puerta, width, height);
    }
    /*BufferedImage image1 = ImageIO.read(new File("images/prohibido.png"));
    BufferedImage image2 = ImageIO.read(new File("images/pasar.png"));
    BufferedImage image;

    public boolean abierta = false;
    private Game game;

    Color color;


    Graphics g;
    Graphics2D g2d = (Graphics2D) g;


    private Timer timer = new Timer("myTimer");
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {


            if (abierta == false) { //color == Color.black
                abierta = true;
                //color = Color.white;
                image = image2;
            }
            else {
                abierta = false;
                image = image1;
                //color = Color.BLACK;

                //g2d.setColor(Color.BLACK);
                //g2d.fillRect(30, 30, 30, 30);
            }

        }
    };

    int y_puerta = 605;
    int x_puerta = 750;
    int width = 37;
    int height = 70;
    int random = 3000;

    public cPuerta(Game game) throws IOException {
        this.game = game;
        timer.scheduleAtFixedRate(timerTask, 0, random);

    }




    public void pintarPuerta(Graphics2D g){

        g.setColor(color);
        g.fillRect(x_puerta, y_puerta, width, height);
        g.drawImage(image, x_puerta + 344, y_puerta, width, height, null);
    }*/

}