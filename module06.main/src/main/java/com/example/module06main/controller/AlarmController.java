package com.example.module06main.controller;

import com.example.module06main.dao.AlarmDaoImpl;
import com.example.module06main.entity.Alarm;

import java.util.ArrayList;

public class AlarmController {

    private AlarmDaoImpl mAlarmDao;

    public AlarmController() {
        mAlarmDao = AlarmDaoImpl.getInstance();
    }

    public ArrayList<Alarm> getAllAlarms() {
        return mAlarmDao.getAll();
    }

    public Alarm createAlarm() {
        return mAlarmDao.createAlarm();
    }

    public Alarm saveAlarm(Alarm alarm) {
        return mAlarmDao.saveAlarm(alarm);
    }

    public boolean removeAlarm(Alarm alarm) {
        return mAlarmDao.deleteAlarmById(alarm.getId());
    }

    public boolean removeAlarmById(int id) {
        return mAlarmDao.deleteAlarmById(id);
    }

    public Alarm getAlarmById(int id) {
        return mAlarmDao.getById(id);
    }

};
