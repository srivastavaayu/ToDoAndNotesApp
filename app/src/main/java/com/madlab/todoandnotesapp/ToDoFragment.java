package com.madlab.todoandnotesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.madlab.todoandnotesapp.data.todo.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDoFragment extends Fragment {

    RecyclerView rvTodos;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToDoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToDoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToDoFragment newInstance(String param1, String param2) {
        ToDoFragment fragment = new ToDoFragment();
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
        rvTodos=(RecyclerView) view.findViewById(R.id.rvTodos);
        rvTodos.setHasFixedSize(true);
        List<Todo> allTodos=MainActivity.todoDatabase.todoDao().getTodos();
        ArrayList<Todo> todoArrayList=new ArrayList<>();
        for(Todo tempTodo: allTodos)
        {
            Todo tempInsTodo=new Todo();
            tempInsTodo.setItemId(tempTodo.getItemId());
            tempInsTodo.setTodoTitle(tempTodo.getTodoTitle());
            tempInsTodo.setTodoDesc(tempTodo.getTodoDesc());
            tempInsTodo.setTodoDate(tempTodo.getTodoDate());
            tempInsTodo.setTodoTime(tempTodo.getTodoTime());
            todoArrayList.add(tempInsTodo);
        }
        layoutManager=new LinearLayoutManager(this.getActivity());
        rvTodos.setLayoutManager(layoutManager);
        adapter=new TodoAdapter(this.getActivity(),todoArrayList);
        rvTodos.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_to_do, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("To-Do");
        return view;
    }
}