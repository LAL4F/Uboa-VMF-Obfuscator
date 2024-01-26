/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class SoundBrowserDialogue extends javax.swing.JDialog {
    private Conf_SoundPanel parent;
    
    public SoundBrowserDialogue(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        setLocationRelativeTo(null);

        refreshSounds();
    }

    private Set<String> getInternalSndFiles() {
        Set<String> fileSet = new HashSet<>();
        try {
            URI uri = getClass().getResource("/internalSnd").toURI();

            Map<String, String> env = new HashMap<>();
            String[] array = uri.toString().split("!");
            FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), env);
            Path path = fs.getPath(array[1]);
            
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(uri));
            
            for (Path streamPath : stream) {
                if ((!Files.isDirectory(streamPath)) && (streamPath.getFileName().toString().contains(".wav"))) {
                    fileSet.add(streamPath.toString());
                }
            }
            
            fs.close();
        } catch (IOException ex) {
            Logger.getLogger(SoundBrowserDialogue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(SoundBrowserDialogue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileSet;
    }
    
    private Set<String> getExternalSndFiles(String dir) {
        Set<String> fileSet = new HashSet<>();
        try {
            System.out.println(new File(".").getCanonicalPath());
            DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir));
            for (Path path : stream) {
                if ((!Files.isDirectory(path)) && (path.getFileName().toString().contains(".wav"))) {
                    fileSet.add(path.toString());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SoundBrowserDialogue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileSet;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        sndList = new javax.swing.JList<>();
        bt_refresh = new javax.swing.JButton();
        slider_sndLevel = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_filePath = new javax.swing.JTextField();
        tf_fileNameFilter = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combo_searchInside = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        checkbox_autoplay = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        bt_confirm = new javax.swing.JButton();
        bt_previewSnd = new javax.swing.JButton();
        bt_stopSnd = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sound Browser");
        setAlwaysOnTop(true);
        setResizable(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sounds"));

        sndList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sndListMouseClicked(evt);
            }
        });
        sndList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sndListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(sndList);

        bt_refresh.setText("Refresh Sounds");
        bt_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_refreshActionPerformed(evt);
            }
        });

        slider_sndLevel.setValue(100);

        jLabel1.setText("Sound Volume:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Sound Name:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Filter:");

        tf_filePath.setEditable(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Search in:");

        combo_searchInside.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Internal", "External" }));
        combo_searchInside.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_searchInsideItemStateChanged(evt);
            }
        });

        checkbox_autoplay.setText("Autoplay Sounds");

        jPanel1.setLayout(new java.awt.GridLayout(0, 3, 25, 25));

        bt_confirm.setText("Ok");
        jPanel1.add(bt_confirm);

        bt_previewSnd.setText("Preview");
        jPanel1.add(bt_previewSnd);

        bt_stopSnd.setText("Stop");
        jPanel1.add(bt_stopSnd);

        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });
        jPanel1.add(bt_cancel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_filePath)
                                        .addComponent(tf_fileNameFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                                    .addComponent(combo_searchInside, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bt_refresh)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(checkbox_autoplay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(slider_sndLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_fileNameFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(combo_searchInside, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(slider_sndLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(checkbox_autoplay))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bt_refresh, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_refreshActionPerformed
        refreshSounds();
    }//GEN-LAST:event_bt_refreshActionPerformed

    private void sndListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sndListMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == evt.BUTTON1) {
            System.out.println("double clicked " + sndList.getSelectedValue());
            dispose();
        }
    }//GEN-LAST:event_sndListMouseClicked

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_bt_cancelActionPerformed

    private void sndListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_sndListValueChanged
        tf_filePath.setText(sndList.getSelectedValue());
    }//GEN-LAST:event_sndListValueChanged

    private void combo_searchInsideItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_searchInsideItemStateChanged
        refreshSounds();
    }//GEN-LAST:event_combo_searchInsideItemStateChanged

    private void refreshSounds() {
        Set<String> externalSndSet = getExternalSndFiles("./sound");
        Set<String> internalSndSet = getInternalSndFiles();
        Set<Set<String>> allSndFileSet = Set.of(externalSndSet, internalSndSet);

        DefaultListModel<String> listModel = new DefaultListModel<>(); 

        switch (combo_searchInside.getSelectedIndex()) {
            case 0: //All
                for (Set<String> set : allSndFileSet) {
                    for (String string : set) {
                        listModel.addElement(string);
                    }
                }

                sndList.setModel(listModel);
                break;
            case 1: //Internal
                for (String string : internalSndSet) {
                    listModel.addElement(string);
                }
                
                sndList.setModel(listModel);
                break;
            case 2: //External
                for (String string : externalSndSet) {
                    listModel.addElement(string);
                }
                
                sndList.setModel(listModel);
                break;
            default:
                break;
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SoundBrowserDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SoundBrowserDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SoundBrowserDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SoundBrowserDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SoundBrowserDialogue dialog = new SoundBrowserDialogue(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_confirm;
    private javax.swing.JButton bt_previewSnd;
    private javax.swing.JButton bt_refresh;
    private javax.swing.JButton bt_stopSnd;
    private javax.swing.JCheckBox checkbox_autoplay;
    private javax.swing.JComboBox<String> combo_searchInside;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider slider_sndLevel;
    private javax.swing.JList<String> sndList;
    private javax.swing.JTextField tf_fileNameFilter;
    private javax.swing.JTextField tf_filePath;
    // End of variables declaration//GEN-END:variables
}
