package config;

import java.awt.Color;
import java.io.File;
import javax.swing.JColorChooser;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLManager {
    private static Document loadXML(){
        Document document = null;
        try{
            //File archivo = new File("src/main/java/resources/settings.xml");
            File archivo = new File("settings.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(archivo);
            
        }catch(Exception e){
            System.err.println("Error loading XML");
        
        }
        return document;
    }  

    private static boolean saveXML(Document document){
        try{
            Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(new File("src/main/java/config/StyleXML.xsl")));

            //Result output =new StreamResult(new File("src/main/java/resources/settings.xml")); 
            Result output =new StreamResult(new File("settings.xml"));
            Source input = new DOMSource(document);
            transformer.transform(input, output);
            return true;
        }catch(Exception e){
            System.err.println("Error saving XML");
            return false;
        }
    }
    
    public static Boolean isSoundEnabled() {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
           return null;
        }
        
        try {
            NodeList node =  document.getElementsByTagName("playsounds");
            if (node.getLength() > 0) {
                Element element = (Element) node.item(0);
                  
                return Boolean.parseBoolean(element.getAttribute("bool"));
            }
            
        } catch (Exception e) {
            System.err.println("Error getting sound enable from XML");
        }
        
        return null;

    }

    public static boolean setSoundEnabled(boolean option) {
        Document document = loadXML();
        
        if (document == null) {
            System.err.println("Error loading XML config");
           return false;
        }
        
        try {
            NodeList node =  document.getElementsByTagName("playsounds");
            if (node.getLength() > 0) {
                Element element = (Element) node.item(0);
                element.setAttribute("bool", String.valueOf(option));
                return saveXML(document);
            }
        } catch (Exception e) {
            System.err.println("Error setting sound enable");
        }
        
        return false;
    }
}
