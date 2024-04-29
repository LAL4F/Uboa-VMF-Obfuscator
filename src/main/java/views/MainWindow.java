/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import config.XMLManager;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import jnafilechooser.api.JnaFileChooser;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import utils.EntityDictionary;
import utils.RandomString;
import utils.SoundPlayer;


public class MainWindow extends javax.swing.JFrame {
    private final ImageIcon ICON_OPENFILE = new ImageIcon(MainWindow.class.getResource("/images/ico20_openFile.png"));
    private final ImageIcon ICON_SAVE = new ImageIcon(MainWindow.class.getResource("/images/ico20_saveFile.png"));
    private final ImageIcon ICON_SAVEAS = new ImageIcon(MainWindow.class.getResource("/images/ico20_saveFileAs.png"));
    private final ImageIcon APPIMAGE = new ImageIcon(MainWindow.class.getResource("/images/appicon.png"));
    
    private final String CHARSET = "ISO-8859-1";
    private String filePath, fileName, vmfContent, rebuiltVmf;
    private int iNumEnts;
    private File selectedFile;
    
    //Swing classes
    private OpenFileProgressDialogue openFileProgressDialogue;
    private AboutDialogue aboutDialogue;
    private ConfigWindow configWindow;
    
    //Stored entity origins and targetnames
    private ArrayList<String> originArray = new ArrayList<>();
    private ArrayList<String> targetnameArray = new ArrayList<>();
    
    //Obfuscation methods
    private int originObfuscationKind;
    private int targetnameObfuscationKind;
    
    //The VMF that is read from a file, a list of strings
    List<String> readVmfList;
    
    public MainWindow() {
        initComponents();
        setIconImage(APPIMAGE.getImage());
        setLocationRelativeTo(null);
        
        openFileProgressDialogue = new OpenFileProgressDialogue(this, false);
        setRandomEntNameParametersEditable(false);

        originObfuscationKind = 0;
        targetnameObfuscationKind = 0;
        
        setupIcons();
        setupRadioButtons();
        loadConfig();
        
        setFileLoaded(false);
        
        textArea.setText("Welcome to Uboa VMF Obfuscator. Load a VMF file to begin.");
        lb_info.setText("Hover over objects to see relevant information");
        lb_charset.setText(CHARSET);
        
    }
    
    private void setupIcons() {
        menuOption_open.setIcon(ICON_OPENFILE);
        menuOption_save.setIcon(ICON_SAVE);
        menuOption_saveAs.setIcon(ICON_SAVEAS);
    }
    
    //Load configuration according to XML file
    private void loadConfig() {
        checkbox_autoobfuscate.setSelected(XMLManager.getBooleanValue("autoObfuscate"));
        checkbox_autosave.setSelected(XMLManager.getBooleanValue("autosave"));
        checkbox_verbose.setSelected(XMLManager.getBooleanValue("verbose"));
        
        switch (XMLManager.getStringValue("entityPosObfuscation")) {
            case "None":
                rb_ePosObfuscation_none.setSelected(true);
                break;
            case "Overlap":
                originObfuscationKind = 1;
                rb_ePosObfuscation_overlap.setSelected(true);
                break;
            case "Random":
                originObfuscationKind = 2;
                rb_ePosObfuscation_randomize.setSelected(true);
                break;
            case "RandomOverlap":
                originObfuscationKind = 3;
                rb_ePosObfuscation_randomizeOverlap.setSelected(true);
                break;
        }
        
        switch (XMLManager.getStringValue("entityNameObfuscation")) {
            case "None":
                rb_eNameObfuscation_none.setSelected(true);
                break;
            case "Exchange":
                targetnameObfuscationKind = 1;
                rb_eNameObfuscation_exchange.setSelected(true);
                break;
            case "Random":
                targetnameObfuscationKind = 2;
                rb_eNameObfuscation_randomize.setSelected(true);
                setRandomEntNameParametersEditable(true);
                break;
        }
        
        comboBox_rndEntNameChoices.setSelectedIndex(XMLManager.getIntegerValue("entityNameObfuscationKind"));
        comboBox_rndEntNameLength.setValue(XMLManager.getIntegerValue("entityNameObfuscationLength"));
    }
    
    private void setupRadioButtons() {
        //Entity position buttons
        rg_ePosObfuscation.add(rb_ePosObfuscation_none);
        rg_ePosObfuscation.add(rb_ePosObfuscation_overlap);
        rg_ePosObfuscation.add(rb_ePosObfuscation_randomize);
        rg_ePosObfuscation.add(rb_ePosObfuscation_randomizeOverlap);
        
        //Entity name buttons
        rg_eNameObfuscation.add(rb_eNameObfuscation_none);
        rg_eNameObfuscation.add(rb_eNameObfuscation_exchange);
        rg_eNameObfuscation.add(rb_eNameObfuscation_randomize);
    }
    
    private void setFileLoaded(boolean state) {
        //After loading file, allow obfuscation as well as closing the file
        menuOption_closeFile.setEnabled(state);
        bt_obfuscate.setEnabled(state);
    }
    
    private void setFileObfuscated(boolean state) {
        //After file is obfuscated, allow save
        menuOption_save.setEnabled(state);
        menuOption_saveAs.setEnabled(state);
    }

    private void setRandomEntNameParametersEditable(boolean state) {
        comboBox_rndEntNameChoices.setEnabled(state);
        comboBox_rndEntNameLength.setEnabled(state);
        comboBox_rndEntNameLabel.setEnabled(state);
    }
    
