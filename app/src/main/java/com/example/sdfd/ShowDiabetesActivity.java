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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowDiabetesActivity extends AppCompatActivity {

    ImageView showdes;
    TextView calo,descriptions,name;
    TabHost tab;
    Button btnexit;
    DiabetesModel diabetesModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diabetes);
        showdes=findViewById(R.id.showdiabetes_img);
        calo=findViewById(R.id.showdiabetes_calo);
        descriptions=findViewById(R.id.description_show);
        name=findViewById(R.id.name_desshow);
        btnexit=findViewById(R.id.btn_exit);
        addControl();
        final Object opject=getIntent().getSerializableExtra("detail");
        if (opject instanceof DiabetesModel){
            diabetesModel =(DiabetesModel) opject;
        }

            Glide.with(getApplicationContext()).load(diabetesModel.getImg_url()).into(showdes);
            calo.setText(diabetesModel.getCalo());
            name.setText(diabetesModel.getName());
            descriptions.setText(diabetesModel.getDescription());
        btnexit.setOnClickListener(new View.OnClickListener() {
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
        tab2.setIndicator("INGREDIENTS");
        tab.addTab(tab2);
        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("INSTRUCTIONS");
        tab.addTab(tab3);
    }
}