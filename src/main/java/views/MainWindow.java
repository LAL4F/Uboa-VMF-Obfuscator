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
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Alejo
 */
public class MainWindow extends javax.swing.JFrame {
    public MainWindow() {
        initComponents();
        setupIcons();
        
        setLocationRelativeTo(null);
    }
    
    private void setupIcons() {
        menuOption_open.setIcon(new ImageIcon("src/images/ico24_openFile.png"));
        menuOption_save.setIcon(new ImageIcon("src/images/ico20_openFile.png"));
        menuOption_saveAs.setIcon(new ImageIcon("src/images/ico20_openFile.png"));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        menuOption_open = new javax.swing.JMenuItem();
        menuOption_save = new javax.swing.JMenuItem();
        menuOption_saveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menu_help = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuOption_preferences = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Uboa VMF Obfuscator");
        setMaximumSize(new java.awt.Dimension(720, 480));
        setMinimumSize(new java.awt.Dimension(720, 480));
        setResizable(false);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

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

        jMenuBar1.add(menu_file);

        menu_help.setText("Help");
        menu_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_helpActionPerformed(evt);
            }
        });
        jMenuBar1.add(menu_help);

        jMenu3.setText("Settings");

        menuOption_preferences.setText("Preferences...");
        menuOption_preferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOption_preferencesActionPerformed(evt);
            }
        });
        jMenu3.add(menuOption_preferences);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuOption_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_openActionPerformed
        JFrame parentFrame = new JFrame();
        
        Image img = Toolkit.getDefaultToolkit().getImage("src/images/ico24_openFile.png"); 
        parentFrame.setIconImage(img);
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
    }//GEN-LAST:event_menuOption_openActionPerformed

    private void menuOption_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_saveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuOption_saveActionPerformed

    private void menuOption_preferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_preferencesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuOption_preferencesActionPerformed

    private void menuOption_saveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOption_saveAsActionPerformed
        JFrame parentFrame = new JFrame();
 
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Guardar como...");
        
        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            System.out.println("Guardado fichero como: " + fichero.getAbsolutePath() );
            
            String VMFContent = "hi";
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(fichero.getAbsolutePath() + ".xml"));

                for (int i = 0; i < VMFContent.length(); i++) {
                    bw.write(VMFContent.charAt(i));
                }
            } catch (IOException ioe) {
                System.out.println("Exception IO");
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException ioe) {
                        System.out.println("Error");
                    }
                }
            }
        }
    }//GEN-LAST:event_menuOption_saveAsActionPerformed

    private void menu_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_helpActionPerformed
        textArea.setText("help");
    }//GEN-LAST:event_menu_helpActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem menuOption_open;
    private javax.swing.JMenuItem menuOption_preferences;
    private javax.swing.JMenuItem menuOption_save;
    private javax.swing.JMenuItem menuOption_saveAs;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenu menu_help;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
