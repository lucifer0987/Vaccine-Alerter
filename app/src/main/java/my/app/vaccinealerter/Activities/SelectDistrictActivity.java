package my.app.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import my.app.vaccinealerter.Adapters.SelectDistrictAdapter;
import my.app.vaccinealerter.Models.District;
import my.app.vaccinealerter.Models.getDistrict;

import my.app.vaccinealerter.R;
import my.app.vaccinealerter.Retrofit.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectDistrictActivity extends AppCompatActivity {

    String seltype = "";
    String state;
    long statecode;
    ProgressBar progressBar;
    RecyclerView rec_view;
    TextView stateName;
    SelectDistrictAdapter selectDistrictAdapter;
    List<District> data = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;
    String user_agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.76 Safari/537.36";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_district);

        seltype = getIntent().getStringExtra("seltype");
        statecode = getIntent().getExtras().getLong("statecode");
        Log.e("this", String.valueOf(statecode));
        Init();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rec_view.setLayoutManager(linearLayoutManager);
        selectDistrictAdapter = new SelectDistrictAdapter(this, data, seltype, state);
        rec_view.setAdapter(selectDistrictAdapter);

        GetDistrict();
    }

    private void GetDistrict() {
        String baseUrl = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/" + statecode;
        Log.e("this", baseUrl);
        Call<getDistrict> call = RetrofitClient.getInstance().getMyApi().getDistrict(baseUrl, user_agent);
        call.enqueue(new Callback<getDistrict>() {
            @Override
            public void onResponse(Call<getDistrict> call, Response<getDistrict> response) {
                if(response.code() == 200){
                    getDistrict getdistrict = response.body();
                    Log.e("this", getdistrict.toString());
                    data = getdistrict.getDistricts();
                    selectDistrictAdapter.setData(data);
                    progressBar.setVisibility(View.GONE);
                    rec_view.setVisibility(View.VISIBLE);
                }else{
                    Log.e("this", String.valueOf(response.code()));
                    showSnackBar();
                    progressBar.setVisibility(View.VISIBLE);
                    rec_view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<getDistrict> call, Throwable t) {
                Log.e("this", t.getMessage());
                showSnackBar();
                progressBar.setVisibility(View.VISIBLE);
                rec_view.setVisibility(View.GONE);
            }
        });
    }

    private void Init() {
        Paper.init(this);
        progressBar = findViewById(R.id.progress_bar);
        rec_view = findViewById(R.id.rec_view);
        stateName = findViewById(R.id.stateName);
        coordinatorLayout = findViewById(R.id.cord);

        state = getIntent().getExtras().getString("state");
        stateName.setText(state);
    }

    private void showSnackBar(){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Please Try Again!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GetDistrict();
                    }
                });
        snackbar.show();
    }

}