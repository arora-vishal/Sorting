package com.mindtickle.vishalarora.mindtickle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by vishalarora on 04/02/18.
 */

public abstract class SortingMethod {

    protected volatile int[] arr;
    protected ArrayUpdateCallback arrayUpdateCallback;
    protected static final int ITERATION_INTERVAL = 2000; // in ms

    protected ExecutorService executorService = Executors.newSingleThreadExecutor();
    protected Future task;

    void performSyncSort(int[] array, ArrayUpdateCallback arrayUpdateCallback) {
        this.arr = array;
        this.arrayUpdateCallback = arrayUpdateCallback;
        arrayUpdateCallback.onSortInitiate();

        internalSortTask().run();
    }

    void performSort(int[] array, ArrayUpdateCallback arrayUpdateCallback) {
        this.arr = array;
        this.arrayUpdateCallback = arrayUpdateCallback;

        arrayUpdateCallback.onSortInitiate();
        task = executorService.submit(internalSortTask());
    }

    public void pauseSort() {
        if (task != null) {
            task.cancel(true);
        }
    }

    abstract Runnable internalSortTask();
}
