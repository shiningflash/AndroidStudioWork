package com.example.bracu_st_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ForgetPasswordActivity extends AppCompatActivity {
    protected EditText confirmation_code;
    protected EditText password;
    protected EditText repeat_password;
    protected Button confirm_button;
    protected Button back_button;
    protected ProgressBar progressBar;

    protected FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        confirmation_code = (EditText) findViewById(R.id.confirmation_code);
        password = (EditText) findViewById(R.id.password);
        repeat_password = (EditText) findViewById(R.id.repeat_password);
        confirm_button = (Button) findViewById(R.id.confirm_button);
        back_button = (Button) findViewById(R.id.back_button);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String confirm_code = confirmation_code.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                String repass = repeat_password.getText().toString().trim();

                if (confirm_code.isEmpty()) {
                    confirmation_code.setError("Please, enter confirmation code");
                    confirmation_code.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                // will work on here






                if (!(confirm_code.equals("123456"))) {
                    Toast.makeText(ForgetPasswordActivity.this, "Wrong confirmation code", Toast.LENGTH_SHORT).show();
                    confirmation_code.setError("Please, enter correct confirmation code");
                    confirmation_code.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (pass.isEmpty()) {
                    password.setError("Please, enter a new password");
                    password.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!(pass.equals(repass))) {
                    Toast.makeText(ForgetPasswordActivity.this, "Repeat Password not matched", Toast.LENGTH_SHORT).show();
                    return;
                }

                // will work here







            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignupActivity = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                startActivity(intentSignupActivity);
            }
        });
    }
}
