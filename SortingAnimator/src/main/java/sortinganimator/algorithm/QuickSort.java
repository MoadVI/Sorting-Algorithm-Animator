package sortinganimator.algorithm;

import sortinganimator.model.AnimationAction;

public class QuickSort extends AbstractSort {

    @Override
    protected void doSort() {
        quickSort(0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            actions.add(new AnimationAction(AnimationAction.Type.MARK_SORTED, i));
        }
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        recordPivot(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            recordCompare(j, high);

            if (array[j] <= pivot) {
                i++;
                if (i != j) {
                    recordSwap(i, j);
                }
            }
        }

        if (i + 1 != high) {
            recordSwap(i + 1, high);
        }
        actions.add(new AnimationAction(AnimationAction.Type.MARK_SORTED, i + 1));

        return i + 1;
    }
}
