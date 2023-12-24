package com.l08gr05.uno;
import com.l08gr05.uno.Game.Menu;
import com.l08gr05.uno.state.MenuState;
import com.l08gr05.uno.state.State;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Application {

    private final GUI gui;
    private State state;
    private final Clip clipAudio;

    public Application() throws Exception {
        AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("cbat.wav"));
        clipAudio = AudioSystem.getClip();
        clipAudio.open(audio);
        clipAudio.loop(clipAudio.LOOP_CONTINUOUSLY);
        FloatControl gainControl = (FloatControl) clipAudio.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15F);
        this.gui = new GUI();
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws Exception {
        new Application().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() {
        clipAudio.start();
        try {
            while (this.state != null) {
                state.step(this,gui);
            }
            gui.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
