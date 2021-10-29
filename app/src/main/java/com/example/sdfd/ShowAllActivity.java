package com.example.sdfd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.adapter.ViewAllAdapter;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.ViewAllModel;

public class ShowAllActivity extends AppCompatActivity {
    ImageView showall;
    TextView calo,descriptions,name;
    TabHost tab;
    Button btnexit;
    ViewAllModel viewAllModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        showall=findViewById(R.id.showall_img);
        calo=findViewById(R.id.showall_calo);
        descriptions=findViewById(R.id.descriptionall_show);
        name=findViewById(R.id.name_allshow);
        btnexit=findViewById(R.id.btn_exit);
        addControl();


        final Object object=getIntent().getSerializableExtra("showall");
        if (object instanceof ViewAllModel){
            viewAllModel =(ViewAllModel) object;
        }

        Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(showall);
        calo.setText(viewAllModel.getCalo());
        name.setText(viewAllModel.getName());
        descriptions.setText(viewAllModel.getDescription());

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
