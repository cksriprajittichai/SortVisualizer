package def;

import java.awt.*;

public final class UiHelper {

    static final int COLUMN_WIDTH = 7;
    static final Color BACKGROUND_COLOR = Color.BLACK;
    static final Color COLUMN_COLOR = Color.DARK_GRAY;

    private final Graphics g;

    public UiHelper(final Graphics g) {
        this.g = g;
    }

    public void unhighlightColumn(final int ndx, final int num) {
        eraseColumn(ndx);
        drawColumn(ndx, num, COLUMN_COLOR);
    }

    public void drawColumn(final int ndx, final int num, final Color color) {
        g.setColor(color);
        g.fillRect(ndx * COLUMN_WIDTH, 600 - num, COLUMN_WIDTH, num);
    }

    /**
     * Must use fillRect method to remove horizontal lines in columns.
     *
     * @param ndx
     */
    public void eraseColumn(final int ndx) {
        g.setColor(BACKGROUND_COLOR);
        // width arg is exclusive for fillRect method
        g.fillRect(ndx * COLUMN_WIDTH, 0, COLUMN_WIDTH, 600);
    }

}
