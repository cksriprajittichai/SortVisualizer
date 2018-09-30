package def;

import sorters.*;

import java.util.ArrayList;
import java.util.Locale;

public final class SortAnimation {

    private final ArrayList<Integer> nums;
    private final Sorter sorter;
    private final int speed;

    public SortAnimation(final ArrayList<Integer> nums, final String sorterName,
                         final int speed, final UiHelper uiHelper) {
        this.nums = nums;
        this.sorter = constructSorterFromName(sorterName, speed, uiHelper);
        this.speed = speed;
    }

    public void startAnimation() {
        sorter.sort(nums);
    }

    private Sorter constructSorterFromName(
            final String sorterName, final int speed, final UiHelper uiHelper) {
        switch (sorterName.toLowerCase(Locale.US)) {
            case "bubble sort":
                return new BubbleSorter(speed, uiHelper);
            case "insertion sort":
                return new InsertionSorter(speed, uiHelper);
            case "merge sort":
                return new MergeSorter(speed, uiHelper);
            case "selection sort (min)":
                return new MinSelectionSorter(speed, uiHelper);
            case "selection sort (max)":
                return new MaxSelectionSorter(speed, uiHelper);
            default:
                return null;
        }
    }

    public ArrayList<Integer> getNums() {
        return nums;
    }

    public String getSorterName() {
        return sorter.getName();
    }

    public int getSpeed() {
        return speed;
    }
}
