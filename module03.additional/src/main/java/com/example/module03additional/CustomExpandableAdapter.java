package com.example.module03additional;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;


public class CustomExpandableAdapter extends BaseExpandableListAdapter {

    private MyCollection<Map<EnumTest, MyCollection<String>>> mGroups;
    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public CustomExpandableAdapter(Context context, MyCollection<Map<EnumTest, MyCollection<String>>> groups) {
        mGroups = groups;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        Map<EnumTest, MyCollection<String>> group = mGroups.get(i);
        return group.values().toArray().length;
    }

    @Override
    public Object getGroup(int i) {
        Map<EnumTest, MyCollection<String>> group = mGroups.get(i);
        return  group.keySet().iterator().next();
    }

    @Override
    public Object getChild(int i, int i1) {
        Map<EnumTest, MyCollection<String>> group = mGroups.get(i);
        Iterator<MyCollection<String>>  iterator = group.values().iterator();
        MyCollection<String> next = iterator.next();
        Log.d("MyLogGetChilled", next.toString());
        return  next.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mLayoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getGroup(i).toString());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        String child = getChild(i, i1).toString();
        textView.setText(child);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
