package com.example.module03additional;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Input implements ParentListItem {

    private EnumTest mTitle;
    private List<Date> mDates;

    public Input(EnumTest title) {
        mTitle = title;
        mDates = new ArrayList<>();
    }

    @Override
    public List<Date> getChildItemList() {
        return mDates;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void addDate(Date date) {
        mDates.add(date);
    }

    public EnumTest getTitle() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Input input = (Input) o;

        if (mTitle != null ? !mTitle.equals(input.mTitle) : input.mTitle != null) return false;
        return mDates != null ? mDates.equals(input.mDates) : input.mDates == null;

    }

    @Override
    public int hashCode() {
        int result = mTitle != null ? mTitle.hashCode() : 0;
        result = 31 * result + (mDates != null ? mDates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Input{" +
                "mTitle='" + mTitle + '\'' +
                ", mDates=" + mDates +
                '}';
    }
}
