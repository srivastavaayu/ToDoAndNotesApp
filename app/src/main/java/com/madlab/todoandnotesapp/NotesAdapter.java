package com.madlab.todoandnotesapp;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.madlab.todoandnotesapp.data.note.Note;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    ArrayList<Note> notes=new ArrayList<>();
    Context context;

    public NotesAdapter(Context context, ArrayList<Note> list) {
        notes = list;
        this.context=context;
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
    public void onBindViewHolder(@NonNull final NotesAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(notes.get(position));
        holder.noteId.setText(String.valueOf(notes.get(position).getItemId()));
        holder.noteTitleText.setText(notes.get(position).getNoteTitle());
        if(notes.get(position).getNoteTitle().equals("")) {
            holder.noteTitleText.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,holder.itemView);
                popupMenu.inflate(R.menu.item_modification_menu);
                popupMenu.setGravity(Gravity.END);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.updatemenu:
                                break;
                            case R.id.deletemenu:
                                MainActivity.noteDatabase.noteDao().removeNote(notes.get(position));
                                Snackbar.make(holder.itemView,"Note successfully deleted!", BaseTransientBottomBar.LENGTH_LONG).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        holder.noteDescText.setText(String.format("Description: \n%s",notes.get(position).getNoteDesc()));
/*        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.noteDatabase.noteDao().removeNote(notes.get(position));
                Snackbar.make(holder.itemView,"Note successfully deleted!", BaseTransientBottomBar.LENGTH_LONG).show();
                return true;
            }
        });*/
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
