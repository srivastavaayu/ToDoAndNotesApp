package com.madlab.todoandnotesapp.data.note;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    public void addNote(Note note);

    @Query("SELECT * FROM NOTE")
    public List<Note> getNotes();
}
