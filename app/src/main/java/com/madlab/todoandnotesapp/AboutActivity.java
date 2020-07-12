package com.madlab.todoandnotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {
    BottomNavigationView navBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        navBottom=findViewById(R.id.navBottom);
        navBottom.setSelectedItemId(R.id.aboutmenu);
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.toString())
                {
                    case "Home" : Intent homeintent=new Intent(AboutActivity.this,MainActivity.class);
                        startActivity(homeintent);
                        break;
                    case "To-Do": Intent todointent=new Intent(AboutActivity.this,TodoActivity.class);
                        startActivity(todointent);
                        break;
                    case "Notes": Intent notesintent=new Intent(AboutActivity.this,NotesActivity.class);
                        startActivity(notesintent);
                        break;
                    case "About": Intent aboutintent=new Intent(AboutActivity.this,AboutActivity.class);
                        startActivity(aboutintent);
                        break;
                }
                return true;
            }
        });
    }
}