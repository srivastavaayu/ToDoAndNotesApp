package com.madlab.todoandnotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rlMain;
    private FloatingActionButton fabMain,fabAddToDo,fabAddNote;
    BottomNavigationView navBottom;
    boolean fabOpenedNow=false, fabOpen=false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navBottom=findViewById(R.id.navBottom);
        rlMain=findViewById(R.id.rlMain);
        fabMain=findViewById(R.id.fabMain);
        fabAddToDo=findViewById(R.id.fabAddToDo);
        fabAddNote=findViewById(R.id.fabAddNote);
        fabAddToDo.setVisibility(View.GONE);
        fabAddNote.setVisibility(View.GONE);
        navBottom.setSelectedItemId(R.id.homemenu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.toString())
                {
                    case "Home" : Intent homeintent=new Intent(MainActivity.this,MainActivity.class);
                                  startActivity(homeintent);
                                  break;
                    case "To-Do": Intent todointent=new Intent(MainActivity.this,TodoActivity.class);
                                  startActivity(todointent);
                                  break;
                    case "Notes": Intent notesintent=new Intent(MainActivity.this,NotesActivity.class);
                                  startActivity(notesintent);
                                  break;
                    case "About": Intent aboutintent=new Intent(MainActivity.this,AboutActivity.class);
                                  startActivity(aboutintent);
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
                fabOpen=false;
                fabOpenedNow=false;

            }
        });
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fabOpen) {
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
                    fabOpen=true;
                    fabOpenedNow = true;
                }
                if(fabOpen & !fabOpenedNow)
                {
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
                    fabOpen=false;
                }
                fabOpenedNow=false;
            }
        });
        fabAddToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTodo=new Intent(MainActivity.this,AddTodoActivity.class);
                startActivity(addTodo);
            }
        });
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNote=new Intent(MainActivity.this,AddNoteActivity.class);
                startActivity(addNote);
            }
        });

    }
}