package def;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NumberListFactory {

    public static List<Integer> getData(final int dataSizeConstant,
                                        final int dataTypeConstant) {
        final List<Integer> nums;
        switch (dataTypeConstant) {
            case DataTypeConstants.RANDOM:
                nums = getRandom(dataSizeConstant);
                break;
            case DataTypeConstants.ASCENDING:
                nums = getSorted(dataSizeConstant);
                break;
            case DataTypeConstants.DESCENDING:
                nums = getReverseSorted(dataSizeConstant);
                break;
            default:
                nums = new ArrayList<>();
                break;
        }

        return nums;
    }

    private static List<Integer> getSorted(final int dataSizeConstant) {
        final int numPts, stepSize;
        switch (dataSizeConstant) {
            case DataSizeConstants.TINY:
                numPts = DataSizeConstants.TINY_NUM_POINTS;
                stepSize = DataSizeConstants.TINY_STEP_SIZE;
                break;
            case DataSizeConstants.SMALL:
                numPts = DataSizeConstants.SMALL_NUM_POINTS;
                stepSize = DataSizeConstants.SMALL_STEP_SIZE;
                break;
            case DataSizeConstants.MEDIUM:
                numPts = DataSizeConstants.MEDIUM_NUM_POINTS;
                stepSize = DataSizeConstants.MEDIUM_STEP_SIZE;
                break;
            case DataSizeConstants.LARGE:
                numPts = DataSizeConstants.LARGE_NUM_POINTS;
                stepSize = DataSizeConstants.LARGE_STEP_SIZE;
                break;
            default:
                numPts = -1;
                stepSize = -1;
                break;
        }

        final List<Integer> nums = new ArrayList<>(numPts);
        for (int num = stepSize; num <= numPts * stepSize; num += stepSize) {
            nums.add(num);
        }
        return nums;
    }

    private static List<Integer> getReverseSorted(final int dataSizeConstant) {
        final List<Integer> sorted = getSorted(dataSizeConstant);
        final List<Integer> revSorted = new ArrayList<>(sorted.size());
        for (int i = sorted.size() - 1; i >= 0; i--) {
            revSorted.add(sorted.get(i));
        }
        return revSorted;
    }

    private static List<Integer> getRandom(final int dataSizeConstant) {
        final List<Integer> nums = getSorted(dataSizeConstant);
        Collections.shuffle(nums);
        return nums;
    }

}
