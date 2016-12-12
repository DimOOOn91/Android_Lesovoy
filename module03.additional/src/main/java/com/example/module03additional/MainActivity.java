package com.example.module03additional;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyCollection<Map<EnumTest, MyCollection<String>>> mCollection;
    private CustomExpandableAdapter mCustomExpandableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollection = new MyCollection<>();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.exp_list_view);
        mCustomExpandableAdapter = new CustomExpandableAdapter(this, mCollection);
        expandableListView.setAdapter(mCustomExpandableAdapter);

        ArrayAdapter<EnumTest> arrayAdapter = new ArrayAdapter<EnumTest>(this, android.R.layout.simple_spinner_item, EnumTest.values());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Map<EnumTest, MyCollection<String>> map;
                EnumTest key = EnumTest.values()[i];
                MyCollection<String> value = null;

                String time = String.format("%1$tD %1$tl:%1$tM (%1$tp)", Calendar.getInstance().getTime());

                Log.d("MyLogCollection", String.valueOf(mCollection.size()));
                for (int index = 0; index < mCollection.size(); index++) {
                    map = mCollection.get(index);
                    if (map.keySet().contains(key)) {
                        value = map.get(key);
                        Log.d("MyLogValue", value.toString());
                        value.add(time);
                        map.put(key, value);
                        mCollection.add(map);
                        break;
                    }
                }
                if (value == null) {
                    map = new HashMap<EnumTest, MyCollection<String>>();
                    value = new MyCollection<String>();
                    value.add(time);
                    map.put(key, value);
                    mCollection.add(map);
                }
                Log.d("MyLogCollection", mCollection.toString());
                mCustomExpandableAdapter.notifyDataSetChanged();
                //TODO Logic
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Snackbar.make(spinner, "Nothing has been selected", Snackbar.LENGTH_SHORT).show();

            }
        });

    }
}
