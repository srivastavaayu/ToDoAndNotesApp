package com.madlab.todoandnotesapp;


import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Todos {
    private String ToDoTitle;
    private String ToDoDescription;
    private String ToDoDate;
    private String ToDoTime;
    public String getToDoTitle() {
        return ToDoTitle;
    }

    public void setToDoTitle(String toDoTitle) {
        ToDoTitle = toDoTitle;
    }

    public String getToDoDescription() {
        return ToDoDescription;
    }

    public void setToDoDescription(String toDoDescription) {
        ToDoDescription = toDoDescription;
    }

    public String getToDoDate() {
        return ToDoDate;
    }

    public void setToDoDate(String toDoDate) {
        ToDoDate = toDoDate;
    }

    public String getToDoTime() {
        return ToDoTime;
    }

    public void setToDoTime(String toDoTime) {
        ToDoTime = toDoTime;
    }
}
