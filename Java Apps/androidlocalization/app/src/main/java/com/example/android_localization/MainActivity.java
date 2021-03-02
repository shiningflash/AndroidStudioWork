package com.example.android_localization;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView title;
    TextView tv;
    TextInputLayout tlayout;
    Button play;

    public static final String[] languages ={"Language","English","বাংলা"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        spinner = findViewById(R.id.spinner);
        tlayout = findViewById(R.id.filledTextField);
        play = findViewById(R.id.play_button);

        tv = findViewById(R.id.select_string_text);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLang = adapterView.getItemAtPosition(i).toString();
                if(selectedLang.equals("বাংলা")){
                    setLocal(MainActivity.this, "bn");
                    Intent intent = new Intent(MainActivity.this, Homepage.class);
                    Toast.makeText(MainActivity.this, getString(R.string.screen_two_text), Toast.LENGTH_SHORT).show();
                    tv.setText(getString(R.string.select_language));
                    tlayout.setHint(getString(R.string.enter_name));
                    play.setText(getString(R.string.play));
                    title.setText(getString(R.string.title));
//                    startActivity(intent);
                }
                else if(selectedLang.equals("English")){
                    setLocal(MainActivity.this,"en");
                    Intent intent = new Intent(MainActivity.this, Homepage.class);
                    Toast.makeText(MainActivity.this, getString(R.string.screen_two_text), Toast.LENGTH_SHORT).show();
                    tv.setText(getString(R.string.select_language));
                    tlayout.setHint(getString(R.string.enter_name));
                    play.setText(getString(R.string.play));
                    title.setText(getString(R.string.title));
//                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select a Language", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public  void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);

        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }
}