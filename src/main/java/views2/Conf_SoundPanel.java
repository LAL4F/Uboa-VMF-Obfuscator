package views2;

import config.XMLManager;
import dialogs.SoundBrowserDialogue;

public class Conf_SoundPanel extends javax.swing.JPanel {
    public MainWindow parent;
    
    public Conf_SoundPanel(MainWindow parent) {
        initComponents();
        this.parent = parent;
        
        loadConfig();
    }
    
    private void loadConfig() {
        checkbox_allSounds.setSelected(XMLManager.getBooleanValue("enableSnd"));
        checkbox_analyzeFileSounds.setSelected(XMLManager.getBooleanValue("enableAnalyzeFileSnd"));
        checkbox_errorSounds.setSelected(XMLManager.getBooleanValue("enableErrorSnd"));
        checkbox_obfuscateFileSounds.setSelected(XMLManager.getBooleanValue("enableObfuscateFileSnd"));
        checkbox_openFileSounds.setSelected(XMLManager.getBooleanValue("enableOpenFileSnd"));
        checkbox_saveFileSounds.setSelected(XMLManager.getBooleanValue("enableSaveFileSnd"));
        
        tf_analyzeFileSnd.setText(XMLManager.getStringValue("analyzeFileSnd"));
        tf_exceptionSnd.setText(XMLManager.getStringValue("errorSnd"));
        tf_obfuscateFileSnd.setText(XMLManager.getStringValue("obfuscateFileSnd"));
        tf_openFileSnd.setText(XMLManager.getStringValue("openFileSnd"));
        tf_saveFileSnd.setText(XMLManager.getStringValue("saveFileSnd"));
        
        masterVolumeSlider.setValue(XMLManager.getIntegerValue("masterVol"));
        setElementState(checkbox_allSounds.isSelected());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        checkbox_allSounds = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        checkbox_openFileSounds = new javax.swing.JCheckBox();
        checkbox_saveFileSounds = new javax.swing.JCheckBox();
        checkbox_analyzeFileSounds = new javax.swing.JCheckBox();
        checkbox_errorSounds = new javax.swing.JCheckBox();
        checkbox_obfuscateFileSounds = new javax.swing.JCheckBox();
        bt_openFileSnd = new javax.swing.JButton();
        bt_analyzeFileSnd = new javax.swing.JButton();
        bt_obfuscateFileSnd = new javax.swing.JButton();
        bt_saveFileSnd = new javax.swing.JButton();
        bt_exceptionSnd = new javax.swing.JButton();
        tf_openFileSnd = new javax.swing.JTextField();
        tf_analyzeFileSnd = new javax.swing.JTextField();
        tf_obfuscateFileSnd = new javax.swing.JTextField();
        tf_saveFileSnd = new javax.swing.JTextField();
        tf_exceptionSnd = new javax.swing.JTextField();
        bt_resetAllSounds = new javax.swing.JButton();
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

        checkbox_openFileSounds.setText("Open file");
        checkbox_openFileSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_openFileSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_openFileSoundsActionPerformed(evt);
            }
        });

        checkbox_saveFileSounds.setText("Save file");
        checkbox_saveFileSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_saveFileSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_saveFileSoundsActionPerformed(evt);
            }
        });

        checkbox_analyzeFileSounds.setText("Analyze file");
        checkbox_analyzeFileSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_analyzeFileSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_analyzeFileSoundsActionPerformed(evt);
            }
        });

        checkbox_errorSounds.setText("On errors");
        checkbox_errorSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_errorSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_errorSoundsActionPerformed(evt);
            }
        });

        checkbox_obfuscateFileSounds.setText("Obfuscate file");
        checkbox_obfuscateFileSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_obfuscateFileSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_obfuscateFileSoundsActionPerformed(evt);
            }
        });

        bt_openFileSnd.setText("...");
        bt_openFileSnd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_openFileSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_openFileSndActionPerformed(evt);
            }
        });

        bt_analyzeFileSnd.setText("...");
        bt_analyzeFileSnd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_analyzeFileSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_analyzeFileSndActionPerformed(evt);
            }
        });

        bt_obfuscateFileSnd.setText("...");
        bt_obfuscateFileSnd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_obfuscateFileSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_obfuscateFileSndActionPerformed(evt);
            }
        });

        bt_saveFileSnd.setText("...");
        bt_saveFileSnd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_saveFileSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_saveFileSndActionPerformed(evt);
            }
        });

        bt_exceptionSnd.setText("...");
        bt_exceptionSnd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_exceptionSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_exceptionSndActionPerformed(evt);
            }
        });

        tf_openFileSnd.setText("jTextField1");
        tf_openFileSnd.setEnabled(false);

        tf_analyzeFileSnd.setText("jTextField2");
        tf_analyzeFileSnd.setEnabled(false);

        tf_obfuscateFileSnd.setText("jTextField3");
        tf_obfuscateFileSnd.setEnabled(false);

        tf_saveFileSnd.setText("jTextField4");
        tf_saveFileSnd.setEnabled(false);

        tf_exceptionSnd.setText("jTextField5");
        tf_exceptionSnd.setEnabled(false);

        bt_resetAllSounds.setText("Reset");
        bt_resetAllSounds.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_resetAllSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_resetAllSoundsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(checkbox_errorSounds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_saveFileSounds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_analyzeFileSounds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_openFileSounds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_obfuscateFileSounds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_openFileSnd)
                    .addComponent(tf_analyzeFileSnd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_exceptionSnd)
                    .addComponent(tf_saveFileSnd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_obfuscateFileSnd, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_obfuscateFileSnd)
                    .addComponent(bt_saveFileSnd)
                    .addComponent(bt_openFileSnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_analyzeFileSnd)
                    .addComponent(bt_exceptionSnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_resetAllSounds)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_openFileSounds)
                    .addComponent(bt_openFileSnd)
                    .addComponent(tf_openFileSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_analyzeFileSounds)
                    .addComponent(bt_analyzeFileSnd)
                    .addComponent(tf_analyzeFileSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_obfuscateFileSounds)
                    .addComponent(bt_obfuscateFileSnd)
                    .addComponent(tf_obfuscateFileSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_saveFileSounds)
                    .addComponent(bt_saveFileSnd)
                    .addComponent(tf_saveFileSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_errorSounds)
                    .addComponent(bt_exceptionSnd)
                    .addComponent(tf_exceptionSnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_resetAllSounds))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 671, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap(464, Short.MAX_VALUE))
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

    private void bt_openFileSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_openFileSndActionPerformed
        openSoundBrowser("openFileSnd");
    }//GEN-LAST:event_bt_openFileSndActionPerformed

    private void checkbox_allSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_allSoundsActionPerformed
        XMLManager.setBooleanValue("enableSnd", checkbox_allSounds.isSelected());
        if (checkbox_allSounds.isSelected()) {
            setElementState(true);
        } else {
            setElementState(false);
        }
    }//GEN-LAST:event_checkbox_allSoundsActionPerformed

    private void masterVolumeSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masterVolumeSliderMouseReleased
        XMLManager.setIntegerValue("masterVol", masterVolumeSlider.getValue());
    }//GEN-LAST:event_masterVolumeSliderMouseReleased

    private void checkbox_openFileSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_openFileSoundsActionPerformed
        XMLManager.setBooleanValue("enableOpenFileSnd", checkbox_openFileSounds.isSelected());
    }//GEN-LAST:event_checkbox_openFileSoundsActionPerformed

    private void checkbox_analyzeFileSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_analyzeFileSoundsActionPerformed
        XMLManager.setBooleanValue("enableAnalyzeFileSnd", checkbox_analyzeFileSounds.isSelected());
    }//GEN-LAST:event_checkbox_analyzeFileSoundsActionPerformed

    private void checkbox_obfuscateFileSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_obfuscateFileSoundsActionPerformed
        XMLManager.setBooleanValue("enableObfuscateFileSnd", checkbox_obfuscateFileSounds.isSelected());
    }//GEN-LAST:event_checkbox_obfuscateFileSoundsActionPerformed

    private void checkbox_saveFileSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_saveFileSoundsActionPerformed
        XMLManager.setBooleanValue("enableSaveFileSnd", checkbox_saveFileSounds.isSelected());
    }//GEN-LAST:event_checkbox_saveFileSoundsActionPerformed

    private void checkbox_errorSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_errorSoundsActionPerformed
        XMLManager.setBooleanValue("enableErrorSnd", checkbox_errorSounds.isSelected());
    }//GEN-LAST:event_checkbox_errorSoundsActionPerformed

    private void bt_analyzeFileSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_analyzeFileSndActionPerformed
        openSoundBrowser("analyzeFileSnd");
    }//GEN-LAST:event_bt_analyzeFileSndActionPerformed

    private void bt_obfuscateFileSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_obfuscateFileSndActionPerformed
        openSoundBrowser("obfuscateFileSnd");
    }//GEN-LAST:event_bt_obfuscateFileSndActionPerformed

    private void bt_saveFileSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveFileSndActionPerformed
        openSoundBrowser("saveFileSnd");
    }//GEN-LAST:event_bt_saveFileSndActionPerformed

    private void bt_exceptionSndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_exceptionSndActionPerformed
        openSoundBrowser("errorSnd");
    }//GEN-LAST:event_bt_exceptionSndActionPerformed

    private void bt_resetAllSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_resetAllSoundsActionPerformed
       XMLManager.setStringValue("openFileSnd", "/internalSnd/open.wav");
       tf_openFileSnd.setText("/internalSnd/open.wav");
       
       XMLManager.setStringValue("analyzeFileSnd", "/internalSnd/analyzeFile.wav");
       tf_analyzeFileSnd.setText("/internalSnd/analyzeFile.wav");
       
       XMLManager.setStringValue("obfuscateFileSnd", "/internalSnd/obfuscate.wav");
       tf_obfuscateFileSnd.setText("/internalSnd/obfuscate.wav");
       
       XMLManager.setStringValue("saveFileSnd", "/internalSnd/save.wav");
       tf_saveFileSnd.setText("/internalSnd/save.wav");
       
       XMLManager.setStringValue("errorSnd", "/internalSnd/error.wav");
       tf_exceptionSnd.setText("/internalSnd/error.wav");
    }//GEN-LAST:event_bt_resetAllSoundsActionPerformed

    private void openSoundBrowser(String event) {
        SoundBrowserDialogue soundBrowserDialogue = new SoundBrowserDialogue(parent, false, this, event);
        soundBrowserDialogue.show();
    }
    
    public void setEventSnd(String sndEvent, String soundPath) {
        XMLManager.setStringValue(sndEvent, soundPath);
        
        switch (sndEvent) {
            case "openFileSnd":
                tf_openFileSnd.setText(soundPath);
                break;
            case "analyzeFileSnd":
                tf_analyzeFileSnd.setText(soundPath);
                break;
            case "obfuscateFileSnd":
                tf_obfuscateFileSnd.setText(soundPath);
                break;
            case "saveFileSnd":
                tf_saveFileSnd.setText(soundPath);
                break;
            case "errorSnd":
                tf_exceptionSnd.setText(soundPath);
                break;
            default:
                break;
        }
    }

    private void setElementState(boolean bool) {
        checkbox_analyzeFileSounds.setEnabled(bool);
        checkbox_errorSounds.setEnabled(bool);
        checkbox_obfuscateFileSounds.setEnabled(bool);
        checkbox_openFileSounds.setEnabled(bool);
        checkbox_saveFileSounds.setEnabled(bool);
        masterVolumeSlider.setEnabled(bool);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_analyzeFileSnd;
    private javax.swing.JButton bt_exceptionSnd;
    private javax.swing.JButton bt_obfuscateFileSnd;
    private javax.swing.JButton bt_openFileSnd;
    private javax.swing.JButton bt_resetAllSounds;
    private javax.swing.JButton bt_saveFileSnd;
    private javax.swing.JCheckBox checkbox_allSounds;
    private javax.swing.JCheckBox checkbox_analyzeFileSounds;
    private javax.swing.JCheckBox checkbox_errorSounds;
    private javax.swing.JCheckBox checkbox_obfuscateFileSounds;
    private javax.swing.JCheckBox checkbox_openFileSounds;
    private javax.swing.JCheckBox checkbox_saveFileSounds;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider masterVolumeSlider;
    private javax.swing.JTextField tf_analyzeFileSnd;
    private javax.swing.JTextField tf_exceptionSnd;
    private javax.swing.JTextField tf_obfuscateFileSnd;
    private javax.swing.JTextField tf_openFileSnd;
    private javax.swing.JTextField tf_saveFileSnd;
    // End of variables declaration//GEN-END:variables
}
