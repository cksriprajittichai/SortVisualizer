package def;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public final class AnimationPanel extends JPanel implements NumberRangeChangedListener {

    private final ArrayList<Integer> nums;
    private final ArrayList<Integer> ui_nums;

    private final Queue<IndexPair> indexPairQueue = new LinkedBlockingQueue<>();

    final static int COLUMN_WIDTH = 7;

    final static Color BACKGROUND_COLOR = Color.CYAN;

    public AnimationPanel(final ArrayList<Integer> nums) {
        this.nums = nums;
        ui_nums = new ArrayList<>(nums);

        setPreferredSize(new Dimension(800, 600));
        setBackground(BACKGROUND_COLOR);
    }

    public void sort(final Sorter sorter) {
        sorter.sort(nums);
    }

    public void showAnimation() {
        final Timer timer = new Timer();
        final TimerTask repaintTask = new TimerTask() {
            @Override
            public void run() {
                if (!indexPairQueue.isEmpty()) {
                    // Upper bound of subList is exclusive
                    ui_nums.subList(indexPairQueue.peek().getFirst(), indexPairQueue.poll().getSecond() + 1)
                            .sort(Integer::compareTo);

                    repaint();
                }
            }
        };
        // Repaint
        timer.schedule(repaintTask, 0, 1);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ui_nums.size(); i++) {
            drawColumn(g, i, ui_nums.get(i) * 3);
        }
    }

    private void drawColumn(final Graphics g, final int x, final int height) {
        g.drawRect(x * COLUMN_WIDTH, 600 - height, COLUMN_WIDTH, height);
    }

    @Override
    public void onNumberRangeChanged(int first, int second) {
        indexPairQueue.add(new IndexPair(first, second));
    }
}
