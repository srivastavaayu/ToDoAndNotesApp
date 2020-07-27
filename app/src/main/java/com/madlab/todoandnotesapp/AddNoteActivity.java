package com.madlab.todoandnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.madlab.todoandnotesapp.data.note.Note;

public class AddNoteActivity extends AppCompatActivity {

    TextInputLayout txtEnterNoteTitle, txtEnterNote;
    Button btnSaveNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        txtEnterNoteTitle=findViewById(R.id.txtEnterNoteTitle);
        txtEnterNote=findViewById(R.id.txtEnterNote);
        btnSaveNote=findViewById(R.id.btnSaveNote);
        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterNoteTitle=txtEnterNoteTitle.getEditText().getText().toString().trim();
                String enterNote=txtEnterNote.getEditText().getText().toString().trim();
                if(enterNote.equals(""))
                {
                    txtEnterNote.setError("Please fill this field!");
                    return;
                }
                if(txtEnterNote.isErrorEnabled())
                {
                    txtEnterNote.setErrorEnabled(false);
                    txtEnterNote.setHelperTextEnabled(true);
                    txtEnterNote.setHelperText("Enter note above");
                }
                Note note=new Note();
                note.setNoteTitle(enterNoteTitle);
                note.setNoteDesc(enterNote);
                MainActivity.noteDatabase.noteDao().addNote(note);
                Toast.makeText(AddNoteActivity.this,"Note added successfully!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}