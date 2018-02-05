package com.mindtickle.vishalarora.mindtickle;

/**
 * Created by vishalarora on 04/02/18.
 */

public class SelectionSort extends SortingMethod {

    private void sort() throws InterruptedException {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;

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
