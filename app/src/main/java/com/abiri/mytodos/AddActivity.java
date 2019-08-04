package com.abiri.mytodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        description = (EditText) findViewById(R.id.description);
    }

    public void onSaveItem(View view) {
        Intent intent = new Intent();
        intent.putExtra("todo_description", description.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
