package com.example.module06main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.module06main.R;
import com.example.module06main.adapter.AlarmAdapter;
import com.example.module06main.controller.AlarmController;
import com.example.module06main.entity.Alarm;
import com.example.module06main.util.AppConstants;

public class AlarmListFragment extends BaseFragment implements View.OnClickListener {

    private AlarmController mAlarmController;

    private Button mBtnAddAlarm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list, container, false);

        mAlarmController = new AlarmController();

        mBtnAddAlarm = (Button) view.findViewById(R.id.main_btn_addAlarm);
        mBtnAddAlarm.setOnClickListener(this);

        RecyclerView recyclerAlarm = (RecyclerView) view.findViewById(R.id.main_recycler_alarm);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        final AlarmAdapter alarmAdapter = new AlarmAdapter(getContext(), mAlarmController.getAllAlarms());
        alarmAdapter.setClickListener(new AlarmAdapter.OnAlarmClickListener() {
            @Override
            public void onClick(int position) {
                int itemId = (int) alarmAdapter.getItemId(position);
                startAlarmFragment(mAlarmController.getAlarmById(itemId));
            }
        });
        alarmAdapter.setSwitchCheckListener(new AlarmAdapter.OnSwitchCheckListener() {
            @Override
            public void onCheckedChanged(int position, boolean isSwitchedOn) {
                int itemId = (int) alarmAdapter.getItemId(position);
                Alarm alarm = mAlarmController.getAlarmById(itemId);
                alarm.setEnabled(isSwitchedOn);
                mAlarmController.saveAlarm(alarm);
            }
        });

        recyclerAlarm.setLayoutManager(linearLayoutManager);
        recyclerAlarm.setAdapter(alarmAdapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_addAlarm :
                startAlarmFragment(mAlarmController.createAlarm());
                break;
            default:
                break;
        }
    }

    private void startAlarmFragment(Alarm alarm) {
        AlarmFragment fragment = new AlarmFragment();
        Bundle alarmBundle = new Bundle();
        alarmBundle.putParcelable(AppConstants.ALARM_OBJ_KEY, alarm);
        fragment.setArguments(alarmBundle);
        replaceFragment(fragment);
    }

}
