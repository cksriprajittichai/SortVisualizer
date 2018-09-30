package sorters;

import def.Sorter;
import def.SortingConstants;
import def.UiHelper;

import java.awt.*;
import java.util.List;

public final class InsertionSorter implements Sorter {

    private static final Color COMPARING = Color.RED;
    private static final Color SORTED = Color.WHITE;

    private final int msSleep;
    private final UiHelper uiHelper;

    public InsertionSorter(final int speed, final UiHelper uiHelper) {
        msSleep = calculateMsSleepFromSpeed(speed);
        this.uiHelper = uiHelper;
    }

    @Override
    public int calculateMsSleepFromSpeed(final int speed) {
        return SortingConstants.MAX_SPEED - speed;
    }

    @Override
    public void sort(final List<Integer> nums) {
        // Element 0 is in sorted section initially
        uiHelper.drawColumn(0, nums.get(0), SORTED);

        final int n = nums.size();
        int temp;
        int sortedIndex;
        for (int unsortedIndex = 1; unsortedIndex < n; unsortedIndex++) {
            sortedIndex = unsortedIndex - 1;
            temp = nums.get(unsortedIndex);

            while (sortedIndex >= 0 && temp < nums.get(sortedIndex)) {
                // Highlight temp, which is at sortedIndex + 1
                uiHelper.drawColumn(sortedIndex + 1, nums.get(sortedIndex + 1), COMPARING);

                try {
                    Thread.sleep(msSleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /* This loop will cause unsortedIndex to no longer point at temp,
                 * but temp is always stored in index sortedIndex + 1. */
                swap(nums, sortedIndex, sortedIndex + 1);

                // Clear excess after swap
                uiHelper.eraseColumn(sortedIndex);
                uiHelper.eraseColumn(sortedIndex + 1);

                // Highlight column switched with temp
                uiHelper.drawColumn(sortedIndex + 1, nums.get(sortedIndex + 1), SORTED);

                sortedIndex--;

                if (sortedIndex < 0 || temp > nums.get(sortedIndex)) {
                    uiHelper.drawColumn(sortedIndex + 1, nums.get(sortedIndex + 1), Color.GREEN);

                    try {
                        Thread.sleep(msSleep);
                    } catch (final InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }

            uiHelper.drawColumn(sortedIndex + 1, nums.get(sortedIndex + 1), SORTED);
        }
    }

    private void swap(final List<Integer> nums, final int first, final int second) {
        final int temp;
        temp = nums.get(first);
        nums.set(first, nums.get(second));
        nums.set(second, temp);
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }

}
