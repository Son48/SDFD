package com.example.sdfd.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sdfd.R;
import com.example.sdfd.ShowDiabetesActivity;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.HomeCategory;

import java.util.List;

public class DiabetesAdapter extends RecyclerView.Adapter<DiabetesAdapter.ViewHolder> {
    private Context context;
    private List<DiabetesModel> diabetesModelList;

    public DiabetesAdapter(Context context, List<DiabetesModel> diabetesModelList) {
        this.context = context;
        this.diabetesModelList = diabetesModelList;
    }

    @NonNull
    @Override
    public DiabetesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.diabete_dish,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiabetesAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(diabetesModelList.get(position).getImg_url()).into(holder.diaImg);
        holder.name.setText(diabetesModelList.get(position).getName());
        holder.description.setText(diabetesModelList.get(position).getDescription());
        holder.calo.setText(diabetesModelList.get(position).getCalo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowDiabetesActivity.class);
                intent.putExtra("detail",diabetesModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diabetesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView diaImg;
        TextView name,description,calo;
        CardView btnclickviewde;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diaImg=itemView.findViewById(R.id.dia_img);
            name=itemView.findViewById(R.id.dia_name);
            description=itemView.findViewById(R.id.dia_des);
            calo=itemView.findViewById(R.id.dia_calo);

        }
    }
}
