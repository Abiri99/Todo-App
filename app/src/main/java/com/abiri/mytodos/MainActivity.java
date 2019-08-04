package com.abiri.mytodos;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView day_num, month, year;

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        day_num = findViewById(R.id.day_num);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        day_num.setText(formattedDate.split("-")[0]);
        month.setText(formattedDate.split("-")[1]);
        year.setText(formattedDate.split("-")[2]);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("thiradsfsad Item");
        items.add("Second Item");
        items.add("Second Item");
        items.add("Second Item");
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
            String title = data.getExtras().getString("title");
            String description = data.getExtras().getString("description");
            // Toast the name to display temporarily on screen
            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            items.add(description);
        }
    }
}
