import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class cMartillo extends cTools{      //clase que hereda de cTools
    int POSICION_ANCHURA_X_MARTILLO;            //variables de posicionament
    int POSICION_ALTURA_Y_MARTILLO;
    cPlayer cPlayer = new cPlayer(game);
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

    BufferedImage image = ImageIO.read(new File("images/caparazon_rojo.png"));      //perque es vegi aquesta imatge

    public cMartillo(Game game, int nuevaX, int posicionY) throws IOException {     //constructor
        super(game);
        POSICION_ANCHURA_X_MARTILLO = nuevaX;
        POSICION_ALTURA_Y_MARTILLO = posicionY;

    }
    public int colAleatoria(){
        int num = (int) (Math.random()*5+0);
        int columna;
        columna = columnas.get(num);

        return columna;
    }

    public int linAleatoria(){
        int num = (int) (Math.random()*5+0);
        int linea;
        linea = lineas.get(num);
        return linea;
    }

    public void move() {                                //moviment
        if(POSICION_ALTURA_Y_MARTILLO >= 600){              //si arriba a 600
            POSICION_ALTURA_Y_MARTILLO = linAleatoria();        //em posa el objecte a una x i y aleatories de entre els posibles que ofereixo
            POSICION_ANCHURA_X_MARTILLO = colAleatoria();
        }
        else {
            POSICION_ALTURA_Y_MARTILLO = POSICION_ALTURA_Y_MARTILLO + game.getVelocidad();      //sino que segueixi baixant
        }
    }

    @Override
    public void effect() {      //efecte martillo
            game.setVidas(game.getVidas() - 2);             //resta dos vidas i torna al jugador a la posicio inicial
            game.cPlayer.POSICION_ANCHURA_X = 80;
    }

    public boolean collisionTool() {
        return game.cPlayer.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X_MARTILLO, POSICION_ALTURA_Y_MARTILLO, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public void paint(Graphics2D g) {
        g.drawImage(image, POSICION_ANCHURA_X_MARTILLO, POSICION_ALTURA_Y_MARTILLO, SPRITE_ANCHURA, SPRITE_ALTURA, null);
    }

}
