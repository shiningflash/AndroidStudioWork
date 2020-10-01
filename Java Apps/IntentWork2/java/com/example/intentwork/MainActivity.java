package com.example.intentwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private String message;

    public static String HELLO_MESSAGE = "Hello! ";

    public void sendMeesage(View view) {
        Intent intent = new Intent(this, ShowMessage.class);
        message = editText.getText().toString();
        intent.putExtra(HELLO_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMeesage(v);
            }
        });
    }
}
