package com.example.sdfd.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sdfd.R;
import com.example.sdfd.models.DiabetesModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class ShowDiabetesActivity extends AppCompatActivity {

    ImageView showdes;
    TextView calo,descriptions,name;
    TabHost tab;
    Button btnexit,btnmenu;
    ImageView btnfa;
    DiabetesModel diabetesModel = null;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    boolean isInMyFavorite=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diabetes);
        showdes=findViewById(R.id.showdiabetes_img);
        calo=findViewById(R.id.showdiabetes_calo);
        descriptions=findViewById(R.id.description_show);
        name=findViewById(R.id.name_desshow);
        btnexit=findViewById(R.id.btn_exit);
        btnfa=findViewById(R.id.btn_fadia);
        btnmenu=findViewById(R.id.add_to_menudia);
        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        addControl();
        final Object opject=getIntent().getSerializableExtra("detail");
        if (opject instanceof DiabetesModel){
            diabetesModel =(DiabetesModel) opject;
        }

            Glide.with(getApplicationContext()).load(diabetesModel.getImg_url()).into(showdes);
            calo.setText(String.valueOf(diabetesModel.getCalo()));
            name.setText(diabetesModel.getName());
            descriptions.setText(diabetesModel.getDescription());
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtomenu();
            }
        });
        btnfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInMyFavorite){
                    removefavorite(ShowDiabetesActivity.this);
                }else {
                    addfavorite(ShowDiabetesActivity.this);
                }
            }
        });
        checkIsFavorite(ShowDiabetesActivity.this);
//        countFavorite(ShowPopularActivity.this);
    }

    private void addtomenu() {
        HashMap<String,Object> menumap= new HashMap<>();
        menumap.put("name",diabetesModel.getName());
        menumap.put("calo",diabetesModel.getCalo());
        menumap.put("img_url",diabetesModel.getImg_url());
        menumap.put("description",diabetesModel.getDescription());

        firestore.collection("AddToMenu").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser").add(menumap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ShowDiabetesActivity.this, "Added To A Menu", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void checkIsFavorite(Context context){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");
        collectionReference.document(diabetesModel.getIddia()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                isInMyFavorite=value.exists();
                if (isInMyFavorite){
                    btnfa.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
                else {
                    btnfa.setImageResource(R.drawable.ic_menu_favorite);
                }
            }
        });
    }

    private void addfavorite(Context context){

        HashMap<String,Object> famap= new HashMap<>();
        famap.put("name",diabetesModel.getName());
        famap.put("calo",diabetesModel.getCalo());
        famap.put("img_url",diabetesModel.getImg_url());
        famap.put("description",diabetesModel.getDescription());
        famap.put("id",diabetesModel.getIddia());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");

        collectionReference.document(diabetesModel.getIddia()).set(famap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Added to your favorites list...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failed to add to favorites list due to"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void removefavorite(Context context){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");

        collectionReference.document(diabetesModel.getIddia()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Remove from your favorites list...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failed to remove from favorites list due to"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addControl() {
        tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("OVERVIEW");
        tab.addTab(tab1);
        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("INGREDIENT");
        tab.addTab(tab2);
        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("INSTRUCTION");
        tab.addTab(tab3);
    }
}