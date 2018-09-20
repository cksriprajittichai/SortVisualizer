package def;

import sorters.BubbleSorter;
import sorters.InsertionSorter;

import javax.swing.*;
import java.util.Locale;

public final class Controller {

    private final AnimationPanel animationPanel = new AnimationPanel();
    private Sorter sorter;

    public Controller() {
        animationPanel.setNums(NumberListFactory.getSmallShuffled());
    }

    public void setSorter(final String sorterName) {
        final UiHelper uiHelper = animationPanel.getUiHelper();
        switch (sorterName.toLowerCase(Locale.US)) {
            case "bubble sort":
                sorter = new BubbleSorter(uiHelper);
                break;
            case "insertion sort":
                sorter = new InsertionSorter(uiHelper);
                break;
//            case "merge sort":
//                sorter = new MergeSorter(uiHelper);
//                break;
        }
    }

    public void sort() {
        animationPanel.sort(sorter);
    }

    public void shuffleDataSet() {
        animationPanel.shuffleDataSet();
    }

    public void setMsSleep(final int msSleep) {
        sorter.setMsSleep(msSleep);
    }

    public JPanel getAnimationPanel() {
        return animationPanel;
    }

}
