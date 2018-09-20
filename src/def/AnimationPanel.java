package def;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class AnimationPanel extends JPanel {

    static final Dimension DIMENSION = new Dimension(800, 600);
    private SortAnimation sortAnimation;
    private ArrayList<Integer> nums;

    public AnimationPanel() {
        setPreferredSize(DIMENSION);
        setBackground(UiHelper.BACKGROUND_COLOR);
    }

    public void loadAnimation(final SortAnimation sortAnimation) {
        this.sortAnimation = sortAnimation;
        nums = sortAnimation.getNums();
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

    public SortAnimation getSortAnimation() {
        return sortAnimation;
    }

}
