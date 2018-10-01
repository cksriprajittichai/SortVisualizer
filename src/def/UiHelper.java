package def;

import java.awt.*;

public final class UiHelper {

    private static final int TINY_COL_WIDTH = 80;
    private static final int SMALL_COL_WIDTH = 8;
    private static final int MEDIUM_COL_WIDTH = 2;
    private static final int LARGE_COL_WIDTH = 1;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color COLUMN_COLOR = Color.DARK_GRAY;

    private final Graphics g;
    private final int colWidth;

    public UiHelper(final Graphics g, final int dataSizeConstant) {
        this.g = g;
        colWidth = calculateColumnWidthFromDataSize(dataSizeConstant);
    }

    public void unhighlightColumn(final int ndx, final int num) {
        eraseColumn(ndx);
        drawColumn(ndx, num, COLUMN_COLOR);
    }

    public void drawColumn(final int ndx, final int num, final Color color) {
        g.setColor(color);
        g.fillRect(ndx * colWidth, 800 - num, colWidth, num);
    }

    public void eraseColumn(final int ndx) {
        g.setColor(BACKGROUND_COLOR);
        // Must use fillRect method to remove horizontal lines in columns
        // Width arg is exclusive for fillRect method
        g.fillRect(ndx * colWidth, 0, colWidth, 800);
    }

    public static int calculateColumnWidthFromDataSize(final int dataSizeConstant) {
        final int colWidth;
        switch (dataSizeConstant) {
            case DataSizeConstants.TINY:
                colWidth = TINY_COL_WIDTH;
                break;
            case DataSizeConstants.SMALL:
                colWidth = SMALL_COL_WIDTH;
                break;
            case DataSizeConstants.MEDIUM:
                colWidth = MEDIUM_COL_WIDTH;
                break;
            case DataSizeConstants.LARGE:
                colWidth = LARGE_COL_WIDTH;
                break;
            default:
                colWidth = -1;
                break;
        }

        return colWidth;
    }

}
