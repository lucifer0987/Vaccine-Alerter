package com.example.vaccinealerter.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaccinealerter.Models.notificationURL;
import com.example.vaccinealerter.R;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.List;

import io.paperdb.Paper;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    Context context;
    List<notificationURL> data;
    Activity activity;

    public MainAdapter(Activity activity, Context context, List<notificationURL> data){
        this.context = context;
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        notificationURL curr = data.get(position);
        holder.state.setText(curr.getState());
        holder.district.setText(curr.getDistrict());
        List<Integer> ages = curr.getAge();
        for(int i = 0; i < ages.size(); i++){
            if(ages.get(i) == 18){
                holder.age_18.setVisibility(View.VISIBLE);
                holder.txt1.setVisibility(View.VISIBLE);
            }else if(ages.get(i) == 45){
                holder.txt2.setVisibility(View.VISIBLE);
                holder.age_45.setVisibility(View.VISIBLE);
            }
        }

        holder.delete_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog mDialog = new MaterialDialog.Builder(activity)
                        .setTitle("Delete?")
                        .setMessage("Are you sure want to delete this file?")
                        .setCancelable(false)
                        .setPositiveButton("Delete", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                data.remove(curr);
                                Paper.init(context);
                                Paper.book().write("noti", data);
                                Paper.book().write("alerts", "");
                                notifyDataSetChanged();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new MaterialDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        })
                        .build();
                mDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView state, district, txt1, txt2;
        ImageView age_18, age_45, delete_alert;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.state);
            district = itemView.findViewById(R.id.district);
            age_18 = itemView.findViewById(R.id.age_18);
            age_45 = itemView.findViewById(R.id.age_45);
            delete_alert = itemView.findViewById(R.id.delete_alert);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
        }
    }

    public void setData(List<notificationURL> p){
        this.data = p;
        notifyDataSetChanged();
    }
}
