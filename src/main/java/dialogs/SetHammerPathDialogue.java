/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package dialogs;

import config.XMLManager;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import jnafilechooser.api.JnaFileChooser;
import views.MainWindow;

public class SetHammerPathDialogue extends javax.swing.JDialog {
    private MainWindow parent;
    private String vmfPath;
    
    public SetHammerPathDialogue(MainWindow parent, boolean modal, String vmfPath) {
        super(parent, modal);
        this.parent = parent;
        this.vmfPath = vmfPath;
        initComponents();
        
        //Find the filename from the full path real quick
        String[] vmfPathSplit = vmfPath.split("\\\\");
        
        int i = -1;
        for (String split : vmfPathSplit) {
            i++;
        }
        
        setTitle("Configure Hammer - " + vmfPathSplit[i]);
        setLocationRelativeTo(null);
        loadConfig();
    }
    
    private void loadConfig() {
        checkbox_preferFileAssociation.setSelected(XMLManager.getBooleanValue("preferFileTypeAssociation"));
        tf_hammerExePath.setText(XMLManager.getStringValue("hammerPath"));
        
        if (checkbox_preferFileAssociation.isSelected()) {
            tf_hammerExePath.setEnabled(false);
            bt_browseExec.setEnabled(false);
        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tf_hammerExePath = new javax.swing.JTextField();
        bt_browseExec = new javax.swing.JButton();
        checkbox_preferFileAssociation = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hammer Executable Setup");
        setResizable(false);

        jButton1.setText("Launch");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Hammer Executable");

        tf_hammerExePath.setText("Path to executable (ex: hammer.exe)");
        tf_hammerExePath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_hammerExePathKeyReleased(evt);
            }
        });

        bt_browseExec.setText("Browse");
        bt_browseExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_browseExecActionPerformed(evt);
            }
        });

        checkbox_preferFileAssociation.setText("Use file type association");
        checkbox_preferFileAssociation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_preferFileAssociationActionPerformed(evt);
            }
        });

        jLabel2.setText("Check this if you have associated VMF files with any Hammer executable");

        jLabel3.setText("(this can be changed later in the options tab)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(tf_hammerExePath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_browseExec))
                    .addComponent(checkbox_preferFileAssociation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_hammerExePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_browseExec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkbox_preferFileAssociation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkbox_preferFileAssociationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_preferFileAssociationActionPerformed
        if (checkbox_preferFileAssociation.isSelected()) {
            XMLManager.setBooleanValue("preferFileTypeAssociation", true);
            tf_hammerExePath.setEnabled(false);
            bt_browseExec.setEnabled(false);
            parent.conf_GeneralPanel.loadConfig();
            return;
        }
        
        XMLManager.setBooleanValue("preferFileTypeAssociation", false);
        tf_hammerExePath.setEnabled(true);
        bt_browseExec.setEnabled(true);
        parent.conf_GeneralPanel.loadConfig();
    }//GEN-LAST:event_checkbox_preferFileAssociationActionPerformed

    private void bt_browseExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_browseExecActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Windows Executable (.exe)", "exe");

        if (fileChooser.showOpenDialog(this)) {
            tf_hammerExePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
            XMLManager.setStringValue("hammerPath", fileChooser.getSelectedFile().getAbsolutePath());
            parent.conf_GeneralPanel.loadConfig();
        }
    }//GEN-LAST:event_bt_browseExecActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (XMLManager.getBooleanValue("preferFileTypeAssociation")) {
            try {
                System.out.println("\"" + vmfPath + "\"");
                Desktop.getDesktop().open(new File(vmfPath));
                dispose();
            } catch (IOException ex) {
                System.out.println("Hammer path not configured");
            }
            parent.conf_GeneralPanel.loadConfig();
            return;
        }
        
        try {
            Runtime.getRuntime().exec(XMLManager.getStringValue("hammerPath") + " \"" + vmfPath + "\"");
            dispose();
        } catch (IOException ex) {
            System.out.println("Hammer path not configured");
        }
        parent.conf_GeneralPanel.loadConfig();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tf_hammerExePathKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_hammerExePathKeyReleased
        XMLManager.setStringValue("hammerPath", tf_hammerExePath.getText());
        parent.conf_GeneralPanel.loadConfig();
    }//GEN-LAST:event_tf_hammerExePathKeyReleased

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
            java.util.logging.Logger.getLogger(SetHammerPathDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetHammerPathDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetHammerPathDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetHammerPathDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SetHammerPathDialogue dialog = new SetHammerPathDialogue(null, true, null);
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
    private javax.swing.JButton bt_browseExec;
    private javax.swing.JCheckBox checkbox_preferFileAssociation;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField tf_hammerExePath;
    // End of variables declaration//GEN-END:variables
}
