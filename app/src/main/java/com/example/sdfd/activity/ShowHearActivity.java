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
import com.example.sdfd.models.HearModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class ShowHearActivity extends AppCompatActivity {
    ImageView showhear;
    TextView calo,descriptions,name;
    TabHost tab;
    Button btnexit,btnmenu;
    ImageView btnfa;
    HearModel hearModel=null;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    boolean isInMyFavorite=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hear);
        showhear=findViewById(R.id.showhear_img);
        calo=findViewById(R.id.showhear_calo);
        descriptions=findViewById(R.id.descriptionhear_show);
        name=findViewById(R.id.name_hearshow);
        btnexit=findViewById(R.id.btn_exit);
        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        btnmenu=findViewById(R.id.add_to_menuhear);
        btnfa=findViewById(R.id.fa_hear);
        addControl();
        final Object opject=getIntent().getSerializableExtra("detail4");
        if (opject instanceof HearModel){
            hearModel =(HearModel) opject;
        }

        Glide.with(getApplicationContext()).load(hearModel.getImg_url()).into(showhear);
        calo.setText(String.valueOf(hearModel.getCalo()));
        name.setText(hearModel.getName());
        descriptions.setText(hearModel.getDescription());
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
                    removefavorite(ShowHearActivity.this);
                }else {
                    addfavorite(ShowHearActivity.this);
                }
            }
        });
        checkIsFavorite(ShowHearActivity.this);
//        countFavorite(ShowPopularActivity.this);
    }

    private void addtomenu() {
        HashMap<String,Object> menumap= new HashMap<>();
        menumap.put("name",hearModel.getName());
        menumap.put("calo",hearModel.getCalo());
        menumap.put("img_url",hearModel.getImg_url());
        menumap.put("description",hearModel.getDescription());

        firestore.collection("AddToMenu").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser").add(menumap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ShowHearActivity.this, "Added To A Menu", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void checkIsFavorite(Context context){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");
        collectionReference.document(hearModel.getHearid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
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
        famap.put("name",hearModel.getName());
        famap.put("calo",hearModel.getCalo());
        famap.put("img_url",hearModel.getImg_url());
        famap.put("description",hearModel.getDescription());
        famap.put("id",hearModel.getHearid());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");

        collectionReference.document(hearModel.getHearid()).set(famap).addOnSuccessListener(new OnSuccessListener<Void>() {
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

        collectionReference.document(hearModel.getHearid()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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