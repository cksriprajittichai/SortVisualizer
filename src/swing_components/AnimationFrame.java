package swing_components;

import def.SortAnimation;
import def.SortingConstants;
import def.UiHelper;

import javax.swing.*;

final class AnimationFrame extends JFrame {

    private final AnimationPanel animationPanel;
    private final int dataSizeConstant;

    AnimationFrame(final int dataSizeConstant) {
        animationPanel = new AnimationPanel(dataSizeConstant);
        this.dataSizeConstant = dataSizeConstant;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setPreferredSize(AnimationPanel.DIMENSION);
        setResizable(false);
        setContentPane(animationPanel);
        setVisible(true);
        pack();
    }

    void loadAnimation(final SortAnimation sortAnimation) {
        animationPanel.loadAnimation(sortAnimation);

        setTitle(String.format(
                "%s - speed: %d/%d",
                sortAnimation.getSorterName(),
                sortAnimation.getSpeed(),
                SortingConstants.MAX_SPEED));
    }

    void startAnimation() {
        animationPanel.getSortAnimation().startAnimation();
    }

    UiHelper getUiHelper() {
        return new UiHelper(animationPanel.getGraphics(), dataSizeConstant);
    }

}
