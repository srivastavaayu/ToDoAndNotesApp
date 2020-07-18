package com.madlab.todoandnotesapp.data.todo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    int itemId;
    @ColumnInfo(name = "todoTitle")
    String todoTitle;
    @ColumnInfo(name = "todoDesc")
    String todoDesc;
    @ColumnInfo(name = "todoDate")
    String todoDate;
    @ColumnInfo(name = "todoTime")
    String todoTime;

    public Todo() {
    }

    public Todo(int itemId, String todoTitle, String todoDesc, String todoDate, String todoTime) {
        this.itemId = itemId;
        this.todoTitle = todoTitle;
        this.todoDesc = todoDesc;
        this.todoDate = todoDate;
        this.todoTime = todoTime;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public String getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(String todoDate) {
        this.todoDate = todoDate;
    }

    public String getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }
}
