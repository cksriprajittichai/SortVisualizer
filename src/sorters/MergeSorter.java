package sorters;

import def.NumberRangeChangedListener;
import def.Sorter;

import java.util.ArrayList;

public final class MergeSorter implements Sorter {

    private final NumberRangeChangedListener rangeChangedListener;

    public MergeSorter(final NumberRangeChangedListener rangeChangedListener) {
        this.rangeChangedListener = rangeChangedListener;
    }

    @Override
    public void sort(final ArrayList<Integer> nums) {
        System.out.println("Sorting: " + getName());

        mergeSort(nums, 0, nums.size());

        System.out.println("Finished");
    }

    private void mergeSort(final ArrayList<Integer> list, final int firstIndex, final int n) {
        // Base case
        if (n == 1) {
            return;
        }

        mergeSort(list, firstIndex, n / 2);
        mergeSort(list, firstIndex + (n / 2), n - (n / 2));
        merge(list, firstIndex, n / 2, n - (n / 2));
    }

    private void merge(final ArrayList<Integer> list, final int firstIndex, final int leftSize, final int rightSize) {
        int n = leftSize + rightSize;
        int rightStart = firstIndex + leftSize;
        // Check shortcut. Works because left segment and right segment are sorted.
        if (list.get(rightStart - 1) <= list.get(rightStart))
            return;

        int lastIndex = rightStart + rightSize - 1;

        // Create a new list of sorted elements, then copy sorted elements into original
        // list.
        ArrayList<Integer> sortedList = new ArrayList<Integer>(n);
        int leftCur = firstIndex;
        int rightCur = rightStart;

        for (int runCount = 1; runCount <= n; runCount++) {
            if (leftCur < rightStart && rightCur <= lastIndex) {
                if (list.get(leftCur) <= list.get(rightCur)) {
                    // If leftCur and rightCur are equal, default takes element from left section
                    // first. Preserves stability.
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

        // Copy sorted elements back into original list
        for (int index = firstIndex, sortedIndex = 0; index < firstIndex + n; index++, sortedIndex++) {
            list.set(index, sortedList.get(sortedIndex));
        }

        notifyRangeChangedListener(firstIndex, firstIndex + n - 1);
    }

    /**
     * @param first
     * @param second INCLUSIVE
     */
    public void notifyRangeChangedListener(final int first, final int second) {
        rangeChangedListener.onNumberRangeChanged(first, second);
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

}
