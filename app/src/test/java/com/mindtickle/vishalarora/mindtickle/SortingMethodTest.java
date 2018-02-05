package com.mindtickle.vishalarora.mindtickle;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by vishalarora on 05/02/18.
 */
public class SortingMethodTest {

    int[] arr;
    boolean continueIteration = true;
    int lastValueBeforeSort;

    ArrayUpdateCallback arrayUpdateCallback;

    @Before
    public void setUp() throws Exception {
        Set<Integer> integerSet = new HashSet<>();

        for (int i = 0; i < 10; ++i) {
            integerSet.add(new Random().nextInt(100));
        }

        arr = new int[integerSet.size()];

        int i = 0;

        Iterator iterator = integerSet.iterator();
        while (iterator.hasNext()) {
            arr[i++] = (int) iterator.next();
        }

        lastValueBeforeSort = arr[arr.length - 1];
        arrayUpdateCallback = new ArrayUpdateCallback() {
            @Override
            public void onIteration(int[] arr) {

                if (!continueIteration) {
                    return;
                }

                continueIteration = false;

                int newPositionForLastIndexValue = -1;

                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == lastValueBeforeSort) {
                        newPositionForLastIndexValue = i;
                        break;
                    }
                }


                for (int i = 0; i < arr.length; ++i) {
                    if (i < newPositionForLastIndexValue) {
                        assertTrue(arr[i] < lastValueBeforeSort);
                    }

                    if (i > newPositionForLastIndexValue) {
                        assertTrue(arr[i] > lastValueBeforeSort);
                    }
                }
            }

            @Override
            public void onSortInitiate() {

            }

            @Override
            public void onSortComplete() {

            }
        };
    }

    @Test
    public void testQuickSort() throws Exception {
        SortingMethod quickSort = SortingMethodFactory.getSortingMethod(SortingMethodFactory.Method.QUICK_SORT);
        quickSort.performSyncSort(arr, arrayUpdateCallback);
    }

    @Test
    public void testBubbleSort() throws Exception {
        SortingMethod bubbleSort = SortingMethodFactory.getSortingMethod(SortingMethodFactory.Method.BUBBLE_SORT);
        bubbleSort.performSyncSort(arr, arrayUpdateCallback);
    }

    @Test
    public void testSelectionSort() throws Exception {
        SortingMethod selectionSort = SortingMethodFactory.getSortingMethod(SortingMethodFactory.Method.SELECTION_SORT);
        selectionSort.performSyncSort(arr, arrayUpdateCallback);
    }

}