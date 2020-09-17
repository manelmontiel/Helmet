import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class cBowser {              //clase feta totalment amb motius estetics, per que el bowser anés independentment i no depengues dels threads de les altres clases
    Image image = new ImageIcon(String.valueOf(new File("images/bowserGif_1.gif"))).getImage();


    static int SPRITE_ALTURA = 260;     //tamany i posicionament
    static int SPRITE_ANCHURA = 250;
    static int POSICION_ALTURA_Y = 150;
    static int POSICION_ANCHURA_X = 750;

    int ya = 0;
    int xa = 0;

    private java.util.Timer timer = new Timer("myTimer");       //timer per a que doni la sensacio de que es mou molt rapid
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    int y_puerta = 570;
    int x_puerta = 750;
    int width = 70;
    int height = 10;
    int random = 3000;

    private Game game;

    public cBowser(Game game) throws IOException {      //constructor
        this.game = game;
        timer.scheduleAtFixedRate(timerTask, 0, random);
    }

    //pintamos la rauqeta
    public void paint(Graphics2D g) {
        g.drawImage(image, POSICION_ANCHURA_X, POSICION_ALTURA_Y, SPRITE_ANCHURA, SPRITE_ALTURA, null);
    }

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X, POSICION_ALTURA_Y, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public int getTop_RACQUET_POSITION() {
        return POSICION_ALTURA_Y - SPRITE_ALTURA;
    }

}

