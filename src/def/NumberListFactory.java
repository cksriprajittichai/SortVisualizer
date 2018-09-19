package def;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Collections;

public final class NumberListFactory {

    static ArrayList<Integer> getSmallSorted() {
        final ArrayList<Integer> nums = new ArrayList<>(100);
        for (int num = 1; num <= 100; num++) {
            nums.add(num);
        }
        return nums;
    }

    static ArrayList<Integer> getSmallReverseSorted() {
        final ArrayList<Integer> nums = getSmallSorted();
        Collections.reverse(nums);
        return nums;
    }

    static ArrayList<Integer> getSmallShuffled() {
        final ArrayList<Integer> nums = getSmallSorted();
        Collections.shuffle(nums);
        return nums;
    }

}
