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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sdfd.R;
import com.example.sdfd.ShowPopularActivity;
import com.example.sdfd.ShowWeightlossActivity;
import com.example.sdfd.models.PopularModel;
import com.example.sdfd.models.WeightlossModel;

import java.util.List;

public class WeightlossAdapter extends RecyclerView.Adapter<WeightlossAdapter.ViewHolder>{

    private Context context;
    private List<WeightlossModel> weightlossModelList;

    public WeightlossAdapter(Context context, List<WeightlossModel> weightlossModelList) {
        this.context = context;
        this.weightlossModelList = weightlossModelList;
    }

    @NonNull
    @Override
    public WeightlossAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weightloss_dish,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeightlossAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(weightlossModelList.get(position).getImg_url()).into(holder.wlImg);
        holder.wlname.setText(weightlossModelList.get(position).getName());
        holder.wldescription.setText(weightlossModelList.get(position).getDescription());
        holder.wlcalo.setText(weightlossModelList.get(position).getCalo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowWeightlossActivity.class);
                intent.putExtra("detail3",weightlossModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weightlossModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView wlImg;
        TextView wlname,wldescription,wlcalo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wlImg=itemView.findViewById(R.id.wl_img);
            wlname=itemView.findViewById(R.id.wl_name);
            wldescription=itemView.findViewById(R.id.wl_des);
            wlcalo=itemView.findViewById(R.id.wl_calo);
        }
    }
}
