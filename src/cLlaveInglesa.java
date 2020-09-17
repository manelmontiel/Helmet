import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class cLlaveInglesa extends cTools{
    BufferedImage image = ImageIO.read(new File("images/caparazon_azul.png"));
    int POSICION_ANCHURA_X_LLAVE;
    int POSICION_ALTURA_Y_LLAVE;

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

    public cLlaveInglesa(Game game, int nuevaX, int posicionY) throws IOException {
        super(game);
        POSICION_ANCHURA_X_LLAVE = nuevaX;
        POSICION_ALTURA_Y_LLAVE = posicionY;
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
        if(POSICION_ALTURA_Y_LLAVE >= 600){                 //move de cLlave amb les seves propies variables coordenades
            POSICION_ALTURA_Y_LLAVE = linAleatoria();       //sino ho feia aixi em movia totes les eines alhora a la mateixa coordenada
            POSICION_ANCHURA_X_LLAVE = colAleatoria();      //si la y arriba a 600, el coloco a una x y aleatories
        }else {
            POSICION_ALTURA_Y_LLAVE = POSICION_ALTURA_Y_LLAVE + game.getVelocidad();        //sino segueix avançant
        }
    }

    @Override
    public void effect() {                                      //efecte llave inglesa, quan colisiona
        Thread tLlaveInglesa = new Thread(new Runnable() {
            @Override
            public void run() {                                 //creo un thread on dic que faci x cosa, i dic que duri un temps
                game.setVidas(game.getVidas() - 1);
                game.cPlayer.llaveInglesaActivo = true;
                System.out.println("Llave activo");
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Llave inactivo");
                game.cPlayer.llaveInglesaActivo = false;
            }

        });

        tLlaveInglesa.start();                                      //crido al thread tLlaveInglesa
    }

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X_LLAVE, POSICION_ALTURA_Y_LLAVE, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public void paint(Graphics2D g) {
        g.drawImage(image, POSICION_ANCHURA_X_LLAVE/* + 344*/, POSICION_ALTURA_Y_LLAVE, SPRITE_ANCHURA, SPRITE_ALTURA, null);
    }
}
