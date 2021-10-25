package com.example.sdfd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sdfd.adapter.ViewAllAdapter;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.ViewAllModel;

public class ShowAllActivity extends AppCompatActivity {
    ImageView showall;
    TextView calo,descriptions,name;

    ViewAllModel viewAllModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        showall=findViewById(R.id.showall_img);
        calo=findViewById(R.id.showall_calo);
        descriptions=findViewById(R.id.descriptionall_show);
        name=findViewById(R.id.name_allshow);


        final Object object=getIntent().getSerializableExtra("showall");
        if (object instanceof ViewAllModel){
            viewAllModel =(ViewAllModel) object;
        }

        Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(showall);
        calo.setText(viewAllModel.getCalo());
        name.setText(viewAllModel.getName());
        descriptions.setText(viewAllModel.getDescription());
    }

}
