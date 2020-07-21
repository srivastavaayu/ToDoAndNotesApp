package com.madlab.todoandnotesapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.madlab.todoandnotesapp.data.note.Note;
import com.madlab.todoandnotesapp.data.todo.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    RecyclerView rvNotes;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    View view;
    SwipeRefreshLayout swipeRefreshLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvNotes=(RecyclerView) view.findViewById(R.id.rvNotes);
        rvNotes.setHasFixedSize(true);
        List<Note> allNotes=MainActivity.noteDatabase.noteDao().getNotes();
        ArrayList<Note> noteArrayList= new ArrayList<>();
        for(Note tempNote: allNotes)
        {
            Note tempInsNote=new Note();
            tempInsNote.setItemId(tempNote.getItemId());
            tempInsNote.setNoteTitle(tempNote.getNoteTitle());
            tempInsNote.setNoteDesc(tempNote.getNoteDesc());
            noteArrayList.add(tempInsNote);
        }
        layoutManager=new LinearLayoutManager(this.getActivity());
        rvNotes.setLayoutManager(layoutManager);
        adapter=new NotesAdapter(this.getActivity(),noteArrayList);
        rvNotes.setAdapter(adapter);
        swipeRefreshLayout=view.findViewById(R.id.srlNotes);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<Note> allNotes2=MainActivity.noteDatabase.noteDao().getNotes();
                ArrayList<Note> noteArrayList2= new ArrayList<>();
                for(Note tempNote: allNotes2)
                {
                    Note tempInsNote=new Note();
                    tempInsNote.setItemId(tempNote.getItemId());
                    tempInsNote.setNoteTitle(tempNote.getNoteTitle());
                    tempInsNote.setNoteDesc(tempNote.getNoteDesc());
                    noteArrayList2.add(tempInsNote);
                }
                adapter=new NotesAdapter(getContext(),noteArrayList2);
                adapter.notifyDataSetChanged();
                rvNotes.setAdapter(adapter);
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(swipeRefreshLayout.isRefreshing())
                        {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                },1000);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_notes, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Notes");
        return view;
    }
}