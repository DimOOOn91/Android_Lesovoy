package com.example.module04main.task01;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.module04main.R;
import com.example.module04main.utils.ArrayUtils;

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
                    arrayA = ArrayUtils.parseString(input);
                } catch (NumberFormatException e) {
                    Snackbar.make(view, String.format("Your input  \"%s\" is wrong!", e.getMessage()),
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }
                arrayB = ArrayUtils.arrayTreatment(arrayA);

                mTextView.setText(String.format("Array \"A\":\n %s \n\nArray \"b\":\n" +
                        " %s", Arrays.toString(arrayA), Arrays.toString(arrayB)));
                mEditText.setText("");
            }
        });

    }





}
