/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import config.XMLManager;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import utils.EntityDictionary;

public class MainWindow extends javax.swing.JFrame {
    private final ImageIcon APPIMAGE = new ImageIcon(MainWindow.class.getResource("/images/appicon.png"));
    
    private ObfuscatePanel obfuscatePanel;
    public Conf_GeneralPanel conf_GeneralPanel;
    private Conf_AppearancePanel conf_AppearancePanel;
    private Conf_EntDictionaryPanel conf_EntDictionaryPanel;
    private Conf_SoundPanel conf_SoundPanel;
    private HelpPanel helpPanel;
    private AboutPanel aboutPanel;
    private RankingPanel rankingPanel;
    
    private CardLayout navigator_obfuscatePanel, navigator_conf_GeneralPanel, navigator_conf_AppearancePanel,
            navigator_conf_EntDictionaryPanel, navigator_conf_SoundPanel, navigator_helpPanel,
            navigator_aboutPanel, navigator_rankingPanel;
    
    public MainWindow() {
        initComponents();
        setIconImage(APPIMAGE.getImage());
        setLocationRelativeTo(null);
        
        loadConfig();  
        loadPanels();
    }
    
    //Load configuration according to XML file
    private void loadConfig() {
        setPreferredSize(new java.awt.Dimension(XMLManager.getIntegerValue("width"), 
                XMLManager.getIntegerValue("height"))
        );
    }

