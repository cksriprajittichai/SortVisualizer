package def;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public final class AnimationPanel extends JPanel {

    private ArrayList<Integer> nums = new ArrayList<>();
    
    public AnimationPanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(UiHelper.BACKGROUND_COLOR);
    }

    /**
     * Don't use a UiHelper to draw, should use passed in Graphics
     *
     * @param g
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.setColor(UiHelper.COLUMN_COLOR);
        for (int i = 0; i < nums.size(); i++) {
            drawColumn(g, i, nums.get(i));
        }
    }

    private void drawColumn(final Graphics g, final int x, final int height) {
        g.fillRect(x * UiHelper.COLUMN_WIDTH, 600 - height, UiHelper.COLUMN_WIDTH, height);
    }

    public void sort(final Sorter sorter) {
        sorter.sort(nums);
    }

    public void shuffleDataSet() {
        Collections.shuffle(nums);
        repaint();
    }

    public UiHelper getUiHelper() {
        return new UiHelper(getGraphics());
    }

    public void setNums(final ArrayList<Integer> nums) {
        this.nums = nums;
    }

}
