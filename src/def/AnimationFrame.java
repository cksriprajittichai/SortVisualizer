package def;

import javax.swing.*;

public final class AnimationFrame extends JFrame {

    private final AnimationPanel animationPanel = new AnimationPanel();

    public AnimationFrame() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setPreferredSize(AnimationPanel.DIMENSION);
        pack(); // Vital
        setResizable(false);
        setContentPane(animationPanel);
        setVisible(true);
    }

    public void loadAnimation(final SortAnimation sortAnimation) {
        animationPanel.loadAnimation(sortAnimation);
    }

    public void startAnimation() {
        animationPanel.getSortAnimation().startAnimation();
    }

    public UiHelper getUiHelper() {
        return new UiHelper(animationPanel.getGraphics());
    }

}
