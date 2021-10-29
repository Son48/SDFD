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
import com.example.sdfd.models.HearModel;
import com.google.firebase.database.DatabaseReference;

public class ShowHearActivity extends AppCompatActivity {
    ImageView showhear;
    TextView calo,descriptions,name;
    TabHost tab;
    Button btnexit;
    HearModel hearModel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hear);
        showhear=findViewById(R.id.showhear_img);
        calo=findViewById(R.id.showhear_calo);
        descriptions=findViewById(R.id.descriptionhear_show);
        name=findViewById(R.id.name_hearshow);
        btnexit=findViewById(R.id.btn_exit);
        addControl();
        final Object opject=getIntent().getSerializableExtra("detail4");
        if (opject instanceof HearModel){
            hearModel =(HearModel) opject;
        }

        Glide.with(getApplicationContext()).load(hearModel.getImg_url()).into(showhear);
        calo.setText(hearModel.getCalo());
        name.setText(hearModel.getName());
        descriptions.setText(hearModel.getDescription());
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