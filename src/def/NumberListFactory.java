package def;

import java.util.ArrayList;
import java.util.Collections;

public final class NumberListFactory {

    public static ArrayList<Integer> getSmallSorted() {
        final ArrayList<Integer> nums = new ArrayList<>(100);
        for (int num = 5; num <= 500; num += 5) {
            nums.add(num);
        }
        return nums;
    }

    public static ArrayList<Integer> getSmallReverseSorted() {
        final ArrayList<Integer> nums = getSmallSorted();
        Collections.reverse(nums);
        return nums;
    }

    public static ArrayList<Integer> getSmallShuffled() {
        final ArrayList<Integer> nums = getSmallSorted();
        Collections.shuffle(nums);
        return nums;
    }

}
