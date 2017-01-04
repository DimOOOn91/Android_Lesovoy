package com.example.module05main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    private SharedPreferences mSharedPreferences;

    private TextView mTextView;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getPreferences(MODE_PRIVATE);

        mTextView = (TextView) findViewById(R.id.text_view);

        Button button1 = (Button) findViewById(R.id.button_save);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button_load);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_save:
                EditText etLogin = (EditText) findViewById(R.id.login);
                EditText etPassword = (EditText) findViewById(R.id.password);

                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();

                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString(LOGIN, login);
                editor.putString(PASSWORD, password);
                editor.apply();

                etLogin.setText("");
                etPassword.setText("");
                mTextView.setText("");
                button2.setVisibility(View.VISIBLE);

                Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_load:
                String result = String.format("Login: %s \nPassword: %s",
                        mSharedPreferences.getString(LOGIN, ""),
                        mSharedPreferences.getString(PASSWORD, ""));
                mTextView.setText(result);
                view.setVisibility(View.GONE);
            default:
                break;
        }
    }
}
