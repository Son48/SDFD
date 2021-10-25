package com.example.sdfd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.HearModel;
import com.google.firebase.database.DatabaseReference;

public class ShowHearActivity extends AppCompatActivity {
    ImageView showhear;
    TextView calo,descriptions,name;


    HearModel hearModel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hear);
        showhear=findViewById(R.id.showhear_img);
        calo=findViewById(R.id.showhear_calo);
        descriptions=findViewById(R.id.descriptionhear_show);
        name=findViewById(R.id.name_hearshow);
        final Object opject=getIntent().getSerializableExtra("detail4");
        if (opject instanceof HearModel){
            hearModel =(HearModel) opject;
        }

        Glide.with(getApplicationContext()).load(hearModel.getImg_url()).into(showhear);
        calo.setText(hearModel.getCalo());
        name.setText(hearModel.getName());
        descriptions.setText(hearModel.getDescription());
    }
}