package com.madlab.todoandnotesapp.data.todo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    public void addTodo(Todo todo);

    @Query("SELECT * FROM TODO")
    public List<Todo> getTodos();
}
