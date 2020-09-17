import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel  {

    private Image backgroundImage = ImageIO.read(new File("images/fondo_mario_bros.png"));;
    private static int ANCHURA = 1000;
    private static int ALTURA = 800;
    private Thread t1, t2, t3;  //instancio el thread

    Sound soundGame = new Sound();

    cPlayer cPlayer = new cPlayer(this);

    cPuerta puerta = new cPuerta(this);
    cBowser bowser = new cBowser(this);

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

    //instancio els objectes eina
    cMartillo cMartillo = new cMartillo(this, 250, -100);
    cMartillo cMartillo2 = new cMartillo(this, 250, -600);
    cMartillo cMartillo3 = new cMartillo(this, 250, -900);
    cVidaExtra cVidaExtra = new cVidaExtra(this, 336, -200);
    cDestornillador cDestornillador = new cDestornillador(this, 422, -300);
    cDestornillador cDestornillador2 = new cDestornillador(this, 422, -700);
    cDestornillador cDestornillador3 = new cDestornillador(this, 422, -1000);
    cEscudo cEscudo = new cEscudo(this, 508, -400);
    cLlaveInglesa cLlaveInglesa = new cLlaveInglesa(this, 594, -500);
    cLlaveInglesa cLlaveInglesa2 = new cLlaveInglesa(this, 594, -800);

    private boolean puesto = false;     //per añadir els objectes només un cop

    private int puntos = 0;             //punts de la partida
    private int velocidad = 1;          //velocitat eines
    private boolean sumado = false;     //per saber si he pujat la velocitat o no
    public int vidas = 5;               //vidas personatge

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

    public ArrayList<cTools> eines = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        Game programa = new Game();
        programa.iniciar();

    }

    private void iniciar() throws IOException, ParseException, org.json.simple.parser.ParseException {

        JFrame frame = new JFrame("NEW SUPER HELMET BROS");

        frame.add(this);
        frame.setSize(ANCHURA, ALTURA);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        soundGame.playLoop();       //musica de fons

        t1 = new Thread(new Runnable() {    //inicialitzo el thread                                                                                //thread que utilitzo per moure les eines i augmentar la seva velocitat quan faci falta
            @Override
            public void run() {             //en el run posem el que volem que faci aquest thread
                while (true) {              //iniciem un while del que no sortirà fins que acabi el joc
                    try {                   //fem un try/catch per poder fer un thread sleep
                        movimentEines();        //per cada volta del while executa el metode movimentEines() que mou les eines
                        aumentarVelocidad();    //per cada volta executa aumentarVelocidad(), que augmenta la velocitat de les eines si fa falta
                        Thread.sleep(10);       //faig un thread sleep, per a que per cada volta esperi 10 milisegons per fer la seguent
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2 = new Thread(new Runnable() {        //thread que utilitzo per crear les eines (ara mateix el que fa es ficar les eines al arraylist que utilitzo per fer el moviment)
            @Override
            public void run() {
                while (true) {
                    if (eines.size() < 20) {
                        try {
                            if(puesto == false) {
                                eines.add(cMartillo);
                                eines.add(cVidaExtra);
                                eines.add(cDestornillador);
                                eines.add(cEscudo);
                                eines.add(cLlaveInglesa);
                                eines.add(cMartillo2);
                                eines.add(cDestornillador2);
                                eines.add(cLlaveInglesa2);
                                eines.add(cMartillo3);
                                eines.add(cDestornillador3);
                                puesto = true;
                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t3 = new Thread(new Runnable() {        //thread que utilitzo per el moviment del personatge
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(250);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cPlayer.move();
                }
            }
        });

        t1.start();     //cridem al thread per a que s'executi
        t2.start();
        t3.start();

        while (true){
            if(getVidas()<=0){    //codi per quan s'acabi el joc em pregunti pel nom i guardi la puntuacio

                soundGame.close();              //acabo amb la musica de fons
                soundGame.playSoundMuerte();       //poso la musica de derrota

                String name = JOptionPane.showInputDialog("Introduzca su nombre: ");
                while(name==null || name.isEmpty()){
                    name = JOptionPane.showInputDialog("Es necesario que introduzca su nombre para continuar: ");
                }

                cPuntuacion.guardarDades(name, getPuntos());

                ArrayList<cScorePoints> partidasScore;
                String puntuacionesMaximas = "Puntuaciones Maximas: " + "\n" + "";

                partidasScore = cPuntuacion.mostrarDades();

                for(int i = 0; i < partidasScore.size(); i++){
                    puntuacionesMaximas = puntuacionesMaximas + partidasScore.get(i) + "\n";
                }

                JOptionPane.showMessageDialog(frame, "Puntuacion: " + getPuntos() + "\n" + "\n" + puntuacionesMaximas);

                System.exit(ABORT);

            }
            else{
                this.move();
                this.repaint();

            }

        }

    }

    public ArrayList<cTools> movimentEines() {  //faig un bucle per a que em mogui les eines que estan dins de l'arraylist

        for (int i = 0; i < eines.size(); i++) {
            eines.get(i).move();
        }
        return eines;
    }

    private void aumentarVelocidad(){       //metode per augmentar la velocitat de caiguda de les eines quan arribi als 100, 200 ... punts

        if ((((puntos % 100) == 0) && puntos > 0)&& !sumado) {      //divideix els punts entre 100 i si el residu es 0, hi ha mes de 0 punts i sumado es false suma 1 a la velocitat
                velocidad = velocidad + 1;
                sumado = true;
        }
        else if(((puntos % 100) != 0) && puntos > 0){
            sumado = false;
        }

    }

    /*private cTools crearEina(Game game) throws IOException{       //metode anterior per crear eines, pero com no vaig aconseguir fer colisions utilitzant aquest metode vaig tenir que rebutjar-lo
        int eina;
        int posicio;

        cTools newTool = null;

        eina = (int) (Math.random()*99+0);
        posicio = (int) (Math.random()*5+0);

        if (eina >= 0 && eina <=10)
            newTool = new cEscudo(game,posiciones.get(posicio), -100);   //250
        else if (eina >= 11 && eina <=20)
            newTool = new cVidaExtra(game,posiciones.get(posicio), -100); //336
        else if (eina >= 21 && eina <=40)
            newTool = new cLlaveInglesa(game,posiciones.get(posicio), -100); //422
        else if (eina >= 41 && eina <=70)
            newTool = new cDestornillador(game,posiciones.get(posicio), -100);   //508
        else if (eina >= 71 && eina <=99)
            newTool = new cMartillo(game,posiciones.get(posicio), -100); //594

        return newTool;
    }*/

    public Game() throws IOException {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cPlayer.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                cPlayer.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    private void move() {       //metode move del thread principal

        try {
            Thread.sleep(50);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {     //metode paint per pintar tots els objectes
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(backgroundImage,0, 0, this.getWidth(), this.getHeight(), null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawString("Puntuación: " + puntos, 20,20);
        g2d.drawString("Vidas: " + vidas, 20,40);
        cPlayer.paint(g2d);
        //cTools.paint(g2d);
        for (int i = 0; i < eines.size(); i++){
            eines.get(i).paint(g2d);
        }
        puerta.pintarPuerta(g2d);
        bowser.paint(g2d);

    }

    //getters/setters
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
