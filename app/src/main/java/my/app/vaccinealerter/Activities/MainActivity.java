package my.app.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import my.app.vaccinealerter.Adapters.MainAdapter;
import my.app.vaccinealerter.Models.notificationURL;
import my.app.vaccinealerter.R;
import my.app.vaccinealerter.Utils.MyWorker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button live_centers, alerts;
    FloatingActionButton add_fab;
    RecyclerView rec_view;
    MainAdapter mainAdapter;
    ImageView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        //check for splash screen
        if (Paper.book().read("first", true)) {
            Intent i = new Intent(MainActivity.this, SplashScreen.class);
            startActivity(i);
            finish();
        } else {
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
                        //.setConstraints(constraints)
                        .addTag("work")
                        .build();

                WorkManager.getInstance().enqueue(request);
                Toast.makeText(this, "Background Work re-started", Toast.LENGTH_LONG).show();
            } else {
                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
                        //.setConstraints(constraints)
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

            alerts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AlertsActivity.class);
                    startActivity(intent);
                }
            });

            live_centers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Will be available in future updates!", Toast.LENGTH_SHORT).show();
                }
            });

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    startActivity(intent);
                }
            });

        }

    }

    private void Init() {
        Paper.init(this);
        live_centers = findViewById(R.id.live_centers_btn);
        alerts = findViewById(R.id.alerts_btn);
        add_fab = findViewById(R.id.add_fab);
        rec_view = findViewById(R.id.rec_view);
        info = findViewById(R.id.info);
    }

}