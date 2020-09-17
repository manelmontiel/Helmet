
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract class cTools { // clase pare de on hereden les eines filles

    // load source images
    static int SPRITE_ALTURA = 80;
    static int SPRITE_ANCHURA = 80;
    private int POSICION_ALTURA_Y = 100;
    private int POSICION_ANCHURA_X = 250;

    int ya = 0;
    int xa = 0;

    protected Game game;

    public cTools(Game game) throws IOException {       //constructor
        this.game = game;
    }


    public abstract void move();        //metode abstracte move pel moviment de cada eina

    public abstract void effect();      //metode abstracte per l'efecte de cada eina

    public abstract void paint(Graphics2D g);       //metode abstracte paint per pintar cada eina

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X, POSICION_ALTURA_Y, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public int getTop_RACQUET_POSITION() {
        return POSICION_ALTURA_Y - SPRITE_ALTURA;
    }

}
