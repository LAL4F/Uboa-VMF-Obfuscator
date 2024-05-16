package utils;

import config.XMLManager;
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
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import views.MainWindow;

public class SoundPlayer  {
    private static AudioInputStream audioIn;
    private static Clip activeClip;

    public enum SoundType {
        SND_OBFUSCATE,
        SND_BATCH,
        SND_OVERRIDE, //Bypasses rules, master volume
        SND_BROWSERDIALOG; //Bypasses rules, sound browser preview volume
    }
    
    public static void killAllSound() {
        if (activeClip != null) {
            activeClip.stop();
        }
    }
    
    private static float getVolume(FloatControl control, SoundType soundType) {
        int volume = XMLManager.getIntegerValue("masterVol");
        
        if (soundType == SoundType.SND_BROWSERDIALOG) {
            volume = XMLManager.getIntegerValue("soundChooserVol");
        }
        
        float range = control.getMinimum();
        float result = range * (1 - volume / 100.0f);
        return result;
    }
    
    public static void initSound(String soundFile, SoundType soundType) {
        if ((soundType != SoundType.SND_OVERRIDE) && (soundType != SoundType.SND_BROWSERDIALOG) && (!XMLManager.getBooleanValue("enableSnd"))) {return;}
        
        switch (soundType) {
            case SND_OBFUSCATE:
                if (!XMLManager.getBooleanValue("enableObfuscateFileSnd")) { return;}
                break;
            case SND_BATCH:
                if (!XMLManager.getBooleanValue("enableSaveFileSnd")) { return;}
                break;
        }
        
        playSound(soundFile, soundType);
    }
    
    private static void playSound(String soundFile, SoundType soundType) {
        try {
            if (soundFile.charAt(0) == '/') {
                InputStream audioSrc = MainWindow.class.getResourceAsStream(soundFile);
                InputStream bufferedAudioInput = new BufferedInputStream(audioSrc);
                audioIn = AudioSystem.getAudioInputStream(bufferedAudioInput);
            } else {
                audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
            }
            
            if (activeClip != null) {
                activeClip.stop();
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(getVolume(control, soundType));
            
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
