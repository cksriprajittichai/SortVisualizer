package def;

import javax.swing.*;
import java.awt.*;

public final class AppWindow {

    private static final Dimension WINDOW_DIMENSION = new Dimension(800, 800);
    private static final String[] SORT_NAMES = {"Bubble Sort", "Insertion Sort"};

    private final JFrame frame = new JFrame("Sort Visualization");
    private final Controller controller = new Controller();

    public AppWindow() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(WINDOW_DIMENSION);
        frame.pack(); // Vital
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        final JPanel rootPanel = new JPanel();
        rootPanel.setPreferredSize(WINDOW_DIMENSION);
        // BoxLayout along the y-axis
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        frame.add(rootPanel);


        final JPanel btnPanel = new JPanel();
        btnPanel.setPreferredSize(new Dimension(800, 200));
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setBackground(Color.GREEN);

        final JComboBox sortNamesComboBox = new JComboBox<>(SORT_NAMES);
        btnPanel.add(sortNamesComboBox);

        final JButton shuffleBtn = new JButton("Shuffle");
        shuffleBtn.addActionListener(e -> controller.shuffleDataSet());
        btnPanel.add(shuffleBtn);

        final JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(1);
        btnPanel.add(speedSlider);

        final JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            controller.setSorter(String.valueOf(sortNamesComboBox.getSelectedItem()));
            controller.setMsSleep((int) Math.pow(speedSlider.getValue(), 3));
            controller.sort();
        });
        btnPanel.add(submitBtn);

        rootPanel.add(btnPanel);


        rootPanel.add(controller.getAnimationPanel());
    }

    public void startApplication() {
        frame.setVisible(true);
    }

}
