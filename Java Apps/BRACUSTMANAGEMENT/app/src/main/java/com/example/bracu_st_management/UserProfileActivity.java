package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {
    protected TextView full_name;
    protected TextView student_id;
    protected TextView department;

    protected TextView email_id;
    protected TextView phone_no;

    protected TableLayout consultation_table;

    protected Button message_button;
    protected Button consultation_button;
    protected Button logout_button;

    private boolean vis;

    protected FirebaseAuth firebaseAuth;
    protected FirebaseUser firebaseUser;
    protected DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        try {
            full_name = (TextView) findViewById(R.id.full_name);
            student_id = (TextView) findViewById(R.id.student_id);
            department = (TextView) findViewById(R.id.department);

            email_id = (TextView) findViewById(R.id.email_id);
            phone_no = (TextView) findViewById(R.id.phone_no);

            consultation_table = (TableLayout) findViewById(R.id.consultation_table);

            message_button = (Button) findViewById(R.id.message_button);
            consultation_button = (Button) findViewById(R.id.consultation_button);
            logout_button = (Button) findViewById(R.id.logout_button);

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();

            final String email = firebaseUser.getEmail();
            String key = "";

            // Log.w("", key);

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

            try {
                key = email.replace(".", "dot");
                databaseReference.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Student student = dataSnapshot.getValue(Student.class);
                        final String name = student.getName();
                        final String sid = student.getStudent_id();
                        final String dept = student.getDepartment();
                        final String st = student.getStudent_tutor();
                        final String phoneno = student.getContact_no();
                        final String eid = student.getEmail_id();

                        full_name.setText(name);
                        student_id.setText("Student id: " + sid);
                        department.setText(dept);

                        email_id.setText(eid);
                        phone_no.setText(phoneno);

                        // make INVISIBLE in xml file later
                        if (st.equals("1")) consultation_button.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(UserProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            vis = true;

            consultation_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!vis) {
                        consultation_table.setVisibility(View.VISIBLE);
                        vis = true;
                    }
                    else {
                        consultation_table.setVisibility(View.INVISIBLE);
                        vis = false;
                    }
                }
            });

            logout_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intentLogin = new Intent(UserProfileActivity.this, LoginActivity.class);
                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentLogin);
                }
            });
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
