package views;

public class Conf_SoundPanel extends javax.swing.JPanel {
    public ConfigWindow parent;
    
    public Conf_SoundPanel(ConfigWindow parent) {
        initComponents();
        this.parent = parent;
        
        loadConfig();
    }
    
    private void loadConfig() {
        
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
        checkbox_allSounds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_allSoundsActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Play Sound On..."));

        checkbox_openFileSounds.setText("Open file");

        checkbox_saveFileSounds.setText("Save file");

        checkbox_analyzeFileSounds.setText("Analyze file");

        checkbox_errorSounds.setText("On errors");

        checkbox_obfuscateFileSounds.setText("Obfuscate file");

        bt_openFileSnd.setText("...");
        bt_openFileSnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_openFileSndActionPerformed(evt);
            }
        });

        bt_analyzeFileSnd.setText("...");

        bt_obfuscateFileSnd.setText("...");

        bt_saveFileSnd.setText("...");

        bt_exceptionSnd.setText("...");

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
                    .addComponent(tf_analyzeFileSnd)
                    .addComponent(tf_obfuscateFileSnd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_saveFileSnd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf_exceptionSnd, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_obfuscateFileSnd)
                            .addComponent(bt_saveFileSnd)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bt_openFileSnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_analyzeFileSnd)))
                        .addGap(145, 145, 145))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bt_exceptionSnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_resetAllSounds)
                        .addContainerGap())))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
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
                .addContainerGap(144, Short.MAX_VALUE))
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
        openSoundBrowser();
    }//GEN-LAST:event_bt_openFileSndActionPerformed

    private void checkbox_allSoundsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_allSoundsActionPerformed
        if (checkbox_allSounds.isSelected()) {
            setElementState(true);
        } else {
            setElementState(false);
        }
    }//GEN-LAST:event_checkbox_allSoundsActionPerformed

    private void openSoundBrowser() {
        SoundBrowserDialogue soundBrowserDialogue = new SoundBrowserDialogue(this);
        soundBrowserDialogue.show();
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
