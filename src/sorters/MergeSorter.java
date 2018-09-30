package sorters;

import def.Sorter;
import def.SortingConstants;
import def.UiHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class MergeSorter implements Sorter {

    private static final Color SORT_COMPLETE = Color.WHITE;
    private static final Color CURRENT_SECTION = Color.YELLOW;
    private static final Color ABOUT_TO_BE_SORTED = Color.RED;
    private static final Color JUST_SORTED = Color.GREEN;
    private static final Color SECTION_SIZE_1 = Color.CYAN;

    private static final int EMPHASIZE_SLEEP_MULTIPLIER = 3;

    private final int msSleep;
    private final UiHelper uiHelper;

    public MergeSorter(final int speed, final UiHelper uiHelper) {
        msSleep = calculateMsSleepFromSpeed(speed);
        this.uiHelper = uiHelper;
    }

    @Override
    public int calculateMsSleepFromSpeed(final int speed) {
        return SortingConstants.MAX_SPEED - speed;
    }

    @Override
    public void sort(final List<Integer> nums) {
        mergeSort(nums, 0, nums.size());
    }

    private void mergeSort(final List<Integer> nums, final int firstIndex, final int n) {
        // Base case
        if (n == 1) {
            return;
        }

        // Color the selected section (left section)
        for (int offset = 0; offset < n / 2; offset++) {
            uiHelper.drawColumn(firstIndex + offset,
                    nums.get(firstIndex + offset),
                    n / 2 <= 1 ? SECTION_SIZE_1 : CURRENT_SECTION);
        }
        try {
            Thread.sleep(msSleep);
        } catch (final InterruptedException ie) {
            ie.printStackTrace();
        }
        // Uncolor the selected section (left section)
        for (int offset = 0; offset < n / 2; offset++) {
            uiHelper.unhighlightColumn(firstIndex + offset,
                    nums.get(firstIndex + offset));
        }
        mergeSort(nums, firstIndex, n / 2);


        // Color the selected section (right section)
        for (int offset = 0; offset < n - (n / 2); offset++) {
            uiHelper.drawColumn(firstIndex + (n / 2) + offset,
                    nums.get(firstIndex + (n / 2) + offset),
                    n - (n / 2) <= 1 ? SECTION_SIZE_1 : CURRENT_SECTION);
        }
        try {
            Thread.sleep(msSleep);
        } catch (final InterruptedException ie) {
            ie.printStackTrace();
        }
        // Uncolor the selected section (right section)
        for (int offset = 0; offset < n - (n / 2); offset++) {
            uiHelper.unhighlightColumn(firstIndex + (n / 2) + offset,
                    nums.get(firstIndex + (n / 2) + offset));
        }
        mergeSort(nums, firstIndex + (n / 2), n - (n / 2));


        merge(nums, firstIndex, n / 2, n - (n / 2));
    }

    private void merge(final List<Integer> list, final int firstIndex,
                       final int leftSize, final int rightSize) {
        final int n = leftSize + rightSize;
        int rightStart = firstIndex + leftSize;
        // Check shortcut. Works because left segment and right segment are sorted.
        if (list.get(rightStart - 1) <= list.get(rightStart)) {
            return;
        }

        final int lastIndex = rightStart + rightSize - 1;

        // Create a new list of sorted elements, then copy sorted elements into original
        // list.
        final ArrayList<Integer> sortedList = new ArrayList<>(n);
        int leftCur = firstIndex;
        int rightCur = rightStart;

        for (int runCount = 1; runCount <= n; runCount++) {
            if (leftCur < rightStart && rightCur <= lastIndex) {
                if (list.get(leftCur) <= list.get(rightCur)) {
                    // If leftCur and rightCur are equal, default takes element
                    // from left section first. Preserves stability.
                    sortedList.add(list.get(leftCur++));
                } else {
                    sortedList.add(list.get(rightCur++));
                }
            } else if (leftCur >= rightStart) {
                sortedList.add(list.get(rightCur++));
            } else {
                sortedList.add(list.get(leftCur++));
            }
        }

        // Color section that is about to be merged (and sorted)
        for (int index = firstIndex, sortedIndex = 0; index < firstIndex + n; index++, sortedIndex++) {
            uiHelper.drawColumn(index, list.get(index), ABOUT_TO_BE_SORTED);
        }
        try {
            Thread.sleep(EMPHASIZE_SLEEP_MULTIPLIER * msSleep);
        } catch (final InterruptedException ie) {
            ie.printStackTrace();
        }

        // Copy sorted elements back into original list
        // Color the sorted section
        for (int index = firstIndex, sortedIndex = 0; index < firstIndex + n; index++, sortedIndex++) {
            list.set(index, sortedList.get(sortedIndex));

            uiHelper.eraseColumn(index);
            uiHelper.drawColumn(index,
                    list.get(index),
                    leftSize + rightSize == list.size() ? SORT_COMPLETE : JUST_SORTED);
        }

        try {
            Thread.sleep(EMPHASIZE_SLEEP_MULTIPLIER * msSleep);
        } catch (final InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}
