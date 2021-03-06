package com.madlab.todoandnotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.madlab.todoandnotesapp.data.todo.Todo;

import java.util.Calendar;

public class AddTodoActivity extends AppCompatActivity {

    CheckBox cbEnableNotifs;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    TextInputLayout txtEnterTodo,txtEnterTodoDescription;
    TextInputLayout txtEnterTodoDate, txtEnterTodoTime;
    TextInputEditText txtEnterTodoDateText, txtEnterTodoTimeText;
    Button btnSaveTodo;
    AlarmManager alarmManager;
    RecyclerView rvTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        alarmManager=(AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        cbEnableNotifs = findViewById(R.id.cbEnableNotifs);
        txtEnterTodo=findViewById(R.id.txtEnterTodo);
        txtEnterTodoDescription=findViewById(R.id.txtEnterTodoDescription);
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
        final Calendar alarm=Calendar.getInstance();
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
                        alarm.set(Calendar.YEAR,year);
                        alarm.set(Calendar.MONTH,month);
                        alarm.set(Calendar.DAY_OF_MONTH,dayOfMonth);
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
                        alarm.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        alarm.set(Calendar.MINUTE,minute);
                        alarm.set(Calendar.SECOND,0);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        btnSaveTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterTodo=txtEnterTodo.getEditText().getText().toString().trim();
                String enterTodoDescription=txtEnterTodoDescription.getEditText().getText().toString().trim();
                String enterTodoDate=txtEnterTodoDate.getEditText().getText().toString().trim();
                String enterTodoTime=txtEnterTodoTime.getEditText().getText().toString().trim();
                if(enterTodo.equals(""))
                {
                    txtEnterTodo.setError("Please fill this field!");
                    return;
                }
                if(txtEnterTodo.isErrorEnabled())
                {

                    txtEnterTodo.setErrorEnabled(false);
                    txtEnterTodo.setHelperTextEnabled(true);
                    txtEnterTodo.setHelperText("Enter to-do item title above");
                }
                if(cbEnableNotifs.isChecked() && enterTodoDate.equals(""))
                {
                    txtEnterTodoDate.setError("Please select a date!");
                    return;
                }
                if(cbEnableNotifs.isChecked() && txtEnterTodoDate.isErrorEnabled())
                {
                    txtEnterTodoDate.setErrorEnabled(false);
                }
                if(cbEnableNotifs.isChecked() && enterTodoTime.equals(""))
                {
                    txtEnterTodoTime.setError("Please select a time!");
                    return;
                }
                if(cbEnableNotifs.isChecked() && txtEnterTodoTime.isErrorEnabled())
                {
                    txtEnterTodoTime.setErrorEnabled(false);
                }
                Todo todo=new Todo();
                todo.setTodoTitle(enterTodo);
                todo.setTodoDesc(enterTodoDescription);
                todo.setTodoDate(enterTodoDate);
                todo.setTodoTime(enterTodoTime);
                if(cbEnableNotifs.isChecked())
                {
                    Intent alarmIntent=new Intent(getApplicationContext(),AlarmBroadcastReceiver.class);
                    alarmIntent.putExtra("todoTitle",enterTodo);
                    alarmIntent.putExtra("todoDesc",enterTodoDescription);
                    PendingIntent pendingAlarmIntent=PendingIntent.getBroadcast(getApplicationContext(),1234,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT|Intent.FILL_IN_DATA);
                    alarmManager.setWindow(alarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),0,pendingAlarmIntent);
                }
                MainActivity.todoDatabase.todoDao().addTodo(todo);
                Toast.makeText(AddTodoActivity.this,"To-Do added successfully!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}