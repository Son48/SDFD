package com.example.sdfd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sdfd.R;
import com.example.sdfd.adapter.ViewAllAdapter;
import com.example.sdfd.adapter.ViewAllWLAdapter;
import com.example.sdfd.models.ViewAllModel;
import com.example.sdfd.models.ViewAllWLModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllWeightLossActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewAllWLAdapter viewAllWLAdapter;
    List<ViewAllWLModel> viewAllWLModelList;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_weight_loss);
        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.view_allwl_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        viewAllWLModelList = new ArrayList<>();
        viewAllWLAdapter = new ViewAllWLAdapter(this, viewAllWLModelList);
        recyclerView.setAdapter(viewAllWLAdapter);

        firestore.collection("ViewAllWeightLoss").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        ViewAllWLModel viewAllWLModel = doc.toObject(ViewAllWLModel.class);
                        viewAllWLModelList.add(viewAllWLModel);
                        viewAllWLAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

}