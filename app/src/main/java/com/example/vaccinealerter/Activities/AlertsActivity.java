package com.example.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.vaccinealerter.R;

import io.paperdb.Paper;

public class AlertsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        TextView alerts = findViewById(R.id.alerts_txt);
        Paper.init(this);
        String alerts_str = Paper.book().read("alerts", "No Alerts Found!");

        if(alerts_str.equals("")){
            alerts.setText("No Alerts Found!");
        }else {
            alerts.setText(alerts_str);
        }

    }
}