package swing_components;

import def.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import static def.SortingConstants.MAX_SPEED;
import static def.SortingConstants.MIN_SPEED;

public final class MenuFrame extends JFrame {

    private static final List<String> SORT_NAMES = SorterFactory.getSorterNames();
    private static final int DEF_GBC_FILL = GridBagConstraints.HORIZONTAL;
    private static final int DEF_GBC_ANCHOR = GridBagConstraints.CENTER;
    private static final Insets NO_INSETS = new Insets(0, 0, 0, 0);

    /**
     * A cell that is under another cell should use this as the top inset.
     */
    private static final int INSET_BETWEEN_ROWS = 20;

    /**
     * A cell that starts (top left position of component) in cell 2 (0-based)
     * or greater should use this as the left inset.
     */
    private static final int INSET_VERTICAL_MIDDLE_GAP = 10;

    public MenuFrame() {
        setTitle("Sort Visualizer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        final JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = DEF_GBC_FILL;
        gbc.anchor = DEF_GBC_ANCHOR;


        final JLabel sortLabel = new JLabel("Sort");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.ipadx = 4; // Create small space between label and combo box
        menuPanel.add(sortLabel, gbc);
        // Restore
        gbc.fill = DEF_GBC_FILL;
        gbc.anchor = DEF_GBC_ANCHOR;
        gbc.ipadx = 0;

        final String[] SORT_NAMES_ARR = new String[SORT_NAMES.size()];
        SORT_NAMES.toArray(SORT_NAMES_ARR);
        final JComboBox sortNamesComboBox = new JComboBox<>(SORT_NAMES_ARR);
        gbc.gridx = 1;
        gbc.gridy = 0;
        menuPanel.add(sortNamesComboBox, gbc);


        final JLabel speedLabel = new JLabel("Speed");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.ipadx = 4; // Create small space between label and text label
        gbc.insets = new Insets(INSET_BETWEEN_ROWS, 0, 0, 0);
        menuPanel.add(speedLabel, gbc);
        // Restore
        gbc.fill = DEF_GBC_FILL;
        gbc.anchor = DEF_GBC_ANCHOR;
        gbc.ipadx = 0;
        gbc.insets = NO_INSETS;


        // Needed to setup speedTextField. Will be setup after speedTextField
        final JSlider speedSlider =
                new JSlider(JSlider.HORIZONTAL, SortingConstants.MIN_SPEED,
                        SortingConstants.MAX_SPEED, SortingConstants.INITIAL_SPEED);
        speedSlider.setPaintTicks(true);
        speedSlider.setMajorTickSpacing(200);
        speedSlider.setMinorTickSpacing(100);
        speedSlider.setPaintLabels(true);

        final JTextField speedTextField =
                new JTextField(String.valueOf(speedSlider.getValue()));
        speedTextField.setPreferredSize(speedLabel.getPreferredSize());
        speedTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                boolean isNumber = false;

                final String text = speedTextField.getText();
                if (text.matches("\\d+")) {
                    isNumber = true;

                    if (text.length() <= String.valueOf(MAX_SPEED).length()) {
                        final int val = Integer.parseInt(text);
                        if (val <= MAX_SPEED && val >= MIN_SPEED) {
                            speedSlider.setValue(val);
                            return;
                        }
                    }
                }

                if (text.isEmpty()) {
                    speedSlider.setValue(MIN_SPEED);
                    /* speedSlider.setValue method also sets the value of
                     * speedTextField. Undo this; make speedTextField empty. */
                    speedTextField.setText("");
                } else if (isNumber) {
                    // text is a number that is out of range
                    if (Integer.parseInt(text) < MIN_SPEED) {
                        speedSlider.setValue(0);
                        speedTextField.setText("");
                    } else {
                        // Number is gt MAX_SPEED
                        // Remove digit that is making number gt MAX_SPEED
                        speedTextField.setText(text.substring(0, text.length() - 1));
                        speedSlider.setValue(Integer.parseInt(speedTextField.getText()));
                    }
                } else {
                    // text is not a number
                    speedSlider.setValue(0);
                    speedTextField.setText("");
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(INSET_BETWEEN_ROWS, 0, 0, 0);
        menuPanel.add(speedTextField, gbc);
        // Restore
        gbc.fill = DEF_GBC_FILL;
        gbc.anchor = DEF_GBC_ANCHOR;
        gbc.insets = NO_INSETS;

        speedSlider.addChangeListener(
                e -> speedTextField.setText(String.valueOf(speedSlider.getValue())));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Close to label and text field
        // No insets on top so that slider will be close to text field
        menuPanel.add(speedSlider, gbc);
        // Restore
        gbc.gridwidth = 1;
        gbc.anchor = DEF_GBC_ANCHOR;


        final JLabel dataSizeLabel = new JLabel("Data Size");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(INSET_BETWEEN_ROWS, 0, 0, 0);
        menuPanel.add(dataSizeLabel, gbc);
        // Restore
        gbc.insets = NO_INSETS;

        final JRadioButton tinySizeRadioBtn = new JRadioButton("Tiny");
        final JRadioButton smallSizeRadioBtn = new JRadioButton("Small");
        final JRadioButton mediumSizeRadioBtn = new JRadioButton("Medium");
        final JRadioButton largeSizeRadioBtn = new JRadioButton("Large", true);
        final JRadioButton[] dataSizeRadioBtns = {
                tinySizeRadioBtn,
                smallSizeRadioBtn,
                mediumSizeRadioBtn,
                largeSizeRadioBtn
        };
        // Must be in button group to implement radio button behavior
        final ButtonGroup dataSizeRadioBtnGroup = new ButtonGroup();
        for (final JRadioButton b : dataSizeRadioBtns) {
            dataSizeRadioBtnGroup.add(b);
        }
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(INSET_BETWEEN_ROWS, 0, 0, 0);
        menuPanel.add(tinySizeRadioBtn, gbc);
        // Restore
        gbc.insets = NO_INSETS;
        gbc.gridy = 2;
        menuPanel.add(smallSizeRadioBtn, gbc);
        gbc.gridy = 3;
        menuPanel.add(mediumSizeRadioBtn, gbc);
        gbc.gridy = 4;
        menuPanel.add(largeSizeRadioBtn, gbc);


        final JLabel dataTypeLabel = new JLabel("Data Type");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(INSET_BETWEEN_ROWS, INSET_VERTICAL_MIDDLE_GAP, 0, 0);
        menuPanel.add(dataTypeLabel, gbc);
        // Restore
        gbc.insets = NO_INSETS;

        final JRadioButton randomDataTypeRadioBtn = new JRadioButton("Random", true);
        final JRadioButton sortedDataTypeRadioBtn = new JRadioButton("Ascending");
        final JRadioButton reverseSortedDataTypeRadioBtn = new JRadioButton("Descending");
        final JRadioButton[] dataTypeRadioBtns = {
                randomDataTypeRadioBtn,
                sortedDataTypeRadioBtn,
                reverseSortedDataTypeRadioBtn
        };
        // Must be in button group to implement radio button behavior
        final ButtonGroup dataTypeRadioBtnGroup = new ButtonGroup();
        for (final JRadioButton b : dataTypeRadioBtns) {
            dataTypeRadioBtnGroup.add(b);
        }
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.insets = new Insets(INSET_BETWEEN_ROWS, 0, 0, 0);
        menuPanel.add(randomDataTypeRadioBtn, gbc);
        // Restore
        gbc.insets = NO_INSETS;
        gbc.gridy = 2;
        menuPanel.add(sortedDataTypeRadioBtn, gbc);
        gbc.gridy = 3;
        menuPanel.add(reverseSortedDataTypeRadioBtn, gbc);


        final JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            new Thread(() -> {
                final String sorterName = String.valueOf(sortNamesComboBox.getSelectedItem());
                final int speed = speedSlider.getValue();

                int dataTypeConstant = -1;
                for (int i = 0; i < dataTypeRadioBtns.length; i++) {
                    if (dataTypeRadioBtns[i].isSelected()) {
                        dataTypeConstant = i;
                        break;
                    }
                }

                int dataSizeConstant = -1;
                for (int i = 0; i < dataSizeRadioBtns.length; i++) {
                    if (dataSizeRadioBtns[i].isSelected()) {
                        dataSizeConstant = i;
                        break;
                    }
                }

                launchAnimation(sorterName, dataSizeConstant, dataTypeConstant, speed);
            }).start();
        });
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = NO_INSETS;
        menuPanel.add(submitBtn, gbc);
        // Restore
        gbc.insets = NO_INSETS;


        setContentPane(menuPanel);
        pack();
    }

    private void launchAnimation(final String sorterName, final int dataSizeConstant,
                                 final int dataTypeConstant, final int speed) {
        final AnimationFrame animationFrame = new AnimationFrame(dataSizeConstant);
        final UiHelper uiHelper = animationFrame.getUiHelper();
        final List<Integer> nums = NumberListFactory.getData(dataSizeConstant, dataTypeConstant);
        final SortAnimation sortAnimation =
                new SortAnimation(nums, sorterName, speed, uiHelper);
        animationFrame.loadAnimation(sortAnimation, dataSizeConstant, dataTypeConstant);

        // Delay before starting the sort
        try {
            Thread.sleep(SortingConstants.MS_DELAY_BEFORE_STARTING_SORT);
        } catch (final InterruptedException ie) {
            ie.printStackTrace();
        }

        animationFrame.startAnimation();
    }

    public void startApplication() {
        setVisible(true);
    }

}
