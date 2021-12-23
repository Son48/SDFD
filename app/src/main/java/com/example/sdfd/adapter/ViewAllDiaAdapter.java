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
import com.example.sdfd.activity.ShowAllActivity;
import com.example.sdfd.models.ViewAllDiaModel;

import java.util.List;

public class ViewAllDiaAdapter extends RecyclerView.Adapter<ViewAllDiaAdapter.ViewHolder> {
    private Context context;
    private List<ViewAllDiaModel> viewAllDiaModelList;

    public ViewAllDiaAdapter(Context context, List<ViewAllDiaModel> viewAllDiaModelList) {
        this.context = context;
        this.viewAllDiaModelList = viewAllDiaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_alldia_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(viewAllDiaModelList.get(position).getImg_url()).into(holder.viewImg);
        holder.viewname.setText(viewAllDiaModelList.get(position).getName());
        holder.viewdescription.setText(viewAllDiaModelList.get(position).getDescription());
        holder.viewcalo.setText(String.valueOf(viewAllDiaModelList.get(position).getCalo()));
        holder.viewtime.setText(viewAllDiaModelList.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, ShowAllActivity.class);
                intent.putExtra("showalldia",viewAllDiaModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllDiaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView viewImg;
        TextView viewname,viewdescription,viewcalo,viewtime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImg=itemView.findViewById(R.id.viewdia_img);
            viewname=itemView.findViewById(R.id.namedia_viewitem);
            viewdescription=itemView.findViewById(R.id.desdia_viewitem);
            viewcalo=itemView.findViewById(R.id.calodia_viewitem);
            viewtime=itemView.findViewById(R.id.timedia_viewitem);
        }
    }
}

