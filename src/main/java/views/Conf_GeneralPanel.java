package views;

import config.XMLConfig;
import jnafilechooser.api.JnaFileChooser;

public class Conf_GeneralPanel extends javax.swing.JPanel {
    private MainWindow parent;
    
    public Conf_GeneralPanel(MainWindow parent) {
        initComponents();
        this.parent = parent;

        loadConfig();
    }

    public void loadConfig() {
        checkbox_preferFileAssociation.setSelected(XMLConfig.getBooleanValue("preferFileTypeAssociation"));
        tf_hammerExePath.setText(XMLConfig.getStringValue("hammerPath"));
        
        if (checkbox_preferFileAssociation.isSelected()) {
            tf_hammerExePath.setEnabled(false);
            bt_browseExec.setEnabled(false);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_hammerExePath = new javax.swing.JTextField();
        bt_browseExec = new javax.swing.JButton();
        checkbox_preferFileAssociation = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(476, 380));
        setMinimumSize(new java.awt.Dimension(476, 380));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hammer Editor"));

        jLabel1.setText("Hammer Executable:");

        tf_hammerExePath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_hammerExePathKeyReleased(evt);
            }
        });

        bt_browseExec.setText("Browse");
        bt_browseExec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_browseExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_browseExecActionPerformed(evt);
            }
        });

        checkbox_preferFileAssociation.setText("Use file type association");
        checkbox_preferFileAssociation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_preferFileAssociation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_preferFileAssociationActionPerformed(evt);
            }
        });

        jLabel2.setText("Check this if you have associated the VMF file type with your Hammer Editor of choice");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(tf_hammerExePath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_browseExec))
                    .addComponent(checkbox_preferFileAssociation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_hammerExePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_browseExec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkbox_preferFileAssociation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(563, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkbox_preferFileAssociationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_preferFileAssociationActionPerformed
        if (checkbox_preferFileAssociation.isSelected()) {
            XMLConfig.setBooleanValue("preferFileTypeAssociation", true);
            tf_hammerExePath.setEnabled(false);
            bt_browseExec.setEnabled(false);
            return;
        }
        
        XMLConfig.setBooleanValue("preferFileTypeAssociation", false);
        tf_hammerExePath.setEnabled(true);
        bt_browseExec.setEnabled(true);
    }//GEN-LAST:event_checkbox_preferFileAssociationActionPerformed

    private void bt_browseExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_browseExecActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Windows Executable (.exe)", "exe");

        if (fileChooser.showOpenDialog(parent)) {
            tf_hammerExePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
            XMLConfig.setStringValue("hammerPath", fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_bt_browseExecActionPerformed

    private void tf_hammerExePathKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_hammerExePathKeyReleased
        XMLConfig.setStringValue("hammerPath", tf_hammerExePath.getText());
    }//GEN-LAST:event_tf_hammerExePathKeyReleased



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_browseExec;
    private javax.swing.JCheckBox checkbox_preferFileAssociation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf_hammerExePath;
    // End of variables declaration//GEN-END:variables
}
