package com.example.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vaccinealerter.Adapters.MainAdapter;
import com.example.vaccinealerter.Models.notificationURL;
import com.example.vaccinealerter.R;
import com.example.vaccinealerter.Utils.MyWorker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button live_centers, settings;
    FloatingActionButton add_fab;
    RecyclerView rec_view;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO add alerts storage
        //TODO add information section
        //TODO add samsung autostart

        Init();

        //check for splash screen
        if (Paper.book().read("first", true)) {
            Intent i = new Intent(MainActivity.this, SplashScreen.class);
            startActivity(i);
            finish();
        } else {
            if (!Paper.book().read("autostart", false)) {
                Toast.makeText(MainActivity.this, "Please Enable Auto-Start for checking vaccine slots in the background./n Select Vaccine Alerter app.", Toast.LENGTH_LONG).show();
                Paper.book().write("autostart", true);
                autoStartPermission();
            }

            List<notificationURL> noti = Paper.book().read("noti", new ArrayList<>());
            for (int i = 0; i < noti.size(); i++) {
                notificationURL curr = noti.get(i);
                Log.e("this", curr.getState());
                Log.e("this1", curr.getDistrict());
                Log.e("this2", curr.getURL());
                for (int j = 0; j < curr.getAge().size(); j++) {
                    Log.e("this", String.valueOf(curr.getAge().get(j)));
                }
            }

            //work manager
            if (Paper.book().read("work_start", false)) {
                WorkManager.getInstance().cancelAllWorkByTag("work");

                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .addTag("work")
                        .build();

                WorkManager.getInstance().enqueue(request);
                Toast.makeText(this, "Background Work re-started", Toast.LENGTH_LONG).show();
            } else {
                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .addTag("work")
                        .build();

                WorkManager.getInstance().enqueue(request);
                Paper.book().write("work_start", true);
                Toast.makeText(this, "Background Work Started", Toast.LENGTH_LONG).show();
            }


            //recycler view
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rec_view.setLayoutManager(linearLayoutManager);
            rec_view.setHasFixedSize(true);
            mainAdapter = new MainAdapter(MainActivity.this, this, noti);
            rec_view.setAdapter(mainAdapter);

            add_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SelectStateActivity.class);
                    intent.putExtra("seltype", "1");
                    startActivity(intent);
                }
            });

        }

    }

    private void Init() {
        Paper.init(this);
        live_centers = findViewById(R.id.live_centers_btn);
        settings = findViewById(R.id.alerts_btn);
        add_fab = findViewById(R.id.add_fab);
        rec_view = findViewById(R.id.rec_view);
    }

    private void autoStartPermission() {
        if (Build.BRAND.equalsIgnoreCase("xiaomi")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivity(intent);
        } else if (Build.BRAND.equalsIgnoreCase("Letv")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
            startActivity(intent);
        } else if (Build.BRAND.equalsIgnoreCase("Honor")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
            startActivity(intent);
        } else if (Build.BRAND.equalsIgnoreCase("oppo")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
            startActivity(intent);
        } else if (Build.BRAND.equalsIgnoreCase("vivo")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
            startActivity(intent);
        }
    }

}