package sortinganimator.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Visualizer extends Pane {

    private List<Rectangle> bars;
    private int arraySize;
    private double barWidth;
    private double maxHeight;
    private int maxValue;

    private static final Color DEFAULT_COLOR = Color.BLUE;
    public static final Color COMPARE_COLOR = Color.RED;
    public static final Color SWAP_COLOR = Color.YELLOW;
    public static final Color SORTED_COLOR = Color.GREEN;
    public static final Color PIVOT_COLOR = Color.PURPLE;

    public Visualizer(int arraySize, int maxValue) {
        this.arraySize = arraySize;
        this.maxValue = maxValue;
        this.bars = new ArrayList<>(arraySize);
        this.maxHeight = 400;
        this.barWidth = 800.0 / arraySize;
        this.setPrefSize(800, maxHeight);
        this.setStyle("-fx-background-color: #f4f4f4;");
    }

    public void setArray(int[] array) {
        this.getChildren().clear();
        this.bars.clear();

        for (int i = 0; i < array.length; i++) {
            double barHeight = (double) array[i] / maxValue * maxHeight;
            Rectangle bar = new Rectangle(barWidth, barHeight);
            bar.setFill(DEFAULT_COLOR);
            bar.setStroke(Color.BLACK);
            bar.setStrokeWidth(0.5);

            bar.setX(i * barWidth);
            bar.setY(maxHeight - barHeight);

            bars.add(bar);
            this.getChildren().add(bar);
        }
    }

    public void resetColors() {
        for (Rectangle bar : bars) {
            bar.setFill(DEFAULT_COLOR);
        }
    }

    public void colorBar(int index, Color color) {
        if (index >= 0 && index < bars.size()) {
            bars.get(index).setFill(color);
        }
    }

    public void swapBars(int i, int j) {
        if (i == j) return;

        double tempX = bars.get(i).getX();
        bars.get(i).setX(bars.get(j).getX());
        bars.get(j).setX(tempX);


        Rectangle temp = bars.get(i);
        bars.set(i, bars.get(j));
        bars.set(j, temp);
    }

    public void setValue(int index, int value) {
        if (index >= 0 && index < bars.size()) {
            Rectangle bar = bars.get(index);
            double newHeight = (double) value / maxValue * maxHeight;
            bar.setHeight(newHeight);
            bar.setY(maxHeight - newHeight);
        }
    }
}
