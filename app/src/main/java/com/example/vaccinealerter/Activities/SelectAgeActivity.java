package com.example.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaccinealerter.Models.notificationURL;
import com.example.vaccinealerter.R;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class SelectAgeActivity extends AppCompatActivity {

    long districtId;
    Button done;
    String state, district;
    TextView statename;
    CheckBox group_18_44, group_45;
    String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_age);

        districtId = getIntent().getExtras().getLong("statecode");
        state = getIntent().getExtras().getString("state");
        district = getIntent().getExtras().getString("district");
        Init();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(group_18_44.isChecked()){
                    if(group_45.isChecked()){
                        List<notificationURL> noti = Paper.book().read("noti", new ArrayList<>());
                        notificationURL curr = new notificationURL();
                        curr.getAge().add(18);
                        curr.getAge().add(45);
                        curr.setState(state);
                        curr.setDistrict(district);
                        curr.setURL(url+districtId);
                        noti.add(curr);
                        Paper.book().write("noti", noti);
                    }else {
                        List<notificationURL> noti = Paper.book().read("noti", new ArrayList<>());
                        notificationURL curr = new notificationURL();
                        curr.getAge().add(18);
                        curr.setState(state);
                        curr.setDistrict(district);
                        curr.setURL(url+districtId);
                        noti.add(curr);
                        Paper.book().write("noti", noti);
                    }
                }else if(group_45.isChecked()){
                    List<notificationURL> noti = Paper.book().read("noti", new ArrayList<>());
                    notificationURL curr = new notificationURL();
                    curr.getAge().add(45);
                    curr.setState(state);
                    curr.setDistrict(district);
                    curr.setURL(url+districtId);
                    noti.add(curr);
                    Paper.book().write("noti", noti);
                }else{
                    Toast.makeText(SelectAgeActivity.this, "Please Select an age group!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(SelectAgeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

    }

    private void Init() {
        Paper.init(this);
        done = findViewById(R.id.done);
        statename = findViewById(R.id.stateName);
        group_18_44 = findViewById(R.id.group_18_44);
        group_45 = findViewById(R.id.group_45);

        statename.setText(state + " / " + district);
    }
}