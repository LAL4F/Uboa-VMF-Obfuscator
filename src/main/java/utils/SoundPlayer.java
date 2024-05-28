package utils;

import config.XMLConfig;
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
        SND_ERROR,
        SND_OVERRIDE, //Bypasses rules, master volume
        SND_BROWSERDIALOG; //Bypasses rules, sound browser preview volume
    }
    

    
    //Initial check to figure out if sound should be played in the first place
    //Sounds with the SND_OVERRIDE and SND_BROWSERDIALOG flags should always play no matter what
    //Otherwise if playing any other sound, check wether the enable sound flag is true, then perform individual checks
    //based on user configuration and sound type
    public static void initSound(String soundFile, SoundType soundType) {
        if ((soundType != SoundType.SND_OVERRIDE) && (soundType != SoundType.SND_BROWSERDIALOG) && (!XMLConfig.getBooleanValue("enableSnd"))) {return;}
        
        switch (soundType) {
            case SND_OBFUSCATE:
                if (!XMLConfig.getBooleanValue("enableObfuscateFileSnd")) { return;}
                break;
            case SND_BATCH:
                if (!XMLConfig.getBooleanValue("enableBatchOperationSnd")) { return;}
                break;
            case SND_ERROR:
                if (!XMLConfig.getBooleanValue("enableErrorSnd")) { return;}
                break;
        }
        
        playSound(soundFile, soundType);
    }
    
    private static void playSound(String soundFile, SoundType soundType) {
        try {
            //If sound is internal (packed inside of the executable), get input stream from the main class resource
            //otherwise, get input stream directly from the file system
            if (soundFile.charAt(0) == '/') {
                InputStream audioSrc = MainWindow.class.getResourceAsStream(soundFile);
                InputStream bufferedAudioInput = new BufferedInputStream(audioSrc);
                audioIn = AudioSystem.getAudioInputStream(bufferedAudioInput);
            } else {
                audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
            }
            
            //Stop any sound that may already be playing
            if (activeClip != null) {
                activeClip.stop();
            }

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            
            //Set volume
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
    
    //As defined above, stop any sound that may be playing without initializing another one
    public static void killAllSound() {
        if (activeClip != null) {
            activeClip.stop();
        }
    }
    
    //Returns volume as a float based on user configuration
    //Sounds played from within the sound browser dialog use a different parameter from the XML config
    private static float getVolume(FloatControl control, SoundType soundType) {
        int volume = XMLConfig.getIntegerValue("masterVol");
        
        if (soundType == SoundType.SND_BROWSERDIALOG) {
            volume = XMLConfig.getIntegerValue("soundChooserVol");
        }
        
        float range = control.getMinimum();
        float result = range * (1 - volume / 100.0f);
        return result;
    }
}
