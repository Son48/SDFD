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
import com.example.sdfd.adapter.HistoryAdapter;
import com.example.sdfd.models.HistoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    RecyclerView hisRec;
    FirebaseFirestore db;
    FirebaseAuth auth;
    HistoryAdapter historyAdapter;
    List<HistoryModel> historyModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_history,container,false);
        db= FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        hisRec=root.findViewById(R.id.his_rec);

        hisRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        historyModelList =new ArrayList<>();
        historyAdapter=new HistoryAdapter(getActivity(),historyModelList);
        hisRec.setAdapter(historyAdapter);

        db.collection("History").document(auth.getCurrentUser().getUid())
                .collection("CurentUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HistoryModel historyModel=document.toObject(HistoryModel.class);
                                historyModelList.add(historyModel);
                                historyAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return root;
    }
}