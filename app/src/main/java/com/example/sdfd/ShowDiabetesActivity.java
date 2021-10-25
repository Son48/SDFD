package com.example.sdfd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
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

    DiabetesModel diabetesModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diabetes);
        showdes=findViewById(R.id.showdiabetes_img);
        calo=findViewById(R.id.showdiabetes_calo);
        descriptions=findViewById(R.id.description_show);
        name=findViewById(R.id.name_desshow);
        final Object opject=getIntent().getSerializableExtra("detail");
        if (opject instanceof DiabetesModel){
            diabetesModel =(DiabetesModel) opject;
        }

            Glide.with(getApplicationContext()).load(diabetesModel.getImg_url()).into(showdes);
            calo.setText(diabetesModel.getCalo());
            name.setText(diabetesModel.getName());
            descriptions.setText(diabetesModel.getDescription());
    }
}