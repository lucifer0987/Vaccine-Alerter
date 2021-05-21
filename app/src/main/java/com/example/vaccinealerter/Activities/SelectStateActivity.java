package com.example.vaccinealerter.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vaccinealerter.Adapters.SelectStateAdapter;
import com.example.vaccinealerter.Models.State;
import com.example.vaccinealerter.Models.getState;
import com.example.vaccinealerter.R;
import com.example.vaccinealerter.Retrofit.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectStateActivity extends AppCompatActivity {

    String seltype = "";
    ProgressBar progressBar;
    RecyclerView rec_view;
    SelectStateAdapter selectStateAdapter;
    List<State> data = new ArrayList<>();
    CoordinatorLayout coordinatorLayout;
    String user_agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.76 Safari/537.36";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);

        seltype = getIntent().getStringExtra("seltype");
        Init();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rec_view.setLayoutManager(linearLayoutManager);
        selectStateAdapter = new SelectStateAdapter(this, data, seltype);
        rec_view.setAdapter(selectStateAdapter);

        GetState();

    }

    private void GetState() {
        Call<getState> call = RetrofitClient.getInstance().getMyApi().getState(user_agent);
        call.enqueue(new Callback<getState>() {
            @Override
            public void onResponse(Call<getState> call, Response<getState> response) {
                if(response.code() == 200){
                    getState getstate = response.body();
                    Log.e("this", getstate.toString());
                    data = getstate.getStates();
                    selectStateAdapter.setData(data);
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
            public void onFailure(Call<getState> call, Throwable t) {
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
        coordinatorLayout = findViewById(R.id.cord);
    }

    private void showSnackBar(){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Please Try Again!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GetState();
                    }
                });
        snackbar.show();
    }
}