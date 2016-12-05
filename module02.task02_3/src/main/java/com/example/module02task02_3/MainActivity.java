package com.example.module02task02_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Вариант 2-25
 * Преобразование аргументов, задаваемых при запуске программы. Программа определяет тип аргумента –
 * шестнадцатеричное число без знака (шаблон: шестнадцатеричным числом без знака считается строка,
 * которая содержит цифры от 0 до 9 и буквы A(a), B(b), C(c), D(d), E(e),F(f)) или строка.
 * Введенные аргументы-числа преобразуются в десятичные числа
 * (каждая i-ая цифра шестнадцатеричного числа преобразуется в десятичное число Ni по формуле
 * Ni=16^n-i-1, где n – количество цифр в числе; i = 0,n – индекс цифры в числе,
 * искомое число является суммой всех Ni). Программа выводит количество заданных аргументов,
 * их значения, а также количество аргументов-чисел и их десятичные значения.
 */
public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;

    private List<String> mInputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputs = new ArrayList<>();
        mEditText = (EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.text_view);

        Button buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mEditText.getText().toString();
                if (input.equals(" ")) {
                    return;
                }
                mInputs.add(input);
                mEditText.setText("");
            }
        });
        Button buttonResult = (Button) findViewById(R.id.button_result);
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText(result());
            }
        });
    }

    private String result() {
        return String.format("Number of arguments: %s \n\n" +
                "Arguments: %s \n\n" +
                "Number of digits: %s \n\n" +
                "Decimal numbers: %s",
                mInputs.size(), mInputs, countAllDigits(mInputs), getAllParsedNumbers(mInputs));
    }

    private List<Integer> getDigitsFormString(String s) {
        List<Integer> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            result.add(Integer.parseInt(matcher.group()));
        }
        return result;
    }

    private int countAllDigits(List<String> inputs) {
        int result = 0;
        for (String input : inputs) {
            result += getDigitsFormString(input).size();
        }
        return result;
    }

    private double parseNumber(List<Integer> digits) {

        if (digits == null || digits.isEmpty()) {
            return 0;
        }

        double result = 0;
        int n = digits.size();
        double i = Double.parseDouble("0." + n);

        for (int digit : digits) {
            result += Math.pow(digit, n - i - 1);
        }

        return result;
    }

    private List<Double> getAllParsedNumbers(List<String> inputs) {
        if (inputs == null) {
            return null;
        }
        List<Double> result = new ArrayList<>();
        for (String input : inputs) {
            List<Integer> digitsFormString = getDigitsFormString(input);
            result.add(parseNumber(digitsFormString));
        }
        return result;
    }
}
