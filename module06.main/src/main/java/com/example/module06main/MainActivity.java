package com.example.module06main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.module06main.fragments.AlarmListFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final AppCompatActivity APP_COMPAT_ACTIVITY = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(new AlarmListFragment());

    }

    @Override
    public void onClick(View view) {
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void startFragmentForResult(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

}
