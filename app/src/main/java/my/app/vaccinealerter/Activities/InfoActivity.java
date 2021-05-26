package my.app.vaccinealerter.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import my.app.vaccinealerter.R;

public class InfoActivity extends AppCompatActivity {

    Button app_info, app_walk, contact_us;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        app_info = findViewById(R.id.app_info_btn);
        app_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 123);
            }
        });

        app_walk = findViewById(R.id.app_walk_btn);
        app_walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, WalkthroughActivity.class);
                startActivity(intent);
            }
        });

        contact_us = findViewById(R.id.contact_us);
        contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailSelectorIntent = new Intent(Intent.ACTION_SENDTO);
                emailSelectorIntent.setData(Uri.parse("mailto:"));

                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gaurv1407@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Vaccine Alerter Issue/Bug");
                emailIntent.setSelector( emailSelectorIntent );

                if( emailIntent.resolveActivity(getPackageManager()) != null ) {
                    startActivity(emailIntent);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){
            Toast.makeText(this, "Hope, You have selected all the options required.", Toast.LENGTH_SHORT).show();
        }
    }
}