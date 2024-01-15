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
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import jnafilechooser.api.JnaFileChooser;
import java.lang.String;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alejo
 */
public class MainWindow extends javax.swing.JFrame {
    private final ImageIcon ICON_OPENFILE = new ImageIcon("src/images/ico20_openFile.png");
    private final ImageIcon ICON_SAVE = new ImageIcon("src/images/ico20_saveFile.png");
    private final ImageIcon ICON_SAVEAS = new ImageIcon("src/images/ico20_saveFileAs.png");
    private final ImageIcon APPIMAGE = new ImageIcon("src/images/appicon.png");
    
    private String filePath, fileName, vmfContent;

    private int iNumEnts;
    
    private File selectedFile;
    
    private OpenFileProgressDialogue openFileProgressDialogue;
    private AboutDialogue aboutDialogue;
    
    private MainWindow mainWindow = this;
    
    public MainWindow() {
        initComponents();
        setIconImage(APPIMAGE.getImage());
        setLocationRelativeTo(null);
        
        openFileProgressDialogue = new OpenFileProgressDialogue(this, false);
        
        setupIcons();
        loadConfig();
        setupRadioButtons();
        
        setFileLoaded(false);
        setRandomEntNameParametersEditable(false);
        
        textArea.setText("Welcome to Uboa VMF Obfuscator. Load a VMF file to begin.");
        lb_info.setText("Hover over objects to see relevant information");

        
    }
    
    private void setupIcons() {
        menuOption_open.setIcon(ICON_OPENFILE);
        menuOption_save.setIcon(ICON_SAVE);
        menuOption_saveAs.setIcon(ICON_SAVEAS);
    }
    
    private void loadConfig() {
        menuOption_playSounds.setSelected(XMLManager.isSoundEnabled());
    }
    
    private void setupRadioButtons() {
        rb_ePosObfuscation_none.setSelected(true);
        rb_eNameObfuscation_none.setSelected(true);
        
        rg_ePosObfuscation.add(rb_ePosObfuscation_none);
        rg_ePosObfuscation.add(rb_ePosObfuscation_overlap);
        rg_ePosObfuscation.add(rb_ePosObfuscation_randomize);
        rg_ePosObfuscation.add(rb_ePosObfuscation_randomizeOverlap);
        
        rg_eNameObfuscation.add(rb_eNameObfuscation_none);
        rg_eNameObfuscation.add(rb_eNameObfuscation_exchange);
        rg_eNameObfuscation.add(rb_eNameObfuscation_randomize);
    }
    
