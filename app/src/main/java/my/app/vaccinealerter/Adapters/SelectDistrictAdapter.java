package my.app.vaccinealerter.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import my.app.vaccinealerter.Activities.ResultsActivity;
import my.app.vaccinealerter.Activities.SelectAgeActivity;
import my.app.vaccinealerter.Models.District;
import my.app.vaccinealerter.R;


import java.util.List;

public class SelectDistrictAdapter extends RecyclerView.Adapter<SelectDistrictAdapter.SelectViewHolder> {

    List<District> data;
    Context context;
    String seltype;
    String state;

    public SelectDistrictAdapter(Context context, List<District> data, String code, String state){
        this.context = context;
        this.data = data;
        this.seltype = code;
        this.state = state;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
        return new SelectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {
        holder.name.setText(data.get(position).getDistrictName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seltype.equals("1")){
                    Intent intent = new Intent(context, SelectAgeActivity.class);
                    intent.putExtra("statecode", data.get(position).getDistrictId());
                    intent.putExtra("state", state);
                    intent.putExtra("district", data.get(position).getDistrictName());
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, ResultsActivity.class);
                    intent.putExtra("statecode", data.get(position).getDistrictId());
                    intent.putExtra("state", state);
                    intent.putExtra("district", data.get(position).getDistrictName());
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SelectViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }

    public void setData(List<District> p){
        data = p;
        notifyDataSetChanged();
    }

}
