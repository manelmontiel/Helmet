import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip nextRound;
    Clip background;

    String name1 = ("cancion_mario_bros.wav");      //diferents audios per fer efectes sonors
    String name2 = ("marioTunel.wav");
    String name3 = ("efecto_escudo.wav");
    String name5 = ("efecto_nivel_nuevo.wav");
    String name6 = ("efecto_muerte.wav");
    String name7 = ("efecto_colision.wav");
    String name8 = ("efecto_estrella.wav");

    //cada audio te el seu propi metode perque es la unica forma que he trobat per que funcioni

    public void playSound() {
        try {

            // The sound dependency that i used only worked with .wav files

            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("audios/" + name + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(("audios/" + name2)));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSoundEstrella() {
        try {

            // The sound dependency that i used only worked with .wav files

            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("audios/" + name + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(("audios/" + name8)));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSoundVida() {
        try {

            // The sound dependency that i used only worked with .wav files

            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("audios/" + name + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(("audios/" + name3)));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSoundNivelNuevo() {
        try {

            // The sound dependency that i used only worked with .wav files

            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("audios/" + name + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(("audios/" + name5)));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSoundMuerte() {
        try {

            // The sound dependency that i used only worked with .wav files

            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("audios/" + name + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(("audios/" + name6)));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSoundColision() {
        try {

            // The sound dependency that i used only worked with .wav files

            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("audios/" + name + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(("audios/" + name7)));
            nextRound = AudioSystem.getClip();
            nextRound.open(audioIn);
            nextRound.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playLoop() {
        try {

            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource("audios/" + name + ".wav"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(("audios/" + name1)));
            background = AudioSystem.getClip();
            background.open(audioIn);
            background.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        background.close();
    }

}
