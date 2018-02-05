package com.mindtickle.vishalarora.mindtickle;

import android.support.annotation.IntDef;

/**
 * Created by vishalarora on 04/02/18.
 */

public class SortingMethodFactory {

    @IntDef({
            Method.BUBBLE_SORT,
            Method.QUICK_SORT,
            Method.SELECTION_SORT

    })
    @interface Method {
        int BUBBLE_SORT = 1;
        int QUICK_SORT = 2;
        int SELECTION_SORT = 3;
    }

    public static SortingMethod getSortingMethod(@Method int sortingMethod){
        switch (sortingMethod){
            case Method.BUBBLE_SORT:
                return new BubbleSort();
            case Method.QUICK_SORT:
                return new QuickSort();
            case Method.SELECTION_SORT:
                return new SelectionSort();
            default:
                return null;
        }
    }
}
