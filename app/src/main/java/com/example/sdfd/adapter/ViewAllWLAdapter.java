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
import com.example.sdfd.models.ViewAllWLModel;

import java.util.List;

public class ViewAllWLAdapter extends RecyclerView.Adapter<ViewAllWLAdapter.ViewHolder> {
    private Context context;
    private List<ViewAllWLModel> viewAllWLModelList;

    public ViewAllWLAdapter(Context context, List<ViewAllWLModel> viewAllWLModelList) {
        this.context = context;
        this.viewAllWLModelList = viewAllWLModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_allwl_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(viewAllWLModelList.get(position).getImg_url()).into(holder.viewImg);
        holder.viewname.setText(viewAllWLModelList.get(position).getName());
        holder.viewdescription.setText(viewAllWLModelList.get(position).getDescription());
        holder.viewcalo.setText(String.valueOf(viewAllWLModelList.get(position).getCalo()));
        holder.viewtime.setText(viewAllWLModelList.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, ShowAllActivity.class);
                intent.putExtra("showallwl",viewAllWLModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllWLModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView viewImg;
        TextView viewname,viewdescription,viewcalo,viewtime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImg=itemView.findViewById(R.id.viewwl_img);
            viewname=itemView.findViewById(R.id.namewl_viewitem);
            viewdescription=itemView.findViewById(R.id.deswl_viewitem);
            viewcalo=itemView.findViewById(R.id.calowl_viewitem);
            viewtime=itemView.findViewById(R.id.timewl_viewitem);
        }
    }
}
