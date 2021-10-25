package com.example.sdfd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.PopularModel;

public class ShowPopularActivity extends AppCompatActivity {
    ImageView showpo;
    TextView calopo,descriptionspo,namepo;
    PopularModel popularModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_popular);
        showpo=findViewById(R.id.showpo_img);
        calopo=findViewById(R.id.showpo_calo);
        descriptionspo=findViewById(R.id.descriptionpo_show);
        namepo=findViewById(R.id.name_poshow);

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
    }
}