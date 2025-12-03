package sortinganimator.algorithm;

import sortinganimator.model.AnimationAction;

public class HeapSort extends AbstractSort {

    @Override
    protected void doSort() {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);

        for (int i = n - 1; i > 0; i--) {
            recordSwap(0, i);
            heapify(i, 0);
            recordMarkSorted(i);
        }
        recordMarkSorted(0);
    }

    void heapify(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n) {
            recordCompare(l, largest);
            if (array[l] > array[largest])
                largest = l;
        }

        if (r < n) {
            recordCompare(r, largest);
            if (array[r] > array[largest])
                largest = r;
        }

        if (largest != i) {
            recordSwap(i, largest);

            heapify(n, largest);
        }
    }
}
