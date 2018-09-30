package def;

import java.util.List;

public final class SortAnimation {

    private final List<Integer> nums;
    private final Sorter sorter;
    private final int speed;

    public SortAnimation(final List<Integer> nums, final String sorterName,
                         final int speed, final UiHelper uiHelper) {
        this.nums = nums;
        this.sorter = SorterFactory.createSorter(sorterName, speed, uiHelper);
        this.speed = speed;
    }

    public void startAnimation() {
        sorter.sort(nums);
    }

    public List<Integer> getNums() {
        return nums;
    }

    public String getSorterName() {
        return sorter.getName();
    }

    public int getSpeed() {
        return speed;
    }
}
