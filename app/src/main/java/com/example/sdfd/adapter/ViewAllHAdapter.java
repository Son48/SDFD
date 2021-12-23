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
import com.example.sdfd.models.ViewAllHModel;

import java.util.List;

public class ViewAllHAdapter extends RecyclerView.Adapter<ViewAllHAdapter.ViewHolder> {
    private Context context;
    private List<ViewAllHModel> viewAllHModelList;

    public ViewAllHAdapter(Context context, List<ViewAllHModel> viewAllHModelList) {
        this.context = context;
        this.viewAllHModelList = viewAllHModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_allh_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(viewAllHModelList.get(position).getImg_url()).into(holder.viewImg);
        holder.viewname.setText(viewAllHModelList.get(position).getName());
        holder.viewdescription.setText(viewAllHModelList.get(position).getDescription());
        holder.viewcalo.setText(String.valueOf(viewAllHModelList.get(position).getCalo()));
        holder.viewtime.setText(viewAllHModelList.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, ShowAllActivity.class);
                intent.putExtra("showallh",viewAllHModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllHModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView viewImg;
        TextView viewname,viewdescription,viewcalo,viewtime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImg=itemView.findViewById(R.id.viewh_img);
            viewname=itemView.findViewById(R.id.nameh_viewitem);
            viewdescription=itemView.findViewById(R.id.desh_viewitem);
            viewcalo=itemView.findViewById(R.id.caloh_viewitem);
            viewtime=itemView.findViewById(R.id.timeh_viewitem);
        }
    }
}

