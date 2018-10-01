package swing_components;

import def.SortAnimation;
import def.UiHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

final class AnimationPanel extends JPanel {

    static final Dimension DIMENSION = new Dimension(800, 800);
    private SortAnimation sortAnimation;
    private List<Integer> nums;
    private boolean sortAnimationIsLoaded = false;
    private final int colWidth;

    AnimationPanel(final int dataSizeConstant) {
        setPreferredSize(DIMENSION);
        setBackground(UiHelper.BACKGROUND_COLOR);

        colWidth = UiHelper.calculateColumnWidthFromDataSize(dataSizeConstant);
    }

    void loadAnimation(final SortAnimation sortAnimation) {
        this.sortAnimation = sortAnimation;
        nums = sortAnimation.getNums();

        // The initial columns are only painted if sortAnimationIsLoaded
        if (!sortAnimationIsLoaded) {
            sortAnimationIsLoaded = true;
            repaint();
        }
    }

    /*
     * Don't use a UiHelper to draw, should use passed in Graphics
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (sortAnimationIsLoaded) {
            g.setColor(UiHelper.COLUMN_COLOR);
            for (int i = 0; i < nums.size(); i++) {
                drawColumn(g, i, nums.get(i));
            }
        }
    }

    private void drawColumn(final Graphics g, final int x, final int height) {
        g.fillRect(x * colWidth, 800 - height, colWidth, height);
    }

    SortAnimation getSortAnimation() {
        return sortAnimation;
    }

}
