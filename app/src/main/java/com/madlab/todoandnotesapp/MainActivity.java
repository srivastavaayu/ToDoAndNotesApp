package com.madlab.todoandnotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.madlab.todoandnotesapp.data.note.NoteDatabase;
import com.madlab.todoandnotesapp.data.todo.TodoDatabase;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabMain, fabAddToDo, fabAddNote;
    private boolean fabOpenedNow = false, fabOpen = false;
    FragmentManager fragmentManager;
    private static final String SHAREDPREF="com.madlab.todoandnotesapp.SHAREDPREF";
    public static TodoDatabase todoDatabase;
    public static NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoDatabase=Room.databaseBuilder(getApplicationContext(),TodoDatabase.class,"tododb").allowMainThreadQueries().build();
        noteDatabase=Room.databaseBuilder(getApplicationContext(),NoteDatabase.class,"notedb").allowMainThreadQueries().build();
        BottomNavigationView navBottom = findViewById(R.id.navBottom);
        RelativeLayout rlMain = findViewById(R.id.rlMain);
        fabMain = findViewById(R.id.fabMain);
        fabAddToDo = findViewById(R.id.fabAddToDo);
        fabAddNote = findViewById(R.id.fabAddNote);
        fabAddToDo.setVisibility(View.GONE);
        fabAddNote.setVisibility(View.GONE);
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,new HomeFragment())
                .commit();
        navBottom.setSelectedItemId(R.id.homemenu);
        SharedPreferences sharedpref = getSharedPreferences(SHAREDPREF, MODE_PRIVATE);
        boolean firstTimeUser = sharedpref.getBoolean("firstTimeUser", true);
        if (firstTimeUser) {
            final DialogFragment dialogFragment = new FirstTimeUserFragment();
            dialogFragment.show(getSupportFragmentManager(), "firstTime");
        }
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.toString()) {
                    case "Home":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container,new HomeFragment())
                                .commit();
                        fabMain.setVisibility(View.VISIBLE);
                        break;
                    case "To-Do":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container,new ToDoFragment())
                                .commit();
                        fabMain.setVisibility(View.VISIBLE);
                        break;
                    case "Notes":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container,new NotesFragment())
                                .commit();
                        fabMain.setVisibility(View.VISIBLE);
                        break;
                    case "About":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container,new AboutFragment())
                                .commit();
                        fabMain.animate().setDuration(350).rotation(0f).start();
                        fabMain.setVisibility(View.GONE);
                        fabAddNote.animate().setDuration(300).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                fabAddNote.setVisibility(View.GONE);
                                super.onAnimationEnd(animation);
                            }
                        }).alpha(0f).start();
                        fabAddToDo.animate().setDuration(300).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                fabAddToDo.setVisibility(View.GONE);
                                super.onAnimationEnd(animation);
                            }
                        }).alpha(0f).start();
                        fabOpen = false;
                        fabOpenedNow = false;
                        break;
                }
                return true;
            }
        });
        rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMain.animate().setDuration(350).rotation(0f).start();
                fabAddToDo.setVisibility(View.GONE);
                fabAddNote.setVisibility(View.GONE);
                fabOpen = false;
                fabOpenedNow = false;

            }
        });
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fabOpen) {
                    fabAddNote.setVisibility(View.VISIBLE);
                    fabAddToDo.setVisibility(View.VISIBLE);
                    fabAddNote.setAlpha(0f);
                    fabAddToDo.setAlpha(0f);
                    fabMain.animate().setDuration(350).rotation(135f).start();
                    fabAddNote.animate().setDuration(400).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    }).alpha(1f).start();
                    fabAddToDo.animate().setDuration(400).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    }).alpha(1f).start();
                    fabOpen = true;
                    fabOpenedNow = true;
                }
                if (fabOpen & !fabOpenedNow) {
                    fabMain.animate().setDuration(350).rotation(0f).start();
                    fabAddNote.animate().setDuration(300).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fabAddNote.setVisibility(View.GONE);
                            super.onAnimationEnd(animation);
                        }
                    }).alpha(0f).start();
                    fabAddToDo.animate().setDuration(300).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fabAddToDo.setVisibility(View.GONE);
                            super.onAnimationEnd(animation);
                        }
                    }).alpha(0f).start();
                    fabOpen = false;
                }
                fabOpenedNow = false;
            }
        });
        fabAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTodo = new Intent(MainActivity.this, AddTodoActivity.class);
                startActivity(addTodo);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,new HomeFragment())
                        .commit();
            }
        });
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNote = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(addNote);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,new HomeFragment())
                        .commit();
            }
        });
    }
}