package com.madlab.todoandnotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotesActivity extends AppCompatActivity {
    private BottomNavigationView navBottom;
    private FloatingActionButton fabAddNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        navBottom=findViewById(R.id.navBottom);
        fabAddNote=findViewById(R.id.fabAddNote);
        navBottom.setSelectedItemId(R.id.notesmenu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.toString())
                {
                    case "Home" : Intent homeintent=new Intent(NotesActivity.this,MainActivity.class);
                        startActivity(homeintent);
                        break;
                    case "To-Do": Intent todointent=new Intent(NotesActivity.this,TodoActivity.class);
                        startActivity(todointent);
                        break;
                    case "Notes": Intent notesintent=new Intent(NotesActivity.this,NotesActivity.class);
                        startActivity(notesintent);
                        break;
                    case "About": Intent aboutintent=new Intent(NotesActivity.this,AboutActivity.class);
                        startActivity(aboutintent);
                        break;
                }
                return true;
            }
        });
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNote=new Intent(NotesActivity.this,AddNoteActivity.class);
                startActivity(addNote);
            }
        });
    }
}