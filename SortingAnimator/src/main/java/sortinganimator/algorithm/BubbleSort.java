package sortinganimator.algorithm;

import sortinganimator.model.AnimationAction;

public class BubbleSort extends AbstractSort {

    @Override
    protected void doSort() {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                recordCompare(j, j + 1);
                if (array[j] > array[j + 1]) {
                    recordSwap(j, j + 1);
                }
            }
            recordMarkSorted(n - 1 - i);
        }
        recordMarkSorted(0);
    }
}
