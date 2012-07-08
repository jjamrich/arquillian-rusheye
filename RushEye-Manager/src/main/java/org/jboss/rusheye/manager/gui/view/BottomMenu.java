/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.rusheye.manager.gui.view;

import org.jboss.rusheye.manager.gui.view.image.ImagePool;
import org.jboss.rusheye.manager.gui.view.image.ImageView;

/**
 *
 * @author hcube
 */
public class BottomMenu extends javax.swing.JPanel {

    private ImageView view;

    public BottomMenu(ImageView view, String viewType) {
        this.view = view;
        initComponents();

        if (viewType.equals(ImagePool.PATTERN))
            patternRadio.setSelected(true);
        else if (viewType.equals(ImagePool.SAMPLE))
            sampleRadio.setSelected(true);
        else if (viewType.equals(ImagePool.DIFF))
            diffRadio.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        patternRadio = new javax.swing.JRadioButton();
        sampleRadio = new javax.swing.JRadioButton();
        diffRadio = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(1, 0));

        buttonGroup1.add(patternRadio);
        patternRadio.setText("Pattern");
        patternRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternRadioActionPerformed(evt);
            }
        });
        add(patternRadio);

        buttonGroup1.add(sampleRadio);
        sampleRadio.setText("Sample");
        sampleRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleRadioActionPerformed(evt);
            }
        });
        add(sampleRadio);

        buttonGroup1.add(diffRadio);
        diffRadio.setText("Diff");
        diffRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diffRadioActionPerformed(evt);
            }
        });
        add(diffRadio);

        jCheckBox1.setText("Mask");
        jCheckBox1.setEnabled(false);
        add(jCheckBox1);

        jButton1.setText("Focus");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
    }// </editor-fold>//GEN-END:initComponents

    private void sampleRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleRadioActionPerformed
        if (sampleRadio.isSelected()) {
            view.changeImage(ImagePool.SAMPLE);
            jButton1.setEnabled(false);
        }
    }//GEN-LAST:event_sampleRadioActionPerformed

    private void patternRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patternRadioActionPerformed
        if (patternRadio.isSelected()) {
            view.changeImage(ImagePool.PATTERN);
            jButton1.setEnabled(false);
        }
    }//GEN-LAST:event_patternRadioActionPerformed

    private void diffRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diffRadioActionPerformed
        if (diffRadio.isSelected()) {
            view.changeImage(ImagePool.DIFF);
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_diffRadioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        view.focus();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton diffRadio;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JRadioButton patternRadio;
    private javax.swing.JRadioButton sampleRadio;
    // End of variables declaration//GEN-END:variables
}
