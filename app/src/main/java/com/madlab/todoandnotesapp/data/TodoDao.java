package com.madlab.todoandnotesapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

@Dao
public interface TodoDao {

    @Insert
    public void addTodo(Todo todo);

    @Query("SELECT * FROM Todo")
    public ArrayList<Todo> getAllTodos();

    @Delete
    public void deleteTodo(int itemId);
}
