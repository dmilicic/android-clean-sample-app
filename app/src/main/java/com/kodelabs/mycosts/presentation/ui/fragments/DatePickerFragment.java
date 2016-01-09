package com.kodelabs.mycosts.presentation.ui.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by dmilicic on 12/20/15.
 */
public class DatePickerFragment extends DialogFragment {

    public DatePickerFragment() {
        // empty
    }

    private DatePickerDialog.OnDateSetListener mListener;

    public void setListener(OnDateSetListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), mListener, year, month, day);
    }
}
