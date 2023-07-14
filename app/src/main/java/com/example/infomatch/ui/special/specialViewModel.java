package com.example.infomatch.ui.special;

import androidx.lifecycle.ViewModel;

public class specialViewModel extends ViewModel {
    private int mYear = -1, mMonth = -1, mDay = -1, mHour = -1, mMinute = -1;

    public int getmYear() {
        return mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public int getmHour() {
        return mHour;
    }

    public int getmMinute() {
        return mMinute;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public void setmMinute(int mMinute) {
        this.mMinute = mMinute;
    }

}
