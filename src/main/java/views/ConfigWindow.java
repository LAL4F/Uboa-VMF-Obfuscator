/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JOptionPane;

public class ConfigWindow extends javax.swing.JDialog {
    public MainWindow parent;
    private CardLayout navigator;
    
    private Conf_AppearancePanel appearancePanel;
    private Conf_PopupPanel popupPanel;
    private Conf_SoundPanel soundPanel;
    
    public ConfigWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        this.parent = (MainWindow) parent;
        
        loadPanels();
        navigate("appearancePanel");
    }

    private void loadPanels() {
        navigator = (CardLayout)panelContainer.getLayout();

        //Instance the panels
        appearancePanel = new Conf_AppearancePanel(this);
        soundPanel = new Conf_SoundPanel(this);
        popupPanel = new Conf_PopupPanel(this);
        
        //Add panels to container
        panelContainer.add(appearancePanel, "appearancePanel");
        panelContainer.add(soundPanel, "soundPanel");
        panelContainer.add(popupPanel, "popupPanel");
    }
    
    private void navigate(String nombrePanel) {
        navigator.show(panelContainer, nombrePanel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        optionList = new javax.swing.JList<>();
        panelContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Preferences");
        setMaximumSize(new java.awt.Dimension(600, 400));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setResizable(false);

        optionList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Appearance", "Sounds", "Popups", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        optionList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        optionList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                optionListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(optionList);

        panelContainer.setMaximumSize(new java.awt.Dimension(480, 388));
        panelContainer.setMinimumSize(new java.awt.Dimension(480, 388));
        panelContainer.setPreferredSize(new java.awt.Dimension(480, 388));
        panelContainer.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optionListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_optionListValueChanged
        switch (optionList.getSelectedValue()) {
            case "Appearance":
                navigate("appearancePanel");
                break;
            case "Sounds":
                navigate("soundPanel");
                break;
            case "Popups":
                navigate("popupPanel");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_optionListValueChanged

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
            java.util.logging.Logger.getLogger(ConfigWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConfigWindow dialog = new ConfigWindow(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> optionList;
    private javax.swing.JPanel panelContainer;
    // End of variables declaration//GEN-END:variables
}
