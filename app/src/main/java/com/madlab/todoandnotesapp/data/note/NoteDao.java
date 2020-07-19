package com.madlab.todoandnotesapp.data.note;

import android.app.Notification;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void addNote(Note note);

    @Query("SELECT * FROM NOTE")
    public List<Note> getNotes();

    @Delete
    public void removeNote(Note note);

    @Update
    public void updateNote(Note note);
}
