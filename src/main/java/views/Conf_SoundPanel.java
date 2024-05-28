package views;

import config.XMLConfig;
import dialogs.SoundBrowserDialogue;
import utils.SoundPlayer;

public class Conf_SoundPanel extends javax.swing.JPanel {
    public MainWindow parent;
    
    public Conf_SoundPanel(MainWindow parent) {
        initComponents();
        this.parent = parent;
        
        loadConfig();
    }
    
    private void loadConfig() {
        checkbox_allSounds.setSelected(XMLConfig.getBooleanValue("enableSnd"));
        checkbox_obfuscateFile.setSelected(XMLConfig.getBooleanValue("enableObfuscateFileSnd"));
        checkbox_finishBatch.setSelected(XMLConfig.getBooleanValue("enableBatchOperationSnd"));
        checkbox_error.setSelected(XMLConfig.getBooleanValue("enableErrorSnd"));
        checkbox_individualSoundsOnBatch.setSelected(XMLConfig.getBooleanValue("playObfuscateSndWhileBatch"));
        
        tf_obfuscateFileSnd.setText(XMLConfig.getStringValue("obfuscateFileSnd"));
        tf_finishBatchSnd.setText(XMLConfig.getStringValue("batchOperationSnd"));
        tf_errorSnd.setText(XMLConfig.getStringValue("errorSnd"));
        
        masterVolumeSlider.setValue(XMLConfig.getIntegerValue("masterVol"));
        setElementState(checkbox_allSounds.isSelected());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        checkbox_allSounds = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        checkbox_finishBatch = new javax.swing.JCheckBox();
        checkbox_obfuscateFile = new javax.swing.JCheckBox();
        bt_obfuscateFileSnd = new javax.swing.JButton();
        bt_batchOperationFile = new javax.swing.JButton();
        tf_obfuscateFileSnd = new javax.swing.JTextField();
        tf_finishBatchSnd = new javax.swing.JTextField();
        bt_resetAllSounds = new javax.swing.JButton();
        checkbox_individualSoundsOnBatch = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        bt_previewObfSnd = new javax.swing.JButton();
        bt_previewBatchSnd = new javax.swing.JButton();
        checkbox_error = new javax.swing.JCheckBox();
        tf_errorSnd = new javax.swing.JTextField();
        bt_errorSnd = new javax.swing.JButton();
        bt_previewErrorSnd = new javax.swing.JButton();
        masterVolumeSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sounds"));

        checkbox_allSounds.setText("Enable sounds");
        checkbox_allSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_allSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_allSoundsActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Play Sound On..."));

        checkbox_finishBatch.setText("Finish Batch Operation");
        checkbox_finishBatch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_finishBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_finishBatchActionPerformed(evt);
            }
        });

        checkbox_obfuscateFile.setText("Obfuscate file");
        checkbox_obfuscateFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_obfuscateFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_obfuscateFileActionPerformed(evt);
            }
        });

        bt_obfuscateFileSnd.setText("...");
        bt_obfuscateFileSnd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_obfuscateFileSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_obfuscateFileSndActionPerformed(evt);
            }
        });

        bt_batchOperationFile.setText("...");
        bt_batchOperationFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_batchOperationFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_batchOperationFileActionPerformed(evt);
            }
        });

        tf_obfuscateFileSnd.setText("jTextField3");
        tf_obfuscateFileSnd.setEnabled(false);

        tf_finishBatchSnd.setText("jTextField4");
        tf_finishBatchSnd.setEnabled(false);

        bt_resetAllSounds.setText("Reset");
        bt_resetAllSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_resetAllSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_resetAllSoundsActionPerformed(evt);
            }
        });

        checkbox_individualSoundsOnBatch.setText("Play \"Obfuscate File\" sound while performing batch operations");
        checkbox_individualSoundsOnBatch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_individualSoundsOnBatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_individualSoundsOnBatchActionPerformed(evt);
            }
        });

        bt_previewObfSnd.setText("Preview");
        bt_previewObfSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_previewObfSndActionPerformed(evt);
            }
        });

        bt_previewBatchSnd.setText("Preview");
        bt_previewBatchSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_previewBatchSndActionPerformed(evt);
            }
        });

        checkbox_error.setText("Error / Interruption");
        checkbox_error.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_error.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_errorActionPerformed(evt);
            }
        });

        tf_errorSnd.setText("jTextField4");
        tf_errorSnd.setEnabled(false);

        bt_errorSnd.setText("...");
        bt_errorSnd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_errorSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_errorSndActionPerformed(evt);
            }
        });

        bt_previewErrorSnd.setText("Preview");
        bt_previewErrorSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_previewErrorSndActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(checkbox_individualSoundsOnBatch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 516, Short.MAX_VALUE)
                        .addComponent(bt_resetAllSounds))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkbox_finishBatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkbox_obfuscateFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkbox_error, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tf_errorSnd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_errorSnd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_previewErrorSnd))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_finishBatchSnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                                    .addComponent(tf_obfuscateFileSnd, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_batchOperationFile)
                                    .addComponent(bt_obfuscateFileSnd))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_previewBatchSnd)
                                    .addComponent(bt_previewObfSnd))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_obfuscateFile)
                    .addComponent(bt_obfuscateFileSnd)
                    .addComponent(tf_obfuscateFileSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_previewObfSnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_finishBatch)
                    .addComponent(bt_batchOperationFile)
                    .addComponent(tf_finishBatchSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_previewBatchSnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_error)
                    .addComponent(bt_errorSnd)
                    .addComponent(tf_errorSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_previewErrorSnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_resetAllSounds)
                    .addComponent(checkbox_individualSoundsOnBatch))
                .addContainerGap())
        );

        masterVolumeSlider.setMajorTickSpacing(25);
        masterVolumeSlider.setMinorTickSpacing(5);
        masterVolumeSlider.setValue(100);
        masterVolumeSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                masterVolumeSliderMouseReleased(evt);
            }
        });

        jLabel1.setText("Master volume:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkbox_allSounds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masterVolumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(masterVolumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(checkbox_allSounds))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(469, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkbox_allSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_allSoundsActionPerformed
        XMLConfig.setBooleanValue("enableSnd", checkbox_allSounds.isSelected());
        if (checkbox_allSounds.isSelected()) {
            setElementState(true);
        } else {
            setElementState(false);
        }
    }//GEN-LAST:event_checkbox_allSoundsActionPerformed

    private void masterVolumeSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterVolumeSliderMouseReleased
        XMLConfig.setIntegerValue("masterVol", masterVolumeSlider.getValue());
    }//GEN-LAST:event_masterVolumeSliderMouseReleased

    private void checkbox_obfuscateFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_obfuscateFileActionPerformed
        XMLConfig.setBooleanValue("enableObfuscateFileSnd", checkbox_obfuscateFile.isSelected());
    }//GEN-LAST:event_checkbox_obfuscateFileActionPerformed

    private void checkbox_finishBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_finishBatchActionPerformed
        XMLConfig.setBooleanValue("enableBatchOperationSnd", checkbox_finishBatch.isSelected());
    }//GEN-LAST:event_checkbox_finishBatchActionPerformed

    private void bt_obfuscateFileSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_obfuscateFileSndActionPerformed
        openSoundBrowser("obfuscateFileSnd");
    }//GEN-LAST:event_bt_obfuscateFileSndActionPerformed

    private void bt_batchOperationFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_batchOperationFileActionPerformed
        openSoundBrowser("batchOperationSnd");
    }//GEN-LAST:event_bt_batchOperationFileActionPerformed

    private void bt_resetAllSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_resetAllSoundsActionPerformed
       XMLConfig.setStringValue("obfuscateFileSnd", "/internalSnd/obfuscate.wav");
       tf_obfuscateFileSnd.setText("/internalSnd/obfuscate.wav");
       
       XMLConfig.setStringValue("batchOperationSnd", "/internalSnd/save.wav");
       tf_finishBatchSnd.setText("/internalSnd/save.wav");
    }//GEN-LAST:event_bt_resetAllSoundsActionPerformed

    private void checkbox_individualSoundsOnBatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_individualSoundsOnBatchActionPerformed
        XMLConfig.setBooleanValue("playObfuscateSndWhileBatch", checkbox_individualSoundsOnBatch.isSelected());
    }//GEN-LAST:event_checkbox_individualSoundsOnBatchActionPerformed

    private void bt_previewObfSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_previewObfSndActionPerformed
        SoundPlayer.initSound(tf_obfuscateFileSnd.getText(), SoundPlayer.SoundType.SND_OVERRIDE);
    }//GEN-LAST:event_bt_previewObfSndActionPerformed

    private void bt_previewBatchSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_previewBatchSndActionPerformed
        SoundPlayer.initSound(tf_finishBatchSnd.getText(), SoundPlayer.SoundType.SND_OVERRIDE);
    }//GEN-LAST:event_bt_previewBatchSndActionPerformed

    private void checkbox_errorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_errorActionPerformed
        XMLConfig.setBooleanValue("enableErrorSnd", checkbox_error.isSelected());
    }//GEN-LAST:event_checkbox_errorActionPerformed

    private void bt_errorSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_errorSndActionPerformed
        openSoundBrowser("errorSnd");
    }//GEN-LAST:event_bt_errorSndActionPerformed

    private void bt_previewErrorSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_previewErrorSndActionPerformed
        SoundPlayer.initSound(tf_errorSnd.getText(), SoundPlayer.SoundType.SND_OVERRIDE);
    }//GEN-LAST:event_bt_previewErrorSndActionPerformed

    private void openSoundBrowser(String event) {
        SoundBrowserDialogue soundBrowserDialogue = new SoundBrowserDialogue(parent, false, this, event);
        soundBrowserDialogue.show();
    }
    
    public void setEventSnd(String sndEvent, String soundPath) {
        XMLConfig.setStringValue(sndEvent, soundPath);
        
        switch (sndEvent) {
            case "obfuscateFileSnd":
                tf_obfuscateFileSnd.setText(soundPath);
                break;
            case "batchOperationSnd":
                tf_finishBatchSnd.setText(soundPath);
                break;
            case "errorSnd":
                tf_errorSnd.setText(soundPath);
                break;
            default:
                break;
        }
    }

    private void setElementState(boolean bool) {
        checkbox_obfuscateFile.setEnabled(bool);
        checkbox_finishBatch.setEnabled(bool);
        checkbox_error.setEnabled(bool);
        checkbox_individualSoundsOnBatch.setEnabled(bool);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_batchOperationFile;
    private javax.swing.JButton bt_errorSnd;
    private javax.swing.JButton bt_obfuscateFileSnd;
    private javax.swing.JButton bt_previewBatchSnd;
    private javax.swing.JButton bt_previewErrorSnd;
    private javax.swing.JButton bt_previewObfSnd;
    private javax.swing.JButton bt_resetAllSounds;
    private javax.swing.JCheckBox checkbox_allSounds;
    private javax.swing.JCheckBox checkbox_error;
    private javax.swing.JCheckBox checkbox_finishBatch;
    private javax.swing.JCheckBox checkbox_individualSoundsOnBatch;
    private javax.swing.JCheckBox checkbox_obfuscateFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider masterVolumeSlider;
    private javax.swing.JTextField tf_errorSnd;
    private javax.swing.JTextField tf_finishBatchSnd;
    private javax.swing.JTextField tf_obfuscateFileSnd;
    // End of variables declaration//GEN-END:variables
}
