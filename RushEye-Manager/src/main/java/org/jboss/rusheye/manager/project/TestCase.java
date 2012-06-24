/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.rusheye.manager.project;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import org.jboss.rusheye.core.DefaultImageComparator;
import org.jboss.rusheye.manager.Main;
import org.jboss.rusheye.manager.gui.view.image.ImagePool;
import org.jboss.rusheye.parser.DefaultConfiguration;
import org.jboss.rusheye.result.ResultEvaluator;
import org.jboss.rusheye.suite.ComparisonResult;
import org.jboss.rusheye.suite.Configuration;
import org.jboss.rusheye.suite.ResultConclusion;

/**
 *
 * @author hcube
 */
public class TestCase extends TestNode {

    private ResultConclusion conclusion;
    private boolean checked;
    private String filename;
    private ImagePool pool;

    public TestCase() {
        super();
        pool = new ImagePool();
    }

    public ResultConclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(ResultConclusion conclusion) {
        this.conclusion = conclusion;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ImagePool getPool() {
        return pool;
    }

    public BufferedImage getImage(String key) {
        BufferedImage img = pool.get(key);
        if (img == null) {
            if (key.equals(ImagePool.PATTERN)) {
                pool.put(key, Main.mainProject.getPatternPath() + "/" + filename);
                return getImage(key);
            } else if (key.equals(ImagePool.SAMPLE)) {
                pool.put(key, Main.mainProject.getSamplesPath() + "/" + filename);
                return getImage(key);
            } else {
                Configuration configuration = new DefaultConfiguration();
                ComparisonResult result = new DefaultImageComparator().compare(getImage(ImagePool.PATTERN), getImage(ImagePool.SAMPLE), configuration.getPerception(),
                        configuration.getMasks());

                conclusion = new ResultEvaluator().evaluate(configuration.getPerception(), result);

                BufferedImage diff = result.getDiffImage();

                pool.put(key, diff);

                return getImage(key);
            }
        } else {
            return img;
        }
    }

    public String toString() {
        return this.getName();
    }

    public TestCase findTest(String path) {
        if (this.getPath().equals(path)) {
            return this;
        } else {
            for (int i = 0; i < this.getChildCount(); ++i) {
                TestCase child = (TestCase) this.getChildAt(i);
                TestCase result = child.findTest(path);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setVisibility(ResultConclusion con) {
        if (conclusion == null || conclusion == con) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }

        for (int i = 0; i < this.getAllChildren().size(); ++i) {
            ((TestCase) this.getAllChildren().get(i)).setVisibility(con);
        }

        collapseInvalidLeafs();
    }

    private void collapseInvalidLeafs() {
        if (conclusion == null && this.getChildCount() == 0) {
            this.setVisible(false);
        }
        for (int i = 0; i < this.getAllChildren().size(); ++i) {
            ((TestCase) this.getAllChildren().get(i)).collapseInvalidLeafs();
        }
    }

    public void setAllVisible() {
        this.setVisible(true);

        for (int i = 0; i < this.getAllChildren().size(); ++i) {
            ((TestCase) this.getAllChildren().get(i)).setAllVisible();
        }
    }
}