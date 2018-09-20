package def;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class MenuFrame extends JFrame {

    private static final Dimension FRAME_DIM = new Dimension(400, 100);
    private static final String[] SORT_NAMES = {"Bubble Sort", "Insertion Sort"};

    public MenuFrame() {
        setTitle("Sort Visualization");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setPreferredSize(FRAME_DIM);
        pack(); // Vital
        setResizable(false);
        setLocationRelativeTo(null);


        final JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(FRAME_DIM);
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBackground(Color.GREEN);

        final JComboBox sortNamesComboBox = new JComboBox<>(SORT_NAMES);
        btnPanel.add(sortNamesComboBox);

        final JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 8);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(1);
        btnPanel.add(speedSlider);

        final JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            new Thread(() -> {
                final String sorterName = String.valueOf(sortNamesComboBox.getSelectedItem());

                // speedSlider displays 1 through 10, but a larger msSleep value
                // corresponds to a slower animation. We want 1 to the slowest
                // animation, and 10 to be the fastest
                int msSleep = 11 - speedSlider.getValue();
                // Magnify msSleep because a ms is tiny
                msSleep = (int) Math.pow(msSleep, 3);

                createAnimation(NumberListFactory.getSmallShuffled(), sorterName, msSleep);


//                sortAnimation.setSorter(String.valueOf(sortNamesComboBox.getSelectedItem()));
//
//                // speedSlider displays 1 through 10, but a larger msSleep value
//                // corresponds to a slower animation. We want 1 to the slowest
//                // animation, and 10 to be the fastest
//                int msSleep = 11 - speedSlider.getValue();
//                // Magnify msSleep because a ms is tiny
//                msSleep = (int) Math.pow(msSleep, 3);
//                sortAnimation.setMsSleep(msSleep);
//
//                sortAnimation.startAnimation();
            }).start();
        });
        btnPanel.add(submitBtn);

        setContentPane(btnPanel);
    }

    private void createAnimation(final ArrayList<Integer> nums, final String sorterName,
                                 final int msSleep) {
        final AnimationFrame animationFrame = new AnimationFrame();
        animationFrame.loadAnimation(
                new SortAnimation(nums, sorterName, msSleep, animationFrame.getUiHelper()));

        try {
            Thread.sleep(3000);
        } catch (final InterruptedException ie) {
            ie.printStackTrace();
        }

        animationFrame.startAnimation();
    }

    public void startApplication() {
        setVisible(true);
    }

}
