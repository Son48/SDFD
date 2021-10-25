package com.example.sdfd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.models.PopularModel;
import com.example.sdfd.models.WeightlossModel;

public class ShowWeightlossActivity extends AppCompatActivity {
    ImageView showwl;
    TextView calowl,descriptionswl,namewl;
    WeightlossModel weightlossModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weightloss);
        showwl=findViewById(R.id.showwl_img);
        calowl=findViewById(R.id.showwl_calo);
        descriptionswl=findViewById(R.id.descriptionwl_show);
        namewl=findViewById(R.id.name_wlshow);
        final Object opject=getIntent().getSerializableExtra("detail3");
        if (opject instanceof WeightlossModel){
            weightlossModel =(WeightlossModel) opject;
        }
        if(weightlossModel != null){
            Glide.with(getApplicationContext()).load(weightlossModel.getImg_url()).into(showwl);
            calowl.setText(weightlossModel.getCalo());
            namewl.setText(weightlossModel.getName());
            descriptionswl.setText(weightlossModel.getDescription());
        }
    }
}