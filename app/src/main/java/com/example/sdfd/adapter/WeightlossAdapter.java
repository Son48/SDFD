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
    public void onBindViewHolder(@NonNull WeightlossAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(weightlossModelList.get(position).getImg_url()).into(holder.wlImg);
        holder.wlname.setText(weightlossModelList.get(position).getName());
        holder.wldescription.setText(weightlossModelList.get(position).getDescription());
        holder.wlcalo.setText(weightlossModelList.get(position).getCalo());
        holder.wltime.setText(weightlossModelList.get(position).getTime());

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        WeightlossModel weightlossModel=weightlossModelList.get(position);
        final CollectionReference collection = firestore.collection("Favorite").document(weightlossModel.getId())
                .collection("CurrentUser");
        //status fa
        final EventListener<DocumentSnapshot> checkifLiked = new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (documentSnapshot.exists()){
                    holder.disfa_wl.setImageResource(R.drawable.ic_baseline_favorite_24);

                }else {
                    holder.disfa_wl.setImageResource(R.drawable.ic_menu_favorite);

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
                    holder.coutfawl.setText("");
                }else {
                    holder.coutfawl.setText(likes);
                }
            }
        };
        collection.addSnapshotListener(likeEvent);

        //add fa
        holder.disfa_wl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CollectionReference collection = firestore.collection("Favorite").document(weightlossModel.getId())
                        .collection("CurrentUser");
                collection.document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()&& task.getResult().exists()){
                            collection.document(firebaseAuth.getCurrentUser().getUid()).delete();

                        }
                        else {
                            HashMap<String,Object> famap= new HashMap<>();
                            famap.put("name",weightlossModelList.get(position).getName());
                            famap.put("calo",weightlossModelList.get(position).getCalo());
                            famap.put("img_url",weightlossModelList.get(position).getImg_url());
                            famap.put("description",weightlossModelList.get(position).getDescription());
                            famap.put("type",weightlossModelList.get(position).getType());
                            famap.put("time",weightlossModelList.get(position).getTime());

                            collection.document(firebaseAuth.getCurrentUser().getUid()).set(famap);

                        }

                    }
                });

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //share wl
                Intent intent=new Intent(context, ShowWeightlossActivity.class);
                intent.putExtra("detail3",weightlossModelList.get(position));
                context.startActivity(intent);
                //date,time
                String saveCurentDate,saveCurentTime;
                Calendar calendar=Calendar.getInstance();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM,dd,yyyy");
                saveCurentDate=simpleDateFormat.format(calendar.getTime());

                SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss a");
                saveCurentTime=simpleTimeFormat.format(calendar.getTime());

                final HashMap<String,Object> hismap=new HashMap<>();

                hismap.put("name",weightlossModelList.get(position).getName());
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
        return weightlossModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView wlImg,disfa_wl;
        TextView wlname,wldescription,wlcalo,wltime,coutfawl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wlImg=itemView.findViewById(R.id.wl_img);
            wlname=itemView.findViewById(R.id.wl_name);
            wldescription=itemView.findViewById(R.id.wl_des);
            disfa_wl=itemView.findViewById(R.id.disfavorite_wl);
            wlcalo=itemView.findViewById(R.id.wl_calo);
            coutfawl=itemView.findViewById(R.id.cout_fawl);
            wltime=itemView.findViewById(R.id.wl_time);
        }
    }
}
