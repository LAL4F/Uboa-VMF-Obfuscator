package config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import views.MainWindow;

public class XMLManager {
    public static boolean xmlExists() {
        if ((!Files.exists(Path.of("./settings.xml"), LinkOption.NOFOLLOW_LINKS)) || (!Files.exists(Path.of("./settings.xsl"), LinkOption.NOFOLLOW_LINKS))) {
            JOptionPane.showMessageDialog(null, "XML configuration file was not found. It will now be rebuilt.", "Config not found", 2);
            rebuildXML();
            return false;
        }
        return true;
    }
    
    private static void rebuildXML() {
        try {
            byte[] backupConfigBytes = MainWindow.class.getResourceAsStream("/backupConfig/settings.xml").readAllBytes();
            BufferedWriter bw = new BufferedWriter(new FileWriter("./settings.xml"));
            
            for (byte bytes : backupConfigBytes) {
                bw.write(bytes);   
            }
            
            bw.close();
            
            byte[] xslBytes = MainWindow.class.getResourceAsStream("/backupConfig/settings.xsl").readAllBytes();
            bw = new BufferedWriter(new FileWriter("./settings.xsl"));
            
            for (byte bytes : xslBytes) {
                bw.write(bytes);   
            }
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Document loadXML(){
        Document document = null;

        try{
            File file = new File("./settings.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(file);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return document;
    }  

    private static boolean saveXML(Document document){
        try{
            Transformer transformer = TransformerFactory
                    .newInstance()
                    .newTransformer(
                            new StreamSource(
                                    new File("./settings.xsl")
                            )
                    );

            Result output = new StreamResult(new File("./settings.xml"));
            Source input = new DOMSource(document);
            transformer.transform(input, output);
            return true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static Boolean getBooleanValue(String elementName) {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
            return null;
        }
        
        try {
            NodeList node =  document.getElementsByTagName(elementName);
            Element element = (Element) node.item(0);
            return Boolean.parseBoolean(element.getAttribute("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;

    }

    public static boolean setBooleanValue(String elementName, boolean value) {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
           return false;
        }
        
        try {
            NodeList node =  document.getElementsByTagName(elementName);
            Element element = (Element) node.item(0);
            element.setAttribute("value", String.valueOf(value));
            return saveXML(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static int getIntegerValue(String elementName) {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
            return -1;
        }
        
        try {
            NodeList node =  document.getElementsByTagName(elementName);
            Element element = (Element) node.item(0);
            return Integer.parseInt(element.getAttribute("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return -1;

    }

    public static boolean setIntegerValue(String elementName, int value) {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
            return false;
        }
        
        try {
            NodeList node =  document.getElementsByTagName(elementName);
            Element element = (Element) node.item(0);
            element.setAttribute("value", String.valueOf(value));
            return saveXML(document);
        } catch (Exception e) {
            System.err.println("Error setting sound enable");
        }
        
        return false;
    }
    
    public static String getStringValue(String elementName) {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
            return null;
        }
        
        try {
            NodeList node =  document.getElementsByTagName(elementName);
            Element element = (Element) node.item(0);
            return element.getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error getting boolean value from XML");
        }
        
        return null;

    }

    public static boolean setStringValue(String elementName, String value) {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
           return false;
        }
        
        try {
            NodeList node =  document.getElementsByTagName(elementName);
            Element element = (Element) node.item(0);
            element.setAttribute("value", value);
            return saveXML(document);
        } catch (Exception e) {
            System.err.println("Error setting sound enable");
        }
        
        return false;
    }
    
}
