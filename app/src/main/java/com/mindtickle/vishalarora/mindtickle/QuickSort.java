package com.mindtickle.vishalarora.mindtickle;

/**
 * Created by vishalarora on 04/02/18.
 */

public class QuickSort extends SortingMethod {

    @Override
    Runnable internalSortTask() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    sort(0, arr.length - 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void swap(int arr[], int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    int partition(int arr[], int l, int h) {
        int x = arr[h];
        int i = (l - 1);

        for (int j = l; j <= h - 1; j++) {
            if (arr[j] <= x) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, h);
        return (i + 1);
    }

    private void sort(int l, int h) throws InterruptedException {
        int stack[] = new int[h - l + 1];

        int top = -1;

        stack[++top] = l;
        stack[++top] = h;

        while (top >= 0) {
            h = stack[top--];
            l = stack[top--];

            int p = partition(arr, l, h);

            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }

            arrayUpdateCallback.onIteration(arr);
            Thread.sleep(ITERATION_INTERVAL);
        }

        arrayUpdateCallback.onSortComplete();
    }
}
