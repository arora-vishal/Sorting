package com.mindtickle.vishalarora.mindtickle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner appCompatSpinner;
    private EditText inputField;
    private TextView iterationStateTextView;
    private Button sortButton;
    private Button pauseButton;
    private SortingMethod sortingMethod;
    private Handler handler = new Handler(Looper.getMainLooper());


    private ArrayUpdateCallback arrayUpdateCallbackImpl = new ArrayUpdateCallback() {
        @Override
        public void onIteration(final int[] arr) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int item : arr) {
                        stringBuilder.append(item);
                        stringBuilder.append(" ");
                    }
                    System.out.println(stringBuilder.toString());
                    iterationStateTextView.setText(stringBuilder.toString());
                }
            });
        }

        @Override
        public void onSortInitiate() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    pauseButton.setEnabled(true);
                    sortButton.setEnabled(false);
                    appCompatSpinner.setEnabled(false);
                }
            });
        }

        @Override
        public void onSortComplete() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Sort Complete", Toast.LENGTH_SHORT).show();
                    sortButton.setEnabled(true);
                    appCompatSpinner.setEnabled(true);
                    pauseButton.setEnabled(false);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initListeners() {
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputNumbers = inputField.getText().toString();
                final String[] numbers = inputNumbers.split(" ");
                int arr[] = new int[numbers.length];

                try {
                    for (int i = 0; i < numbers.length; i++) {
                        arr[i] = Integer.valueOf(numbers[i]);
                    }
                    sortingMethod.performSort(arr, arrayUpdateCallbackImpl);
                } catch (NumberFormatException ne) {
                    Toast.makeText(MainActivity.this, "Wrong number format", Toast.LENGTH_SHORT).show();
                }

            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortButton.setEnabled(true);

                if (sortingMethod != null) {
                    sortingMethod.pauseSort();
                }
            }
        });

        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        sortingMethod = SortingMethodFactory.getSortingMethod(SortingMethodFactory.Method.QUICK_SORT);
                        break;
                    case 1:
                        sortingMethod = SortingMethodFactory.getSortingMethod(SortingMethodFactory.Method.BUBBLE_SORT);
                        break;
                    case 2:
                        sortingMethod = SortingMethodFactory.getSortingMethod(SortingMethodFactory.Method.SELECTION_SORT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sortingMethod = SortingMethodFactory.getSortingMethod(SortingMethodFactory.Method.QUICK_SORT);
            }
        });
    }


    private void initViews() {
        inputField = (EditText) findViewById(R.id.mt_integer_input);
        iterationStateTextView = (TextView) findViewById(R.id.mt_iteration_state);
        sortButton = (Button) findViewById(R.id.mt_sort);
        pauseButton = (Button) findViewById(R.id.mt_pause);
        appCompatSpinner = (AppCompatSpinner) findViewById(R.id.mt_sorting_method);
    }
}