    //!! CANCER !!
    private void loadPanels() {
        //Get layout from panels
        navigator_obfuscatePanel = (CardLayout)panelContainer_Obfuscate.getLayout();
        navigator_conf_GeneralPanel = (CardLayout)panelContainer_Options_General.getLayout();
        navigator_conf_AppearancePanel = (CardLayout)panelContainer_Options_Appearance.getLayout();
        navigator_conf_EntDictionaryPanel = (CardLayout)panelContainer_Options_Edict.getLayout();
        navigator_conf_SoundPanel = (CardLayout)panelContainer_Options_Sound.getLayout();
        navigator_helpPanel = (CardLayout)panelContainer_Help.getLayout();
        navigator_aboutPanel = (CardLayout)panelContainer_About.getLayout();
        navigator_rankingPanel = (CardLayout)panelContainer_Leaderboard.getLayout();

        //Instance the panels
        obfuscatePanel = new ObfuscatePanel(this);
        conf_GeneralPanel = new Conf_GeneralPanel(this);
        conf_AppearancePanel = new Conf_AppearancePanel(this);
        conf_EntDictionaryPanel = new Conf_EntDictionaryPanel(this);
        conf_SoundPanel = new Conf_SoundPanel(this);
        helpPanel = new HelpPanel();
        aboutPanel = new AboutPanel();
        rankingPanel = new RankingPanel();
        
        //Add panels to container
        panelContainer_Obfuscate.add(obfuscatePanel, "obfuscatePanel");
        panelContainer_Options_General.add(conf_GeneralPanel, "conf_GeneralPanel");
        panelContainer_Options_Appearance.add(conf_AppearancePanel, "conf_AppearancePanel");
        panelContainer_Options_Edict.add(conf_EntDictionaryPanel, "conf_EntDictionaryPanel");
        panelContainer_Options_Sound.add(conf_SoundPanel, "conf_SoundPanel");
        panelContainer_Help.add(helpPanel, "helpPanel");
        panelContainer_About.add(aboutPanel, "aboutPanel");
        panelContainer_Leaderboard.add(rankingPanel, "rankingPanel");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rg_ePosObfuscation = new javax.swing.ButtonGroup();
        rg_eNameObfuscation = new javax.swing.ButtonGroup();
        tabbedPanel = new javax.swing.JTabbedPane();
        panelContainer_Obfuscate = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panelContainer_Options_General = new javax.swing.JPanel();
        panelContainer_Options_Appearance = new javax.swing.JPanel();
        panelContainer_Options_Edict = new javax.swing.JPanel();
        panelContainer_Options_Sound = new javax.swing.JPanel();
        panelContainer_Help = new javax.swing.JPanel();
        panelContainer_About = new javax.swing.JPanel();
        panelContainer_Leaderboard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uboa VMF Obfuscator");
        setMinimumSize(new java.awt.Dimension(1100, 700));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        tabbedPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelContainer_Obfuscate.setLayout(new java.awt.CardLayout());
        tabbedPanel.addTab("Obfuscate", panelContainer_Obfuscate);

        panelContainer_Options_General.setLayout(new java.awt.CardLayout());
        jTabbedPane2.addTab("General", panelContainer_Options_General);

        panelContainer_Options_Appearance.setLayout(new java.awt.CardLayout());
        jTabbedPane2.addTab("Appearance", panelContainer_Options_Appearance);

        panelContainer_Options_Edict.setLayout(new java.awt.CardLayout());
        jTabbedPane2.addTab("Entity Dictionary", panelContainer_Options_Edict);

        panelContainer_Options_Sound.setLayout(new java.awt.CardLayout());
        jTabbedPane2.addTab("Sounds", panelContainer_Options_Sound);

        tabbedPanel.addTab("Options", jTabbedPane2);

        panelContainer_Help.setLayout(new java.awt.CardLayout());
        tabbedPanel.addTab("Help", panelContainer_Help);

        panelContainer_About.setLayout(new java.awt.CardLayout());
        tabbedPanel.addTab("About", panelContainer_About);

        panelContainer_Leaderboard.setLayout(new java.awt.CardLayout());
        tabbedPanel.addTab("Leaderboard", panelContainer_Leaderboard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPanel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        XMLManager.setIntegerValue("width", getWidth());
        XMLManager.setIntegerValue("height", getHeight());
    }//GEN-LAST:event_formComponentResized

    private static void setLookAndFeel() {
        String lookAndFeel = XMLManager.getStringValue("lookAndFeel");
        try {
            switch (lookAndFeel) {
                case "FlatMacDarkLaf":
                    UIManager.setLookAndFeel( new FlatMacDarkLaf());
                    break;
                case "FlatMacLightLaf":
                    UIManager.setLookAndFeel( new FlatMacLightLaf());
                    break;
                case "Metal":
                    UIManager.setLookAndFeel( new MetalLookAndFeel());
                    break;
                case "Nimbus":
                    UIManager.setLookAndFeel( new NimbusLookAndFeel());
                    break;
                case "Motif":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    break;
                case "Windows":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                case "Windows Classic":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    break;
                default:
                    UIManager.setLookAndFeel( new FlatMacDarkLaf());
                    break;
            } 
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }
    
    //Called when applying a new theme from the configuration window
    public void restartApp() {
        dispose();
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
        
        setLookAndFeel();
    }

    public static void main(String args[]) {
        if (!XMLManager.xmlExists()) {
            System.out.println("Rebuilding XML");
        }
        
        if (!EntityDictionary.edictExists()) {
            System.out.println("Rebuilding edict");
        }
        
        setLookAndFeel();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel panelContainer_About;
    private javax.swing.JPanel panelContainer_Help;
    private javax.swing.JPanel panelContainer_Leaderboard;
    private javax.swing.JPanel panelContainer_Obfuscate;
    private javax.swing.JPanel panelContainer_Options_Appearance;
    private javax.swing.JPanel panelContainer_Options_Edict;
    private javax.swing.JPanel panelContainer_Options_General;
    private javax.swing.JPanel panelContainer_Options_Sound;
    private javax.swing.ButtonGroup rg_eNameObfuscation;
    private javax.swing.ButtonGroup rg_ePosObfuscation;
    private javax.swing.JTabbedPane tabbedPanel;
    // End of variables declaration//GEN-END:variables
}
