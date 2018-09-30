package def;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NumberListFactory {

    private static List<Integer> getSmallSorted() {
        final List<Integer> nums = new ArrayList<>(100);
        for (int num = 5; num <= 500; num += 5) {
            nums.add(num);
        }
        return nums;
    }

    private static List<Integer> getSmallReverseSorted() {
        final List<Integer> nums = getSmallSorted();
        Collections.reverse(nums);
        return nums;
    }

    private static List<Integer> getSmallShuffled() {
        final List<Integer> nums = getSmallSorted();
        Collections.shuffle(nums);
        return nums;
    }

    public static List<Integer> getData(final int dataTypeConstant) {
        final List<Integer> nums;
        switch (dataTypeConstant) {
            case DataTypeConstants.RANDOM:
                nums = getSmallShuffled();
                break;
            case DataTypeConstants.ASCENDING:
                nums = getSmallSorted();
                break;
            case DataTypeConstants.DESCENDING:
                nums = getSmallReverseSorted();
                break;
            default:
                nums = new ArrayList<>();
                break;
        }

        return nums;
    }

}
