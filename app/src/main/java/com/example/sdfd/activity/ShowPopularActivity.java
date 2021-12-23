package com.example.sdfd.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class ShowPopularActivity extends AppCompatActivity {
    ImageView showpo,showpo2,showpo3;
    ImageView btnfashowpo;
    TextView calopo,descriptionspo,namepo,namepo2,ingredient2,typepo,timepo,typepo2,namepo3,typepo3,instructionpo3;
    TabHost tab;
    Button btnexit,btnexit2,btnexit3;
    PopularModel popularModel = null;
    Button addmenu;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    boolean isInMyFavorite=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_popular);

        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        showpo=findViewById(R.id.showpo_img);
        calopo=findViewById(R.id.showpo_calo);
        namepo=findViewById(R.id.name_poshow);
        descriptionspo=findViewById(R.id.descriptionpo_show);
        btnexit=findViewById(R.id.btn_exit);
        typepo=findViewById(R.id.showpo_type);
        timepo=findViewById(R.id.showpo_time);
        btnfashowpo=findViewById(R.id.fashow_po);
        addmenu=findViewById(R.id.add_to_menu1);

        ingredient2=findViewById(R.id.ingredient_show2);
        showpo2=findViewById(R.id.showpo_img2);
        namepo2=findViewById(R.id.name_poshow2);
        btnexit2=findViewById(R.id.btn_exit2);
        typepo2=findViewById(R.id.showpo_type2);

        showpo3=findViewById(R.id.show_po_img3);
        namepo3=findViewById(R.id.show_po_name3);
        btnexit3=findViewById(R.id.btn_exit3);
        typepo3=findViewById(R.id.show_po_type3);
        instructionpo3=findViewById(R.id.show_po_instruction3);

        addControl();
        final Object opject=getIntent().getSerializableExtra("detail2");
        if (opject instanceof PopularModel){
            popularModel =(PopularModel) opject;
        }
        if(popularModel != null){
            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(showpo);
            calopo.setText(String.valueOf(popularModel.getCalo()));
            namepo.setText(popularModel.getName());
            descriptionspo.setText(popularModel.getDescription());
            timepo.setText(popularModel.getTime());
            typepo.setText(popularModel.getType());

            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(showpo2);
            namepo2.setText(popularModel.getName());
            ingredient2.setText(popularModel.getIngredient());
            typepo2.setText(popularModel.getType());

            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(showpo3);
            namepo3.setText(popularModel.getName());
            instructionpo3.setText(popularModel.getInstruction());
            typepo3.setText(popularModel.getType());

        }

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnexit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnexit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtomenu();
            }
        });
        btnfashowpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInMyFavorite){
                    removefavorite(ShowPopularActivity.this);
                }else {
                    addfavorite(ShowPopularActivity.this);
                }
            }
        });
        checkIsFavorite(ShowPopularActivity.this);
//        countFavorite(ShowPopularActivity.this);
    }

    private void addtomenu() {
        HashMap<String,Object> menumap= new HashMap<>();
        menumap.put("name",popularModel.getName());
        menumap.put("calo",popularModel.getCalo());
        menumap.put("img_url",popularModel.getImg_url());
        menumap.put("description",popularModel.getDescription());

        firestore.collection("AddToMenu").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser").add(menumap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ShowPopularActivity.this, "Added To A Menu", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void checkIsFavorite(Context context){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");
        collectionReference.document(popularModel.getId()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                isInMyFavorite=value.exists();
                if (isInMyFavorite){
                    btnfashowpo.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
                else {
                    btnfashowpo.setImageResource(R.drawable.ic_menu_favorite);
                }
            }
        });
    }

    private void addfavorite(Context context){

        HashMap<String,Object> famap= new HashMap<>();
        famap.put("name",popularModel.getName());
        famap.put("calo",popularModel.getCalo());
        famap.put("img_url",popularModel.getImg_url());
        famap.put("description",popularModel.getDescription());
        famap.put("id",popularModel.getId());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser");

        collectionReference.document(popularModel.getId()).set(famap).addOnSuccessListener(new OnSuccessListener<Void>() {
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

        collectionReference.document(popularModel.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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