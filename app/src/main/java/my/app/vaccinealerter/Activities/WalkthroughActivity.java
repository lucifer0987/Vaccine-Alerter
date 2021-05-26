package my.app.vaccinealerter.Activities;

import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

import my.app.vaccinealerter.R;

public class WalkthroughActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Add Alerts", "Select the plus button to add a location and receive alerts", R.drawable.add_button);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setTitleColor(R.color.white);
        ahoyOnboarderCard1.setDescriptionColor(R.color.whitish);
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(8, this));
        ahoyOnboarderCard1.setIconLayoutParams(600, 1000, 0, 0, 0, 0);

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Info Button", "Click on the info button to select optimal settings for the app", R.drawable.info_button);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setTitleColor(R.color.white);
        ahoyOnboarderCard2.setDescriptionColor(R.color.whitish);
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(8, this));
        ahoyOnboarderCard2.setIconLayoutParams(600, 1000, 0, 0, 0, 0);

        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Delete Alerts", "Click on the delete alert to delete the alerts for that particular location.", R.drawable.delete_button);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setTitleColor(R.color.white);
        ahoyOnboarderCard3.setDescriptionColor(R.color.whitish);
        ahoyOnboarderCard3.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard3.setDescriptionTextSize(dpToPixels(8, this));
        ahoyOnboarderCard3.setIconLayoutParams(600, 1000, 0, 0, 0, 0);

        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard2);
        setOnboardPages(pages);

        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.teal_200);
        colorList.add(R.color.darkblue);
        colorList.add(R.color.yellow);
        setColorBackground(colorList);

        showNavigationControls(true);
        setInactiveIndicatorColor(R.color.grey);
        setActiveIndicatorColor(R.color.white);
        setFinishButtonTitle("Get Started");
        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));


    }

    @Override
    public void onFinishButtonPressed() {
        Intent i = new Intent(WalkthroughActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}