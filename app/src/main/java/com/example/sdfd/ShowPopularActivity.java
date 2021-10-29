package com.example.sdfd;

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

public class ShowPopularActivity extends AppCompatActivity {
    ImageView showpo;
    TextView calopo,descriptionspo,namepo;
    TabHost tab;
    Button btnexit;
    PopularModel popularModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_popular);
        showpo=findViewById(R.id.showpo_img);
        calopo=findViewById(R.id.showpo_calo);
        descriptionspo=findViewById(R.id.descriptionpo_show);
        btnexit=findViewById(R.id.btn_exit);
        namepo=findViewById(R.id.name_poshow);
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
        }
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