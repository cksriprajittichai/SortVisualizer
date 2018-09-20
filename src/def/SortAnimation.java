package def;

import sorters.BubbleSorter;
import sorters.InsertionSorter;

import java.util.ArrayList;
import java.util.Locale;

public final class SortAnimation {

    private final ArrayList<Integer> nums;
    private final Sorter sorter;

    public SortAnimation(final ArrayList<Integer> nums, final String sorterName,
                         final int msSleep, final UiHelper uiHelper) {
        this.nums = nums;
        this.sorter = constructSorterFromName(sorterName, msSleep, uiHelper);
    }

    public void startAnimation() {
        sorter.sort(nums);
    }

    private Sorter constructSorterFromName(
            final String sorterName, final int msSleep, final UiHelper uiHelper) {
        switch (sorterName.toLowerCase(Locale.US)) {
            case "bubble sort":
                return new BubbleSorter(msSleep, uiHelper);
            case "insertion sort":
                return new InsertionSorter(msSleep, uiHelper);
//            case "merge startAnimation":
//                return new MergeSorter(uiHelper);
            default:
                return null;
        }
    }

    public ArrayList<Integer> getNums() {
        return nums;
    }

}
