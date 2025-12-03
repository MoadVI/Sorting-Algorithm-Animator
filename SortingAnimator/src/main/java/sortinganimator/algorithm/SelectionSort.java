package sortinganimator.algorithm;

import sortinganimator.model.AnimationAction;

public class SelectionSort extends AbstractSort {

    @Override
    protected void doSort() {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                recordCompare(min_idx, j);
                if (array[j] < array[min_idx]) {
                    min_idx = j;
                }
            }

            if (min_idx != i) {
                recordSwap(i, min_idx);
            }
            recordMarkSorted(i);
        }
        recordMarkSorted(n - 1);
    }
}
