package com.example.module03additional;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Date;

/**
 * Реализовать коллекцию по данному интерфейсу
 * (https://docs.google.com/document/d/1JwVronpGMUBYioJ50_W096yLlvpdNpbKb5eSkae6884/edit)
 * Покажите пример работы с Iterator (Iterable).
 * Показать пример работы с Enumeration.
 * Показать пример работы с ExpandableListView.
 * Показать пример работы со Spinner. Обработать выбора элемента из Spiner.
 * Проверить, работает ли OnItemSelectedListener с обычным ArrayAdapter.
 */

public class MainActivity extends AppCompatActivity {

    private MyCollection<Input> mCollection;
    private RecyclerView mRecyclerView;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollection = new MyCollection<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayAdapter<EnumTest> arrayAdapter = new ArrayAdapter<EnumTest>(this, android.R.layout.simple_spinner_item, EnumTest.values());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setAdapter(arrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                EnumTest key = EnumTest.values()[i];
                boolean isContainKey = false;

                for (int k = 0; k < mCollection.size(); k++) {
                    Input input = mCollection.get(k);
                    boolean equals = key.equals(input.getTitle());
                    if (equals) {
                        input.addDate(new Date());
                        isContainKey = true;
                    }
                }
                if (!isContainKey) {
                    Input input = new Input(key);
                    input.addDate(new Date());
                    mCollection.add(input);
                }
                mRecyclerView.setAdapter(new CustomExpandableAdapter(MainActivity.this, mCollection));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Snackbar.make(mSpinner, "Nothing has been selected", Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
