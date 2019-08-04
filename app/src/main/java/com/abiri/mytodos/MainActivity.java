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

public class MainActivity extends AppCompatActivity {

    TextView day_num, month, year;

    private ArrayList<Todo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Lookup the recyclerview in activity layout
        RecyclerView rvTodos = (RecyclerView) findViewById(R.id.rvTodos);

        // Initialize contacts
        todos = Todo.createTodoList(20);
        // Create adapter passing in the sample user data
        TodoAdapter adapter = new TodoAdapter(todos);
        // Attach the adapter to the recyclerview to populate items
        rvTodos.setAdapter(adapter);
        // Set layout manager to position the items
        rvTodos.setLayoutManager(new LinearLayoutManager(this));
        // That's all!



        day_num = findViewById(R.id.day_num);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        day_num.setText(formattedDate.split("-")[0]);
        month.setText(formattedDate.split("-")[1]);
        year.setText(formattedDate.split("-")[2]);

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
}
