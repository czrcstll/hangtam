import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {
    static Clip clip;
    public Music() {
        try {
            File music = new File("./music.wav");
            if (music.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
            }
            else {
                System.out.println("File not found.");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void playMusic() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void setVolume(int level) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(level);
    }
}