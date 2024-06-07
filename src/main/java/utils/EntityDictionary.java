/*
    This file mainly exists to facilitate methods regarding the entity dictionary,
    a list of entities whose origin can be modified without affecting
    functionality
 */

package utils;

import config.XMLConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import views.MainWindow;

public class EntityDictionary {
    //Check if it exists in the active directory, if not, rebuild
    public static boolean edictExists() {
        if (!Files.exists(Path.of("./entityDictionary.txt"), LinkOption.NOFOLLOW_LINKS)) {
            JOptionPane.showMessageDialog(null, "Entity dictionary was not found. It will now be rebuilt.", "Edict not found", 2);
            System.out.println("Rebuilding edict");
            rebuildEdict();
            return false;
        }
        return true;
    }
    
    //Rebuild dictionary from backup
    public static void rebuildEdict() {
        try {
            byte[] backupEdictBytes = MainWindow.class.getResourceAsStream("/backupConfig/entityDictionary.txt").readAllBytes();
            BufferedWriter bw = new BufferedWriter(new FileWriter("./entityDictionary.txt"));
            
            for (byte bytes : backupEdictBytes) {
                bw.write(bytes);   
            }
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Get contents of the external edict file as an ArrayList
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
    
    //Save an ArrayList to external file 
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
    