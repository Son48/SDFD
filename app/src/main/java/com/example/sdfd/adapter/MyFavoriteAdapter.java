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
import com.example.sdfd.activity.ShowPopularActivity;
import com.example.sdfd.models.FavoriteModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.ViewHolder> {
    Context context;
    List<FavoriteModel> favoriteModels;

    public MyFavoriteAdapter(Context context, List<FavoriteModel> favoriteModels) {
        this.context = context;
        this.favoriteModels = favoriteModels;
    }

    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_favorite_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(favoriteModels.get(position).getImg_url()).into(holder.hearImg);
        holder.name.setText(favoriteModels.get(position).getName());
        holder.calo.setText(String.valueOf(favoriteModels.get(position).getCalo()));

        firestore= FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                        .collection("CurrentUser");

              collectionReference.document(favoriteModels.get(position).getIdfa()).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    favoriteModels.remove(favoriteModels.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Remove Favorite", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //share po
                Intent intent=new Intent(context, ShowPopularActivity.class);
                intent.putExtra("detailshowfa",favoriteModels.get(position));
                context.startActivity(intent);

                //date,time
                String saveCurentDate,saveCurentTime;
                Calendar calendar=Calendar.getInstance();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM,dd,yyyy");
                saveCurentDate=simpleDateFormat.format(calendar.getTime());

                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss a");
                saveCurentTime=simpleTimeFormat.format(calendar.getTime());

                final HashMap<String,Object> hismap=new HashMap<>();

                hismap.put("name",favoriteModels.get(position).getName());
                hismap.put("description",favoriteModels.get(position).getDescription());
                hismap.put("calo",favoriteModels.get(position).getCalo());
                hismap.put("img_url",favoriteModels.get(position).getImg_url());
                hismap.put("curentDate",saveCurentDate);
                hismap.put("curentTime",saveCurentTime);
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                firestore.collection("History").document(firebaseAuth.getCurrentUser().getUid())
                        .collection("CurentUser").add(hismap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return favoriteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hearImg,btndelete;
        TextView name,calo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hearImg=itemView.findViewById(R.id.fa_nav_view);
            name=itemView.findViewById(R.id.fa_nav_name);
            calo=itemView.findViewById(R.id.fa_nav_calo);
            btndelete=itemView.findViewById(R.id.delete_fa);
        }
    }
}
