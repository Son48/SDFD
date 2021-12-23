package com.example.sdfd.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.sdfd.R;
import com.example.sdfd.models.UserClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;


public class CaloFragment extends Fragment {
    EditText age,height,weight;
    Button btn_start;
    TextView bmr_current,calo_current;
    int TDEE;


    int height_et;
    int age_et;
    int weght_et;
    int bmr;

    static double parameter;


    RadioGroup radioGroup,radioGroup1,radioGroup2;


    FirebaseStorage storage;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calo,container,false);

        btn_start=root.findViewById(R.id.btn_next);
        age =root.findViewById(R.id.age_calo);
        height=root.findViewById(R.id.height_calo);
        weight=root.findViewById(R.id.weight_calo);
        radioGroup =root.findViewById(R.id.radio_group);
        radioGroup1 =root.findViewById(R.id.radio_group1);
        radioGroup2=root.findViewById(R.id.radio_group2);



        bmr_current=root.findViewById(R.id.bmt_current);
        calo_current=root.findViewById(R.id.calo_current);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        //caculator calo
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=root.findViewById(checkedId);
                Button btnbmr = root.findViewById(R.id.btn_bmr);
                switch (checkedId) {
                    case R.id.radio_female:
                        parameter=-161.0;
                      btnbmr.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                            height_et =Integer.valueOf(String.valueOf(height.getText()));
                            age_et =Integer.valueOf(String.valueOf(age.getText()));
                            weght_et = Integer.valueOf(String.valueOf(weight.getText()));
                            bmr=(int) (10*weght_et*2.205+6.25*height_et*0.39370-5*age_et+parameter);
                            bmr_current.setText(String.valueOf((bmr)));
                          }
                      });

                        break;
                    case  R.id.radio_male:
                        parameter=5.0;
                        btnbmr.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                height_et =Integer.valueOf(String.valueOf(height.getText()));
                                age_et =Integer.valueOf(String.valueOf(age.getText()));
                                weght_et = Integer.valueOf(String.valueOf(weight.getText()));
                                bmr=(int)(10*weght_et*2.205+6.25*height_et*0.39370-5*age_et+parameter);
                                bmr_current.setText(String.valueOf((bmr)));
                            }
                        });
                        break;
                }

            }
        });
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = radioGroup1.getCheckedRadioButtonId();
                RadioButton radioButton=root.findViewById(checkedId);
                Button button1= root.findViewById(R.id.btn_calo);

                switch (checkedId){
                    case R.id.radio_m1:
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TDEE=(int)(1.2*bmr) ;
                                    calo_current.setText(String.valueOf(TDEE));
                                }
                            });
                            break;
                    case  R.id.radio_m2:
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TDEE=(int)(1.35*bmr) ;
                                    calo_current.setText(String.valueOf(TDEE));
                                }
                            });
                            break;
                    case  R.id.radio_m3:
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TDEE=(int)(1.55*bmr) ;
                                    calo_current.setText(String.valueOf(TDEE));
                                }
                            });
                            break;
                    case  R.id.radio_m4:
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TDEE=(int)(1.725*bmr) ;
                                    calo_current.setText(String.valueOf(TDEE));
                                }
                            });
                            break;
                    case  R.id.radio_m5:
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    TDEE=(int)(1.9*bmr) ;
                                    calo_current.setText(String.valueOf(TDEE));
                                }
                            });
                            break;
                }
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap<String, Object> calomap = new HashMap<>();

                calomap.put("height",height_et);
                calomap.put("weight",weght_et);
                calomap.put("age",age_et);
                calomap.put("TDEE",TDEE);

                DatabaseReference databaseReference;
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(firebaseAuth.getUid()).updateChildren(calomap);



            }
        });




//        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                checkedId=radioGroup2.getCheckedRadioButtonId();
//                switch (checkedId){
//                    case R.id.radio_w1:
//                }
//            }
//        });

        return root;




    }


}