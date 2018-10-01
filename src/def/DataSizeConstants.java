package def;

public interface DataSizeConstants {

    int TINY = 0;
    int SMALL = 1;
    int MEDIUM = 2;
    int LARGE = 3;

    int TINY_STEP_SIZE = 80;
    int SMALL_STEP_SIZE = 8;
    int MEDIUM_STEP_SIZE = 2;
    int LARGE_STEP_SIZE = 1;

    int TINY_NUM_POINTS = 10;
    int SMALL_NUM_POINTS = 100;
    int MEDIUM_NUM_POINTS = 400;
    int LARGE_NUM_POINTS = 800;

    static String getDisplayName(final int dataSizeConstant) {
        switch (dataSizeConstant) {
            case DataSizeConstants.TINY:
                return "Tiny";
            case DataSizeConstants.SMALL:
                return "Small";
            case DataSizeConstants.MEDIUM:
                return "Medium";
            case DataSizeConstants.LARGE:
                return "Large";
            default:
                return "";
        }
    }
}
