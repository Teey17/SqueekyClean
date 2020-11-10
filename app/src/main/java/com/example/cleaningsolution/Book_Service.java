package com.example.cleaningsolution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Book_Service extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Spinner spnrServiceSelected;
    Calendar calendar = Calendar.getInstance();
    TextView tvBookingSelected;
    String date = "";
    String time = "";
    String service = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__service);

        spnrServiceSelected = findViewById(R.id.spnrServiceSelected);
        tvBookingSelected = findViewById(R.id.tvBookingSelected);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.services_array,
                        android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrServiceSelected.setAdapter(staticAdapter);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.YEAR,year);

        date = DateFormat.getInstance().format(calendar.getTime());

        displayDetails();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);

        date = DateFormat.getInstance().format(calendar.getTime());
        time = DateFormat.getInstance().format(calendar.getTime());

        displayDetails();
    }
    public void selectDateOnClick(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"Date Picker");
    }

    public void selectTimeOnClick(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(),"Time Picker");
    }


    public void book(View view) {
        if (spnrServiceSelected.getSelectedItem() == "Select a service"){
            Toast.makeText(getApplicationContext(),"Please select a valid service",Toast.LENGTH_LONG).show();
        }
        else
            service = spnrServiceSelected.getSelectedItem().toString();

        if (date.equals("")){
            Toast.makeText(getApplicationContext(),"Please select a Date",Toast.LENGTH_LONG).show();
        }
        if (time.equals("")){
            Toast.makeText(getApplicationContext(),"Please select a Time",Toast.LENGTH_LONG).show();
        }


    }

    public void displayDetails(){
        if (spnrServiceSelected.getSelectedItem() == "Select a service"){
            Toast.makeText(getApplicationContext(),"Please select a valid service",Toast.LENGTH_LONG).show();
        }
        else
            service = spnrServiceSelected.getSelectedItem().toString();

        tvBookingSelected.setText("Booking Details: " +service+ " On the " + date);
    }
}
