package com.example.cleaningsolution;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TimePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minuteOfDay = calendar.get(Calendar.MINUTE);


        return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener) getActivity(),hourOfDay,minuteOfDay, DateFormat.is24HourFormat(getActivity()));
    }
}
