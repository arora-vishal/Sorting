package com.mindtickle.vishalarora.mindtickle;

/**
 * Created by vishalarora on 04/02/18.
 */

public class BubbleSort extends SortingMethod {

    private void sort() throws InterruptedException {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            arrayUpdateCallback.onIteration(arr);
            Thread.sleep(ITERATION_INTERVAL);
        }

        arrayUpdateCallback.onSortComplete();
    }

    @Override
    Runnable internalSortTask() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    sort();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