    //Add text to the textArea, then automatically scroll down
    //to the bottom
    private void addText(String string) {
        textArea.append(string);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    private void addVerbose(String string) {
        if (checkbox_verbose.isSelected()) {
            textArea.append(string);
            textArea.setCaretPosition(textArea.getDocument().getLength());    
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rg_ePosObfuscation = new javax.swing.ButtonGroup();
        rg_eNameObfuscation = new javax.swing.ButtonGroup();
        jMenuItem4 = new javax.swing.JMenuItem();
        textAreaScrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        rb_ePosObfuscation_none = new javax.swing.JRadioButton();
        rb_ePosObfuscation_overlap = new javax.swing.JRadioButton();
        rb_ePosObfuscation_randomize = new javax.swing.JRadioButton();
        rb_ePosObfuscation_randomizeOverlap = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        rb_eNameObfuscation_none = new javax.swing.JRadioButton();
        rb_eNameObfuscation_exchange = new javax.swing.JRadioButton();
        rb_eNameObfuscation_randomize = new javax.swing.JRadioButton();
        comboBox_rndEntNameChoices = new javax.swing.JComboBox<>();
        comboBox_rndEntNameLength = new javax.swing.JSpinner();
        comboBox_rndEntNameLabel = new javax.swing.JLabel();
        bt_randomStringTest = new javax.swing.JButton();
        lb_info = new javax.swing.JLabel();
        bt_obfuscate = new javax.swing.JButton();
        lb_filelength = new javax.swing.JLabel();
        lb_charset = new javax.swing.JLabel();
        checkbox_autosave = new javax.swing.JCheckBox();
        checkbox_autoobfuscate = new javax.swing.JCheckBox();
        checkbox_verbose = new javax.swing.JCheckBox();
        bt_clearConsole = new javax.swing.JButton();
        bt_exportLog = new javax.swing.JButton();
        bt_copyClipboard = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        menuOption_open = new javax.swing.JMenuItem();
        menuOption_save = new javax.swing.JMenuItem();
        menuOption_saveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuOption_closeFile = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuOption_exit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuOption_preferences = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menu_help = new javax.swing.JMenu();
        menuOption_openManual = new javax.swing.JMenuItem();
        menuOption_about = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uboa VMF Obfuscator");
        setMinimumSize(new java.awt.Dimension(720, 480));
        setResizable(false);

        textAreaScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Console"));

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setTabSize(4);
        textAreaScrollPane.setViewportView(textArea);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Logic Entity Position Obfuscation"));

        rb_ePosObfuscation_none.setText("None");
        rb_ePosObfuscation_none.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_ePosObfuscation_none.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_ePosObfuscation_noneMouseEntered(evt);
            }
        });
        rb_ePosObfuscation_none.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_ePosObfuscation_noneActionPerformed(evt);
            }
        });

        rb_ePosObfuscation_overlap.setText("Overlap");
        rb_ePosObfuscation_overlap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_ePosObfuscation_overlap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_ePosObfuscation_overlapMouseEntered(evt);
            }
        });
        rb_ePosObfuscation_overlap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_ePosObfuscation_overlapActionPerformed(evt);
            }
        });

        rb_ePosObfuscation_randomize.setText("Randomize");
        rb_ePosObfuscation_randomize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_ePosObfuscation_randomize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_ePosObfuscation_randomizeMouseEntered(evt);
            }
        });
        rb_ePosObfuscation_randomize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_ePosObfuscation_randomizeActionPerformed(evt);
            }
        });

        rb_ePosObfuscation_randomizeOverlap.setText("Randomize w/ Overlap");
        rb_ePosObfuscation_randomizeOverlap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_ePosObfuscation_randomizeOverlap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_ePosObfuscation_randomizeOverlapMouseEntered(evt);
            }
        });
        rb_ePosObfuscation_randomizeOverlap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_ePosObfuscation_randomizeOverlapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb_ePosObfuscation_none)
                .addGap(18, 18, 18)
                .addComponent(rb_ePosObfuscation_overlap)
                .addGap(18, 18, 18)
                .addComponent(rb_ePosObfuscation_randomize)
                .addGap(18, 18, 18)
                .addComponent(rb_ePosObfuscation_randomizeOverlap)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_ePosObfuscation_none)
                    .addComponent(rb_ePosObfuscation_overlap)
                    .addComponent(rb_ePosObfuscation_randomize)
                    .addComponent(rb_ePosObfuscation_randomizeOverlap))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Entity Name Obfuscation"));

        rb_eNameObfuscation_none.setText("None");
        rb_eNameObfuscation_none.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_eNameObfuscation_none.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_eNameObfuscation_noneMouseEntered(evt);
            }
        });
        rb_eNameObfuscation_none.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_eNameObfuscation_noneActionPerformed(evt);
            }
        });

        rb_eNameObfuscation_exchange.setText("Exchange");
        rb_eNameObfuscation_exchange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_eNameObfuscation_exchange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_eNameObfuscation_exchangeMouseEntered(evt);
            }
        });
        rb_eNameObfuscation_exchange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_eNameObfuscation_exchangeActionPerformed(evt);
            }
        });

        rb_eNameObfuscation_randomize.setText("Randomized...");
        rb_eNameObfuscation_randomize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_eNameObfuscation_randomize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_eNameObfuscation_randomizeMouseEntered(evt);
            }
        });
        rb_eNameObfuscation_randomize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_eNameObfuscation_randomizeActionPerformed(evt);
            }
        });

        comboBox_rndEntNameChoices.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Letters", "Numbers", "Letters and numbers", "Symbols", "All of the above" }));
        comboBox_rndEntNameChoices.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboBox_rndEntNameChoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_rndEntNameChoicesActionPerformed(evt);
            }
        });

        comboBox_rndEntNameLength.setModel(new javax.swing.SpinnerNumberModel(5, 5, 30, 1));
        comboBox_rndEntNameLength.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comboBox_rndEntNameLength.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                comboBox_rndEntNameLengthStateChanged(evt);
            }
        });

        comboBox_rndEntNameLabel.setText("Length");

        bt_randomStringTest.setText("Test Random");
        bt_randomStringTest.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_randomStringTest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_randomStringTestMouseEntered(evt);
            }
        });
        bt_randomStringTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_randomStringTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rb_eNameObfuscation_none)
                .addGap(18, 18, 18)
                .addComponent(rb_eNameObfuscation_exchange)
                .addGap(18, 18, 18)
                .addComponent(rb_eNameObfuscation_randomize)
                .addGap(18, 18, 18)
                .addComponent(comboBox_rndEntNameChoices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBox_rndEntNameLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBox_rndEntNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_randomStringTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_eNameObfuscation_none)
                    .addComponent(rb_eNameObfuscation_exchange)
                    .addComponent(rb_eNameObfuscation_randomize)
                    .addComponent(comboBox_rndEntNameChoices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_rndEntNameLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_rndEntNameLabel)
                    .addComponent(bt_randomStringTest))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_info.setText("Hover over objects to see relevant information");
        lb_info.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bt_obfuscate.setText("Obfuscate!");
        bt_obfuscate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_obfuscate.setEnabled(false);
        bt_obfuscate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_obfuscateActionPerformed(evt);
            }
        });

        lb_filelength.setText("length: 0 | lines: 0");
        lb_filelength.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_charset.setText("Charset");
        lb_charset.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        checkbox_autosave.setText("Autosave");
        checkbox_autosave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_autosave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkbox_autosaveMouseEntered(evt);
            }
        });
        checkbox_autosave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_autosaveActionPerformed(evt);
            }
        });

        checkbox_autoobfuscate.setText("Auto-obfuscate");
        checkbox_autoobfuscate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_autoobfuscate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkbox_autoobfuscateMouseEntered(evt);
            }
        });
        checkbox_autoobfuscate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_autoobfuscateActionPerformed(evt);
            }
        });

        checkbox_verbose.setText("Verbose");
        checkbox_verbose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_verbose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkbox_verboseMouseEntered(evt);
            }
        });
        checkbox_verbose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_verboseActionPerformed(evt);
            }
        });

        bt_clearConsole.setText("Clear");
        bt_clearConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_clearConsole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_clearConsoleMouseEntered(evt);
            }
        });
        bt_clearConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_clearConsoleActionPerformed(evt);
            }
        });

        bt_exportLog.setText("Export log...");
        bt_exportLog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_exportLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_exportLogMouseEntered(evt);
            }
        });
        bt_exportLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exportLogActionPerformed(evt);
            }
        });

        bt_copyClipboard.setText("Copy to Clipboard");
        bt_copyClipboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_copyClipboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_copyClipboardMouseEntered(evt);
            }
        });
        bt_copyClipboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_copyClipboardActionPerformed(evt);
            }
        });

        menu_file.setText("File");

        menuOption_open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOption_open.setText("Open File...");
        menuOption_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_openActionPerformed(evt);
            }
        });
        menu_file.add(menuOption_open);

        menuOption_save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOption_save.setText("Save");
        menuOption_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_saveActionPerformed(evt);
            }
        });
        menu_file.add(menuOption_save);

        menuOption_saveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOption_saveAs.setText("Save As...");
        menuOption_saveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_saveAsActionPerformed(evt);
            }
        });
        menu_file.add(menuOption_saveAs);
        menu_file.add(jSeparator1);

        menuOption_closeFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOption_closeFile.setText("Close");
        menuOption_closeFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_closeFileActionPerformed(evt);
            }
        });
        menu_file.add(menuOption_closeFile);
        menu_file.add(jSeparator2);

        menuOption_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuOption_exit.setText("Exit");
        menuOption_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_exitActionPerformed(evt);
            }
        });
        menu_file.add(menuOption_exit);

        jMenuBar1.add(menu_file);

        jMenu3.setText("Settings");

        menuOption_preferences.setText("Preferences...");
        menuOption_preferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_preferencesActionPerformed(evt);
            }
        });
        jMenu3.add(menuOption_preferences);

        jMenu1.setText("Languages");

        jMenuItem2.setText("English");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Spanish");
        jMenu1.add(jMenuItem3);

        jMenu3.add(jMenu1);

        jMenuBar1.add(jMenu3);

        menu_help.setText("Help");

        menuOption_openManual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menuOption_openManual.setText("User Manual");
        menuOption_openManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_openManualActionPerformed(evt);
            }
        });
        menu_help.add(menuOption_openManual);

        menuOption_about.setText("About...");
        menuOption_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_aboutActionPerformed(evt);
            }
        });
        menu_help.add(menuOption_about);

        jMenuBar1.add(menu_help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textAreaScrollPane)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_obfuscate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox_autoobfuscate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkbox_autosave)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_info, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_filelength, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_copyClipboard)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_exportLog, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkbox_verbose, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_charset, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(bt_clearConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_obfuscate)
                    .addComponent(checkbox_autosave)
                    .addComponent(checkbox_autoobfuscate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_copyClipboard, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_clearConsole)
                        .addComponent(bt_exportLog)
                        .addComponent(checkbox_verbose)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_info)
                    .addComponent(lb_filelength)
                    .addComponent(lb_charset)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFile() {
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Valve Map File (.vmf)", "vmf", "vmx");

        if (fileChooser.showOpenDialog(this)) {
            clearProgram();
            new Thread(new Runnable() {
                public void run() {
                    openFileProgressDialogue.show();
                }
            }).start();

            selectedFile = fileChooser.getSelectedFile();

            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            fileName = selectedFile.getName();

            setTitle("Loading, please wait... - Uboa VMF Obfuscator");

            try {
                asyncReadVMF();
            } catch (ExecutionException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }          
    }
    
    private void asyncReadVMF() throws ExecutionException {
        try {
            Thread vmfReaderThread;
            vmfReaderThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        ArrayList<String> eDidct = EntityDictionary.getEdict();
                        
                        textArea.setText("Opening " + fileName + "\n...");
                        long startTime = System.nanoTime();
                        
                        readVmfList = Files.readAllLines(Paths.get(filePath), Charset.forName(CHARSET));
                        
                        int totalLines = readVmfList.size();
                        String totalLinesPretty = String.format("%,d", totalLines);
                        SoundPlayer.playSound(XMLManager.getStringValue("openFileSnd"), SoundPlayer.SoundType.SND_OPENFILE);
                        addText("\n\nFinished opening " + fileName + ".\nTime to open file: " + String.format("%.2f", (double)(System.nanoTime() - startTime) / 1_000_000_000. ) + " seconds");
                        addText("\nTotal lines: " + totalLinesPretty);
                        
                        int beginEntitySection = readVmfList.indexOf("entity");
                        float percentageLinesSkipped = (float)beginEntitySection / totalLines * 100;
                        addText("\n\nDon't care about solids, moving to entity section, skipping " +  String.format("%,d",beginEntitySection) + " lines ("+String.format("%.2f", percentageLinesSkipped) + "% of all lines)");
                        addText("\nAnalyzing entity section... working, please wait...");
                        addText("\n...");
                        
                        List<String> solidSection = readVmfList.subList(0, beginEntitySection);
                        
                        vmfContent = String.join("\n", solidSection);
                        
                        openFileProgressDialogue.setFileText("Reading " + fileName);
                        openFileProgressDialogue.setProgressBarMin(beginEntitySection);
                        openFileProgressDialogue.setProgressBarMax(totalLines);

                        boolean isParsingEntity = false;
                        boolean isPointEntity = false;
                        String verboseTextToAppend = "";
                        
                        for (int i = beginEntitySection; i < readVmfList.size(); i++) {
                            
                            //Check if reading entity
                            if (readVmfList.get(i).equals("entity")) {
                                iNumEnts++;
                                isParsingEntity = true;
                            }
                            
                            if (isParsingEntity) {
                                //Figure out class of entity
                                if (readVmfList.get(i).contains("classname")) {
                                    Pattern pattern = Pattern.compile("\"([^\"]*)\"");
                                    Matcher matcher = pattern.matcher(readVmfList.get(i));

                                    while( matcher.find() ) {
                                        if (!matcher.group(1).equals("classname")) {
                                            if (eDidct.contains(matcher.group(1))) {
                                                verboseTextToAppend += "\n\nFound logic entity " + matcher.group(1) + ", obtaining origins";
                                            } else {
                                                isPointEntity = true;
                                                verboseTextToAppend += "\n\nFound brush/point entity " + matcher.group(1) + ", ignoring origins";
                                            }
                                        }
                                    }
                                }

                                //Get targetname regardless of entity class
                                if (readVmfList.get(i).contains("\"targetname\"")) {
                                    Pattern pattern = Pattern.compile("\"([^\"]*)\"");
                                    Matcher matcher = pattern.matcher(readVmfList.get(i));

                                    while( matcher.find() ) {
                                        if (!matcher.group(1).equals("targetname")) {
                                            verboseTextToAppend += "\n  -Found targetname " + matcher.group(1);
                                            targetnameArray.add(matcher.group(1));  
                                        }
                                    }
                                }
                                
                                //Get origin only if the found entity is a logic entity
                                if ((!isPointEntity) && (readVmfList.get(i).contains("\"origin\""))) {
                                    Pattern pattern = Pattern.compile("\"([^\"]*)\"");
                                    Matcher matcher = pattern.matcher(readVmfList.get(i));

                                    while( matcher.find() ) {
                                        if (!matcher.group(1).equals("origin")) {
                                            verboseTextToAppend += "\n  -Found origin " + matcher.group(1);
                                            originArray.add(matcher.group(1));
                                        }
                                    }
                                }
                            }
                            
                            //Close entity
                            try {
                                if ((isParsingEntity) && (readVmfList.get(i).equals("}")) && (readVmfList.get(i + 1).equals("entity")) || (readVmfList.get(i + 1).equals("cameras")) ) {
                                    isParsingEntity = false;
                                    isPointEntity = false;
                                    verboseTextToAppend += "\nFinished parsing entity";
                                }
                            } catch (IndexOutOfBoundsException e) {}

                            vmfContent += readVmfList.get(i) + "\n";
                            openFileProgressDialogue.setStatusText("Line " + String.format("%,d", i) + " of " + totalLinesPretty);
                            openFileProgressDialogue.setProgressBarValue(i);
                            openFileProgressDialogue.appendTextArea(readVmfList.get(i));     
                            
                            if (checkbox_verbose.isSelected()) {
                                addText(verboseTextToAppend);
                                verboseTextToAppend = "";
                            }
                        }
                        
                        String fileSizePretty = String.format("%.2f", Float.valueOf(selectedFile.length()) / 1024000);
                        
                        setTitle(fileName + " - " + fileSizePretty + " MB - Uboa VMF Obfuscator");
                        
                        SoundPlayer.playSound(XMLManager.getStringValue("analyzeFileSnd"), SoundPlayer.SoundType.SND_ANALYZEFILE);
                        addText("\n\nFinished reading " + fileName + "\n\nTime to read file: " + String.format("%.2f", (double)(System.nanoTime() - startTime) / 1_000_000_000. ) + " seconds");
                        addText("\nNumber of entities: " + iNumEnts);
                        setFileLoaded(true);
                        openFileProgressDialogue.setVisible(false);
                        openFileProgressDialogue.dispose();
                        lb_filelength.setText("length: " + String.format("%,d", vmfContent.length()) + " | lines: " + totalLinesPretty);
                        
                        if (XMLManager.getBooleanValue("autoObfuscate")) {obfuscateVMF();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            vmfReaderThread.start();
            
        } catch (Exception e) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void obfuscateVMF() {
        rebuiltVmf = vmfContent;
        ArrayList<Integer> spentOriginsIndexes = new ArrayList<>();
        ArrayList<String> sanitizedOrigins = new ArrayList<>();
        
        ArrayList<Integer> spentTargetnameIndexes = new ArrayList<>();
        ArrayList<String> sanitizedTargetnames = new ArrayList<>();
        
        Random rnd =  new Random();
        int randomIndex = 0;
        String sanitizedString = "";
        String regex = "";
        String doubleQuoteRegex = ""; //Workaround because this thing likes to add unnecessary quotes
        
        addText("\n\nStored Origins:\n");
        for (String origin : originArray) {
            addText(origin + "\n");
        }
        
        if (originObfuscationKind == 2) { //Randomized origins
            //We must first sanitize origins stored in the VMF
            //to prevent horrible things from happening, like two entities
            //having their origins set to the same value, which
            //this obfuscation method should prevent
            addText("\nOrigin randomization method: sanitizing origins\n");
            for (String origin : originArray) {
                regex = "(." + origin + "?)";
                RandomString randomString = new RandomString();
                
                do {
                    sanitizedString = randomString.getRandomString(1, 20);
                    if (sanitizedOrigins.contains(sanitizedString)) addText("\nSanitized origin " + sanitizedString + " is already in use, rerolling");
                } while (sanitizedOrigins.contains(sanitizedString));
                
                addText("Origin " + origin + " sanitized with string " + sanitizedString + "\n");
                rebuiltVmf = rebuiltVmf.replaceAll(regex, "\"" + sanitizedString);
                sanitizedOrigins.add(sanitizedString);
            }
        }
        
        int sanitizedOriginsIndex = 0;
        if (originObfuscationKind != 0) {
            for (String origin : originArray) {
                regex = "(." + origin + "?)";
                
                switch (originObfuscationKind) {
                    case 1: //Overlap
                        addText("\nObfuscating origin " + origin + " using overlap method");
                        rebuiltVmf = rebuiltVmf.replaceAll(regex, "\"" + originArray.getFirst());
                        doubleQuoteRegex = "(. \"\"" + originArray.getFirst() + "?)";
                        rebuiltVmf = rebuiltVmf.replaceAll(doubleQuoteRegex, "\" \"" + originArray.getFirst());
                        break;
                    case 2: //Randomized
                        regex = "(." + sanitizedOrigins.get(sanitizedOriginsIndex) + "?)";
                        addText("\nObfuscating origin " + sanitizedOrigins.get(sanitizedOriginsIndex) + "  using random method");
                        do {
                            randomIndex = rnd.nextInt(originArray.size());
                            if (spentOriginsIndexes.contains(randomIndex)) addText("\nOrigin index " + randomIndex + " is already in use, rerolling");
                        } while (spentOriginsIndexes.contains(randomIndex));
                        
                        addText("\nFound proper index " + randomIndex);
                        addText("\nReplacing sanitized origin " + sanitizedOrigins.get(sanitizedOriginsIndex) + " with " + originArray.get(randomIndex));
                        
                        rebuiltVmf = rebuiltVmf.replaceAll(regex, "\"" + originArray.get(randomIndex));
                        
                        doubleQuoteRegex = "(. \"\"" + originArray.get(randomIndex) + "?)";
                        rebuiltVmf = rebuiltVmf.replaceAll(doubleQuoteRegex, "\" \"" + originArray.get(randomIndex));
                        spentOriginsIndexes.add(randomIndex);
                        addText("\nAdded " + randomIndex + " to spent origin list\n");
                        sanitizedOriginsIndex++;
                        break;
                    case 3: //Randomize w/ Overlap
                        addText("\nObfuscating origin " + origin + " using random w/ overlap method");
                        randomIndex = rnd.nextInt(originArray.size());
                        addText(", random index " + randomIndex);
                        rebuiltVmf = rebuiltVmf.replaceAll(regex, "\"" + originArray.get(randomIndex));
                        
                        doubleQuoteRegex = "(. \"\"" + originArray.get(randomIndex) + "?)";
                        rebuiltVmf = rebuiltVmf.replaceAll(doubleQuoteRegex, "\" \"" + originArray.get(randomIndex));
                        break;
                }
            }  
            addText("\nFinished obfuscating origins\n");
        } else {
            addText("\nSkipping origin obfuscation");
        }

        addText("\n\nStored targetnames:\n");
        for (String targetname : targetnameArray) {
            addText(targetname + "\n");
        }
        
        if (targetnameObfuscationKind == 1) { //Exchange targetnames
            //Sanitize targetnames to ensure that there's no repetition unless
            //stated so, for example, when two entities share the same 
            //targetname 
            addText("\nTargetname exchange method: sanitizing targetnames\n");
            for (String targetname : targetnameArray) {
                regex = " \"" + targetname;
                RandomString randomString = new RandomString();
                
                do {
                    sanitizedString = randomString.getRandomString(1, 20);
                    if (sanitizedOrigins.contains(sanitizedString)) addText("\nSanitized origin " + sanitizedString + " is already in use, rerolling");
                } while (sanitizedOrigins.contains(sanitizedString));
                
                addText("Targetname " + targetname + " sanitized with string " + sanitizedString + "\n");
                rebuiltVmf = rebuiltVmf.replaceAll(regex, " \"" + sanitizedString);
                sanitizedTargetnames.add(sanitizedString);
            }
        }
        
        int targetnameArrayIndex = 0;
        if (targetnameObfuscationKind != 0) {
            for (String targetname : targetnameArray) {
                switch (targetnameObfuscationKind) {
                    case 1: //Exchange
                        regex = sanitizedTargetnames.get(targetnameArrayIndex);
                        
                        addText("\nObfuscating targetname " + sanitizedTargetnames.get(targetnameArrayIndex) + " using exchange method");
                        do {
                            randomIndex = rnd.nextInt(targetnameArray.size());
                            if (spentTargetnameIndexes.contains(randomIndex)) addVerbose("\nOrigin index " + randomIndex + " is already in use, rerolling");
                        } while (spentTargetnameIndexes.contains(randomIndex));
                        
                        addText("\nFound proper index " + randomIndex);
                        addText("\nReplacing sanitized targetname " + sanitizedTargetnames.get(targetnameArrayIndex) + " with " + targetnameArray.get(randomIndex));
                        
                        rebuiltVmf = rebuiltVmf.replaceAll(regex, targetnameArray.get(randomIndex));
                        
                        //doubleQuoteRegex = "(. \"\"" + targetnameArray.get(randomIndex) + "?)";
                        //rebuiltVmf = rebuiltVmf.replaceAll(doubleQuoteRegex, "\" \"" + targetnameArray.get(randomIndex));
                        
                        spentTargetnameIndexes.add(randomIndex);
                        addText("\nAdded " + randomIndex + " to spent targetname list\n");
                        targetnameArrayIndex++;
                        break;  
                    case 2: //Randomize
                        break;
                }
            }  
            addText("\nFinished obfuscating targetnames\n");
        } else {
            addText("\nSkipping targetname obfuscation");
        }
        
        /*
        addText("\nTargetnames:\n");
        for (String targetname : targetnameArray) {
            addText(targetname + "\n");
            String regex = "(." + targetname + "?)";
            rebuiltVmf = rebuiltVmf.replaceAll(regex, "\"" + targetnameArray.getFirst());
        }
        */
        
        addText("\nObfuscated VMF! file is ready to save");
        setFileObfuscated(true);
        SoundPlayer.playSound(XMLManager.getStringValue("obfuscateFileSnd"), SoundPlayer.SoundType.SND_OBFUSCATE);
        if (XMLManager.getBooleanValue("autosave")) {saveFile();}
    }
    
    private void menuOption_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_openActionPerformed
        openFile();
    }//GEN-LAST:event_menuOption_openActionPerformed

    private void saveFile() {
        String filePathNoExtension = filePath.split(".vmf")[0];
        String fileNameNoExtension = fileName.split(".vmf")[0];
        File fileToWrite = new File(filePathNoExtension + "_obf.vmf");
        
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));
            
            bufferedWriter.write(rebuiltVmf);
            bufferedWriter.close();
            SoundPlayer.playSound(XMLManager.getStringValue("saveFileSnd"), SoundPlayer.SoundType.SND_SAVE);
            JCheckBox checkbox = new JCheckBox("Do not show save messages ever again");
            Object[] params = {"Success!\nFor your safety, the input VMF has not been overridden. \nA copy of the VMF has been saved with the name of " + fileNameNoExtension + "_obf.vmf\n", "\n", checkbox};
            
            JOptionPane.showMessageDialog(this, params, "Uboa - Success", 1);
            if (checkbox.isSelected()) {
                System.out.println("Never show again");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void menuOption_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_saveActionPerformed
        saveFile();
    }//GEN-LAST:event_menuOption_saveActionPerformed
    
    private void menuOption_preferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_preferencesActionPerformed
        configWindow = new ConfigWindow(this, false);
        configWindow.show();
    }//GEN-LAST:event_menuOption_preferencesActionPerformed

    private void saveAs() {
        BufferedWriter bufferedWriter;
        
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Valve Map File (.vmf)", "vmf");
        fileChooser.setDefaultFileName(fileName);
        fileChooser.setCurrentDirectory(filePath);
        if (fileChooser.showSaveDialog(this)) {
            try {
                File fileToWrite = new File(fileChooser.getSelectedFile().getAbsolutePath());
                
                if (fileToWrite.getName().contains(".vmf")) {
                    bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));     
                } else {
                    bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite + ".vmf"));
                }
                
                bufferedWriter.write(rebuiltVmf);
                bufferedWriter.close();
                
                SoundPlayer.playSound(XMLManager.getStringValue("saveFileSnd"), SoundPlayer.SoundType.SND_SAVE);

            JCheckBox checkbox = new JCheckBox("Do not show save messages ever again");
            Object[] params = {"Success!", "\n", checkbox};
            
            JOptionPane.showMessageDialog(this, params, "Uboa - Success", 1);
            if (checkbox.isSelected()) {
                System.out.println("Never show again");
            }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }
    
    private void menuOption_saveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_saveAsActionPerformed
        saveAs();
    }//GEN-LAST:event_menuOption_saveAsActionPerformed

    private void clearProgram() {
        setFileLoaded(false);
        
        setTitle("Uboa VMF Obfuscator");
                
        iNumEnts = 0;
        originArray.clear();
        targetnameArray.clear();
        filePath = "";
        fileName = "";
        vmfContent = "";
        rebuiltVmf = "";
         
        selectedFile = null;
        
        textArea.setText("Welcome to Uboa VMF Obfuscator. Load a VMF file to begin.");
        lb_info.setText("Hover over objects to see relevant information");
        lb_filelength.setText("length: 0 | lines: 0");
    }
    
    private void menuOption_closeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_closeFileActionPerformed
        clearProgram();
    }//GEN-LAST:event_menuOption_closeFileActionPerformed

    private void menuOption_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuOption_exitActionPerformed

    private void rb_ePosObfuscation_noneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_noneMouseEntered
        lb_info.setText("Position of logic entities will remain the same");
    }//GEN-LAST:event_rb_ePosObfuscation_noneMouseEntered

    private void rb_ePosObfuscation_overlapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_overlapMouseEntered
        lb_info.setText("All logic entities will be moved to the best suitable coordinates");
    }//GEN-LAST:event_rb_ePosObfuscation_overlapMouseEntered

    private void rb_ePosObfuscation_randomizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeMouseEntered
        lb_info.setText("Logic entities will exchange positions in a random manner, ensuring no overlap");
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeMouseEntered

    private void rb_ePosObfuscation_randomizeOverlapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeOverlapMouseEntered
        lb_info.setText("Position of logic entities will be exchanged, allowing overlap");
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeOverlapMouseEntered

    private void rb_eNameObfuscation_noneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_noneMouseEntered
        lb_info.setText("Targetname of logic and point entities will remain the same");
    }//GEN-LAST:event_rb_eNameObfuscation_noneMouseEntered

    private void rb_eNameObfuscation_exchangeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_exchangeMouseEntered
        lb_info.setText("Logic and point entities will exchange targetnames with each other");
    }//GEN-LAST:event_rb_eNameObfuscation_exchangeMouseEntered

    private void rb_eNameObfuscation_randomizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_randomizeMouseEntered
        lb_info.setText("Logic and point entities will have their targetname randomized");
    }//GEN-LAST:event_rb_eNameObfuscation_randomizeMouseEntered

    private void menuOption_openManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_openManualActionPerformed
        try {
            //the manual pdf must be read into an input stream of bytes, then a temp file
            //is rebuilt using the stream and opened by the OS
            
            //as for the path of the temp file, it must be the same folder as the jar file
            InputStream inputStream = MainWindow.class.getResourceAsStream("/pdf/manual.pdf");
            File file = new File("manual.pdf");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            Desktop.getDesktop().open(file);
        } catch (NoSuchFileException ex) {
            JCheckBox checkbox = new JCheckBox("Do not show exception messages ever again");
            Object[] params = {"Ah fiddlesticks, what now?!","Got NoSuchFileException", "\n", checkbox};
            
            JOptionPane.showMessageDialog(this, params, "Uboa - Exception", 1);
            if (checkbox.isSelected()) {
                System.out.println("Never show again");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }//GEN-LAST:event_menuOption_openManualActionPerformed

    private void rb_eNameObfuscation_randomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_randomizeActionPerformed
        setRandomEntNameParametersEditable(true);
        XMLManager.setStringValue("entityNameObfuscation", "Random");
        targetnameObfuscationKind = 2;
    }//GEN-LAST:event_rb_eNameObfuscation_randomizeActionPerformed

    private void rb_eNameObfuscation_exchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_exchangeActionPerformed
        setRandomEntNameParametersEditable(false);
        XMLManager.setStringValue("entityNameObfuscation", "Exchange");
        targetnameObfuscationKind = 1;
    }//GEN-LAST:event_rb_eNameObfuscation_exchangeActionPerformed

    private void rb_eNameObfuscation_noneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_noneActionPerformed
        setRandomEntNameParametersEditable(false);
        XMLManager.setStringValue("entityNameObfuscation", "None");
        targetnameObfuscationKind = 0;
    }//GEN-LAST:event_rb_eNameObfuscation_noneActionPerformed

    private void menuOption_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_aboutActionPerformed
        aboutDialogue = new AboutDialogue(this, false);
        aboutDialogue.show();
    }//GEN-LAST:event_menuOption_aboutActionPerformed

    private void checkbox_autosaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkbox_autosaveMouseEntered
        lb_info.setText("Automatically save on finish obfuscation. Copy will be saved with the _obf suffix");
    }//GEN-LAST:event_checkbox_autosaveMouseEntered

    private void checkbox_autoobfuscateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkbox_autoobfuscateMouseEntered
        lb_info.setText("Automatically obfuscate on finish analysis");
    }//GEN-LAST:event_checkbox_autoobfuscateMouseEntered

    private void bt_obfuscateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_obfuscateActionPerformed
        obfuscateVMF();
    }//GEN-LAST:event_bt_obfuscateActionPerformed

    private void checkbox_autoobfuscateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_autoobfuscateActionPerformed
        XMLManager.setBooleanValue("autoObfuscate", checkbox_autoobfuscate.isSelected());
    }//GEN-LAST:event_checkbox_autoobfuscateActionPerformed

    private void checkbox_autosaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_autosaveActionPerformed
        XMLManager.setBooleanValue("autosave", checkbox_autosave.isSelected());
    }//GEN-LAST:event_checkbox_autosaveActionPerformed

    private void rb_ePosObfuscation_noneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_noneActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "None");
        originObfuscationKind = 0;
    }//GEN-LAST:event_rb_ePosObfuscation_noneActionPerformed

    private void rb_ePosObfuscation_overlapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_overlapActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "Overlap");
        originObfuscationKind = 1;
    }//GEN-LAST:event_rb_ePosObfuscation_overlapActionPerformed

    private void rb_ePosObfuscation_randomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "Random");
        originObfuscationKind = 2;
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeActionPerformed

    private void rb_ePosObfuscation_randomizeOverlapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeOverlapActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "RandomOverlap");
        originObfuscationKind = 3;
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeOverlapActionPerformed

    private void comboBox_rndEntNameChoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_rndEntNameChoicesActionPerformed
        XMLManager.setIntegerValue("entityNameObfuscationKind", comboBox_rndEntNameChoices.getSelectedIndex());
    }//GEN-LAST:event_comboBox_rndEntNameChoicesActionPerformed

    private void comboBox_rndEntNameLengthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboBox_rndEntNameLengthStateChanged
        XMLManager.setIntegerValue("entityNameObfuscationLength", (int)comboBox_rndEntNameLength.getValue());
    }//GEN-LAST:event_comboBox_rndEntNameLengthStateChanged

    private void checkbox_verboseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_verboseActionPerformed
        XMLManager.setBooleanValue("verbose", checkbox_verbose.isSelected());
    }//GEN-LAST:event_checkbox_verboseActionPerformed

    private void bt_randomStringTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_randomStringTestActionPerformed
        RandomString stringGenerator = new RandomString();
        addText("\n" 
                + stringGenerator.getRandomString(comboBox_rndEntNameChoices.getSelectedIndex(), (int)comboBox_rndEntNameLength.getValue())
                + " (" + comboBox_rndEntNameChoices.getSelectedItem().toString() + ", " + comboBox_rndEntNameLength.getValue().toString() + " entropy)");
    }//GEN-LAST:event_bt_randomStringTestActionPerformed

    private void checkbox_verboseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkbox_verboseMouseEntered
        lb_info.setText("Additional console messages during analysis and obfuscation");
    }//GEN-LAST:event_checkbox_verboseMouseEntered

    private void bt_randomStringTestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_randomStringTestMouseEntered
        lb_info.setText("Test entity name randomization");
    }//GEN-LAST:event_bt_randomStringTestMouseEntered

    private void bt_exportLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exportLogActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Plain Text File", "txt");

        if (fileChooser.showSaveDialog(this)) {
            File file = fileChooser.getSelectedFile();
            
            String consoleContent = textArea.getText();
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file.getAbsolutePath() + ".txt"));
                bw.write(consoleContent);
            } catch (IOException ioe) {
                System.out.println("IO Exception");
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException ioe) {
                        System.out.println("IO Exception");
                    }
                }
            }
        }
    }//GEN-LAST:event_bt_exportLogActionPerformed

    private void bt_copyClipboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_copyClipboardActionPerformed
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData = new StringSelection(textArea.getText());
        c.setContents(testData, testData);
    }//GEN-LAST:event_bt_copyClipboardActionPerformed

    private void bt_clearConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_clearConsoleActionPerformed
        textArea.setText("");
    }//GEN-LAST:event_bt_clearConsoleActionPerformed

    private void bt_clearConsoleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_clearConsoleMouseEntered
        lb_info.setText("Clear the console");
    }//GEN-LAST:event_bt_clearConsoleMouseEntered

    private void bt_copyClipboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_copyClipboardMouseEntered
        lb_info.setText("Copy the entire console output to the clipboard");
    }//GEN-LAST:event_bt_copyClipboardMouseEntered

    private void bt_exportLogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_exportLogMouseEntered
        lb_info.setText("Export the console output as a text file");
    }//GEN-LAST:event_bt_exportLogMouseEntered


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
    private javax.swing.JButton bt_clearConsole;
    private javax.swing.JButton bt_copyClipboard;
    private javax.swing.JButton bt_exportLog;
    private javax.swing.JButton bt_obfuscate;
    private javax.swing.JButton bt_randomStringTest;
    private javax.swing.JCheckBox checkbox_autoobfuscate;
    private javax.swing.JCheckBox checkbox_autosave;
    private javax.swing.JCheckBox checkbox_verbose;
    private javax.swing.JComboBox<String> comboBox_rndEntNameChoices;
    private javax.swing.JLabel comboBox_rndEntNameLabel;
    private javax.swing.JSpinner comboBox_rndEntNameLength;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lb_charset;
    private javax.swing.JLabel lb_filelength;
    private javax.swing.JLabel lb_info;
    private javax.swing.JMenuItem menuOption_about;
    private javax.swing.JMenuItem menuOption_closeFile;
    private javax.swing.JMenuItem menuOption_exit;
    private javax.swing.JMenuItem menuOption_open;
    private javax.swing.JMenuItem menuOption_openManual;
    private javax.swing.JMenuItem menuOption_preferences;
    private javax.swing.JMenuItem menuOption_save;
    private javax.swing.JMenuItem menuOption_saveAs;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenu menu_help;
    private javax.swing.JRadioButton rb_eNameObfuscation_exchange;
    private javax.swing.JRadioButton rb_eNameObfuscation_none;
    private javax.swing.JRadioButton rb_eNameObfuscation_randomize;
    private javax.swing.JRadioButton rb_ePosObfuscation_none;
    private javax.swing.JRadioButton rb_ePosObfuscation_overlap;
    private javax.swing.JRadioButton rb_ePosObfuscation_randomize;
    private javax.swing.JRadioButton rb_ePosObfuscation_randomizeOverlap;
    private javax.swing.ButtonGroup rg_eNameObfuscation;
    private javax.swing.ButtonGroup rg_ePosObfuscation;
    private javax.swing.JTextArea textArea;
    private javax.swing.JScrollPane textAreaScrollPane;
    // End of variables declaration//GEN-END:variables
}
