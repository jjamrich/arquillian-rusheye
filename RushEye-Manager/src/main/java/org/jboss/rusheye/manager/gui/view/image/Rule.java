/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.rusheye.manager.gui.view.image;

import java.awt.*;
import javax.swing.JComponent;

/**
 * Rule displayed above and on the left of every image.
 *
 * @author Jakub D.
 */
public class Rule extends JComponent {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static final int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
    private int SIZE = 30;
    private int orientation;
    private boolean isMetric;
    private int increment;
    private int units;
    private double scaleMod = 1;

    public Rule(int orientation, boolean m, double scale) {
        this.orientation = orientation;
        isMetric = m;
        setIncrementAndUnits();
        scaleMod = scale;
    }

    public Rule(int orientation, boolean m) {
        this.orientation = orientation;
        isMetric = m;
        setIncrementAndUnits();
    }

    public void setIsMetric(boolean isMetric) {
        this.isMetric = isMetric;
        setIncrementAndUnits();
        repaint();
    }

    private void setIncrementAndUnits() {
        if (isMetric) {
            units = (int) ((double) dpi / 2.54);
            increment = units;
        } else {
            units = 100;
            increment = units / 2;
        }
    }

    public boolean isMetric() {
        return this.isMetric;
    }

    public int getIncrement() {
        return increment;
    }

    public void setPreferredHeight(int ph) {
        setPreferredSize(new Dimension(SIZE, ph));
    }

    public void setPreferredWidth(int pw) {
        setPreferredSize(new Dimension(pw, SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Rectangle clip = g.getClipBounds();

        g.setColor(new Color(200, 200, 200));
        g.fillRect(clip.x, clip.y, clip.width, clip.height);

        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g.setColor(Color.black);

        int end = 0;
        int start = 0;
        int tickLength = 0;
        String text = null;

        if (orientation == Rule.HORIZONTAL) {
            start = (clip.x / increment) * increment;
            end = (((clip.x + clip.width) / increment) + 1)
                    * increment;
        } else {
            start = (clip.y / increment) * increment;
            end = (((clip.y + clip.height) / increment) + 1)
                    * increment;
        }

        if (start == 0) {
            text = Integer.toString(0) + (isMetric ? " cm" : " px");
            tickLength = 5;
            if (orientation == Rule.HORIZONTAL) {
                g.drawLine(0, SIZE - 1, 0, SIZE - tickLength - 1);
                g.drawString(text, 2, 21);
            } else {
                g.drawLine(SIZE - 1, 0, SIZE - tickLength - 1, 0);
                g.drawString(text, 5, 10);
            }
            text = null;
            start = increment;
        }

        for (int i = start; i < end; i += increment) {

            int nr = i / units;
            if (!isMetric)
                nr *= 100;
            text = Integer.toString((int) (nr / scaleMod));

            if (i % units == 0) {
                tickLength = 5;
            } else {
                tickLength = 3;
                text = "";
            }

            if (orientation == Rule.HORIZONTAL) {
                g.drawLine(i, SIZE - 1, i, SIZE - tickLength - 1);
                g.drawString(text, i - 3, 21);
            } else {
                g.drawLine(SIZE - 1, i, SIZE - tickLength - 1, i);
                g.drawString(text, 5, i + 3);
            }
        }
    }
}
