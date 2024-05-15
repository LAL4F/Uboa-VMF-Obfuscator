/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views2;

import config.XMLManager;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import jnafilechooser.api.JnaFileChooser;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.filechooser.FileSystemView;
import utils.EntityDictionary;
import utils.RandomString;
import utils.SoundPlayer;
import dialogs.ProgressDialogue;
import dialogs.SetHammerPathDialogue;

public class ObfuscatePanel extends javax.swing.JPanel {
    private final ImageIcon APPIMAGE = new ImageIcon(views2.MainWindow.class.getResource("/images/appicon.png"));
    
    //The MainWindow which acts as a parent to this panel
    private MainWindow parent;
    
    private final String CHARSET = "ISO-8859-1";
    private String filePath, fileName, vmfContent, rebuiltVmf;
    private int iNumEnts;
    private File selectedFile;
    
    //Stored entity origins and targetnames
    private ArrayList<String> originArray = new ArrayList<>();
    private ArrayList<String> targetnameArray = new ArrayList<>();
    
    //Obfuscation methods
    private int originObfuscationKind;
    private int targetnameObfuscationKind;
    
    //The VMF that is read from a file, a list of strings
    List<String> readVmfList;
    
    //Swing classes
    private ProgressDialogue openFileProgressDialogue;
    
