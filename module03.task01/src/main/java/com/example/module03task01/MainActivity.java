package com.example.module03task01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Пользователь имеет возможность вводить текст, который появляется в списке.
 * Сделай такую проверку, чтобы в списке были только уникальные строки.
 *
 * Пользователь имеет возможность вводить текст, который появляется в списке.
 * Каждой строке должно соответствовать число, которое будет обозначать частоту
 * ввода этой строки пользователем. При вводе строки, которая уже есть в списке,
 * надо увеличивать такое число только для этой строки.
 * Вывести все строки без повторений и числа, которые к ним относятся.
 * Проверить, что при вводе числа напротив этих строк увеличиваются.
 *
 * Показать пример работы с WebView, ScrollView.
 */

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;

    private ArrayList<String> mListKey;
    private ArrayList<Integer> mListValue;

    private ListView mListView;
    private MyAdapter mMyAdapter;
    private WebView mWebView;

    /**
     * Реализовать коллекцию по данному (https://docs.google.com/document/d/1JwVronpGMUBYioJ50_W096yLlvpdNpbKb5eSkae6884/edit) интерфейсу
     * Покажите пример работы с Iterator (Iterable).
     * Показать пример работы с Enumeration.
     * Показать пример работы с ExpandableListView.
     * Показать пример работы со Spinner. Обработать выбора элемента из Spiner.
     * Проверить, работает ли OnItemSelectedListener с обычным ArrayAdapter.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListKey = new ArrayList<>();
        mListValue = new ArrayList<>();

        mListView = (ListView) findViewById(R.id.list_view);
        mMyAdapter = new MyAdapter(MainActivity.this, mListKey, mListValue);
        mListView.setAdapter(mMyAdapter);

        mEditText = (EditText) findViewById(R.id.edit_text);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mEditText.getText().toString();
                if (input.equals("")) {
                    return;
                }
                if (mListKey.contains(input)) {
                    int index = mListKey.indexOf(input);
                    int counter = mListValue.get(index);
                    mListValue.set(index, ++counter);
                } else {
                    mListKey.add(input);
                    mListValue.add(1);
                }
                mEditText.setText("");
                mMyAdapter.notifyDataSetChanged();
            }
        });

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://git-scm.com");

    }
}
