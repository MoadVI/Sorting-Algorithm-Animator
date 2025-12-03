package sortinganimator.controller;

import sortinganimator.algorithm.*;
import sortinganimator.model.AnimationAction;
import sortinganimator.view.Visualizer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class AppController {

    @FXML private VBox visualizationContainer;
    @FXML private ComboBox<String> algorithmComboBox;
    @FXML private Slider speedSlider;
    @FXML private Slider sizeSlider;
    @FXML private Button generateButton;
    @FXML private Button startButton;
    @FXML private Label statusLabel;

    private Visualizer visualizer;
    private int[] currentArray;
    private List<AnimationAction> currentActions;
    private PauseTransition animationStep;
    private AtomicInteger actionIndex;

    private static final int MAX_VALUE = 100;

    @FXML
    public void initialize() {

        algorithmComboBox.setItems(FXCollections.observableArrayList(
                "Bubble Sort", "Selection Sort", "Merge Sort", "QuickSort", "Heap Sort"
        ));
        algorithmComboBox.getSelectionModel().selectFirst();


        sizeSlider.setMin(10);
        sizeSlider.setMax(100);
        sizeSlider.setValue(50);
        sizeSlider.setBlockIncrement(10);
        sizeSlider.setMajorTickUnit(10);
        sizeSlider.setMinorTickCount(0);
        sizeSlider.setSnapToTicks(true);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);

        speedSlider.setMin(1);
        speedSlider.setMax(10);
        speedSlider.setValue(5);
        speedSlider.setBlockIncrement(1);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setMinorTickCount(0);
        speedSlider.setSnapToTicks(true);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);


        generateNewArray();
        updateStatus("Ready. Array size: " + currentArray.length);
    }

    @FXML
    private void generateNewArray() {
        int size = (int) sizeSlider.getValue();
        currentArray = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            currentArray[i] = random.nextInt(MAX_VALUE) + 1;
        }

        if (visualizer == null) {
            visualizer = new Visualizer(size, MAX_VALUE);
            visualizationContainer.getChildren().add(visualizer);
        }
        visualizer.setArray(currentArray.clone());

        if (animationStep != null) {
            animationStep.stop();
        }
        currentActions = null;
        startButton.setDisable(false);
        updateStatus("New array generated. Size: " + size);
    }

    @FXML
    private void startAnimation() {
        startButton.setDisable(true);
        generateButton.setDisable(true);
        algorithmComboBox.setDisable(true);
        sizeSlider.setDisable(true);

        visualizer.setArray(currentArray.clone());
        AbstractSort sorter = getSelectedSorter();
        if (sorter == null) {
            updateStatus("Error: Please select a sorting algorithm.");
            resetControls();
            return;
        }
        currentActions = sorter.sort(currentArray.clone());
        actionIndex = new AtomicInteger(0);

        updateStatus("Starting " + algorithmComboBox.getValue() + " animation...");


        animateNextStep();
    }

    private AbstractSort getSelectedSorter() {
        String algorithm = algorithmComboBox.getValue();
        if (algorithm == null) return null;

        switch (algorithm) {
            case "Bubble Sort": return new BubbleSort();
            case "Selection Sort": return new SelectionSort();
            case "Merge Sort": return new MergeSort();
            case "QuickSort": return new QuickSort();
            case "Heap Sort": return new HeapSort();
            default: return null;
        }
    }

    private void animateNextStep() {
        if (currentActions == null || actionIndex.get() >= currentActions.size()) {
            updateStatus(algorithmComboBox.getValue() + " animation finished. Total steps: " + currentActions.size());
            resetControls();
            return;
        }

        double speedFactor = speedSlider.getMax() - speedSlider.getValue() + 1;
        double delayMs = 50 * speedFactor;

        animationStep = new PauseTransition(Duration.millis(delayMs));
        animationStep.setOnFinished(event -> {

            visualizer.resetColors();

            AnimationAction action = currentActions.get(actionIndex.getAndIncrement());
            performAction(action);

            animateNextStep();
        });
        animationStep.play();
    }

    private void performAction(AnimationAction action) {
        switch (action.getType()) {
            case COMPARE:
                action.getIndices().forEach(i -> visualizer.colorBar(i, Visualizer.COMPARE_COLOR));
                updateStatus("Step " + actionIndex.get() + ": Comparing indices " + action.getIndices());
                break;
            case SWAP:
                int i = action.getIndices().get(0);
                int j = action.getIndices().get(1);
                visualizer.colorBar(i, Visualizer.SWAP_COLOR);
                visualizer.colorBar(j, Visualizer.SWAP_COLOR);
                visualizer.swapBars(i, j);
                updateStatus("Step " + actionIndex.get() + ": Swapping indices " + i + " and " + j);
                break;
            case SET_VALUE:
                int index = action.getIndices().get(0);
                int value = action.getValue();
                visualizer.colorBar(index, Visualizer.SWAP_COLOR);
                visualizer.setValue(index, value);
                updateStatus("Step " + actionIndex.get() + ": Setting value " + value + " at index " + index);
                break;
            case MARK_SORTED:
                action.getIndices().forEach(idx -> visualizer.colorBar(idx, Visualizer.SORTED_COLOR));
                updateStatus("Step " + actionIndex.get() + ": Marking index " + action.getIndices().get(0) + " as sorted");
                break;
            case PIVOT:
                action.getIndices().forEach(idx -> visualizer.colorBar(idx, Visualizer.PIVOT_COLOR));
                updateStatus("Step " + actionIndex.get() + ": Marking index " + action.getIndices().get(0) + " as pivot");
                break;
        }
    }

    private void updateStatus(String message) {
        Platform.runLater(() -> statusLabel.setText(message));
    }

    private void resetControls() {
        startButton.setDisable(false);
        generateButton.setDisable(false);
        algorithmComboBox.setDisable(false);
        sizeSlider.setDisable(false);
    }
}
