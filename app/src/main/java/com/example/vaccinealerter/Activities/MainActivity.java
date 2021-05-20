package com.example.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vaccinealerter.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button live_centers, settings;
    FloatingActionButton add_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        //check for splash screen
        if(Paper.book().read("first", true)){
            Intent i = new Intent(MainActivity.this, SplashScreen.class);
            startActivity(i);
            finish();
        }

        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }

    private void Init() {
        Paper.init(this);
        live_centers = findViewById(R.id.live_centers_btn);
        settings = findViewById(R.id.settings_btn);
        add_fab = findViewById(R.id.add_fab);
    }
}