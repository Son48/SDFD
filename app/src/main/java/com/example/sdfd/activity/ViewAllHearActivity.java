package com.example.sdfd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sdfd.R;
import com.example.sdfd.adapter.ViewAllAdapter;
import com.example.sdfd.adapter.ViewAllHAdapter;
import com.example.sdfd.models.ViewAllHModel;
import com.example.sdfd.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllHearActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewAllHAdapter viewAllHAdapter;
    List<ViewAllHModel> viewAllHModelList;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_hear);
        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.view_allh_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        viewAllHModelList = new ArrayList<>();
        viewAllHAdapter = new ViewAllHAdapter(this, viewAllHModelList);
        recyclerView.setAdapter(viewAllHAdapter);

        firestore.collection("Heart-related diseaes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        ViewAllHModel viewAllHModel= doc.toObject(ViewAllHModel.class);
                        viewAllHModelList.add(viewAllHModel);
                        viewAllHAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

}