package def;

import sorters.BubbleSorter;
import sorters.InsertionSorter;
import sorters.MergeSorter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public final class AppWindow {

    private static final Dimension WINDOW_DIMENSION = new Dimension(800, 800);

    private static final String[] SORT_NAMES = {"Bubble Sort", "Insertion Sort", "Merge Sort"};

    private final JFrame frame;
    private final JPanel topPanel;
    private final JPanel buttonPanel;
    private final AnimationPanel animationPanel;

    public AppWindow() {
        frame = new JFrame("Sorter Visualization");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(WINDOW_DIMENSION);
        frame.pack(); // Vital
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        topPanel = new JPanel();
        topPanel.setPreferredSize(WINDOW_DIMENSION);
        // BoxLayout along the y-axis
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        frame.add(topPanel);


        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(800, 200));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.GREEN);
        final JComboBox sortNamesComboBox = new JComboBox<>(SORT_NAMES);
        buttonPanel.add(sortNamesComboBox);

        /**
         *
         */
        final ArrayList<Integer> shuffledNums = NumberListFactory.getSmallShuffled();

        final JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                animationPanel.sort(getSelectedSorter(String.valueOf(sortNamesComboBox.getSelectedItem())));
            }
        });
        buttonPanel.add(submitBtn);

        final JButton showAnimationBtn = new JButton("Show Animation");
        showAnimationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationPanel.showAnimation();
            }
        });
        buttonPanel.add(showAnimationBtn);

        topPanel.add(buttonPanel);


        animationPanel = new AnimationPanel(shuffledNums);
        topPanel.add(animationPanel);
    }

    private Sorter getSelectedSorter(final String sorterName) {
        switch (sorterName.toLowerCase(Locale.US)) {
            case "bubble sort":
                return new BubbleSorter(animationPanel);
            case "insertion sort":
                return new InsertionSorter(animationPanel);
            case "merge sort":
                return new MergeSorter(animationPanel);
            default:
                return null;
        }
    }

    public void startApplication() {
        frame.setVisible(true);
    }

}
