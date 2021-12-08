package com.example.sdfd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class ShowPopularActivity extends AppCompatActivity {
    ImageView showpo,showpo2,showpo3;
    ImageView btnfashowpo;
    TextView coutfashow;
    TextView calopo,descriptionspo,namepo,calopo2,namepo2,ingredient2,typepo,timepo,typepo2,timepo2,calopo3,namepo3,typepo3,timepo3,instructionpo3;
    TabHost tab;
    Button btnexit,btnexit2,btnexit3;
    PopularModel popularModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_popular);

        showpo=findViewById(R.id.showpo_img);
        calopo=findViewById(R.id.showpo_calo);
        namepo=findViewById(R.id.name_poshow);
        descriptionspo=findViewById(R.id.descriptionpo_show);
        btnexit=findViewById(R.id.btn_exit);
        typepo=findViewById(R.id.showpo_type);
        timepo=findViewById(R.id.showpo_time);
        btnfashowpo=findViewById(R.id.fashow_po);
        coutfashow=findViewById(R.id.coutfashow);

        ingredient2=findViewById(R.id.ingredient_show2);
        showpo2=findViewById(R.id.showpo_img2);
        namepo2=findViewById(R.id.name_poshow2);
        calopo2=findViewById(R.id.showpo_calo2);
        btnexit2=findViewById(R.id.btn_exit2);
        typepo2=findViewById(R.id.showpo_type2);
        timepo2=findViewById(R.id.showpo_time2);

        showpo3=findViewById(R.id.show_po_img3);
        namepo3=findViewById(R.id.show_po_name3);
        calopo3=findViewById(R.id.show_po_calo3);
        btnexit3=findViewById(R.id.btn_exit3);
        typepo3=findViewById(R.id.show_po_type3);
        timepo3=findViewById(R.id.show_po_time3);
        instructionpo3=findViewById(R.id.show_po_instruction3);

        addControl();
        final Object opject=getIntent().getSerializableExtra("detail2");
        if (opject instanceof PopularModel){
            popularModel =(PopularModel) opject;
        }
        if(popularModel != null){
            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(showpo);
            calopo.setText(popularModel.getCalo());
            namepo.setText(popularModel.getName());
            descriptionspo.setText(popularModel.getDescription());
            timepo.setText(popularModel.getTime());
            typepo.setText(popularModel.getType());

            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(showpo2);
            calopo2.setText(popularModel.getCalo());
            namepo2.setText(popularModel.getName());
            ingredient2.setText(popularModel.getIngredient());
            timepo2.setText(popularModel.getTime());
            typepo2.setText(popularModel.getType());

            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(showpo3);
            calopo3.setText(popularModel.getCalo());
            namepo3.setText(popularModel.getName());
            instructionpo3.setText(popularModel.getInstruction());
            timepo3.setText(popularModel.getTime());
            typepo3.setText(popularModel.getType());


            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            final CollectionReference collection = firestore.collection("Favorite").document(firebaseAuth.getCurrentUser().getUid())
                    .collection("CurrentUser");

            final EventListener<DocumentSnapshot> checkifLiked = new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                    if (documentSnapshot.exists()){
                        btnfashowpo.setImageResource(R.drawable.ic_baseline_favorite_24);
                    }else {
                        btnfashowpo.setImageResource(R.drawable.ic_menu_favorite);
                    }

                }
            };

            final EventListener<QuerySnapshot> likeEvent = new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    String likes = String.valueOf(documentSnapshots.getDocuments().size());
                    if (likes.equals("0")){
                        coutfashow.setText("");
                    }else {
                        coutfashow.setText(likes);
                    }
                }
            };

            collection.addSnapshotListener(likeEvent);

            collection.document(firebaseAuth.getCurrentUser().getUid()).addSnapshotListener(checkifLiked);

            btnfashowpo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    collection.document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()&& task.getResult().exists()){
                                collection.document(popularModel.getId());

                            }
                            else {
                                HashMap<String,Object> famap= new HashMap<>();
                                famap.put("name",popularModel.getName());
                                famap.put("calo",popularModel.getCalo());
                                famap.put("img_url",popularModel.getImg_url());
                                famap.put("description",popularModel.getDescription());

                                collection.document(popularModel.getId()).set(famap);

                            }

                        }
                    });

                }
            });

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