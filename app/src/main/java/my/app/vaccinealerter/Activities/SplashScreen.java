package my.app.vaccinealerter.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


import io.paperdb.Paper;
import my.app.vaccinealerter.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Paper.init(this);
        Toast.makeText(SplashScreen.this, "Please Enable Auto-Start for checking vaccine slots in the background./n Select Vaccine Alerter app.", Toast.LENGTH_LONG).show();
        autoStartPermission();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){
            Paper.book().write("autostart", true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, WalkthroughActivity.class);
                    Paper.book().write("first", false);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }
    }

    private void autoStartPermission() {
        if (Build.BRAND.equalsIgnoreCase("xiaomi")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivityForResult(intent, 123);
        } else if (Build.BRAND.equalsIgnoreCase("Letv")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
            startActivityForResult(intent, 123);
        } else if (Build.BRAND.equalsIgnoreCase("Honor")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
            startActivityForResult(intent, 123);
        } else if (Build.BRAND.equalsIgnoreCase("oppo")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
            startActivityForResult(intent, 123);
        } else if (Build.BRAND.equalsIgnoreCase("vivo")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
            startActivityForResult(intent, 123);
        }else{
            Paper.book().write("autostart", true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, WalkthroughActivity.class);
                    Paper.book().write("first", false);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }
    }

}