package views;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import utils.EntityDictionary;
import utils.SoundPlayer;

public class Conf_EntDictionary extends javax.swing.JPanel {
    private ConfigWindow parent;
    private ArrayList<String> edict = new ArrayList<>();
            
    public Conf_EntDictionary(ConfigWindow parent) {
        initComponents();
        this.parent = parent;

        loadConfig();
        populateList();
        //sortDictionary();
    }

    private void loadConfig() {
        edict = EntityDictionary.getEdict();
    }
    
    private void sortDictionary() {
        Collections.sort(edict);
        EntityDictionary.saveEdict(edict);
        populateList();
    }
    
    private void populateList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        for (String entity : edict) {
            listModel.addElement(entity);
        }
        
        list_eDict.setModel(listModel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_eDict = new javax.swing.JList<>();
        tf_addEntity = new javax.swing.JTextField();
        bt_addEntity = new javax.swing.JButton();
        bt_removeEntity = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        bt_resetDict = new javax.swing.JButton();
        bt_removeSelected = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(476, 380));
        setMinimumSize(new java.awt.Dimension(476, 380));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Entity Dictionary"));

        list_eDict.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_eDictMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list_eDict);

        bt_addEntity.setText("+");
        bt_addEntity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addEntityActionPerformed(evt);
            }
        });

        bt_removeEntity.setText("-");
        bt_removeEntity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_removeEntityActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(null);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("All entities defined in the list above will have both their targetname and origin obfuscated. Entities NOT in the list will exclusively have their targetname obfuscated.\n\nIf there is any entity that is not included in the dictionary whose origin you'd like to target, write its classname in the textbox and click on the plus button.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jTextArea1.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea1);

        bt_resetDict.setText("Reset");
        bt_resetDict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_resetDictActionPerformed(evt);
            }
        });

        bt_removeSelected.setText("Remove Selected");
        bt_removeSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_removeSelectedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tf_addEntity, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_addEntity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_removeEntity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(bt_removeSelected)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_resetDict))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_removeSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_addEntity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_addEntity)
                        .addComponent(bt_removeEntity)
                        .addComponent(bt_resetDict)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    private void bt_addEntityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addEntityActionPerformed
        if ((tf_addEntity.getText().isEmpty()) || (tf_addEntity.getText().isBlank())) {
            JOptionPane.showMessageDialog(parent, "Type something will you", "Warning", 2);
            return;
        }
        
        if (edict.contains(tf_addEntity.getText())) {
            JOptionPane.showMessageDialog(parent, "This entity already exists in the dictionary", "Warning", 2);
            return;
        }
        
        edict.add(tf_addEntity.getText());
        tf_addEntity.setText("");
        sortDictionary();
    }//GEN-LAST:event_bt_addEntityActionPerformed

    private void bt_removeEntityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_removeEntityActionPerformed
        edict.remove(tf_addEntity.getText());
        tf_addEntity.setText("");
        sortDictionary();
    }//GEN-LAST:event_bt_removeEntityActionPerformed

    private void bt_removeSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_removeSelectedActionPerformed
        int[] selectedEntities = list_eDict.getSelectedIndices();
        
        for (int index : selectedEntities) {
            edict.remove(index);
        }
        
        sortDictionary();
        //list_eDict.setSelectedIndex(selectedEntities[0]);
    }//GEN-LAST:event_bt_removeSelectedActionPerformed

    private void list_eDictMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_eDictMouseClicked
        if (evt.getClickCount() == 2 && evt.getButton() == evt.BUTTON1) {
            int confirmDelete = JOptionPane.showConfirmDialog(this, "Remove selected entry from the entity dictionary?", "Entity Dictionary", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == 0) {
                edict.remove(list_eDict.getSelectedValue());
                sortDictionary();
            }
        }
    }//GEN-LAST:event_list_eDictMouseClicked

    private void bt_resetDictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_resetDictActionPerformed
            int confirmDelete = JOptionPane.showConfirmDialog(this, "The entity dictionary will be factory reset, is this okay?", "Entity Dictionary", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == 0) {
                EntityDictionary.rebuildEdict();
                edict = EntityDictionary.getEdict();
                sortDictionary();
            }
    }//GEN-LAST:event_bt_resetDictActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addEntity;
    private javax.swing.JButton bt_removeEntity;
    private javax.swing.JButton bt_removeSelected;
    private javax.swing.JButton bt_resetDict;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JList<String> list_eDict;
    private javax.swing.JTextField tf_addEntity;
    // End of variables declaration//GEN-END:variables
}
