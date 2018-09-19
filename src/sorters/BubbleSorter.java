package sorters;

import def.NumberRangeChangedListener;
import def.Sorter;

import java.util.ArrayList;

public final class BubbleSorter implements Sorter {

    private final NumberRangeChangedListener rangeChangedListener;

    public BubbleSorter(final NumberRangeChangedListener rangeChangedListener) {
        this.rangeChangedListener = rangeChangedListener;
    }

    @Override
    public synchronized void sort(final ArrayList<Integer> nums) {
        System.out.println("Sorting: " + getName());

        final int n = nums.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums.get(j) > nums.get(j + 1)) {
                    swap(nums, j, j + 1);

                    notifySwapListener(j, j + 1);
                }
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
        return "Bubble Sort";
    }

}
