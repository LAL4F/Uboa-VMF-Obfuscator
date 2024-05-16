package views;

import config.XMLManager;
import database.SQLDatabaseManager;
import java.sql.SQLException;

public class RankingPanel extends javax.swing.JPanel {
    public RankingPanel() {
        initComponents();
        
        loadConfig();
    }
    
    private void loadConfig() {
        setComponentState(false);
        
        tf_username.setText(XMLManager.getStringValue("displayName"));
        
        if (XMLManager.getStringValue("displayName").equals("$USERACCOUNT")) {
            tf_username.setText(System.getProperty("user.name"));    
        } 
        
        checkbox_consent.setSelected(XMLManager.getBooleanValue("rankingParticipation"));
        
        if (checkbox_consent.isSelected()) {
            setComponentState(true);
        }
        
        checkbox_username.setSelected(XMLManager.getBooleanValue("allowUsername"));
        if (checkbox_username.isSelected() && checkbox_username.isEnabled() && checkbox_consent.isSelected()) {
            rb_useName.setEnabled(true);
            rb_useIpAddr.setEnabled(true);    
        }
        
        switch (XMLManager.getStringValue("usernameType")) {
            case "displayName":
                rb_useName.setSelected(true);
                break;
            case "ip":
                rb_useIpAddr.setSelected(true);
                break;
        }
        
        checkbox_allowVmfDownload.setSelected(XMLManager.getBooleanValue("allowUsername"));
        if (checkbox_allowVmfDownload.isSelected() && checkbox_allowVmfDownload.isEnabled() && checkbox_consent.isSelected()) {
            rb_obfVmf.setEnabled(true);
            rb_srcVmf.setEnabled(true);    
        }
        
        switch (XMLManager.getStringValue("vmfDownloadType")) {
            case "src":
                rb_srcVmf.setSelected(true);
                break;
            case "obf":
                rb_obfVmf.setSelected(true);
                break;
        }
        
        checkbox_filename.setSelected(XMLManager.getBooleanValue("allowFilename"));
        checkbox_date.setSelected(XMLManager.getBooleanValue("allowDate"));
        checkbox_timeElapsed.setSelected(XMLManager.getBooleanValue("allowTimeElapsed"));
        checkbox_nEnts.setSelected(XMLManager.getBooleanValue("allowNumEnts"));
        checkbox_size.setSelected(XMLManager.getBooleanValue("allowSize"));
        
    }

