package com.example.module06main.dao;

import com.example.module06main.entity.Alarm;

import java.util.ArrayList;

public interface AlarmDao {

    Alarm createAlarm();
    Alarm saveAlarm(Alarm alarm);
    boolean deleteAlarmById(int id);
    Alarm getById(int id);
    ArrayList<Alarm> getAll();
}
