package sorters;

import def.Sorter;
import def.SortingConstants;
import def.UiHelper;

import java.awt.*;
import java.util.ArrayList;

public final class MaxSelectionSorter implements Sorter {

    private static final Color SORTED = Color.WHITE;
    private static final Color ITERATOR = Color.YELLOW;
    private static final Color CURRENT_MAX = Color.CYAN;
    private static final Color BEFORE_SWAP = Color.RED;
    private static final Color AFTER_SWAP = Color.GREEN;

    private final int msSleep;
    private final UiHelper uiHelper;

    public MaxSelectionSorter(final int speed, final UiHelper uiHelper) {
        msSleep = calculateMsSleepFromSpeed(speed);
        this.uiHelper = uiHelper;
    }

    @Override
    public void sort(final ArrayList<Integer> nums) {
        int sortedSize, i, j, maxNdx;
        for (sortedSize = 0, i = nums.size() - 1 - sortedSize; i > 0; i--) {
            maxNdx = i;
            uiHelper.drawColumn(maxNdx, nums.get(maxNdx), CURRENT_MAX);
            for (j = i - 1; j >= 0; j--) {
                uiHelper.drawColumn(j, nums.get(j), ITERATOR);
                try {
                    Thread.sleep(msSleep);
                } catch (final InterruptedException ie) {
                    ie.printStackTrace();
                }

                if (nums.get(j) > nums.get(maxNdx)) {
                    // Uncolor old max
                    uiHelper.unhighlightColumn(maxNdx, nums.get(maxNdx));

                    maxNdx = j;

                    // Color new max
                    uiHelper.drawColumn(maxNdx, nums.get(maxNdx), CURRENT_MAX);
                } else {
                    // Uncolor iterator if it is not the max
                    uiHelper.unhighlightColumn(j, nums.get(j));
                }
            }


            uiHelper.drawColumn(i, nums.get(i), BEFORE_SWAP);
            uiHelper.drawColumn(maxNdx, nums.get(maxNdx), BEFORE_SWAP);
            try {
                Thread.sleep(msSleep);
            } catch (final InterruptedException ie) {
                ie.printStackTrace();
            }

            swap(nums, i, maxNdx);

            uiHelper.eraseColumn(i);
            uiHelper.eraseColumn(maxNdx);
            uiHelper.drawColumn(i, nums.get(i), AFTER_SWAP);
            uiHelper.drawColumn(maxNdx, nums.get(maxNdx), AFTER_SWAP);
            try {
                Thread.sleep(msSleep);
            } catch (final InterruptedException ie) {
                ie.printStackTrace();
            }
            uiHelper.unhighlightColumn(maxNdx, nums.get(maxNdx));


            // Color sorted section
            uiHelper.drawColumn(i, nums.get(i), SORTED);
        }

        /* The last column (index 0) isn't colored in the loop because
         * i is always >= 1. */
        uiHelper.drawColumn(0, nums.get(0), SORTED);
    }

    @Override
    public int calculateMsSleepFromSpeed(final int speed) {
        return SortingConstants.MAX_SPEED - speed;
    }

    private void swap(final ArrayList<Integer> nums, final int first, final int second) {
        final int temp;
        temp = nums.get(first);
        nums.set(first, nums.get(second));
        nums.set(second, temp);
    }

    @Override
    public String getName() {
        return "Selection Sort (Max)";
    }

}
