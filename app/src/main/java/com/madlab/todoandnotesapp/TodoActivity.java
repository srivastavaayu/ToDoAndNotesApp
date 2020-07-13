package com.madlab.todoandnotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    private BottomNavigationView navBottom;
    private FloatingActionButton fabAddToDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        navBottom=findViewById(R.id.navBottom);
        fabAddToDo=findViewById(R.id.fabAddToDo);
        navBottom.setSelectedItemId(R.id.todomenu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.toString())
                {
                    case "Home" : Intent homeintent=new Intent(TodoActivity.this,MainActivity.class);
                        startActivity(homeintent);
                        break;
                    case "To-Do": Intent todointent=new Intent(TodoActivity.this,TodoActivity.class);
                        startActivity(todointent);
                        break;
                    case "Notes": Intent notesintent=new Intent(TodoActivity.this,NotesActivity.class);
                        startActivity(notesintent);
                        break;
                    case "About": Intent aboutintent=new Intent(TodoActivity.this,AboutActivity.class);
                        startActivity(aboutintent);
                        break;
                }
                return true;
            }
        });
        fabAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTodo=new Intent(TodoActivity.this,AddTodoActivity.class);
                startActivity(addTodo);
            }
        });
    }
}