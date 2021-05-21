package com.example.vaccinealerter.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.airbnb.lottie.utils.Utils;
import com.example.vaccinealerter.Models.Center;
import com.example.vaccinealerter.Models.Session;
import com.example.vaccinealerter.Models.getResults;
import com.example.vaccinealerter.Models.notificationURL;
import com.example.vaccinealerter.R;
import com.example.vaccinealerter.Retrofit.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyWorker extends Worker {

    Context context;
    String user_agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.76 Safari/537.36";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Paper.init(context);
        List<notificationURL> noti = Paper.book().read("noti", new ArrayList<>());

        for(int i = 0; i < noti.size(); i++){
            String Base_URL = noti.get(i).getURL();
            Log.e("this", Base_URL);
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            Log.e("this", formattedDate);
            Base_URL += "&date="+formattedDate;
            Log.e("this", Base_URL);

            List<Integer> ages = noti.get(i).getAge();

            Call<getResults> call = RetrofitClient.getInstance().getMyApi().getResults(Base_URL, user_agent);
            int finalI = i;
            call.enqueue(new Callback<getResults>() {
                @Override
                public void onResponse(Call<getResults> call, Response<getResults> response) {
                    if(response.code() == 200){
                        getResults curr = response.body();
                        List<Center> curr_center = curr.getCenters();
                        for(int j = 0; j < curr_center.size(); j++){
                            List<Session> curr_session = curr_center.get(j).getSessions();
                            for(int k = 0; k < curr_session.size(); k++){
                                for(int l = 0; l < ages.size(); l++){
                                    if(curr_session.get(k).getMinAgeLimit() == ages.get(l) && curr_session.get(k).getAvailableCapacity() > 0){
                                        displayNotification("Vaccine Available!", noti.get(finalI).getState()+", "+noti.get(finalI).getDistrict()+"\n"+curr_session.get(k).getDate());
                                    }
                                }
                            }
                        }
                    }else{
                        Log.e("Work Manager", "Some Failure Occurred!");
                    }
                }

                @Override
                public void onFailure(Call<getResults> call, Throwable t) {
                    Log.e("Work Manager", "Some Failure Occurred!");
                }
            });
        }

        return Result.success();
    }

    private void displayNotification(String task, String desc){

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("coding", "coding", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "coding")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);

        Random r = new Random();
        manager.notify(r.nextInt(), builder.build());
        playNotificationSound();

    }

    public void playNotificationSound() {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