    private void setFileLoaded(boolean state) {
        menuOption_closeFile.setEnabled(state);
        menuOption_save.setEnabled(state);
        menuOption_saveAs.setEnabled(state);

        bt_beginObfuscate.setEnabled(state);
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
        lb_info = new javax.swing.JLabel();
        bt_beginObfuscate = new javax.swing.JButton();
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
        menuOption_playSounds = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menu_help = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uboa VMF Obfuscator");
        setMinimumSize(new java.awt.Dimension(720, 480));
        setResizable(false);

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

        rb_ePosObfuscation_overlap.setText("Overlap");
        rb_ePosObfuscation_overlap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_ePosObfuscation_overlap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_ePosObfuscation_overlapMouseEntered(evt);
            }
        });

        rb_ePosObfuscation_randomize.setText("Randomize");
        rb_ePosObfuscation_randomize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_ePosObfuscation_randomize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_ePosObfuscation_randomizeMouseEntered(evt);
            }
        });

        rb_ePosObfuscation_randomizeOverlap.setText("Randomize w/ Overlap");
        rb_ePosObfuscation_randomizeOverlap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_ePosObfuscation_randomizeOverlap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rb_ePosObfuscation_randomizeOverlapMouseEntered(evt);
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

        comboBox_rndEntNameChoices.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Letters", "Numbers", "Letters and numbers", "Symbols only??", "ALL of the above" }));

        comboBox_rndEntNameLength.setModel(new javax.swing.SpinnerNumberModel(5, 5, 20, 1));

        comboBox_rndEntNameLabel.setText("Length");

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
                .addContainerGap(167, Short.MAX_VALUE))
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
                    .addComponent(comboBox_rndEntNameLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_info.setText("Hover over objects to see relevant information");
        lb_info.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bt_beginObfuscate.setText("Obfuscate");
        bt_beginObfuscate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        menuOption_playSounds.setSelected(true);
        menuOption_playSounds.setText("Play Sounds");
        menuOption_playSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_playSoundsActionPerformed(evt);
            }
        });
        jMenu3.add(menuOption_playSounds);

        jMenu1.setText("Languages");

        jMenuItem2.setText("English");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Spanish");
        jMenu1.add(jMenuItem3);

        jMenu3.add(jMenu1);

        jMenuBar1.add(jMenu3);

        menu_help.setText("Help");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("User Manual");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menu_help.add(jMenuItem1);

        jMenuItem6.setText("About...");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menu_help.add(jMenuItem6);

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
                        .addComponent(bt_beginObfuscate)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lb_info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_beginObfuscate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_info)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFile() {
        clearProgram();
        
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Valve Map File (.vmf)", "vmf");

        if (fileChooser.showOpenDialog(this)) {
            new Thread(new Runnable() {
                public void run() {
                    //openFileProgressDialogue = new OpenFileProgressDialogue(mainWindow, false);
                    openFileProgressDialogue.show();
                }
            }).start();
            
            selectedFile = fileChooser.getSelectedFile();

            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            fileName = selectedFile.getName();

            setTitle("Loading, please wait... - Uboa VMF Obfuscator");

            textArea.setText("Reading " + filePath + "\n...");

            try {
                asyncReadVMF();
            } catch (ExecutionException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }          
    }
    
    private void asyncReadVMF() throws ExecutionException {
        try {
            Thread vmfReaderThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        var bufferedReader = new BufferedReader(new FileReader(filePath));
                        long startTime = System.nanoTime();
                        String readLine = bufferedReader.readLine();
                        vmfContent = readLine + "\n";
                        openFileProgressDialogue.setFileText("Reading " + fileName);
                        
                        while (bufferedReader.ready()) {
                            readLine = bufferedReader.readLine();
                            if (readLine.contains("entity")) {
                                iNumEnts++;
                            }
                            
                            vmfContent += readLine + "\n";       
                            openFileProgressDialogue.setStatusText(readLine);
                        }
                        
                        bufferedReader.close();
                        long endTime = System.nanoTime() - startTime;
                        double timeToRead = endTime / 1_000_000_000.;
                        
                        String fileSizePretty = String.format("%.2f", Float.valueOf(selectedFile.length()) / 1024000);
                        
                        setTitle(fileName + " - " + fileSizePretty + " MB - Uboa VMF Obfuscator");
                        
                        textArea.append("\nFinished reading " + filePath + "\n\nTime to read file: " + String.format("%.2f", timeToRead) + " seconds");
                        textArea.append("\nNumber of entities: " + iNumEnts);
                        setFileLoaded(true);
                        openFileProgressDialogue.setVisible(false);
                        if (menuOption_playSounds.isSelected()) {playSound("src/snd/success.wav");}
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
    
    private void menuOption_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_openActionPerformed
        openFile();
    }//GEN-LAST:event_menuOption_openActionPerformed

    private void saveFile() {
        String filePathNoExtension = filePath.split(".vmf")[0];
        String fileNameNoExtension = fileName.split(".vmf")[0];
        File fileToWrite = new File(filePathNoExtension + "_obf.vmf");
        
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));
            
            bufferedWriter.write(vmfContent);
            bufferedWriter.close();
            System.out.println(fileNameNoExtension + "_obf.vmf");
            if (menuOption_playSounds.isSelected()) {playSound("src/snd/success2.wav");}
            
            JOptionPane.showMessageDialog(this, "Success!\n"
                    + "For your safety, the input VMF has not been overridden.\n"
                    + "A copy of the VMF has been saved with the name of " + fileNameNoExtension + "_obf.vmf" , "Uboa - Success", 1);

        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void menuOption_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_saveActionPerformed
        saveFile();
    }//GEN-LAST:event_menuOption_saveActionPerformed
    

    
    private void menuOption_preferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_preferencesActionPerformed
        // TODO add your handling code here:
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
                
                bufferedWriter.write(vmfContent);
                bufferedWriter.close();
                
                System.out.println(fileToWrite.getPath());
                if (menuOption_playSounds.isSelected()) {playSound("src/snd/success2.wav");}

                JOptionPane.showMessageDialog(this, "Success!" , "Uboa - Success", 1);

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
        
        filePath = "";
        fileName = "";
        vmfContent = "";
         
        textArea.setText("Welcome to Uboa VMF Obfuscator. Load a VMF file to begin.");
        lb_info.setText("Hover over objects to see relevant information");
    }
    
    private void menuOption_closeFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_closeFileActionPerformed
        clearProgram();
    }//GEN-LAST:event_menuOption_closeFileActionPerformed

    private void menuOption_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuOption_exitActionPerformed

    private void menuOption_playSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_playSoundsActionPerformed
        XMLManager.setSoundEnabled(menuOption_playSounds.isSelected());
    }//GEN-LAST:event_menuOption_playSoundsActionPerformed

    private void rb_ePosObfuscation_noneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_noneMouseEntered
        lb_info.setText("Position of logic entities will remain the same");
    }//GEN-LAST:event_rb_ePosObfuscation_noneMouseEntered

    private void rb_ePosObfuscation_overlapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rb_ePosObfuscation_overlapMouseEntered
        lb_info.setText("All logic entities will be moved to the best suitable coordinates, based on the first entity found");
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Desktop.getDesktop().open(new File("src/manual.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void rb_eNameObfuscation_randomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_randomizeActionPerformed
        setRandomEntNameParametersEditable(true);
    }//GEN-LAST:event_rb_eNameObfuscation_randomizeActionPerformed

    private void rb_eNameObfuscation_exchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_exchangeActionPerformed
        setRandomEntNameParametersEditable(false);
    }//GEN-LAST:event_rb_eNameObfuscation_exchangeActionPerformed

    private void rb_eNameObfuscation_noneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_eNameObfuscation_noneActionPerformed
        setRandomEntNameParametersEditable(false);
    }//GEN-LAST:event_rb_eNameObfuscation_noneActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        aboutDialogue = new AboutDialogue(this, false);
        aboutDialogue.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void setRandomEntNameParametersEditable(boolean state) {
        comboBox_rndEntNameChoices.setEnabled(state);
        comboBox_rndEntNameLength.setEnabled(state);
        comboBox_rndEntNameLabel.setEnabled(state);
        
    }
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    void playSound(String soundFile) {
        AudioInputStream audioIn = null;
        try {
            File f = new File("./" + soundFile);
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audioIn.close();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_beginObfuscate;
    private javax.swing.JComboBox<String> comboBox_rndEntNameChoices;
    private javax.swing.JLabel comboBox_rndEntNameLabel;
    private javax.swing.JSpinner comboBox_rndEntNameLength;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lb_info;
    private javax.swing.JMenuItem menuOption_closeFile;
    private javax.swing.JMenuItem menuOption_exit;
    private javax.swing.JMenuItem menuOption_open;
    private javax.swing.JCheckBoxMenuItem menuOption_playSounds;
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
