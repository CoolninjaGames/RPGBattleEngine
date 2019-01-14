package coolninja.rpg;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles playing sound
 * @version 1.0
 * @since 1.0
 * @author Ben Ballard
 */
public class SoundHandler extends Thread{
    
    public URL pathToSound;
    public boolean shouldLoop;
    
    public Clip audio;
    private AudioInputStream input;
    
    /**
     * Starts playing sound
     * @since 1.0
     * @param pathToSound
     * @param shouldLoop
     */
    public SoundHandler(URL pathToSound, boolean shouldLoop){
        this.pathToSound = pathToSound;
        this.shouldLoop = shouldLoop;
    }

    @Override
    public void run() {
        File file = new File(pathToSound.getFile());
        
        input = null;
        try {
            input = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        AudioFormat format = input.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        
        audio = null;
        try {
            audio = (Clip) AudioSystem.getLine(info);
            if(shouldLoop){
                audio.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        
        try {
            audio.open(input);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        audio.start();
        while (audio.isRunning()){
            try{
                this.wait(1);
            }catch(InterruptedException e){
               
            }
        }
        
    }
    
    public void end(){
        audio.close();
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.interrupt();
    }
}