package com.abiri.mytodos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abiri.mytodos.model.Todo;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {


    // Store a member variable for the contacts
    private List<Todo> mTodos;

    private OnTodoListener onTodoListener;

    // Pass in the contact array into the constructor
    public TodoAdapter(List<Todo> todos, OnTodoListener onTodoListener) {
        mTodos = todos;
        this.onTodoListener = onTodoListener;
    }


    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View todoView = inflater.inflate(R.layout.todo_item_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(todoView, onTodoListener);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TodoAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Todo todo = mTodos.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.todo_description;
        textView.setText(todo.getText());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView todo_description;
        public ImageView todo_done, todo_remove;
        OnTodoListener onTodoListener;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, OnTodoListener onTodoListener) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.onTodoListener = onTodoListener;

            todo_description = (TextView) itemView.findViewById(R.id.todo_description);
            todo_done = (ImageView) itemView.findViewById(R.id.todo_done);
            todo_remove = (ImageView) itemView.findViewById(R.id.todo_remove);

            // TODO: 8/4/2019 maybe i need to seperate view.set.... s.
            //itemView.setOnClickListener(this);
            todo_description.setOnClickListener(this);
            todo_done.setOnClickListener(this);
            todo_remove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.todo_description:
                    onTodoListener.onTodoClick(getAdapterPosition());
                    break;
                case R.id.todo_done:
                    onTodoListener.onDoneClick(getAdapterPosition(), itemView);
                    break;
                case R.id.todo_remove:
                    onTodoListener.onRemoveClick(getAdapterPosition());
                    break;
                default:
                    break;
            }
            //onTodoListener.onTodoClick(getAdapterPosition());
        }
    }

    public interface OnTodoListener {
        void onTodoClick(int position);
        void onDoneClick(int position, View view);
        void onRemoveClick(int position);
    }
}