    public ObfuscatePanel(MainWindow parent) {
        initComponents();
        
        this.parent = parent;
        openFileProgressDialogue = new ProgressDialogue(parent, false);
        setRandomEntNameParametersEditable(false);

        originObfuscationKind = 0;
        targetnameObfuscationKind = 0;
        
        setupRadioButtons();
        loadConfig();
        
        textArea.setText("Welcome to Uboa VMF Obfuscator. Ensure proper input and output parameters and click the big obfuscate button.");
        lb_info.setText("Hover over objects to see relevant information");
        lb_charset.setText(CHARSET);
    }

private void loadConfig() {
        setPreferredSize(new java.awt.Dimension(XMLManager.getIntegerValue("width"), 
                XMLManager.getIntegerValue("height"))
        );
        
        combo_input.setSelectedIndex(XMLManager.getIntegerValue("inputType"));
        combo_output.setSelectedIndex(XMLManager.getIntegerValue("outputType"));
        tf_input.setText(XMLManager.getStringValue("inputPath"));
        
        if (combo_output.getSelectedIndex() == 0) {
            tf_output.setText(XMLManager.getStringValue("outputSubfolder"));
            bt_browseOutput.setEnabled(false);
            bt_gotoOutput.setEnabled(false);
        } else {
            bt_browseOutput.setEnabled(true);
            bt_gotoOutput.setEnabled(true);
            if (XMLManager.getStringValue("outputWorkfolder").equals("$DOCUMENTS")) {
                tf_output.setText(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
            } else {
                tf_output.setText(XMLManager.getStringValue("outputWorkfolder"));
            }
        }
        
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
        
        checkbox_verbose.setSelected(XMLManager.getBooleanValue("verbose"));
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
        textAreaScrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        rb_ePosObfuscation_none = new javax.swing.JRadioButton();
        rb_ePosObfuscation_overlap = new javax.swing.JRadioButton();
        rb_ePosObfuscation_randomize = new javax.swing.JRadioButton();
        rb_ePosObfuscation_randomizeOverlap = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
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
        checkbox_verbose = new javax.swing.JCheckBox();
        bt_clearConsole = new javax.swing.JButton();
        bt_exportLog = new javax.swing.JButton();
        bt_copyClipboard = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        combo_input = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        combo_output = new javax.swing.JComboBox<>();
        tf_input = new javax.swing.JTextField();
        tf_output = new javax.swing.JTextField();
        bt_browseInput = new javax.swing.JButton();
        bt_browseOutput = new javax.swing.JButton();
        bt_gotoOutput = new javax.swing.JButton();
        bt_gotoInput = new javax.swing.JButton();
        bt_cancelObfuscation = new javax.swing.JButton();
        combo_obfuscatedVmfList = new javax.swing.JComboBox<>();
        bt_gotoObfuscatedVmf = new javax.swing.JButton();
        bt_openObfuscatedVmf = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        textAreaScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Console"));

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setTabSize(4);
        textAreaScrollPane.setViewportView(textArea);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Entity Position Obfuscation"));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_ePosObfuscation_none)
                    .addComponent(rb_ePosObfuscation_overlap)
                    .addComponent(rb_ePosObfuscation_randomize)
                    .addComponent(rb_ePosObfuscation_randomizeOverlap))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Entity Name Obfuscation"));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
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
                .addComponent(bt_randomStringTest)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
        bt_obfuscate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_obfuscateActionPerformed(evt);
            }
        });

        lb_filelength.setText("length: 0 | lines: 0");
        lb_filelength.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_charset.setText("Charset");
        lb_charset.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Input / Output"));

        combo_input.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "File", "Folder" }));
        combo_input.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                combo_inputMouseEntered(evt);
            }
        });
        combo_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_inputActionPerformed(evt);
            }
        });

        jLabel1.setText("Input");

        jLabel2.setText("Output");

        combo_output.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subfolder (of VMF input)", "Work folder" }));
        combo_output.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_output.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                combo_outputMouseEntered(evt);
            }
        });
        combo_output.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_outputActionPerformed(evt);
            }
        });

        tf_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tf_inputMouseEntered(evt);
            }
        });
        tf_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_inputKeyReleased(evt);
            }
        });

        tf_output.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tf_outputMouseEntered(evt);
            }
        });
        tf_output.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_outputKeyReleased(evt);
            }
        });

        bt_browseInput.setText("Browse...");
        bt_browseInput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_browseInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_browseInputActionPerformed(evt);
            }
        });

        bt_browseOutput.setText("Browse...");
        bt_browseOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_browseOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_browseOutputActionPerformed(evt);
            }
        });

        bt_gotoOutput.setText("Goto");
        bt_gotoOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_gotoOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_gotoOutputActionPerformed(evt);
            }
        });

        bt_gotoInput.setText("Goto");
        bt_gotoInput.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_gotoInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_gotoInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo_input, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo_output, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_input)
                    .addComponent(tf_output))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_browseInput, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(bt_browseOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_gotoInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_gotoOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tf_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_browseInput)
                    .addComponent(bt_gotoInput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combo_output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_browseOutput)
                    .addComponent(bt_gotoOutput))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bt_cancelObfuscation.setText("Cancel obfuscation");
        bt_cancelObfuscation.setEnabled(false);

        combo_obfuscatedVmfList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Output VMF files will go here" }));
        combo_obfuscatedVmfList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_obfuscatedVmfList.setEnabled(false);

        bt_gotoObfuscatedVmf.setText("Goto");
        bt_gotoObfuscatedVmf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_gotoObfuscatedVmf.setEnabled(false);
        bt_gotoObfuscatedVmf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_gotoObfuscatedVmfActionPerformed(evt);
            }
        });

        bt_openObfuscatedVmf.setText("Open in Editor");
        bt_openObfuscatedVmf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_openObfuscatedVmf.setEnabled(false);
        bt_openObfuscatedVmf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_openObfuscatedVmfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textAreaScrollPane)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bt_copyClipboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_exportLog, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkbox_verbose, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_clearConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lb_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_filelength, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_charset, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_obfuscate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_cancelObfuscation)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(combo_obfuscatedVmfList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_openObfuscatedVmf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_gotoObfuscatedVmf, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_obfuscate, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(bt_cancelObfuscation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_copyClipboard, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_clearConsole)
                        .addComponent(bt_exportLog)
                        .addComponent(checkbox_verbose)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_obfuscatedVmfList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_gotoObfuscatedVmf)
                    .addComponent(bt_openObfuscatedVmf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_info)
                    .addComponent(lb_filelength)
                    .addComponent(lb_charset)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void openFile() {
        clearProgram();
        
        if (combo_input.getSelectedIndex() == 1) {
            //Batch obfuscation
        }
        
        new Thread(new Runnable() {
            public void run() {
                openFileProgressDialogue.show();
            }
        }).start();

        selectedFile = new File(tf_input.getText());

        filePath = tf_input.getText();
        fileName = selectedFile.getName();

        //setTitle("Loading, please wait... - Uboa VMF Obfuscator");

        try {
            asyncReadVMF();
        } catch (ExecutionException ex) {
            Logger.getLogger(views2.MainWindow.class.getName()).log(Level.SEVERE, null, ex);
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
                        
                        //setTitle(fileName + " - " + fileSizePretty + " MB - Uboa VMF Obfuscator");
                        
                        addText("\n\nFinished reading " + fileName + "\n\nTime to read file: " + String.format("%.2f", (double)(System.nanoTime() - startTime) / 1_000_000_000. ) + " seconds");
                        addText("\nNumber of entities: " + iNumEnts);
                        openFileProgressDialogue.setVisible(false);
                        openFileProgressDialogue.dispose();
                        lb_filelength.setText("length: " + String.format("%,d", vmfContent.length()) + " | lines: " + totalLinesPretty);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    obfuscateVMF();
                }
            });
            vmfReaderThread.start();
        } catch (Exception e) {
            Logger.getLogger(views2.MainWindow.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
    }
    
    private void obfuscateVMF() {
        rebuiltVmf = vmfContent;
        ArrayList<Integer> spentOriginsIndexes = new ArrayList<>();
        ArrayList<String> sanitizedOrigins = new ArrayList<>();
        
        ArrayList<Integer> spentTargetnameIndexes = new ArrayList<>();
        ArrayList<String> sanitizedTargetnames = new ArrayList<>();
        
        ArrayList<String> spentRandomStrings = new ArrayList<>();
        
        Random rnd =  new Random();
        int randomIndex = 0;
        String sanitizedString = "";
        String regex = "";
        String doubleQuoteRegex = ""; //Workaround because this thing likes to add unnecessary quotes
        String randomStringResult = "";
        
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
        
        if (targetnameObfuscationKind != 0) { //Exchange and randomize targetnames
            //Sanitize targetnames to ensure that there's no repetition unless
            //stated so, for example, when two entities share the same 
            //targetname 
            addText("\nUsing targetname exchange or randomization method: sanitizing targetnames\n");
            for (String targetname : targetnameArray) {
                regex = " \"" + targetname;
                RandomString randomString = new RandomString();
                
                do {
                    sanitizedString = randomString.getRandomString(1, 20);
                    if (sanitizedTargetnames.contains(sanitizedString)) addText("\nSanitized targetname " + sanitizedString + " is already in use, rerolling");
                } while (sanitizedTargetnames.contains(sanitizedString));
                
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
                        
                        spentTargetnameIndexes.add(randomIndex);
                        addText("\nAdded " + randomIndex + " to spent targetname list\n");
                        targetnameArrayIndex++;
                        break;  
                    case 2: //Randomize
                        regex = sanitizedTargetnames.get(targetnameArrayIndex);
                        
                        addText("\nObfuscating targetname " + sanitizedTargetnames.get(targetnameArrayIndex) + " using random string method");
                        
                        do {
                            RandomString randomString = new RandomString();
                            randomStringResult = randomString.getRandomString(comboBox_rndEntNameChoices.getSelectedIndex(), (int)comboBox_rndEntNameLength.getValue());  
                            if (spentRandomStrings.contains(randomStringResult)) addVerbose("\nRandom targetname " + randomStringResult + " is already in use, rerolling (this is super rare)");
                        } while (spentRandomStrings.contains(randomStringResult));

                        addText("\nReplacing sanitized targetname " + sanitizedTargetnames.get(targetnameArrayIndex) + " with " + randomStringResult + "\n");
                        
                        rebuiltVmf = rebuiltVmf.replaceAll(regex, randomStringResult);
                        
                        spentTargetnameIndexes.add(randomIndex);
                        targetnameArrayIndex++;
                        break;
                }
            }  
            addText("\nFinished obfuscating targetnames\n");
        } else {
            addText("\nSkipping targetname obfuscation");
        }
        
        addText("\nObfuscated VMF! file is ready to go");
        
        bt_obfuscate.setEnabled(true);
        bt_cancelObfuscation.setEnabled(false);
        
        combo_obfuscatedVmfList.setEnabled(true);
        bt_openObfuscatedVmf.setEnabled(true);
        bt_gotoObfuscatedVmf.setEnabled(true);
        
        saveFile();
    }
    
    private void saveFile() {
        String filePathNoExtension = filePath.split(".vmf")[0];
        File fileToWrite = new File(filePathNoExtension + "_obf.vmf");
        
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));
            
            bufferedWriter.write(rebuiltVmf);
            bufferedWriter.close();
            SoundPlayer.playSound(XMLManager.getStringValue("saveFileSnd"), SoundPlayer.SoundType.SND_SAVE);
            addText("\nSaved file to " + fileToWrite);
        } catch (IOException ex) {
            Logger.getLogger(views2.MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addVMFtoComboBox();
    }  
    
    private void addVMFtoComboBox() {
        String filePathNoExtension = filePath.split(".vmf")[0];
        String fileToWrite = filePathNoExtension + "_obf.vmf";
        
        DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel<>();
        comboBox.addElement(fileToWrite);
        combo_obfuscatedVmfList.setModel(comboBox);
    }
    
    private void clearProgram() {
            //setTitle("Uboa VMF Obfuscator");

            iNumEnts = 0;
            originArray.clear();
            targetnameArray.clear();
            filePath = "";
            fileName = "";
            vmfContent = "";
            rebuiltVmf = "";

            selectedFile = null;

            textArea.setText("Welcome to Uboa VMF Obfuscator. Ensure proper input and output parameters and click the big obfuscate button.");
            lb_info.setText("Hover over objects to see relevant information");
            lb_filelength.setText("length: 0 | lines: 0");
    }

    private void rb_ePosObfuscation_noneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_noneMouseEntered
        lb_info.setText("Position of logic entities will remain the same");
    }//GEN-LAST:event_rb_ePosObfuscation_noneMouseEntered

    private void rb_ePosObfuscation_noneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_noneActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "None");
        originObfuscationKind = 0;
    }//GEN-LAST:event_rb_ePosObfuscation_noneActionPerformed

    private void rb_ePosObfuscation_overlapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_overlapMouseEntered
        lb_info.setText("All logic entities will be moved to the best suitable coordinates");
    }//GEN-LAST:event_rb_ePosObfuscation_overlapMouseEntered

    private void rb_ePosObfuscation_overlapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_overlapActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "Overlap");
        originObfuscationKind = 1;
    }//GEN-LAST:event_rb_ePosObfuscation_overlapActionPerformed

    private void rb_ePosObfuscation_randomizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeMouseEntered
        lb_info.setText("Logic entities will exchange positions in a random manner, ensuring no overlap");
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeMouseEntered

    private void rb_ePosObfuscation_randomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "Random");
        originObfuscationKind = 2;
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeActionPerformed

    private void rb_ePosObfuscation_randomizeOverlapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeOverlapMouseEntered
        lb_info.setText("Position of logic entities will be exchanged, allowing overlap");
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeOverlapMouseEntered

    private void rb_ePosObfuscation_randomizeOverlapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_randomizeOverlapActionPerformed
        XMLManager.setStringValue("entityPosObfuscation", "RandomOverlap");
        originObfuscationKind = 3;
    }//GEN-LAST:event_rb_ePosObfuscation_randomizeOverlapActionPerformed

    private void rb_eNameObfuscation_noneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_noneMouseEntered
        lb_info.setText("Targetname of logic and point entities will remain the same");
    }//GEN-LAST:event_rb_eNameObfuscation_noneMouseEntered

    private void rb_eNameObfuscation_noneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_noneActionPerformed
        setRandomEntNameParametersEditable(false);
        XMLManager.setStringValue("entityNameObfuscation", "None");
        targetnameObfuscationKind = 0;
    }//GEN-LAST:event_rb_eNameObfuscation_noneActionPerformed

    private void rb_eNameObfuscation_exchangeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_exchangeMouseEntered
        lb_info.setText("Logic and point entities will exchange targetnames with each other");
    }//GEN-LAST:event_rb_eNameObfuscation_exchangeMouseEntered

    private void rb_eNameObfuscation_exchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_exchangeActionPerformed
        setRandomEntNameParametersEditable(false);
        XMLManager.setStringValue("entityNameObfuscation", "Exchange");
        targetnameObfuscationKind = 1;
    }//GEN-LAST:event_rb_eNameObfuscation_exchangeActionPerformed

    private void rb_eNameObfuscation_randomizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_randomizeMouseEntered
        lb_info.setText("Logic and point entities will have their targetname randomized");
    }//GEN-LAST:event_rb_eNameObfuscation_randomizeMouseEntered

    private void rb_eNameObfuscation_randomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_randomizeActionPerformed
        setRandomEntNameParametersEditable(true);
        XMLManager.setStringValue("entityNameObfuscation", "Random");
        targetnameObfuscationKind = 2;
    }//GEN-LAST:event_rb_eNameObfuscation_randomizeActionPerformed

    private void comboBox_rndEntNameChoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_rndEntNameChoicesActionPerformed
        XMLManager.setIntegerValue("entityNameObfuscationKind", comboBox_rndEntNameChoices.getSelectedIndex());
    }//GEN-LAST:event_comboBox_rndEntNameChoicesActionPerformed

    private void comboBox_rndEntNameLengthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_comboBox_rndEntNameLengthStateChanged
        XMLManager.setIntegerValue("entityNameObfuscationLength", (int)comboBox_rndEntNameLength.getValue());
    }//GEN-LAST:event_comboBox_rndEntNameLengthStateChanged

    private void bt_randomStringTestMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_randomStringTestMouseEntered
        lb_info.setText("Test entity name randomization");
    }//GEN-LAST:event_bt_randomStringTestMouseEntered

    private void bt_randomStringTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_randomStringTestActionPerformed
        RandomString stringGenerator = new RandomString();
        addText("\n"
            + stringGenerator.getRandomString(comboBox_rndEntNameChoices.getSelectedIndex(), (int)comboBox_rndEntNameLength.getValue())
            + " (" + comboBox_rndEntNameChoices.getSelectedItem().toString() + ", " + comboBox_rndEntNameLength.getValue().toString() + " entropy)");
    }//GEN-LAST:event_bt_randomStringTestActionPerformed

    private void bt_obfuscateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_obfuscateActionPerformed
        bt_obfuscate.setEnabled(false);
        bt_cancelObfuscation.setEnabled(true);

        openFile();
    }//GEN-LAST:event_bt_obfuscateActionPerformed

    private void checkbox_verboseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkbox_verboseMouseEntered
        lb_info.setText("Additional console messages during analysis and obfuscation");
    }//GEN-LAST:event_checkbox_verboseMouseEntered

    private void checkbox_verboseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_verboseActionPerformed
        XMLManager.setBooleanValue("verbose", checkbox_verbose.isSelected());
    }//GEN-LAST:event_checkbox_verboseActionPerformed

    private void bt_clearConsoleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_clearConsoleMouseEntered
        lb_info.setText("Clear the console");
    }//GEN-LAST:event_bt_clearConsoleMouseEntered

    private void bt_clearConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_clearConsoleActionPerformed
        textArea.setText("");
    }//GEN-LAST:event_bt_clearConsoleActionPerformed

    private void bt_exportLogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_exportLogMouseEntered
        lb_info.setText("Export the console output as a text file");
    }//GEN-LAST:event_bt_exportLogMouseEntered

    private void bt_exportLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exportLogActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Plain Text File", "txt");

        if (fileChooser.showSaveDialog(parent)) {
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

    private void bt_copyClipboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_copyClipboardMouseEntered
        lb_info.setText("Copy the entire console output to the clipboard");
    }//GEN-LAST:event_bt_copyClipboardMouseEntered

    private void bt_copyClipboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_copyClipboardActionPerformed
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData = new StringSelection(textArea.getText());
        c.setContents(testData, testData);
    }//GEN-LAST:event_bt_copyClipboardActionPerformed

    private void combo_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_inputMouseEntered
        lb_info.setText("Path to the input VMF. For batch operations, select folder from the combo box");
    }//GEN-LAST:event_combo_inputMouseEntered

    private void combo_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_inputActionPerformed
        XMLManager.setIntegerValue("inputType", combo_input.getSelectedIndex());
    }//GEN-LAST:event_combo_inputActionPerformed

    private void combo_outputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_outputMouseEntered
        lb_info.setText("Where should the resulting VMF go after it is obfuscated");
    }//GEN-LAST:event_combo_outputMouseEntered

    private void combo_outputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_outputActionPerformed
        XMLManager.setIntegerValue("outputType", combo_output.getSelectedIndex());

        if (combo_output.getSelectedIndex() == 0) {
            tf_output.setText(XMLManager.getStringValue("outputSubfolder"));
            bt_browseOutput.setEnabled(false);
            bt_gotoOutput.setEnabled(false);
            return;
        }

        tf_output.setText(XMLManager.getStringValue("outputWorkfolder"));
        bt_browseOutput.setEnabled(true);
        bt_gotoOutput.setEnabled(true);

        if (XMLManager.getStringValue("outputWorkfolder").equals("$DOCUMENTS")) {
            tf_output.setText(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
            return;
        }
    }//GEN-LAST:event_combo_outputActionPerformed

    private void tf_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_inputMouseEntered
        lb_info.setText("Path to the input VMF. For batch operations, select folder from the combo box");
    }//GEN-LAST:event_tf_inputMouseEntered

    private void tf_inputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_inputKeyReleased
        XMLManager.setStringValue("inputPath", tf_input.getText());
    }//GEN-LAST:event_tf_inputKeyReleased

    private void tf_outputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_outputMouseEntered
        lb_info.setText("Where should the resulting VMF go after it is obfuscated");
    }//GEN-LAST:event_tf_outputMouseEntered

    private void tf_outputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_outputKeyReleased
        if (combo_output.getSelectedIndex() == 0) {
            XMLManager.setStringValue("outputSubfolder", tf_output.getText());
            return;
        }

        XMLManager.setStringValue("outputWorkfolder", tf_input.getText());
    }//GEN-LAST:event_tf_outputKeyReleased

    private void bt_browseInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_browseInputActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Valve Map File (.vmf)", "vmf", "vmx");
        fileChooser.setDefaultFileName("[Folder Selection]");

        if (fileChooser.showOpenDialog(parent)) {
            String regex = fileChooser.getSelectedFile().getName();

            if (regex.contains("[Folder Selection]")) {
                combo_input.setSelectedIndex(1);
                String sanitizedPath = fileChooser.getSelectedFile().getAbsolutePath().replace(regex, "");
                tf_input.setText(sanitizedPath);
                XMLManager.setStringValue("inputPath", fileChooser.getSelectedFile().getAbsolutePath());
                return;
            }
            
            if (regex.contains(".vm")) {
                combo_input.setSelectedIndex(0);
            }

            tf_input.setText(fileChooser.getSelectedFile().getAbsolutePath());
            XMLManager.setStringValue("inputPath", fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_bt_browseInputActionPerformed

    private void bt_browseOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_browseOutputActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.setDefaultFileName("[Folder Selection]");

        if (fileChooser.showOpenDialog(parent)) {
            String regex = fileChooser.getSelectedFile().getName();

            String sanitizedPath = fileChooser.getSelectedFile().getAbsolutePath().replace(regex, "");
            tf_output.setText(sanitizedPath);
            XMLManager.setStringValue("outputWorkfolder", sanitizedPath);
        }
    }//GEN-LAST:event_bt_browseOutputActionPerformed

    private void bt_gotoOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_gotoOutputActionPerformed
        try {
            Runtime.getRuntime().exec("explorer.exe " + tf_output.getText());
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_gotoOutputActionPerformed

    private void bt_gotoInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_gotoInputActionPerformed
        try {
            Runtime.getRuntime().exec("explorer.exe /select," + tf_input.getText());
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_gotoInputActionPerformed

    private void bt_gotoObfuscatedVmfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_gotoObfuscatedVmfActionPerformed
        try {
            Runtime.getRuntime().exec("explorer.exe /select, " + combo_obfuscatedVmfList.getSelectedItem().toString());
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_gotoObfuscatedVmfActionPerformed

    private void bt_openObfuscatedVmfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_openObfuscatedVmfActionPerformed
        SetHammerPathDialogue hammerSetupDialog = new SetHammerPathDialogue(parent, true, combo_obfuscatedVmfList.getSelectedItem().toString());

        if (XMLManager.getBooleanValue("preferFileTypeAssociation")) {
            try {
                System.out.println("\"" + combo_obfuscatedVmfList.getSelectedItem().toString() + "\"");
                Desktop.getDesktop().open(new File(combo_obfuscatedVmfList.getSelectedItem().toString()));
            } catch (IOException ex) {
                System.out.println("Hammer path not configured");
                hammerSetupDialog.show();
            }
            return;
        }

        if (XMLManager.getStringValue("hammerPath").equals("Path to executable (ex: hammerplusplus.exe)")) {
            hammerSetupDialog.show();
        }

        try {
            Runtime.getRuntime().exec(XMLManager.getStringValue("hammerPath") + " \"" + combo_obfuscatedVmfList.getSelectedItem().toString() + "\"");
        } catch (IOException ex) {
            System.out.println("Hammer path not configured");
            hammerSetupDialog.show();
        }
    }//GEN-LAST:event_bt_openObfuscatedVmfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_browseInput;
    private javax.swing.JButton bt_browseOutput;
    private javax.swing.JButton bt_cancelObfuscation;
    private javax.swing.JButton bt_clearConsole;
    private javax.swing.JButton bt_copyClipboard;
    private javax.swing.JButton bt_exportLog;
    private javax.swing.JButton bt_gotoInput;
    private javax.swing.JButton bt_gotoObfuscatedVmf;
    private javax.swing.JButton bt_gotoOutput;
    private javax.swing.JButton bt_obfuscate;
    private javax.swing.JButton bt_openObfuscatedVmf;
    private javax.swing.JButton bt_randomStringTest;
    private javax.swing.JCheckBox checkbox_verbose;
    private javax.swing.JComboBox<String> comboBox_rndEntNameChoices;
    private javax.swing.JLabel comboBox_rndEntNameLabel;
    private javax.swing.JSpinner comboBox_rndEntNameLength;
    private javax.swing.JComboBox<String> combo_input;
    private javax.swing.JComboBox<String> combo_obfuscatedVmfList;
    private javax.swing.JComboBox<String> combo_output;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb_charset;
    private javax.swing.JLabel lb_filelength;
    private javax.swing.JLabel lb_info;
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
    private javax.swing.JTextField tf_input;
    private javax.swing.JTextField tf_output;
    // End of variables declaration//GEN-END:variables
}
