package com.example.intentwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShowMessage extends AppCompatActivity {

    private TextView textView;
    private Button searchButton;

    public void searchGoogle(View view, String message) {
        Uri uri = Uri.parse("https://www.google.com/search?q=" + message);
        Intent search = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(search);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainActivity.HELLO_MESSAGE);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchGoogle(view, message);
            }
        });
    }
}
