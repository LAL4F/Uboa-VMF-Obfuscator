/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import views.MainWindow;

public class SoundPlayer  {
    private static AudioInputStream audioIn;
    private static Clip activeClip;
    
    private boolean isPlaybackCompleted;
    
    private SoundType soundType;

    public enum SoundType {
        SND_OPENFILE,
        SND_ANALYZEFILE,
        SND_OBFUSCATE,
        SND_SAVE,
        SND_ERROR,
        SND_OVERRIDE;
    }
    
    public static void initSound(String soundFile, SoundType soundType) {
        
        //return false;
    }
    
    public static void killAllSound() {
        if (activeClip != null) {
            activeClip.stop();
        }
    }
    
    public static void playSoundInternal(String soundFile) {
        try {
            //Took a while to figure out how to play sounds inside a .jar file but it's here
            InputStream audioSrc = MainWindow.class.getResourceAsStream(soundFile);
            InputStream bufferedAudioInput = new BufferedInputStream(audioSrc);
            
            if (activeClip != null) {
                activeClip.stop();
            }

            audioIn = AudioSystem.getAudioInputStream(bufferedAudioInput);
            
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            activeClip = clip;
            clip.drain();
            audioIn.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void playSoundExternal(String soundFile) {
        try {
            if (activeClip != null) {
                activeClip.stop();
            }
            
            audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
            
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            activeClip = clip;
            clip.drain();
            audioIn.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
