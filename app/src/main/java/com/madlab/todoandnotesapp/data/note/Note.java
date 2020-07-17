package com.madlab.todoandnotesapp.data.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    int itemId;
    @ColumnInfo(name = "noteTitle")
    String noteTitle;
    @ColumnInfo(name = "noteDesc")
    String noteDesc;

    public int getItemId() {
        return itemId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }
}