    private void setComponentState(boolean state) {
        checkbox_username.setEnabled(state);
        checkbox_allowVmfDownload.setEnabled(state);
        checkbox_filename.setEnabled(state);
        checkbox_date.setEnabled(state);
        checkbox_timeElapsed.setEnabled(state);
        checkbox_nEnts.setEnabled(state);
        checkbox_size.setEnabled(state);
        
        rb_useName.setEnabled(state);
        rb_useIpAddr.setEnabled(state);  

        rb_obfVmf.setEnabled(state);
        rb_srcVmf.setEnabled(state);  
  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        rankingTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        checkbox_consent = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        checkbox_username = new javax.swing.JCheckBox();
        jSeparator5 = new javax.swing.JSeparator();
        rb_useName = new javax.swing.JRadioButton();
        rb_useIpAddr = new javax.swing.JRadioButton();
        tf_username = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        checkbox_allowVmfDownload = new javax.swing.JCheckBox();
        rb_srcVmf = new javax.swing.JRadioButton();
        rb_obfVmf = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        checkbox_filename = new javax.swing.JCheckBox();
        checkbox_date = new javax.swing.JCheckBox();
        checkbox_timeElapsed = new javax.swing.JCheckBox();
        checkbox_nEnts = new javax.swing.JCheckBox();
        checkbox_size = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();

        jButton1.setText("Refresh Leaderboard");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Sort by");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Username", "Map", "Date", "Time Elapsed", "Entities", "Size", "Download" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Leaderboard"));

        rankingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Map", "Date", "Time Elapsed (s)", "Entities", "Size (MB)", "Download"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(rankingTable);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Participation"));

        jLabel5.setText("Here you can choose what data, if any, you publicly share with the rest of the users of this application and the developer. ");

        jLabel6.setText("You can revoke your participation at any point, however, any data you do decide to upload will continue to exist.");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        checkbox_consent.setText("I consent to my data being stored and publicly shared");
        checkbox_consent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_consent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_consentActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        checkbox_username.setText("Username (if not, appear as anonymous)");
        checkbox_username.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_usernameActionPerformed(evt);
            }
        });

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        buttonGroup1.add(rb_useName);
        rb_useName.setText("Username");
        rb_useName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_useName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_useNameActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb_useIpAddr);
        rb_useIpAddr.setText("IP Address");
        rb_useIpAddr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_useIpAddr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_useIpAddrActionPerformed(evt);
            }
        });

        tf_username.setText("Your name");
        tf_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_usernameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rb_useIpAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_useName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_username))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(checkbox_username)
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkbox_username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_useName)
                            .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_useIpAddr))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        checkbox_allowVmfDownload.setText("Allow VMF download");
        checkbox_allowVmfDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_allowVmfDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_allowVmfDownloadActionPerformed(evt);
            }
        });

        buttonGroup2.add(rb_srcVmf);
        rb_srcVmf.setText("Original");
        rb_srcVmf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_srcVmf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_srcVmfActionPerformed(evt);
            }
        });

        buttonGroup2.add(rb_obfVmf);
        rb_obfVmf.setText("Obfuscated");
        rb_obfVmf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rb_obfVmf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_obfVmfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rb_obfVmf, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_srcVmf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(checkbox_allowVmfDownload))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkbox_allowVmfDownload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rb_srcVmf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_obfVmf)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new java.awt.GridLayout(0, 1));

        checkbox_filename.setText("Map Filename");
        checkbox_filename.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_filename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_filenameActionPerformed(evt);
            }
        });
        jPanel5.add(checkbox_filename);

        checkbox_date.setText("Obfuscation Date");
        checkbox_date.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_dateActionPerformed(evt);
            }
        });
        jPanel5.add(checkbox_date);

        checkbox_timeElapsed.setText("Time Elapsed");
        checkbox_timeElapsed.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_timeElapsed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_timeElapsedActionPerformed(evt);
            }
        });
        jPanel5.add(checkbox_timeElapsed);

        checkbox_nEnts.setText("Number of Entities");
        checkbox_nEnts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_nEnts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_nEntsActionPerformed(evt);
            }
        });
        jPanel5.add(checkbox_nEnts);

        checkbox_size.setText("Size");
        checkbox_size.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkbox_size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_sizeActionPerformed(evt);
            }
        });
        jPanel5.add(checkbox_size);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkbox_consent)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(checkbox_consent)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("A row will be inserted into the Database on each map obfuscation.");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try (var connection =  SQLDatabaseManager.connect()){
            System.out.println("Connected to the MySQL database.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkbox_consentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_consentActionPerformed
        setComponentState(checkbox_consent.isSelected());
    }//GEN-LAST:event_checkbox_consentActionPerformed

    private void checkbox_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_usernameActionPerformed
        XMLManager.setBooleanValue("allowUsername", checkbox_username.isSelected());
        rb_useIpAddr.setEnabled(checkbox_username.isSelected());
        rb_useName.setEnabled(checkbox_username.isSelected());
    }//GEN-LAST:event_checkbox_usernameActionPerformed

    private void checkbox_allowVmfDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_allowVmfDownloadActionPerformed
        XMLManager.setBooleanValue("allowVmfDownload", checkbox_allowVmfDownload.isSelected());
        rb_obfVmf.setEnabled(checkbox_allowVmfDownload.isSelected());
        rb_srcVmf.setEnabled(checkbox_allowVmfDownload.isSelected());
    }//GEN-LAST:event_checkbox_allowVmfDownloadActionPerformed

    private void rb_useNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_useNameActionPerformed
        XMLManager.setStringValue("usernameType", "displayName");
        tf_username.setEnabled(true);
    }//GEN-LAST:event_rb_useNameActionPerformed

    private void rb_useIpAddrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_useIpAddrActionPerformed
        XMLManager.setStringValue("usernameType", "ip");
        tf_username.setEnabled(false);
    }//GEN-LAST:event_rb_useIpAddrActionPerformed

    private void checkbox_filenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_filenameActionPerformed
        XMLManager.setBooleanValue("allowFilename", checkbox_filename.isSelected());
    }//GEN-LAST:event_checkbox_filenameActionPerformed

    private void checkbox_dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_dateActionPerformed
        XMLManager.setBooleanValue("allowDate", checkbox_filename.isSelected());
    }//GEN-LAST:event_checkbox_dateActionPerformed

    private void checkbox_nEntsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_nEntsActionPerformed
        XMLManager.setBooleanValue("allowNumEnts", checkbox_filename.isSelected());
    }//GEN-LAST:event_checkbox_nEntsActionPerformed

    private void checkbox_sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_sizeActionPerformed
        XMLManager.setBooleanValue("allowSize", checkbox_filename.isSelected());
    }//GEN-LAST:event_checkbox_sizeActionPerformed

    private void checkbox_timeElapsedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_timeElapsedActionPerformed
        XMLManager.setBooleanValue("allowTimeElapsed", checkbox_filename.isSelected());
    }//GEN-LAST:event_checkbox_timeElapsedActionPerformed

    private void tf_usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_usernameKeyReleased
        XMLManager.setStringValue("displayName", tf_username.getText());
    }//GEN-LAST:event_tf_usernameKeyReleased

    private void rb_srcVmfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_srcVmfActionPerformed
        XMLManager.setStringValue("vmfDownloadType", "src");
    }//GEN-LAST:event_rb_srcVmfActionPerformed

    private void rb_obfVmfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_obfVmfActionPerformed
        XMLManager.setStringValue("vmfDownloadType", "obf");
    }//GEN-LAST:event_rb_obfVmfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox checkbox_allowVmfDownload;
    private javax.swing.JCheckBox checkbox_consent;
    private javax.swing.JCheckBox checkbox_date;
    private javax.swing.JCheckBox checkbox_filename;
    private javax.swing.JCheckBox checkbox_nEnts;
    private javax.swing.JCheckBox checkbox_size;
    private javax.swing.JCheckBox checkbox_timeElapsed;
    private javax.swing.JCheckBox checkbox_username;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable rankingTable;
    private javax.swing.JRadioButton rb_obfVmf;
    private javax.swing.JRadioButton rb_srcVmf;
    private javax.swing.JRadioButton rb_useIpAddr;
    private javax.swing.JRadioButton rb_useName;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
