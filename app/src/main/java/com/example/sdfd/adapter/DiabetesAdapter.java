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
import com.example.sdfd.activity.ShowDiabetesActivity;
import com.example.sdfd.models.DiabetesModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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
    public void onBindViewHolder(@NonNull DiabetesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(diabetesModelList.get(position).getImg_url()).into(holder.diaImg);
        holder.name.setText(diabetesModelList.get(position).getName());
        holder.description.setText(diabetesModelList.get(position).getDescription());
        holder.calo.setText(String.valueOf(diabetesModelList.get(position).getCalo()));
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DiabetesModel diabetesModel=diabetesModelList.get(position);
        final CollectionReference collection = firestore.collection("Favorite").document(diabetesModel.getIddia())
                .collection("CurrentUser");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ShowDiabetesActivity.class);
                intent.putExtra("detail",diabetesModelList.get(position));
                context.startActivity(intent);

                //date,time
                String saveCurentDate,saveCurentTime;
                Calendar calendar=Calendar.getInstance();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM,dd,yyyy");
                saveCurentDate=simpleDateFormat.format(calendar.getTime());

                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss a");
                saveCurentTime=simpleTimeFormat.format(calendar.getTime());

                final HashMap<String,Object> hismap=new HashMap<>();

                hismap.put("name",diabetesModelList.get(position).getName());
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
        return diabetesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView diaImg;
        TextView name,description,calo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diaImg=itemView.findViewById(R.id.dia_img);
            name=itemView.findViewById(R.id.dia_name);
            description=itemView.findViewById(R.id.dia_des);
            calo=itemView.findViewById(R.id.dia_calo);

        }
    }
}

