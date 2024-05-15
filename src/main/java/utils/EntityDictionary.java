/*
    This file mainly exists to facilitate methods regarding the entity dictionary,
    a list of entities whose origin can be modified without affecting
    functionality
 */
package utils;

import config.XMLManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import views2.MainWindow;

public class EntityDictionary {
    public static boolean edictExists() {
        if (!Files.exists(Path.of("./entityDictionary.txt"), LinkOption.NOFOLLOW_LINKS)) {
            JOptionPane.showMessageDialog(null, "Entity dictionary was not found. It will now be rebuilt.", "Edict not found", 2);
            rebuildEdict();
            return false;
        }
        return true;
    }
    
    public static void rebuildEdict() {
        try {
            byte[] backupEdictBytes = MainWindow.class.getResourceAsStream("/backupConfig/entityDictionary.txt").readAllBytes();
            BufferedWriter bw = new BufferedWriter(new FileWriter("./entityDictionary.txt"));
            
            for (byte bytes : backupEdictBytes) {
                bw.write(bytes);   
            }
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<String> getEdict() {
        if (!edictExists()) {
            return null;
        }
        
        ArrayList<String> edict = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./entityDictionary.txt"));
            
            while (br.ready()) {
                edict.add(br.readLine());
            }
            
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EntityDictionary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EntityDictionary.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return edict;
    }
    
    public static void saveEdict(ArrayList<String> edict) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./entityDictionary.txt"));
            
            for (String string : edict) {
                bw.write(string + "\n");
            }
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(EntityDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    