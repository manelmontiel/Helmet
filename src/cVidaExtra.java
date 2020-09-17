import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class cVidaExtra extends cTools{
    int POSICION_ANCHURA_X_VIDA;
    int POSICION_ALTURA_Y_VIDA;
    private final static int COL1 = 250;
    private final static int COL2 = 336;
    private final static int COL3 = 422;
    private final static int COL4 = 508;
    private final static int COL5 = 594;

    private final static int LIN1 = -100;
    private final static int LIN2 = -200;
    private final static int LIN3 = -300;
    private final static int LIN4 = -400;
    private final static int LIN5 = -500;

    private ArrayList<Integer> columnas = new ArrayList<>(){{
        add(COL1);
        add(COL2);
        add(COL3);
        add(COL4);
        add(COL5);
    }};

    private ArrayList<Integer> lineas = new ArrayList<>(){{
        add(LIN1);
        add(LIN2);
        add(LIN3);
        add(LIN4);
        add(LIN5);
    }};

    BufferedImage image = ImageIO.read(new File("images/seta_verde_mario.png"));

    public cVidaExtra(Game game, int nuevaX, int posicionY) throws IOException {
        super(game);
        POSICION_ANCHURA_X_VIDA = nuevaX;
        POSICION_ALTURA_Y_VIDA = posicionY;
    }

    public int colAleatoria(){
        int num = (int) (Math.random()*5+0);
        int columna;
        columna = columnas.get(num);
        /*if (num == 0){
            columna = columnas.get(num);
        } else if(num == 1){

        } else if(num == 2){

        } else if(num == 3){

        } else if(num == 4){

        }*/

        return columna;
    }

    public int linAleatoria(){
        int num = (int) (Math.random()*5+0);
        int linea;
        linea = lineas.get(num);
        return linea;
    }

    public void move() {
        if(POSICION_ALTURA_Y_VIDA >= 600){      //move de cLlave amb les seves propies variables coordenades
            POSICION_ALTURA_Y_VIDA = linAleatoria();        //sino ho feia aixi em movia totes les eines alhora a la mateixa coordenada
            POSICION_ANCHURA_X_VIDA = colAleatoria();       //si la y arriba a 600, el coloco a una x y aleatories
        }else {
            POSICION_ALTURA_Y_VIDA = POSICION_ALTURA_Y_VIDA + game.getVelocidad();      //sino segueix avançant
        }
    }

    @Override
    public void effect() {
        game.setVidas(game.getVidas() + 1);         //efecte vida extra, suma una vida
    }

    public boolean collisionTool() {
        return game.cPlayer.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X_VIDA, POSICION_ALTURA_Y_VIDA, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public void paint(Graphics2D g) {
        //super.paint(g);
        g.drawImage(image, POSICION_ANCHURA_X_VIDA, POSICION_ALTURA_Y_VIDA, SPRITE_ANCHURA, SPRITE_ALTURA, null);
    }
}
