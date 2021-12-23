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
import com.example.sdfd.models.FavoriteModel;
import com.example.sdfd.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class ShowFavoriteActivity extends AppCompatActivity {
    ImageView showpo,showpo2,showpo3;
    ImageView btnfashowpo;
    TextView calopo,descriptionspo,namepo,namepo2,ingredient2,typepo,timepo,typepo2,namepo3,typepo3,instructionpo3;
    TabHost tab;
    Button btnexit,btnexit2,btnexit3;
    FavoriteModel favoriteModel = null;
    Button addmenu;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    boolean isInMyFavorite=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_favorite);
        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

//        showpo=findViewById(R.id.showpo_img1);
//        calopo=findViewById(R.id.showpo_calo1);
//        namepo=findViewById(R.id.name_poshow1);
//        descriptionspo=findViewById(R.id.descriptionpo_show1);
//        btnexit=findViewById(R.id.btn_exit1);
//        typepo=findViewById(R.id.showpo_type11);
//        timepo=findViewById(R.id.showpo_time1);
////        btnfashowpo=findViewById(R.id.fashow_po1);
//        addmenu=findViewById(R.id.add_to_menu11);
//
//        ingredient2=findViewById(R.id.ingredient_show21);
//        showpo2=findViewById(R.id.showpo_img21);
//        namepo2=findViewById(R.id.name_poshow21);
//        btnexit2=findViewById(R.id.btn_exit21);
//        typepo2=findViewById(R.id.showpo_type21);
//
//        showpo3=findViewById(R.id.show_po_img31);
//        namepo3=findViewById(R.id.show_po_name31);
//        btnexit3=findViewById(R.id.btn_exit31);
//        typepo3=findViewById(R.id.show_po_type31);
//        instructionpo3=findViewById(R.id.show_po_instruction31);
//
//        addControl();
//        final Object opject=getIntent().getSerializableExtra("detailshowfa");
//        if (opject instanceof FavoriteModel){
//            favoriteModel =(FavoriteModel) opject;
//        }
//        if(favoriteModel != null){
//            Glide.with(getApplicationContext()).load(favoriteModel.getImg_url()).into(showpo);
//            calopo.setText(String.valueOf(favoriteModel.getCalo()));
//            namepo.setText(favoriteModel.getName());
//            descriptionspo.setText(favoriteModel.getDescription());
//            timepo.setText(favoriteModel.getTime());
//            typepo.setText(favoriteModel.getType());
//
//            Glide.with(getApplicationContext()).load(favoriteModel.getImg_url()).into(showpo2);
//            namepo2.setText(favoriteModel.getName());
//            ingredient2.setText(favoriteModel.getIngredient());
//            typepo2.setText(favoriteModel.getType());
//
//            Glide.with(getApplicationContext()).load(favoriteModel.getImg_url()).into(showpo3);
//            namepo3.setText(favoriteModel.getName());
//            instructionpo3.setText(favoriteModel.getInstruction());
//            typepo3.setText(favoriteModel.getType());
//
//        }
//
//        btnexit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        btnexit2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        btnexit3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
////        addmenu.setOnClickListener(new View.OnClickListener() {
////            @Override
//            public void onClick(View v) {
//                addtomenu();
//            }
//        });
//        btnfashowpo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isInMyFavorite){
//                    removefavorite(ShowFavoriteActivity.this);
//                }else {
//                    addfavorite(ShowFavoriteActivity.this);
//                }
//            }
//        });
//        checkIsFavorite(ShowFavoriteActivity.this);
////        countFavorite(ShowPopularActivity.this);
    }

//    private void addtomenu() {
//        HashMap<String,Object> menumap= new HashMap<>();
//        menumap.put("name",favoriteModel.getName());
//        menumap.put("calo",favoriteModel.getCalo());
//        menumap.put("img_url",favoriteModel.getImg_url());
//        menumap.put("description",favoriteModel.getDescription());
//
//        firestore.collection("AddToMenu").document(firebaseAuth.getCurrentUser().getUid())
//                .collection("CurrentUser").add(menumap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentReference> task) {
//                Toast.makeText(ShowFavoriteActivity.this, "Added To A Menu", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
//    }
//
//    private void checkIsFavorite(Context context){
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
//                .collection("CurrentUser");
//        collectionReference.document(favoriteModel.getIdfa()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                isInMyFavorite=value.exists();
//                if (isInMyFavorite){
//                    btnfashowpo.setImageResource(R.drawable.ic_baseline_favorite_24);
//                }
//                else {
//                    btnfashowpo.setImageResource(R.drawable.ic_menu_favorite);
//                }
//            }
//        });
//    }
//
//    private void addfavorite(Context context){
//
//        HashMap<String,Object> famap= new HashMap<>();
//        famap.put("name",favoriteModel.getName());
//        famap.put("calo",favoriteModel.getCalo());
//        famap.put("img_url",favoriteModel.getImg_url());
//        famap.put("description",favoriteModel.getDescription());
//        famap.put("id",favoriteModel.getIdfa());
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
//                .collection("CurrentUser");
//
//        collectionReference.document(favoriteModel.getIdfa()).set(famap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(context, "Added to your favorites list...", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(context, "Failed to add to favorites list due to"+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
//    private void removefavorite(Context context){
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
//                .collection("CurrentUser");
//
//        collectionReference.document(favoriteModel.getIdfa()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(context, "Remove from your favorites list...", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(context, "Failed to remove from favorites list due to"+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
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