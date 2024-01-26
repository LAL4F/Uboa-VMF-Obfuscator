package views;

import config.XMLManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Conf_AppearancePanel extends javax.swing.JPanel {
    private ConfigWindow parent;
    
    public Conf_AppearancePanel(ConfigWindow parent) {
        initComponents();
        this.parent = parent;
        
        loadConfig();
    }

    private void loadConfig() {
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cb_lookandfeel = new javax.swing.JComboBox<>();
        bt_save = new javax.swing.JButton();
        lb_lafpreview = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lafPreviewDesc = new javax.swing.JTextArea();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Look and Feel"));

        cb_lookandfeel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FlatMacDarkLaf", "FlatMacLightLaf", "Metal", "Nimbus", "Motif", "Windows", "Windows Classic" }));
        cb_lookandfeel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_lookandfeelItemStateChanged(evt);
            }
        });

        bt_save.setText("Apply");
        bt_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cb_lookandfeel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_save)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_lookandfeel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_save))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lafPreviewDesc.setEditable(false);
        lafPreviewDesc.setColumns(20);
        lafPreviewDesc.setRows(5);
        jScrollPane1.setViewportView(lafPreviewDesc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(lb_lafpreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_lafpreview, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_lookandfeelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_lookandfeelItemStateChanged
        lb_lafpreview.setIcon(new ImageIcon(MainWindow.class.getResource("/images/lafPreviews/" + cb_lookandfeel.getSelectedItem().toString() + ".png")));
    }//GEN-LAST:event_cb_lookandfeelItemStateChanged

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed
        saveLaf();
    }//GEN-LAST:event_bt_saveActionPerformed

    private void saveLaf() {
        XMLManager.setStringValue("lookAndFeel", cb_lookandfeel.getSelectedItem().toString()); 
        parent.parent.restartApp();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_save;
    private javax.swing.JComboBox<String> cb_lookandfeel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea lafPreviewDesc;
    private javax.swing.JLabel lb_lafpreview;
    // End of variables declaration//GEN-END:variables
}
