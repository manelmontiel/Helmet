import javax.imageio.ImageIO;
import javax.swing.*;
/*import java.applet.Applet;
import java.applet.AudioClip;*/
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
//import java.net.URL;

public class cPlayer {

    // load source images
    //BufferedImage image = ImageIO.read(new File("images/mario_bros.png"));
    Image image = new ImageIcon(String.valueOf(new File("images/marioGif_1.gif"))).getImage();      //perque el personatge es vegi amb forma d'aquest gif

    Sound soundPlayer = new Sound();        //objecte sound pels efectes sonors

    static int SPRITE_ALTURA = 90;          //tamany i posicio
    static int SPRITE_ANCHURA = 80;
    static int POSICION_ALTURA_Y = 605;
    static int POSICION_ANCHURA_X = 80; //80

    int ya = 0;
    int xa = 0;

    boolean escudoActivo = false;               //per saber si alguns efectes estan actius o no
    boolean destornilladorActivo = false;
    boolean llaveInglesaActivo = false;
    boolean sumadoVida;

    private Game game;
    private cPuerta puerta;

    public cPlayer(Game game) throws IOException {      //constructor
        this.game = game;
    }

    //limites de movimiento
    public void move() {        //moviment

        if ((POSICION_ANCHURA_X + xa >= 80)&&(POSICION_ANCHURA_X + xa < game.getWidth() - (SPRITE_ANCHURA + 140)))      //que es mogui entre les limitacions que marco
            POSICION_ANCHURA_X = POSICION_ANCHURA_X + xa;

        if(((POSICION_ANCHURA_X) >= 682) && (puerta.abierta == true)){      //en cas d'arribar a la porta i si aquesta esta oberta pasa i torna a la posicio inicial i suma 5 punts
            POSICION_ANCHURA_X = 80;
            game.setPuntos(game.getPuntos() + 5);
            if ((((game.getPuntos() % 100) == 0) && game.getPuntos() > 0)) {        //efecte sonor de crear la porta, i efecte especial de quan s'augmenta la velocitat
                soundPlayer.playSoundNivelNuevo();
            }else {
                soundPlayer.playSound();
            }

        }
        if(collisionMartillo() && !escudoActivo){       //colisions i crido als efectes de cada objecte i els sorolls
            game.cMartillo.effect();
            soundPlayer.playSoundColision();
        }
        if(collisionMartillo2() && !escudoActivo){
            game.cMartillo2.effect();
            soundPlayer.playSoundColision();
        }
        if(collisionMartillo3() && !escudoActivo){
            game.cMartillo3.effect();
            soundPlayer.playSoundColision();
        }
        if(collisionVida() && !sumadoVida){
            game.cVidaExtra.effect();
            soundPlayer.playSoundVida();
            sumadoVida = true;
            game.cVidaExtra.POSICION_ALTURA_Y_VIDA = game.cVidaExtra.linAleatoria();
            game.cVidaExtra.POSICION_ANCHURA_X_VIDA = game.cVidaExtra.colAleatoria();
        }
        if(!collisionVida() && sumadoVida){
            sumadoVida = false;
        }
        if(collisionEscudo() && !escudoActivo){
            game.cEscudo.effect();
            soundPlayer.playSoundEstrella();
            game.cEscudo.POSICION_ALTURA_Y_ESCUDO = game.cEscudo.linAleatoria();
            game.cEscudo.POSICION_ANCHURA_X_ESCUDO = game.cEscudo.colAleatoria();
        }
        if(collisionDestornillador() && !escudoActivo){
            game.cDestornillador.effect();
            game.cDestornillador.POSICION_ALTURA_Y_DESTORNILLADOR = game.cDestornillador.linAleatoria();
            game.cDestornillador.POSICION_ANCHURA_X_DESTORNILLADOR = game.cDestornillador.colAleatoria();
            soundPlayer.playSoundColision();
        }
        if(collisionDestornillador2() && !escudoActivo){
            game.cDestornillador2.effect();
            game.cDestornillador2.POSICION_ALTURA_Y_DESTORNILLADOR = game.cDestornillador.linAleatoria();
            game.cDestornillador2.POSICION_ANCHURA_X_DESTORNILLADOR = game.cDestornillador.colAleatoria();
            soundPlayer.playSoundColision();
        }
        if(collisionDestornillador3() && !escudoActivo){
            game.cDestornillador3.effect();
            game.cDestornillador3.POSICION_ALTURA_Y_DESTORNILLADOR = game.cDestornillador.linAleatoria();
            game.cDestornillador3.POSICION_ANCHURA_X_DESTORNILLADOR = game.cDestornillador.colAleatoria();
            soundPlayer.playSoundColision();
        }
        if(collisionLlaveInglesa() && !escudoActivo){
            game.cLlaveInglesa.effect();
            game.cLlaveInglesa.POSICION_ALTURA_Y_LLAVE = game.cLlaveInglesa.linAleatoria();
            game.cLlaveInglesa.POSICION_ANCHURA_X_LLAVE = game.cLlaveInglesa.colAleatoria();
            soundPlayer.playSoundColision();
        }
        if(collisionLlaveInglesa2() && !escudoActivo){
            game.cLlaveInglesa2.effect();
            game.cLlaveInglesa2.POSICION_ALTURA_Y_LLAVE = game.cLlaveInglesa.linAleatoria();
            game.cLlaveInglesa2.POSICION_ANCHURA_X_LLAVE = game.cLlaveInglesa.colAleatoria();
            soundPlayer.playSoundColision();
        }

    }

