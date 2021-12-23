package com.example.sdfd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sdfd.R;
import com.example.sdfd.adapter.ViewAllAdapter;
import com.example.sdfd.adapter.ViewAllDiaAdapter;
import com.example.sdfd.models.ViewAllDiaModel;
import com.example.sdfd.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllDiabetesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewAllDiaAdapter viewAllDiaAdapter;
    List<ViewAllDiaModel> viewAllDiaModelList;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_diabetes);
        recyclerView = findViewById(R.id.view_alldia_rec);
        firestore=FirebaseFirestore.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        viewAllDiaModelList = new ArrayList<>();
        viewAllDiaAdapter = new ViewAllDiaAdapter(this, viewAllDiaModelList);
        recyclerView.setAdapter(viewAllDiaAdapter);

        firestore.collection("ViewAllDiabetes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        ViewAllDiaModel viewAllDiaModel = doc.toObject(ViewAllDiaModel.class);
                        viewAllDiaModelList.add(viewAllDiaModel);
                        viewAllDiaAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

}