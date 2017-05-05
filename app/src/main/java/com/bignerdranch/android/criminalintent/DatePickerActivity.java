package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;

/**
 * Created by Garry on 27/04/2017.
 */

public class DatePickerActivity extends SingleFragmentActivity{

    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";

    public static Intent newIntent(Context context, Date date) {
        Intent intent = new Intent(context, DatePickerActivity.class);
        intent.putExtra(EXTRA_DATE, date);

        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Date date;
        if (getIntent() != null) {
            date = (Date) getIntent().getSerializableExtra(EXTRA_DATE);
        } else {
            date = new Date();
        }

        return new DatePickerFragment().newInstance(date, false);
    }
}
