package sorters;

import def.Sorter;
import def.UiHelper;

import java.awt.*;
import java.util.ArrayList;

public final class BubbleSorter implements Sorter {

    private static final Color SORTED = Color.WHITE;
    private static final Color COMPARING = Color.YELLOW;
    private static final Color SWAPPING = Color.RED;

    private final UiHelper uiHelper;
    private int msSleep;

    public BubbleSorter(final UiHelper uiHelper) {
        this.uiHelper = uiHelper;
    }

    @Override
    public void sort(final ArrayList<Integer> nums) {
        System.out.println("Sorting: " + getName());

        final int n = nums.size();
        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {
                uiHelper.highlightColumn(j, nums.get(j), COMPARING);
                uiHelper.highlightColumn(j + 1, nums.get(j + 1), COMPARING);

                try {
                    Thread.sleep(msSleep);
                } catch (final InterruptedException ie) {
                    ie.printStackTrace();
                }

                if (nums.get(j) > nums.get(j + 1)) {
                    uiHelper.highlightColumn(j, nums.get(j), SWAPPING);
                    uiHelper.highlightColumn(j + 1, nums.get(j + 1), SWAPPING);

                    try {
                        Thread.sleep(msSleep);
                    } catch (final InterruptedException ie) {
                        ie.printStackTrace();
                    }

                    swap(nums, j, j + 1);

                    // eraseColumn method clears the whole column, so unhighlight
                    // column doesn't need to be called
                    uiHelper.eraseColumn(j);
                    uiHelper.eraseColumn(j + 1);

                    uiHelper.highlightColumn(j, nums.get(j), SWAPPING);
                    uiHelper.highlightColumn(j + 1, nums.get(j + 1), SWAPPING);
                }

                try {
                    Thread.sleep(msSleep);
                } catch (final InterruptedException ie) {
                    ie.printStackTrace();
                }

                uiHelper.unhighlightColumn(j, nums.get(j));
                uiHelper.unhighlightColumn(j + 1, nums.get(j + 1));
            }

            uiHelper.highlightColumn(nums.size() - 1 - i, nums.get(nums.size() - 1 - i), SORTED);
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
    public void setMsSleep(final int msSleep) {
        this.msSleep = msSleep;
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }

}
