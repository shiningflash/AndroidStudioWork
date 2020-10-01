package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student_ListActivity extends AppCompatActivity {

    protected Button logout_button;
    protected ScrollView scrollView;
    protected HorizontalScrollView horizontalScrollView;
    protected RelativeLayout relativeLayout;
    protected TableLayout tableLayout;

    protected DatabaseReference databaseReference;

    protected List<Student> students;

    protected int serial = 1;

    public void init() {
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        TableRow tableRowHeader = new TableRow(this);

        TextView textView1 = new TextView(this);
        textView1.setText(" sl No. ");
        textView1.setTextColor(Color.WHITE);
        textView1.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView1);

        TextView textView2 = new TextView(this);
        textView2.setText("  Std id.  ");
        textView2.setTextColor(Color.WHITE);
        textView2.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setText(" Name ");
        textView3.setTextColor(Color.WHITE);
        textView3.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView3);

        TextView textView4 = new TextView(this);
        textView4.setText(" Department ");
        textView4.setTextColor(Color.WHITE);
        textView4.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView4);

        tableLayout.addView(tableRowHeader);

        for (int i = 0; i < 50; i++) {
            TableRow tableRow = new TableRow(this);

            TextView textView11 = new TextView(this);
            textView11.setText("" + serial++);
            textView11.setTextColor(Color.WHITE);
            textView11.setGravity(Gravity.CENTER);
            tableRow.addView(textView11);

            TextView textView12 = new TextView(this);
            textView12.setText("  17101537  ");
            textView12.setTextColor(Color.WHITE);
            textView12.setGravity(Gravity.CENTER);
            tableRow.addView(textView12);

            TextView textView13 = new TextView(this);
            textView13.setText(" Amirul Islam ");
            textView13.setTextColor(Color.WHITE);
            textView13.setGravity(Gravity.CENTER);
            tableRow.addView(textView13);

            TextView textView14 = new TextView(this);
            textView14.setText(" CSE ");
            textView14.setTextColor(Color.WHITE);
            textView14.setGravity(Gravity.CENTER);
            tableRow.addView(textView14);

            tableLayout.addView(tableRow);
        }
    }

    public void showTable() {
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        TableRow tableRowHeader = new TableRow(this);

        TextView textView1 = new TextView(this);
        textView1.setText(" sl No. ");
        textView1.setTextColor(Color.WHITE);
        textView1.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView1);

        TextView textView2 = new TextView(this);
        textView2.setText("  Std id.  ");
        textView2.setTextColor(Color.WHITE);
        textView2.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setText(" Name ");
        textView3.setTextColor(Color.WHITE);
        textView3.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView3);

        TextView textView4 = new TextView(this);
        textView4.setText(" Department ");
        textView4.setTextColor(Color.WHITE);
        textView4.setGravity(Gravity.CENTER);
        tableRowHeader.addView(textView4);

        tableLayout.addView(tableRowHeader);

        Log.w("Array size: ", "Total Students : " + students.size());

        for (Student student : students) {
            String name = student.getName();
            String sid = student.getStudent_id();
            String dept = student.getDepartment();

            TableRow tableRow = new TableRow(this);

            TextView textView11 = new TextView(this);
            textView11.setText("" + serial++);
            textView11.setTextColor(Color.WHITE);
            textView11.setGravity(Gravity.CENTER);
            tableRow.addView(textView11);

            TextView textView12 = new TextView(this);
            textView12.setText(sid);
            textView12.setTextColor(Color.WHITE);
            textView12.setGravity(Gravity.CENTER);
            tableRow.addView(textView12);

            TextView textView13 = new TextView(this);
            textView13.setText(name);
            textView13.setTextColor(Color.WHITE);
            textView13.setGravity(Gravity.CENTER);
            tableRow.addView(textView13);

            TextView textView14 = new TextView(this);
            textView14.setText(dept);
            textView14.setTextColor(Color.WHITE);
            textView14.setGravity(Gravity.CENTER);
            tableRow.addView(textView14);

            tableLayout.addView(tableRow);
        }
    }

    public void showStudentList() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                students.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Student student = keyNode.getValue(Student.class);
                    students.add(student);
                    Log.w("===============", "Student : " + student);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " , databaseError.getMessage());
                Log.w("The read failed: " , databaseError.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_student__list);

        logout_button = (Button) findViewById(R.id.logout_button);

        students = new ArrayList<>();

        // init();
        showStudentList();
        showTable();

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intentLogin = new Intent(Student_ListActivity.this, LoginActivity.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogin);
            }
        });
    }
}
