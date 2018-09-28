package swing_components;

import def.SortAnimation;
import def.UiHelper;

import javax.swing.*;

final class AnimationFrame extends JFrame {

    private final AnimationPanel animationPanel = new AnimationPanel();

    AnimationFrame() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setPreferredSize(AnimationPanel.DIMENSION);
        pack(); // Vital
        setResizable(false);
        setContentPane(animationPanel);
        setVisible(true);
    }

    void loadAnimation(final SortAnimation sortAnimation) {
        animationPanel.loadAnimation(sortAnimation);

        setTitle(String.format(
                "%s - speed: %d/1000",
                sortAnimation.getSorterName(),
                sortAnimation.getSpeed()));
    }

    void startAnimation() {
        animationPanel.getSortAnimation().startAnimation();
    }

    UiHelper getUiHelper() {
        return new UiHelper(animationPanel.getGraphics());
    }

}
