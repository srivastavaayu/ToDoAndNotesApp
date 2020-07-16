package com.madlab.todoandnotesapp.data;

import android.widget.ArrayAdapter;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    public void addTodo(Todo todo);

    @Query("SELECT * FROM TODO")
    public List<Todo> getTodos();
}
