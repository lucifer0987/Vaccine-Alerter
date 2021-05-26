package my.app.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import my.app.vaccinealerter.R;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //TODO take data from intent and on the basis of that, get either state-district data or pincode data.
    }
}