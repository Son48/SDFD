package com.example.sdfd.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sdfd.R;
import com.example.sdfd.models.MenuModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyMenuAdapter extends RecyclerView.Adapter<MyMenuAdapter.ViewHolder>{
    Context context;
    List<MenuModel> menuModels;
    int totalcaloused=0;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    public MyMenuAdapter(Context context, List<MenuModel> menuModels) {
        this.context = context;
        this.menuModels = menuModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(menuModels.get(position).getImg_url()).into(holder.hearImg);
        holder.name.setText(menuModels.get(position).getName());
        holder.calo.setText(String.valueOf(menuModels.get(position).getCalo()));

        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        totalcaloused=totalcaloused+menuModels.get(position).getCalo();
        Intent intent=new Intent("MyTotalCaloUsed");
        intent.putExtra("totalCalo",totalcaloused);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("AddToMenu").document(firebaseAuth.getCurrentUser().getUid())
                        .collection("CurrentUser").document(menuModels.get(position).getMenuId()).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    menuModels.remove(menuModels.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Dish Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hearImg,btndelete;
        TextView name,calo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hearImg=itemView.findViewById(R.id.menu_nav_view);
            name=itemView.findViewById(R.id.menu_nav_name);
            calo=itemView.findViewById(R.id.menu_nav_calo);
            btndelete=itemView.findViewById(R.id.delete_menu);
        }
    }
}
