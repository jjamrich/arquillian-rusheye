/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.rusheye.manager.gui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.jboss.rusheye.manager.project.testcase.TestCase;
import org.jboss.rusheye.suite.ResultConclusion;

/**
 * Custom renderer for JTree. Manages icons.
 * 
 * @author Jakub D.
 */
public class CustomTreeRenderer extends DefaultTreeCellRenderer {

    ImageIcon same, notTested, diff, pSame;

    /**
     * COstructor where we initialize icons.
     */
    public CustomTreeRenderer() {
        same = new ImageIcon("same.png");
        notTested = new ImageIcon("not_tested.png");
        diff = new ImageIcon("diff.png");
        pSame = new ImageIcon("pSame.png");
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,
                expanded, leaf, row, hasFocus);

        if (value instanceof TestCase) {
            TestCase node = (TestCase) value;
            if (node.isLeaf()) {
                if (node.getConclusion() == ResultConclusion.SAME)
                    setIcon(same);
                else if (node.getConclusion() == ResultConclusion.PERCEPTUALLY_SAME)
                    setIcon(pSame);
                else if (node.getConclusion() == ResultConclusion.DIFFER)
                    setIcon(diff);
                else if (node.getConclusion() == ResultConclusion.NOT_TESTED || node.getConclusion() == null)
                    setIcon(notTested);
            }
        }
        return this;
    }
}