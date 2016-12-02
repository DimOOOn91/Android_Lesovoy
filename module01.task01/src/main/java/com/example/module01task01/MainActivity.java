package com.example.module01task01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Integer variable = 0;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.main_text);
        textView.setText(String.valueOf(variable));

        editText = (EditText) findViewById(R.id.main_editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editText.getText().toString();
                if (text.length() > 12) {
                    increaseVariable();
                }
            }
        });

        final TextView textViewFibonacciRes = (TextView) findViewById(R.id.textView_fib_res);
        final EditText editTextFibonacciReq = (EditText) findViewById(R.id.editText_fib_req);
        editTextFibonacciReq.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int value = 0;
                if (editTextFibonacciReq.length() > 0) {
                    String input = editTextFibonacciReq.getText().toString();
                    value = Integer.valueOf(input);
                }
                textViewFibonacciRes.setText(String.valueOf(fibonacci(value)));
            }
        });

        Button button = (Button) findViewById(R.id.main_button);
        button.setText("Increase");

    }

    public void increase(View view) {
        increaseVariable();
    }

    private void increaseVariable() {
        variable++;
        if (variable >= 23) {
            textView.setText("I love GoIt so much");
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Press on the text to reset", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        textView.setText(String.valueOf(variable));
    }

    private long fibonacci(int n) {
        if (n <= 25) {
            return (n <= 2 ? 1 : fibonacci(n - 1) + fibonacci(n - 2));
        } else {
            Toast.makeText(MainActivity.this, "Please enter index less than 25", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public void reset(View view) {
        textView.setText(String.valueOf(variable = 0));
        editText.setText("");
    }
}
