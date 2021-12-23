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
import com.example.sdfd.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {
    private Context context;
    private List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.viewImg);
        holder.viewname.setText(viewAllModelList.get(position).getName());
        holder.viewdescription.setText(viewAllModelList.get(position).getDescription());
        holder.viewcalo.setText(String.valueOf(viewAllModelList.get(position).getCalo()));
        holder.viewtime.setText(viewAllModelList.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, ShowAllActivity.class);
                intent.putExtra("showall",viewAllModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView viewImg;
        TextView viewname,viewdescription,viewcalo,viewtime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImg=itemView.findViewById(R.id.view_img);
            viewname=itemView.findViewById(R.id.name_viewitem);
            viewdescription=itemView.findViewById(R.id.des_viewitem);
            viewcalo=itemView.findViewById(R.id.calo_viewitem);
            viewtime=itemView.findViewById(R.id.time_viewitem);
        }
    }
}
