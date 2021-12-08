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
import com.example.sdfd.ShowDiabetesActivity;
import com.example.sdfd.ShowPopularActivity;
import com.example.sdfd.ViewAllActivity;
import com.example.sdfd.models.PopularModel;
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

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;
    TextView btnviewall;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_dish,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModelList.get(position).getName());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.calo.setText(popularModelList.get(position).getCalo());
        holder.timepo.setText(popularModelList.get(position).getTime());

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        PopularModel popularModel=popularModelList.get(position);
        final CollectionReference collection = firestore.collection("Favorite").document(popularModel.getId())
                .collection("CurrentUser");

        //status fa
        final EventListener<DocumentSnapshot> checkifLiked = new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (documentSnapshot.exists()){
                    holder.disfa_po.setImageResource(R.drawable.ic_baseline_favorite_24);

                }else {
                    holder.disfa_po.setImageResource(R.drawable.ic_menu_favorite);

                }

            }
        };
        collection.document(firebaseAuth.getCurrentUser().getUid()).addSnapshotListener(checkifLiked);

        //cout fa
        final EventListener<QuerySnapshot> likeEvent = new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                String likes = String.valueOf(documentSnapshots.getDocuments().size());
                if (likes.equals("0")){
                    holder.coutfapo.setText("");
                }else {
                    holder.coutfapo.setText(likes);
                }
            }
        };
        collection.addSnapshotListener(likeEvent);


        //add fa
        holder.disfa_po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CollectionReference collection = firestore.collection("Favorite").document(popularModel.getId())
                        .collection("CurrentUser");
                collection.document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()&& task.getResult().exists()){
                            collection.document(firebaseAuth.getCurrentUser().getUid()).delete();

                        }
                        else {
                            HashMap<String,Object> famap= new HashMap<>();
                            famap.put("name",popularModelList.get(position).getName());
                            famap.put("calo",popularModelList.get(position).getCalo());
                            famap.put("img_url",popularModelList.get(position).getImg_url());
                            famap.put("description",popularModelList.get(position).getDescription());
                            famap.put("type",popularModelList.get(position).getType());
                            famap.put("time",popularModelList.get(position).getTime());

                            collection.document(firebaseAuth.getCurrentUser().getUid()).set(famap);

                        }

                    }
                });

            }
        });

        //history and show dish
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //share po
                Intent intent=new Intent(context, ShowPopularActivity.class);
                intent.putExtra("detail2",popularModelList.get(position));
                context.startActivity(intent);

                //date,time
                String saveCurentDate,saveCurentTime;
                Calendar calendar=Calendar.getInstance();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM,dd,yyyy");
                saveCurentDate=simpleDateFormat.format(calendar.getTime());

                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss a");
                saveCurentTime=simpleTimeFormat.format(calendar.getTime());

                final HashMap<String,Object> hismap=new HashMap<>();

                hismap.put("name",popularModelList.get(position).getName());
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
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView popImg,disfa_po;
        TextView name,description,calo,coutfapo,timepo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg=itemView.findViewById(R.id.popular_img);
            name=itemView.findViewById(R.id.pop_name);
            description=itemView.findViewById(R.id.pop_des);
            calo=itemView.findViewById(R.id.pop_calo);
            disfa_po=itemView.findViewById(R.id.disfavorite_po);
            coutfapo=itemView.findViewById(R.id.cout_fapo);
            timepo=itemView.findViewById(R.id.pop_time);
        }
    }

}
