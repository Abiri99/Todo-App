package com.abiri.mytodos;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abiri.mytodos.model.Todo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoListener {

    TextView day_num, month, year;
    RecyclerView rvTodos;

    private ArrayList<Todo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize contacts
        todos = Todo.createTodoList(20);

        this.initRecyclerView();

        this.initializeViews();

        this.setCurrentTime();
    }

    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;

    public void onAddItem(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String description = data.getExtras().getString("todo_description");
            Todo todo = new Todo(description);
            // Toast the name to display temporarily on screen
            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            todos.add(todo);
        }
    }

    @Override
    public void onTodoClick(int position) {
        todos.get(position);
        Intent intent = new Intent(this, TodoDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDoneClick(int position, View v) {
        todos.get(position).setDone(true);
        if(v.findViewById(R.id.todo_line).getVisibility() == View.VISIBLE) {
            v.findViewById(R.id.todo_line).setVisibility(View.INVISIBLE);
        } else {
            v.findViewById(R.id.todo_line).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRemoveClick(int position) {
        todos.remove(position);
        initRecyclerView();
    }

    public void initRecyclerView() {
        // Lookup the recyclerview in activity layout
        rvTodos = (RecyclerView) findViewById(R.id.rvTodos);

        // Create adapter passing in the sample user data
        TodoAdapter adapter = new TodoAdapter(todos, this);
        // Attach the adapter to the recyclerview to populate items
        rvTodos.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rvTodos.setLayoutManager(mLayoutManager);
        // That's all!
    }

    public void setCurrentTime() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        day_num.setText(formattedDate.split("-")[0]);
        month.setText(formattedDate.split("-")[1]);
        year.setText(formattedDate.split("-")[2]);
    }

    public void initializeViews() {

        day_num = findViewById(R.id.day_num);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            hideSystemUI();
//        }
//    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}
