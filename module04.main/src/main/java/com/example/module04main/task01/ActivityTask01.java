package com.example.module04main.task01;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.module04main.R;

import java.util.Arrays;

/**
 * Вариант 1-09
 *
 * Сформировать массив b из исходного одномерного массива a по следующему алгоритму:
 * сначала идут элементы массива a с четными значениями в порядке их возрастания,
 * затем элементы с нечетными значениями в порядке их убывания.
 */

public class ActivityTask01 extends AppCompatActivity {

    private int[] arrayA;
    private int[] arrayB;

    private EditText mEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task01);

        setTitle("Task 01");

        mEditText = (EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.text_view);

        Button button = (Button) findViewById(R.id.button_fill_array);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = mEditText.getText().toString();

                if (input.equals("")) {
                    return;
                }
                try {
                    arrayA = parseString(input);
                } catch (NumberFormatException e) {
                    Snackbar.make(view, String.format("Your input  \"%s\" is wrong!", e.getMessage()),
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }
                arrayB = arrayTreatment(arrayA);

                mTextView.setText(String.format("Array \"A\":\n %s \n\nArray \"b\":\n" +
                        " %s", Arrays.toString(arrayA), Arrays.toString(arrayB)));
                mEditText.setText("");
            }
        });

    }

    public static int[] parseString(String s) throws NumberFormatException {
        int[] result = new int[0];
        String[] strings = s.split(",");
        for (String string : strings) {
            if (string.equals("")) {
                break;
            }
            try {
                int element = Integer.parseInt(string);
                result = addToArray(result, element);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(string);
            }
        }
        return result;
    }

    private int[] arrayTreatment(int[] arrayA) {
        int[] evenElements = new int[0];
        int[] oddElements = new int[0];
        int[] result = new int[0];

        for (int i = 0; i < arrayA.length; i++) {
            int element = arrayA[i];
            if (element % 2 == 0) {
                evenElements = addToArray(evenElements, element);
            } else {
                oddElements = addToArray(oddElements, element);
            }
        }

        sort(evenElements, true);
        sort(oddElements, false);

        for (int element : evenElements) {
            result = addToArray(result, element);
        }
        for (int element : oddElements) {
            result = addToArray(result, element);
        }

        return result;
    }

    public static int[] addToArray(int[] array, int element) {
        int prevLength = array.length;
        array = Arrays.copyOf(array, prevLength + 1);
        array[prevLength] = element;
        return array;
    }

    private void sort(int[] array, boolean rise) {
        for (int i = array.length - 1; i >= 1; i--) {
            boolean isSorted = true;
            for (int j = 0; j < i; j++) {
                boolean compare;
                if (rise) {
                    compare = array[j] > array[j + 1];
                } else {
                    compare = array[j] < array[j + 1];
                }
                if (compare) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

        }
    }

}
