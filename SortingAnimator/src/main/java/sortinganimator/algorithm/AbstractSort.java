package sortinganimator.algorithm;

import sortinganimator.model.AnimationAction;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSort {

    protected List<AnimationAction> actions;
    protected int[] array;

    public AbstractSort() {
        this.actions = new ArrayList<>();
    }

    /**
     * Sorts the given array and records the animation steps.
     * @param array The array to sort.
     * @return A list of AnimationAction objects representing the sorting process.
     */
    public List<AnimationAction> sort(int[] array) {
        this.array = array.clone();
        this.actions.clear();
        doSort();
        return actions;
    }

    protected abstract void doSort();

    protected void recordCompare(int i, int j) {
        actions.add(new AnimationAction(AnimationAction.Type.COMPARE, i, j));
    }

    protected void recordSwap(int i, int j) {
        actions.add(new AnimationAction(AnimationAction.Type.SWAP, i, j));
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    protected void recordSetValue(int index, int value) {
        actions.add(new AnimationAction(index, value));
        array[index] = value;
    }

    protected void recordMarkSorted(int index) {
        actions.add(new AnimationAction(AnimationAction.Type.MARK_SORTED, index));
    }

    protected void recordPivot(int index) {
        actions.add(new AnimationAction(AnimationAction.Type.PIVOT, index));
    }

    protected void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
