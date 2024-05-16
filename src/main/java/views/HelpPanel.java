package views;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class HelpPanel extends javax.swing.JPanel {
    public HelpPanel() {
        initComponents();
        
        bt_vdc.setIcon(new ImageIcon(MainWindow.class.getResource("/images/vdcLogo.png")));
        bt_github.setIcon(new ImageIcon(MainWindow.class.getResource("/images/github.png")));
        bt_manual.setIcon(new ImageIcon(MainWindow.class.getResource("/images/manualicon.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bt_vdc = new javax.swing.JButton();
        bt_manual = new javax.swing.JButton();
        bt_github = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Links"));
        jPanel1.setMaximumSize(new java.awt.Dimension(838, 374));
        jPanel1.setMinimumSize(new java.awt.Dimension(838, 374));

        bt_vdc.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bt_vdc.setBorder(null);
        bt_vdc.setContentAreaFilled(false);
        bt_vdc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_vdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_vdcActionPerformed(evt);
            }
        });

        bt_manual.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bt_manual.setBorder(null);
        bt_manual.setContentAreaFilled(false);
        bt_manual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_manualActionPerformed(evt);
            }
        });

        bt_github.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        bt_github.setBorder(null);
        bt_github.setBorderPainted(false);
        bt_github.setContentAreaFilled(false);
        bt_github.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_github.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_githubActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_vdc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_manual, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_github, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_manual, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_github, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_vdc, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(357, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bt_githubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_githubActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/LAL4F/Uboa-VMF-Obfuscator"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_githubActionPerformed

    private void bt_manualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_manualActionPerformed
        //the manual pdf must be read into an input stream of bytes, then a temp file
        //is rebuilt using the stream and opened by the OS

        //as for the path of the temp file, it must be the same folder as the jar file
        InputStream inputStream = MainWindow.class.getResourceAsStream("/pdf/manual.pdf");
        File file = new File("manual.pdf");

        try {
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_manualActionPerformed

    private void bt_vdcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_vdcActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://developer.valvesoftware.com/wiki/Main_Page"));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(HelpPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_vdcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_github;
    private javax.swing.JButton bt_manual;
    private javax.swing.JButton bt_vdc;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
