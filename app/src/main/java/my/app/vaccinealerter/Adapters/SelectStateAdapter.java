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

import my.app.vaccinealerter.Activities.SelectDistrictActivity;
import my.app.vaccinealerter.Models.State;
import my.app.vaccinealerter.R;

import java.util.List;

public class SelectStateAdapter extends RecyclerView.Adapter<SelectStateAdapter.SelectViewHolder>{

    List<State> data;
    Context context;
    String seltype;

    public SelectStateAdapter(Context context, List<State> data, String code){
        this.context = context;
        this.data = data;
        this.seltype = code;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
        return new SelectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {
        holder.name.setText(data.get(position).getStateName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectDistrictActivity.class);
                intent.putExtra("seltype", seltype);
                intent.putExtra("statecode", data.get(position).getStateId());
                intent.putExtra("state", data.get(position).getStateName());
                context.startActivity(intent);
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

    public void setData(List<State> p){
        data = p;
        notifyDataSetChanged();
    }

}
