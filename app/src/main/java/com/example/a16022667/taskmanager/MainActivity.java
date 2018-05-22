package com.example.a16022667.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button btnAdd;
    TaskArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lvTask);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        DBHelper dbh = new DBHelper(this);
        ArrayList<Task> tasks = dbh.getAllNotes();
        adapter = new TaskArrayAdapter(this,R.layout.row, dbh.getAllNotes());
        lv.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent i = new Intent(MainActivity.this,AddActivity.class);

                startActivity(i);

            }
        });

    }
}
