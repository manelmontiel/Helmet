import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class cEscudo extends cTools{
    int POSICION_ANCHURA_X_ESCUDO;
    int POSICION_ALTURA_Y_ESCUDO;



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



    BufferedImage image = ImageIO.read(new File("images/estrella_mario.png"));

    Sound soundEscudo = new Sound();

    public cEscudo(Game game, int nuevaX, int posicionY) throws IOException {
        super(game);
        POSICION_ANCHURA_X_ESCUDO = nuevaX;
        POSICION_ALTURA_Y_ESCUDO = posicionY;
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

    public void move() {                                //move de cEscudo amb les seves propies variables coordenades
        if(POSICION_ALTURA_Y_ESCUDO >= 600){            //sino ho feia aixi em movia totes les eines alhora a la mateixa coordenada
            POSICION_ALTURA_Y_ESCUDO = linAleatoria();  //si la y arriba a 600, el coloco a una x y aleatories
            POSICION_ANCHURA_X_ESCUDO = colAleatoria();
        }else {
            POSICION_ALTURA_Y_ESCUDO = POSICION_ALTURA_Y_ESCUDO + game.getVelocidad();      //sino segueix avançant
        }
    }

    @Override
    public void effect() {                              //efecte escudo
        Thread tEscudo = new Thread(new Runnable() {
            @Override
            public void run() {                         //creo un thread on dic que faci x cosa, i dic que duri un temps
                game.cPlayer.escudoActivo = true;
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.cPlayer.escudoActivo = false;
            }

        });

        tEscudo.start();                //crido el thread anterior
    }

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X_ESCUDO, POSICION_ALTURA_Y_ESCUDO, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public void paint(Graphics2D g) {
        g.drawImage(image, POSICION_ANCHURA_X_ESCUDO, POSICION_ALTURA_Y_ESCUDO, SPRITE_ANCHURA, SPRITE_ALTURA, null);
    }
}
