package views;

import config.XMLConfig;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Conf_AppearancePanel extends javax.swing.JPanel {
    private MainWindow parent;
    private Map<String, String> lafDescriptionMap = new HashMap<String, String>();
            
    public Conf_AppearancePanel(MainWindow parent) {
        initComponents();
        this.parent = parent;
        
        lafDescriptionMap.put("FlatMacDarkLaf", "Default Look And Feel for this application.\nCredits: www.formdev.com");
        lafDescriptionMap.put("FlatMacLightLaf", "Light theme of the default Look And Feel for this application. MY EYES! \nCredits: www.formdev.com");
        lafDescriptionMap.put("Metal", "The Java Look And Feel.\nIt is crossplatform, meaning it looks the same on all systems.");
        lafDescriptionMap.put("Nimbus", "Nimbus is a cross-platform Look And Feel.\nIt uses Java 2D vector graphics to draw the user interface, so the UI can be crisply rendered at any resolution.");
        lafDescriptionMap.put("Motif", "Default Look And Feel for vintage Linux and Solaris systems if GTK+ 2.2 or later isn't available.");
        lafDescriptionMap.put("Windows", "An attempt to replicate the Windows Look And Feel.");
        lafDescriptionMap.put("Windows Classic", "An attempt to replicate the old Windows Look And Feel.");
        
        loadConfig();
    }

    private void loadConfig() {
        cb_lookandfeel.setSelectedItem(XMLConfig.getStringValue("lookAndFeel"));
        lb_lafpreview.setIcon(new ImageIcon(MainWindow.class.getResource("/lafPreviews/" + cb_lookandfeel.getSelectedItem().toString() + ".PNG")));
        lafPreviewDesc.setText(lafDescriptionMap.get(cb_lookandfeel.getSelectedItem().toString()));
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

        setMaximumSize(new java.awt.Dimension(476, 380));
        setMinimumSize(new java.awt.Dimension(476, 380));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Look and Feel"));

        cb_lookandfeel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FlatMacDarkLaf", "FlatMacLightLaf", "Metal", "Nimbus", "Motif", "Windows", "Windows Classic" }));
        cb_lookandfeel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_lookandfeel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_lookandfeelItemStateChanged(evt);
            }
        });

        bt_save.setText("Apply");
        bt_save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_save)
                .addContainerGap(784, Short.MAX_VALUE))
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

        lb_lafpreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_lafpreview.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        lafPreviewDesc.setEditable(false);
        lafPreviewDesc.setColumns(20);
        lafPreviewDesc.setLineWrap(true);
        lafPreviewDesc.setRows(5);
        lafPreviewDesc.setWrapStyleWord(true);
        lafPreviewDesc.setMargin(new java.awt.Insets(4, 6, 2, 4));
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
                .addComponent(lb_lafpreview, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_lookandfeelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_lookandfeelItemStateChanged
        lb_lafpreview.setIcon(new ImageIcon(MainWindow.class.getResource("/lafPreviews/" + cb_lookandfeel.getSelectedItem().toString() + ".PNG")));
        lafPreviewDesc.setText(lafDescriptionMap.get(cb_lookandfeel.getSelectedItem().toString()));
    }//GEN-LAST:event_cb_lookandfeelItemStateChanged

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed
        saveLaf();
    }//GEN-LAST:event_bt_saveActionPerformed

    private void saveLaf() {
        XMLConfig.setStringValue("lookAndFeel", cb_lookandfeel.getSelectedItem().toString()); 
        if (JOptionPane.showConfirmDialog(this, "You must restart the application to apply these changes.\nWill you restart?", "Uboa - Alert", 0) == 0) {
            parent.restartApp();
        }
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
