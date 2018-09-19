package sorters;

import def.NumberRangeChangedListener;
import def.Sorter;

import java.util.ArrayList;

public final class InsertionSorter implements Sorter {

    private final NumberRangeChangedListener rangeChangedListener;

    public InsertionSorter(final NumberRangeChangedListener rangeChangedListener) {
        this.rangeChangedListener = rangeChangedListener;
    }

    @Override
    public void sort(final ArrayList<Integer> nums) {
        System.out.println("Sorting: " + getName());

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
                // This loop will cause unsortedIndex to no longer point at temp, but temp is
                // always stored in index sortedIndex + 1.
                swap(nums, sortedIndex, sortedIndex + 1);

                notifySwapListener(sortedIndex, sortedIndex + 1);

                sortedIndex--;
            }
        }

        System.out.println("Finished");
    }

    private void swap(final ArrayList<Integer> nums, final int first, final int second) {
        final int temp;
        temp = nums.get(first);
        nums.set(first, nums.get(second));
        nums.set(second, temp);
    }

    public void notifySwapListener(final int first, final int second) {
        rangeChangedListener.onNumberRangeChanged(first, second);
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }

}
