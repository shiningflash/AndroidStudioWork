package com.example.bracu_st_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ST_ListActivity extends AppCompatActivity {
    protected TableLayout tableLayout;
    protected TableRow tableRow;
    protected Button st_name;
    protected TextView course_code;
    protected TextView section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st__list);

        tableLayout = (TableLayout) findViewById(R.id.st_list);

    }
}
