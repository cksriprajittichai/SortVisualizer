package swing_components;

import def.NumberListFactory;
import def.SortAnimation;
import def.UiHelper;

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

        final JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 750);
        btnPanel.add(speedSlider);

        final JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            new Thread(() -> {
                final String sorterName = String.valueOf(sortNamesComboBox.getSelectedItem());
                final int speed = speedSlider.getValue();

                launchAnimation(NumberListFactory.getSmallShuffled(), sorterName, speed);
            }).start();
        });
        btnPanel.add(submitBtn);

        setContentPane(btnPanel);
    }

    private void launchAnimation(final ArrayList<Integer> nums, final String sorterName,
                                 final int msSleep) {
        final AnimationFrame animationFrame = new AnimationFrame();
        final UiHelper uiHelper = animationFrame.getUiHelper();
        final SortAnimation sortAnimation =
                new SortAnimation(nums, sorterName, msSleep, uiHelper);
        animationFrame.loadAnimation(sortAnimation);

        // Show the initial data for 3 seconds before beginning the sort
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
