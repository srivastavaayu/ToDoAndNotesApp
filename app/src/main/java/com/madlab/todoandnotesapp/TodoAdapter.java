package com.madlab.todoandnotesapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.madlab.todoandnotesapp.data.todo.Todo;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{

    ArrayList<Todo> todos = new ArrayList<>();
    Context context;

    public TodoAdapter() {
        notifyDataSetChanged();
    }

    public TodoAdapter(Context context, ArrayList<Todo> list) {
        todos = list;
        this.context=context;
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
    public void onBindViewHolder(@NonNull final TodoAdapter.ViewHolder holder, final int position) {
        /*holder.getTodoId().setText(todos.get(position).getItemId());
        holder.getTodoTitleText().setText(todos.get(position).getTodoTitle().toString().trim());
        holder.getTodoDescText().setText(todos.get(position).getTodoDesc().toString().trim());
        holder.getTodoDateText().setText(todos.get(position).getTodoDate().toString().trim());
        holder.getTodoTimeText().setText(todos.get(position).getTodoTime().toString().trim());*/
        holder.itemView.setTag(todos.get(position));
        holder.todoId.setText(String.valueOf(todos.get(position).getItemId()));
        holder.todoTitleText.setText(todos.get(position).getTodoTitle().trim());
        holder.todoDescText.setText(String.format("Description: %s",todos.get(position).getTodoDesc().trim()));
        if(todos.get(position).getTodoDesc().equals("")){
            holder.todoDescText.setVisibility(View.GONE);
        }
        holder.todoDateText.setText(String.format("Date: %s",todos.get(position).getTodoDate().trim()));
        if(todos.get(position).getTodoDate().equals("")){
            holder.todoDateText.setVisibility(View.GONE);
        }
        holder.todoTimeText.setText(String.format("Time: %s",todos.get(position).getTodoTime().trim()));
        if(todos.get(position).getTodoTime().equals("")){
            holder.todoTimeText.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu=new PopupMenu(context,holder.itemView);
                popupMenu.inflate(R.menu.item_modification_menu);
                popupMenu.setGravity(Gravity.END);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.copymenu:
                                ClipboardManager clipboardManager=(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                                if(todos.get(position).getTodoDesc().equals("")){
                                    ClipData clip=ClipData.newPlainText("To-Do","To-Do:" +
                                            "\nTo-Do Title: " + holder.todoTitleText.getText());
                                    clipboardManager.setPrimaryClip(clip);
                                }
                                else {
                                    ClipData clip= ClipData.newPlainText("To-Do", "To-Do:" +
                                            "\nTo-Do Title: " + holder.todoTitleText.getText() +
                                            "\nTo-Do Description: " + todos.get(position).getTodoDesc().trim());
                                    clipboardManager.setPrimaryClip(clip);
                                }
                                break;
                            case R.id.updatemenu:
                                break;
                            case R.id.deletemenu:
                                MainActivity.todoDatabase.todoDao().removeTodo(todos.get(position));
                                Snackbar.make(holder.itemView,"To-Do successfully deleted!", BaseTransientBottomBar.LENGTH_LONG).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
/*        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.todoDatabase.todoDao().removeTodo(todos.get(position));
                Snackbar.make(holder.itemView,"To-Do successfully deleted!", BaseTransientBottomBar.LENGTH_LONG).show();
                return true;
            }
        });*/
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
