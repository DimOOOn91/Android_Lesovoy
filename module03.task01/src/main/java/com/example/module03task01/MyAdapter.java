package com.example.module03task01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends BaseAdapter {

    private ArrayList<String> mListKey;
    private ArrayList<Integer> mListValue;

    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, ArrayList<String> listKey, ArrayList<Integer> listValue) {
        mListKey = listKey;
        mListValue = listValue;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListKey.size();
    }

    @Override
    public Object getItem(int i) {
        return mListKey.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = view;
        if (rootView == null) {
            rootView = mLayoutInflater.inflate(R.layout.item_layout, viewGroup, false);
        }

        TextView key = (TextView) rootView.findViewById(R.id.key);
        TextView value = (TextView) rootView.findViewById(R.id.value);

        key.setText(mListKey.get(i));
        value.setText(String.valueOf(mListValue.get(i)));

        return rootView;
    }
}
