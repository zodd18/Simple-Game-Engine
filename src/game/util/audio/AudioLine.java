package game.util.audio;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioLine {

    private Clip audioClip;

    public AudioLine() { }

    public void playMusic(String path) {
        playSound(path);
        audioClip.loop(Integer.MAX_VALUE);
    }

    public void playSound(String path) {
        AudioInputStream audioStream = null;

        try {
            audioStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(path));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
            audioClip.loop(Integer.MAX_VALUE);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if(audioClip != null) audioClip.stop();
    }
}
