package com.example.module02task02_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**Вариант 2-07
 *Если аргумент имеет вид "имя=значение", то он является ключевым параметром (тип "Keyed"),
 * если аргумент имеет вид "-значение" или "/значение", то он является опцией (тип "Optional")
 * и если имеет вид "значение", то является непосредственным параметром (тип "Immediate").
 * Шаблон для значения: одна или несколько цифр и букв (включая буквы кириллицы).
 * Программа выводит количество заданных аргументов и, для каждого аргумента, его тип и значение
 * (для ключевых параметров дополнительно выводится имя параметра).
 *
 */

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private TextView mTextView;
    private int mArgumentCounter = 0;

    private static List<Keyed> sKeyedValues = new ArrayList<>();
    private static List<Optional> sOptionalValues = new ArrayList<>();
    private static List<Immediate> sImmediateValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text) ;

        Button buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mEditText.getText().toString();
                if (Pattern.matches("[\\w]+\\=[\\w]+", input)) {
                    sKeyedValues.add(new Keyed(input));
                    mArgumentCounter++;
                    Toast.makeText(MainActivity.this, R.string.saved, Toast.LENGTH_SHORT).show();
                    mEditText.setText("");
                } else if (Pattern.matches("[\\-|/][\\w]+", input)) {
                    sOptionalValues.add(new Optional(input));
                    mArgumentCounter++;
                    Toast.makeText(MainActivity.this, R.string.saved, Toast.LENGTH_SHORT).show();
                    mEditText.setText("");
                } else{
                    sImmediateValues.add(new Immediate(input));
                    mArgumentCounter++;
                    Toast.makeText(MainActivity.this, R.string.saved, Toast.LENGTH_SHORT).show();
                    mEditText.setText("");
                }
            }
        });

        Button buttonArguments = (Button) findViewById(R.id.button);
        buttonArguments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText(result());
            }
        });

        mTextView = (TextView) findViewById(R.id.text_view);
    }

    private String result () {

        return String.format("Total number of arguments: %s \n\n Keyed: %s \n Optional: %s \n Immediate: asdasd%s",
                mArgumentCounter, sKeyedValues, sOptionalValues, sImmediateValues);
    }

}
