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
import com.example.sdfd.ShowHearActivity;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.HearModel;

import java.util.List;

public class HearAdapter extends RecyclerView.Adapter<HearAdapter.ViewHolder>{
    private Context context;
    private List<HearModel> hearModelList ;

    public HearAdapter(Context context, List<HearModel> hearModelList) {
        this.context = context;
        this.hearModelList = hearModelList;
    }

    @NonNull
    @Override
    public HearAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hear_dish,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HearAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(hearModelList.get(position).getImg_url()).into(holder.hearImg);
        holder.hname.setText(hearModelList.get(position).getName());
        holder.hdescription.setText(hearModelList.get(position).getDescription());
        holder.hcalo.setText(hearModelList.get(position).getCalo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowHearActivity.class);
                intent.putExtra("detail4",hearModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hearModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hearImg;
        TextView hname,hdescription,hcalo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hearImg=itemView.findViewById(R.id.hear_img);
            hname=itemView.findViewById(R.id.hear_name);
            hdescription=itemView.findViewById(R.id.hear_des);
            hcalo=itemView.findViewById(R.id.hear_calo);

        }
    }
}
