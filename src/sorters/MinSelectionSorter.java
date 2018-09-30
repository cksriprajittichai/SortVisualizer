package sorters;

import def.Sorter;
import def.UiHelper;

import java.awt.*;
import java.util.ArrayList;

public final class MinSelectionSorter implements Sorter {

    private static final Color SORTED = Color.WHITE;
    private static final Color ITERATOR = Color.YELLOW;
    private static final Color CURRENT_MIN = Color.CYAN;
    private static final Color BEFORE_SWAP = Color.RED;
    private static final Color AFTER_SWAP = Color.GREEN;

    private final int msSleep;
    private final UiHelper uiHelper;

    public MinSelectionSorter(final int speed, final UiHelper uiHelper) {
        msSleep = calculateMsSleepFromSpeed(speed);
        this.uiHelper = uiHelper;
    }

    @Override
    public void sort(final ArrayList<Integer> nums) {
        int i, j, minNdx;
        for (i = 0; i < nums.size() - 1; i++) {
            minNdx = i;
            uiHelper.drawColumn(minNdx, nums.get(minNdx), CURRENT_MIN);
            for (j = i + 1; j < nums.size(); j++) {
                uiHelper.drawColumn(j, nums.get(j), ITERATOR);
                try {
                    Thread.sleep(msSleep);
                } catch (final InterruptedException ie) {
                    ie.printStackTrace();
                }

                if (nums.get(j) < nums.get(minNdx)) {
                    // Uncolor old min
                    uiHelper.unhighlightColumn(minNdx, nums.get(minNdx));

                    minNdx = j;

                    // Color new min
                    uiHelper.drawColumn(minNdx, nums.get(minNdx), CURRENT_MIN);
                } else {
                    // Uncolor iterator if it is not the min
                    uiHelper.unhighlightColumn(j, nums.get(j));
                }
            }


            uiHelper.drawColumn(i, nums.get(i), BEFORE_SWAP);
            uiHelper.drawColumn(minNdx, nums.get(minNdx), BEFORE_SWAP);
            try {
                Thread.sleep(msSleep);
            } catch (final InterruptedException ie) {
                ie.printStackTrace();
            }

            swap(nums, i, minNdx);

            uiHelper.eraseColumn(i);
            uiHelper.eraseColumn(minNdx);
            uiHelper.drawColumn(i, nums.get(i), AFTER_SWAP);
            uiHelper.drawColumn(minNdx, nums.get(minNdx), AFTER_SWAP);
            try {
                Thread.sleep(msSleep);
            } catch (final InterruptedException ie) {
                ie.printStackTrace();
            }
            uiHelper.unhighlightColumn(minNdx, nums.get(minNdx));


            // Color sorted section
            uiHelper.drawColumn(i, nums.get(i), SORTED);
        }
    }

    @Override
    public int calculateMsSleepFromSpeed(final int speed) {
        return 1000 - speed;
    }

    private void swap(final ArrayList<Integer> nums, final int first, final int second) {
        final int temp;
        temp = nums.get(first);
        nums.set(first, nums.get(second));
        nums.set(second, temp);
    }

    @Override
    public String getName() {
        return "Selection Sort (Min)";
    }

}
