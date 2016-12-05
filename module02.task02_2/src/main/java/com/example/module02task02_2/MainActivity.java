package com.example.module02task02_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Вариант 2-11
 * Программа определяет, какие символы содержатся во введенных аргументах
 * (например, аргументы "abc", "cf", "bfc" содержат символы "abcf").
 * Шаблон аргумента: строка либо латинских букв, либо букв кириллицы.
 * Программа выводит количество заданных аргументов, значения аргументов и строку символов,
 * содержащихся в аргументах.
 *
 */

public class MainActivity extends AppCompatActivity {

    private Set<Character> mCharacters = new HashSet<>();
    private List<String> mArguments = new ArrayList<>();

    private EditText mEditText;
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);
        mTextView = (TextView) findViewById(R.id.text_view);
        Button buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String argument = mEditText.getText().toString();
                if (argument.equals("")) {
                    return;
                }
                mArguments.add(argument);
                for (int i = 0; i < argument.length(); i++) {
                    char character = argument.charAt(i);
                    if (character != ' ') {
                        mCharacters.add(character);
                    }
                }
                mEditText.setText("");
            }
        });
        Button buttonResult = (Button) findViewById(R.id.button);
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Character> chars = new ArrayList<>(mCharacters);
                Collections.sort(chars);
                mTextView.setText(String.format("Total number of saved arguments is: %s \n\n" +
                        "Arguments: %s \n\n Symbols: %s", mArguments.size(), mArguments, chars));
            }
        });
    }
}
