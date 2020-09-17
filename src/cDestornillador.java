import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class cDestornillador extends cTools{        //clase que hereda de cTools
    BufferedImage image = ImageIO.read(new File("images/caparazon_verde.png"));     //li dic que em mostri aquesta imatge
    int POSICION_ANCHURA_X_DESTORNILLADOR;      //variables de posicionament
    int POSICION_ALTURA_Y_DESTORNILLADOR;

    private final static int COL1 = 250;        //variables de posicionament
    private final static int COL2 = 336;
    private final static int COL3 = 422;
    private final static int COL4 = 508;
    private final static int COL5 = 594;

    private final static int LIN1 = -100;
    private final static int LIN2 = -200;
    private final static int LIN3 = -300;
    private final static int LIN4 = -400;
    private final static int LIN5 = -500;

    private ArrayList<Integer> columnas = new ArrayList<>(){{       //arraylist on guardo les variables COL
        add(COL1);
        add(COL2);
        add(COL3);
        add(COL4);
        add(COL5);
    }};

    private ArrayList<Integer> lineas = new ArrayList<>(){{         //arraylist on guardo les variables LIN
        add(LIN1);
        add(LIN2);
        add(LIN3);
        add(LIN4);
        add(LIN5);
    }};

    public cDestornillador(Game game, int nuevaX, int posicionY) throws IOException {       //constructor
        super(game);
        POSICION_ANCHURA_X_DESTORNILLADOR = nuevaX;
        POSICION_ALTURA_Y_DESTORNILLADOR = posicionY;
    }

    public int colAleatoria(){                      //faig un random per aconseguir un numero index, agafo el index del arraylist columnas i el retorno
        int num = (int) (Math.random()*5+0);
        int columna;
        columna = columnas.get(num);

        return columna;
    }                                           //aquests dos metodes son iguals a totes les clases filles, aixi que nomes estaran comentades aqui

    public int linAleatoria(){                  //faig un random per aconseguir un numero index, agafo el index del arraylist lineas i el retorno
        int num = (int) (Math.random()*5+0);
        int linea;
        linea = lineas.get(num);
        return linea;
    }

    public void move() {                                //move de cDestornillador amb les seves propies variables coordenades
        if(POSICION_ALTURA_Y_DESTORNILLADOR >= 600){                    //sino ho feia aixi em movia totes les eines alhora a la mateixa coordenada
            POSICION_ALTURA_Y_DESTORNILLADOR = linAleatoria();
            POSICION_ANCHURA_X_DESTORNILLADOR = colAleatoria();         //si la y arriba a 600, el coloco a una x y aleatories
        }else {
            POSICION_ALTURA_Y_DESTORNILLADOR = POSICION_ALTURA_Y_DESTORNILLADOR + game.getVelocidad();      //sino segueix avançant
        }
    }

    @Override
    public void effect() {                                      //efecte destornillador
        Thread tDestornillador = new Thread(new Runnable() {
            @Override
            public void run() {                                         //creo thread on li dic que faci tal cosa en x temps
                game.setVidas(game.getVidas() - 1);
                game.cPlayer.destornilladorActivo = true;    //variable per fer l'efecte de canvi de sentit a la clase player
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                game.cPlayer.destornilladorActivo = false;
            }

        });

        tDestornillador.start();            //crido el thread tDestornillador
    }

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X_DESTORNILLADOR, POSICION_ALTURA_Y_DESTORNILLADOR, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public void paint(Graphics2D g) {
        //super.paint(g);
        g.drawImage(image, POSICION_ANCHURA_X_DESTORNILLADOR /*+ 258*/, POSICION_ALTURA_Y_DESTORNILLADOR, SPRITE_ANCHURA, SPRITE_ALTURA, null);
    }

}
