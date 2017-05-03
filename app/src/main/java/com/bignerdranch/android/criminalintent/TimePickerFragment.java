package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by Garry on 03/05/2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    private static String ARG_TIME = "time";
    public static String EXTRA_TIME = "com.bignerdranch.criminalintent.time";

    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_TIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) v;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        }

        return new TimePickerDialog(getActivity(), this, hour, minute ,false);

//        return new AlertDialog.Builder(getActivity())
//                .setTitle(R.string.time_picker_title)
//                .setView(R.layout.dialog_time)
//                .setPositiveButton(android.R.string.ok, )
//                .create();
    }

    private void setResult(int hour, int minute) {
        Intent intent = new Intent()
                .putExtra(EXTRA_TIME, getDateFromHoursMinutes(hour, minute));

        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

    private Date getDateFromHoursMinutes(int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(0,0,0, hours, minutes);
        return calendar.getTime();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        setResult(hourOfDay, minute);
    }
}
