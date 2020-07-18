package com.madlab.todoandnotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.madlab.todoandnotesapp.data.todo.Todo;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    ArrayList<Todo> todos = new ArrayList<>();

    public TodoAdapter(Context context, ArrayList<Todo> list) {
        todos = list;
    }


    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.todoitem;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        /*holder.getTodoId().setText(todos.get(position).getItemId());
        holder.getTodoTitleText().setText(todos.get(position).getTodoTitle().toString().trim());
        holder.getTodoDescText().setText(todos.get(position).getTodoDesc().toString().trim());
        holder.getTodoDateText().setText(todos.get(position).getTodoDate().toString().trim());
        holder.getTodoTimeText().setText(todos.get(position).getTodoTime().toString().trim());*/
        holder.itemView.setTag(todos.get(position));
        holder.todoId.setText(String.valueOf(todos.get(position).getItemId()));
        holder.todoTitleText.setText(todos.get(position).getTodoTitle().trim());
        holder.todoDescText.setText(todos.get(position).getTodoDesc().trim());
        holder.todoDateText.setText(todos.get(position).getTodoDate().trim());
        holder.todoTimeText.setText(todos.get(position).getTodoTime().trim());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView todoId, todoTitleText, todoDescText, todoDateText, todoTimeText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            todoId = itemView.findViewById(R.id.todoId);
            todoTitleText = itemView.findViewById(R.id.todoTitleText);
            todoDescText = itemView.findViewById(R.id.todoDescText);
            todoDateText = itemView.findViewById(R.id.todoDateText);
            todoTimeText = itemView.findViewById(R.id.todoTimeText);
        }

        /*public TextView getTodoId() {
            return todoId;
        }

        public TextView getTodoTitleText() {
            return todoTitleText;
        }

        public TextView getTodoDescText() {
            return todoDescText;
        }

        public TextView getTodoDateText() {
            return todoDateText;
        }

        public TextView getTodoTimeText() {
            return todoTimeText;
        }*/
    }
}
