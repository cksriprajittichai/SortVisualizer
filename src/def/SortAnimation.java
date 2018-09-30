package def;

import java.util.ArrayList;

public final class SortAnimation {

    private final ArrayList<Integer> nums;
    private final Sorter sorter;
    private final int speed;

    public SortAnimation(final ArrayList<Integer> nums, final String sorterName,
                         final int speed, final UiHelper uiHelper) {
        this.nums = nums;
        this.sorter = SorterFactory.createSorter(sorterName, speed, uiHelper);
        this.speed = speed;
    }

    public void startAnimation() {
        sorter.sort(nums);
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
