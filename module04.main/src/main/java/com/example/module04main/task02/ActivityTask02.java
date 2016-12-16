package com.example.module04main.task02;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.module04main.R;
import com.example.module04main.task01.ActivityTask01;

/**
 * Вариант 1-20
 * <p>
 * Определите (в процентах от общего количества элементов), сколько элементов в массиве a
 * имеют значение меньшее, чем среднее значение, сколько элементов – значение,
 * равное среднему значению и сколько элементов имеют значение, большее, чем среднее значение.
 */

public class ActivityTask02 extends AppCompatActivity {

    private int[] mArray;

    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task02);

        setTitle("Task 02");

        mEditText = (EditText) findViewById(R.id.edit_text2);
        mTextView = (TextView) findViewById(R.id.text_view2);

        Button button = (Button) findViewById(R.id.button_fill_array2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = mEditText.getText().toString();

                if (input.equals("")) {
                    return;
                }
                try {
                    mArray = ActivityTask01.parseString(input);
                } catch (NumberFormatException e) {
                    Snackbar.make(view, String.format("Your input  \"%s\" is wrong!", e.getMessage()),
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }

                int average = findAverage(mArray);

                int belowTheAverageCounter = 0;
                int aboveTheAverageCounter = 0;
                int averageCounter = 0;

                for (int element : mArray) {
                    if (element < average) {
                        belowTheAverageCounter++;
                    } else if (element > average) {
                        aboveTheAverageCounter++;
                    } else {
                        averageCounter++;
                    }
                }

                int arrayLength = mArray.length;
                String text = String.format("Length of array: %s\n" +
                        "Average: %s\n" +
                        "Average elements in array: %s\n" +
                        "Below average elements in array: %s\n" +
                        "Above average elements in array: %s", arrayLength, average, averageCounter,
                        (belowTheAverageCounter * 100) / arrayLength + "%",
                        (aboveTheAverageCounter * 100) / arrayLength + "%");
                mTextView.setText(text);



            }
        });
    }

    private int findAverage(int[] array) {
        int maxElement = maxOrMinElement(array, true);
        int minElement = maxOrMinElement(array, false);
        return (minElement + maxElement) / 2;
    }

    private int maxOrMinElement (int[] array, boolean isMax) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            boolean comparator;
            if (isMax) {
                comparator = result < array[i];
            } else {
                comparator = result > array[i];
            }
            if (comparator) {
                result = array[i];
            }
        }
        return result;
    }



}
