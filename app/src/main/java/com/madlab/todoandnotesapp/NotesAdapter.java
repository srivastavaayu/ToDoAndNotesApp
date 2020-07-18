package com.madlab.todoandnotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madlab.todoandnotesapp.data.note.Note;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    ArrayList<Note> notes=new ArrayList<>();

    public NotesAdapter(Context context, ArrayList<Note> list) {
        notes = list;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.noteitem;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(notes.get(position));
        holder.noteId.setText(String.valueOf(notes.get(position).getItemId()));
        holder.noteTitleText.setText(notes.get(position).getNoteTitle());
        holder.noteDescText.setText(notes.get(position).getNoteDesc());
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteId, noteTitleText, noteDescText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteId = itemView.findViewById(R.id.noteId);
            noteTitleText = itemView.findViewById(R.id.noteTitleText);
            noteDescText = itemView.findViewById(R.id.noteDescText);
        }
    }
}
