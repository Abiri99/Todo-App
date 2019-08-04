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
public class TodoAdapter extends
        RecyclerView.Adapter<TodoAdapter.ViewHolder> {


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView todo_description;
        public ImageView todo_done, todo_remove;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            todo_description = (TextView) itemView.findViewById(R.id.todo_description);
            todo_done = (ImageView) itemView.findViewById(R.id.todo_done);
            todo_remove = (ImageView) itemView.findViewById(R.id.todo_remove);
        }
    }

    // Store a member variable for the contacts
    private List<Todo> mTodos;

    // Pass in the contact array into the constructor
    public TodoAdapter(List<Todo> todos) {
        mTodos = todos;
    }


    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View todoView = inflater.inflate(R.layout.todo_item_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(todoView);
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
}