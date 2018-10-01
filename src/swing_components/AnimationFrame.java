package swing_components;

import def.*;

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

    void loadAnimation(final SortAnimation sortAnimation, final int dataSizeConstant,
                       final int dataTypeConstant) {
        animationPanel.loadAnimation(sortAnimation);

        setTitle(String.format(
                "%s - Speed: %d/%d - Size: %s - Type: %s",
                sortAnimation.getSorterName(),
                sortAnimation.getSpeed(),
                SortingConstants.MAX_SPEED,
                DataSizeConstants.getDisplayName(dataSizeConstant),
                DataTypeConstants.getDisplayName(dataTypeConstant)));
    }

    void startAnimation() {
        animationPanel.getSortAnimation().startAnimation();
    }

    UiHelper getUiHelper() {
        return new UiHelper(animationPanel.getGraphics(), dataSizeConstant);
    }

}
