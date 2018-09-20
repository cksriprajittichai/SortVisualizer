package sorters;

import def.Sorter;
import def.UiHelper;

import java.awt.*;
import java.util.ArrayList;

public final class InsertionSorter implements Sorter {

    private static final Color COMPARING = Color.RED;
    private static final Color SORTED = Color.WHITE;

    private int msSleep;
    private final UiHelper uiHelper;

    public InsertionSorter(final int msSleep, final UiHelper uiHelper) {
        this.msSleep = msSleep;
        this.uiHelper = uiHelper;
    }

    @Override
    public void sort(final ArrayList<Integer> nums) {
        System.out.println("Sorting: " + getName());

        // Element 0 is in sorted section initially
        uiHelper.drawColumn(0, nums.get(0), SORTED);

        final int n = nums.size();
        int temp;
        int sortedIndex;
        for (int unsortedIndex = 1; unsortedIndex < n; unsortedIndex++) {
            sortedIndex = unsortedIndex - 1;
            temp = nums.get(unsortedIndex);

            /* SortedIndex is the last index of the sorted section. UnsortedIndex is the
             * first index of the unsorted section. Starting with the element in focus at
             * index 1, while the element to it's left is less than the element in focus,
             * swap these two elements. O(n^2).
             *
             * This is faster than finding the correct insert index of the focus element in
             * the sorted section, then removing the focus element and inserting it into its
             * new position - because doing that requires shifting all displaced elements
             * after already iterating through some of them to find insert index. ArrayLists
             * are lists backed by arrays. O(n^2) I think.
             */
            while (sortedIndex >= 0 && temp < nums.get(sortedIndex)) {
                // Highlight temp, which is at sortedIndex + 1
                uiHelper.drawColumn(sortedIndex + 1, nums.get(sortedIndex + 1), COMPARING);

                try {
                    Thread.sleep(msSleep);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // This loop will cause unsortedIndex to no longer point at temp, but temp is
                // always stored in index sortedIndex + 1.
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

        System.out.println("Finished");
    }

    private void swap(final ArrayList<Integer> nums, final int first, final int second) {
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