    //pintamos el jugador
    public void paint(Graphics2D g) {
        g.drawImage(image, POSICION_ANCHURA_X, POSICION_ALTURA_Y, SPRITE_ANCHURA, SPRITE_ALTURA, null);
    }

    //sin pulsar teclas
    public void keyReleased(KeyEvent e) {
        xa = 0;
        ya = 0;
    }

    //pulsando  [<--] o [-->]
    public void keyPressed(KeyEvent e) {
        //MOVIMIENTO HORIZONTAL
        if (e.getKeyCode() == KeyEvent.VK_LEFT && !destornilladorActivo && !llaveInglesaActivo)     //si presionen LEFT o RIGHT i ningun efecte esta actiu el moviment es normal
            xa = -86;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && !destornilladorActivo && !llaveInglesaActivo)
            xa = 86;
        if (e.getKeyCode() == KeyEvent.VK_LEFT && destornilladorActivo && !llaveInglesaActivo)      //si l'efecte del destornillador esta actiu les tecles estan invertides
            xa = 86;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && destornilladorActivo && !llaveInglesaActivo)
            xa = -86;
        if (e.getKeyCode() == KeyEvent.VK_LEFT && llaveInglesaActivo)           //si l'efecte de la clau anglesa esta actiu no es pot moure
            xa = 0;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && llaveInglesaActivo)
            xa = 0;
    }
    //metodes per les colisions
    public boolean collisionMartillo() {
        return game.cMartillo.getBounds().intersects(getBounds());
    }
    public boolean collisionMartillo2() {
        return game.cMartillo2.getBounds().intersects(getBounds());
    }
    public boolean collisionMartillo3() {
        return game.cMartillo3.getBounds().intersects(getBounds());
    }
    public boolean collisionVida() {
        return game.cVidaExtra.getBounds().intersects(getBounds());
    }
    public boolean collisionEscudo() {
        return game.cEscudo.getBounds().intersects(getBounds());
    }
    public boolean collisionDestornillador() {
        return game.cDestornillador.getBounds().intersects(getBounds());
    }
    public boolean collisionDestornillador2() {
        return game.cDestornillador2.getBounds().intersects(getBounds());
    }
    public boolean collisionDestornillador3() {
        return game.cDestornillador3.getBounds().intersects(getBounds());
    }
    public boolean collisionLlaveInglesa() {
        return game.cLlaveInglesa.getBounds().intersects(getBounds());
    }
    public boolean collisionLlaveInglesa2() {
        return game.cLlaveInglesa2.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds(){
        return new Rectangle(POSICION_ANCHURA_X, POSICION_ALTURA_Y, SPRITE_ANCHURA, SPRITE_ALTURA);
    }

    public int getTop_RACQUET_POSITION() {
        return POSICION_ALTURA_Y - SPRITE_ALTURA;
    }

}