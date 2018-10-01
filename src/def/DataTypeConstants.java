package def;

public interface DataTypeConstants {

    int RANDOM = 0;
    int ASCENDING = 1;
    int DESCENDING = 2;

    static String getDisplayName(final int dataTypeConstant) {
        switch (dataTypeConstant) {
            case DataTypeConstants.RANDOM:
                return "Random";
            case DataTypeConstants.ASCENDING:
                return "Ascending";
            case DataTypeConstants.DESCENDING:
                return "Descending";
            default:
                return "";
        }
    }

}
