package com.example.module04main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.module04main.task01.ActivityTask01;
import com.example.module04main.task02.ActivityTask02;

public class MainActivity extends AppCompatActivity {

    /**
     * Сделай две задачи на массивы.
     * Показать пример работы GridLayout.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.task1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityTask01.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.task2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityTask02.class);
                startActivity(intent);
            }
        });
    }
}
