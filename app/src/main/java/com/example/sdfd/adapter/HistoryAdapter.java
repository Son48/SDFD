package com.example.sdfd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sdfd.R;
import com.example.sdfd.models.HistoryModel;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private Context context;
    private List<HistoryModel> historyModels;

    public HistoryAdapter(Context context, List<HistoryModel> historyModels) {
        this.context = context;
        this.historyModels = historyModels;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.namehis.setText(historyModels.get(position).getName());
        holder.datehis.setText(historyModels.get(position).getCurentDate());
        holder.timehis.setText(historyModels.get(position).getCurentTime());
    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namehis,datehis,timehis;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namehis=itemView.findViewById(R.id.his_name);
            datehis=itemView.findViewById(R.id.his_date);
            timehis=itemView.findViewById(R.id.his_time);
        }
    }
}

