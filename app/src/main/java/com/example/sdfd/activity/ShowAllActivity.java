package com.example.sdfd.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.R;
import com.example.sdfd.adapter.ViewAllAdapter;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.ViewAllModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowAllActivity extends AppCompatActivity {
    ImageView showall,showall2,showall3;
    TextView calo,descriptions,name,type,time,caloall2,nameall2,typeall2,timeall2,ingredientall2,caloall3,nameall3,typeall3,timeall3,instruction3;
    TabHost tab;
    Button btnexit,btnexit2,btnexit3;
    ViewAllModel viewAllModel = null;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    boolean isInMyFavorite=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        showall=findViewById(R.id.showall_img);
        calo=findViewById(R.id.showall_calo);
        type=findViewById(R.id.showall_type);
        time=findViewById(R.id.showall_time);
        descriptions=findViewById(R.id.descriptionall_show);
        name=findViewById(R.id.name_allshow);
        btnexit=findViewById(R.id.btn_exit);


        ingredientall2=findViewById(R.id.ingredient_showall2);
        showall2=findViewById(R.id.show_all_po_img2);
        nameall2=findViewById(R.id.show_all_po_name2);

        btnexit2=findViewById(R.id.btn_exit2);
        typeall2=findViewById(R.id.show_all_po_type2);


        instruction3=findViewById(R.id.instruction_showall3);
        showall3=findViewById(R.id.show_all_po_img3);
        nameall3=findViewById(R.id.show_all_po_name3);

        btnexit3=findViewById(R.id.btn_exit3);
        typeall3=findViewById(R.id.show_all_po_type3);




        addControl();


        final Object object=getIntent().getSerializableExtra("showall");
        if (object instanceof ViewAllModel){
            viewAllModel =(ViewAllModel) object;
        }

        Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(showall);
        calo.setText(String.valueOf(viewAllModel.getCalo()));
        name.setText(viewAllModel.getName());
        type.setText(viewAllModel.getType());
        time.setText(viewAllModel.getTime());
        descriptions.setText(viewAllModel.getDescription());

        Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(showall2);

        nameall2.setText(viewAllModel.getName());
        ingredientall2.setText(viewAllModel.getIngredient());
        typeall2.setText(viewAllModel.getType());

        Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(showall3);

        nameall3.setText(viewAllModel.getName());
        instruction3.setText(viewAllModel.getInstruction());

        typeall3.setText(viewAllModel.getType());


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
