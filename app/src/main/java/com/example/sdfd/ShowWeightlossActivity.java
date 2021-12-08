package com.example.sdfd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.models.PopularModel;
import com.example.sdfd.models.WeightlossModel;

public class ShowWeightlossActivity extends AppCompatActivity {
    ImageView showwl;
    TextView calowl,descriptionswl,namewl,timewl,typewl;
    TabHost tab;
    Button btnexit;
    WeightlossModel weightlossModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weightloss);
        showwl=findViewById(R.id.showwei_img);
        calowl=findViewById(R.id.showwei_calo);
        descriptionswl=findViewById(R.id.descriptionwei_show);
        namewl=findViewById(R.id.name_weishow);
        timewl=findViewById(R.id.showwei_time);
        typewl=findViewById(R.id.showwei_type);

        btnexit=findViewById(R.id.btn_exit);
        addControl();
        final Object opject=getIntent().getSerializableExtra("detail3");
        if (opject instanceof WeightlossModel){
            weightlossModel =(WeightlossModel) opject;
        }
        if(weightlossModel != null){
            Glide.with(getApplicationContext()).load(weightlossModel.getImg_url()).into(showwl);
            calowl.setText(weightlossModel.getCalo());
            namewl.setText(weightlossModel.getName());
            timewl.setText(weightlossModel.getTime());
            typewl.setText(weightlossModel.getType());
            descriptionswl.setText(weightlossModel.getDescription());

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
        tab2.setIndicator("INGREDIENT");
        tab.addTab(tab2);
        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("INSTRUCTION");
        tab.addTab(tab3);
    }
}