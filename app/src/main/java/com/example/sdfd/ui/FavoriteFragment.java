package com.example.sdfd.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sdfd.R;
import com.example.sdfd.adapter.MyFavoriteAdapter;
import com.example.sdfd.adapter.MyMenuAdapter;
import com.example.sdfd.models.FavoriteModel;
import com.example.sdfd.models.MenuModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    MyFavoriteAdapter myFavoriteAdapter;
    List<FavoriteModel> favoriteModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_favorite, container, false);
        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.fa_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoriteModels=new ArrayList<>();
        myFavoriteAdapter=new MyFavoriteAdapter(getActivity(),favoriteModels);

        recyclerView.setAdapter(myFavoriteAdapter);

        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String faid= document.getId();
                        FavoriteModel favoriteModel=document.toObject(FavoriteModel.class);
                        favoriteModel.setIdfa(faid);
                        favoriteModels.add(favoriteModel);
                        myFavoriteAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
}