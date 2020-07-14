package com.madlab.todoandnotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;

public class AddTodoActivity extends AppCompatActivity {

    CheckBox cbEnableNotifs;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    TextInputLayout txtEnterTodoDate, txtEnterTodoTime;
    TextInputEditText txtEnterTodoDateText, txtEnterTodoTimeText;
    Button btnSaveTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        cbEnableNotifs = findViewById(R.id.cbEnableNotifs);
        txtEnterTodoDate = findViewById(R.id.txtEnterTodoDate);
        txtEnterTodoTime = findViewById(R.id.txtEnterTodoTime);
        txtEnterTodoDateText = findViewById(R.id.txtEnterTodoDateText);
        txtEnterTodoTimeText = findViewById(R.id.txtEnterTodoTimeText);
        btnSaveTodo=findViewById(R.id.btnSaveTodo);
        cbEnableNotifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    txtEnterTodoDate.setVisibility(View.VISIBLE);
                    txtEnterTodoDateText.setVisibility(View.VISIBLE);
                    txtEnterTodoTime.setVisibility(View.VISIBLE);
                    txtEnterTodoTimeText.setVisibility(View.VISIBLE);
                } else {
                    txtEnterTodoDate.setVisibility(View.GONE);
                    txtEnterTodoDateText.setVisibility(View.GONE);
                    txtEnterTodoTime.setVisibility(View.GONE);
                    txtEnterTodoTimeText.setVisibility(View.GONE);
                    txtEnterTodoDateText.setText(null);
                    txtEnterTodoTimeText.setText(null);
                }
            }
        });
        txtEnterTodoDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(AddTodoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtEnterTodoDateText.setText(String.format("%02d/%02d/%02d", dayOfMonth, (month + 1), year));
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        txtEnterTodoTimeText.setOnClickListener(new View.OnClickListener() {
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minute = cldr.get(Calendar.MINUTE);

            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(AddTodoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int hour = hourOfDay % 12;
                        txtEnterTodoTimeText.setText(String.format("%02d:%02d %s", hour == 0 ? 12 : hour, minute, hourOfDay < 12 ? "AM" : "PM"));
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
    }
}