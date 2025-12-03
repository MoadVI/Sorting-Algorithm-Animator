package sortinganimator.algorithm;

import sortinganimator.model.AnimationAction;

public class MergeSort extends AbstractSort {

    @Override
    protected void doSort() {
        mergeSort(0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            actions.add(new AnimationAction(AnimationAction.Type.MARK_SORTED, i));
        }
    }

    private void mergeSort(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(l, m);
            mergeSort(m + 1, r);
            merge(l, m, r);
        }
    }

    private void merge(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            recordCompare(l + i, m + 1 + j);
            if (L[i] <= R[j]) {
                recordSetValue(k, L[i]);
                i++;
            } else {
                recordSetValue(k, R[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            recordSetValue(k, L[i]);
            i++;
            k++;
        }

        while (j < n2) {
            recordSetValue(k, R[j]);
            j++;
            k++;
        }
    }
}
