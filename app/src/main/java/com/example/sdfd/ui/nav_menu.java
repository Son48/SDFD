package com.example.sdfd.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdfd.R;
import com.example.sdfd.adapter.MyMenuAdapter;
import com.example.sdfd.models.HistoryModel;
import com.example.sdfd.models.MenuModel;
import com.example.sdfd.models.UserClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class nav_menu extends Fragment {

    RadioGroup radioGroup;
    UserClass userClass;
    FirebaseStorage storage;

    FirebaseDatabase firebaseDatabase;
    TabHost tab;
    TextView daycalo;
    DatabaseReference databaseReference;
    Button btngocalo;
    TextView setcalo;
    int caloinday;
    static int parameter;
    int tongcalocan;

    TextView calomenu1,calomenu2;

    //create menu
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    MyMenuAdapter myMenuAdapter;
    List<MenuModel>menuModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_nav_menu, container, false);

        tab=root.findViewById(R.id.tabmenu);

        //tap2
        calomenu1=root.findViewById(R.id.text_calomenu);
        calomenu2=root.findViewById(R.id.calo_inaday2);

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalCaloUsed"));

        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        recyclerView=root.findViewById(R.id.menu_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        menuModels=new ArrayList<>();
        myMenuAdapter=new MyMenuAdapter(getActivity(),menuModels);

        recyclerView.setAdapter(myMenuAdapter);

        firestore.collection("AddToMenu").document(firebaseAuth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String menuid= document.getId();
                        MenuModel menuModel=document.toObject(MenuModel.class);
                        menuModel.setMenuId(menuid);
                        menuModels.add(menuModel);
                        myMenuAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //tap1
        daycalo=root.findViewById(R.id.day_calo);
        radioGroup=root.findViewById(R.id.radio_group2);
        btngocalo=root.findViewById(R.id.btn_gocalomenu);
        setcalo=root.findViewById(R.id.txt_setcalo);


        databaseReference =FirebaseDatabase.getInstance().getReference().child("Users");

        // set calo

        databaseReference.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userClass =snapshot.getValue(UserClass.class);
                daycalo.setText(String.valueOf(userClass.getTDEE()));
                calomenu1.setText(String.valueOf(userClass.getCalointoday()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // set calo gain/
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=root.findViewById(checkedId);
                Button btnsetcalo = root.findViewById(R.id.btn_dcalo);

                switch (checkedId) {
                    case R.id.radio_w1:
                        parameter = 0;
                        btnsetcalo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                caloinday=Integer.parseInt(daycalo.getText().toString());
                                tongcalocan=(int)(caloinday+parameter);
                                setcalo.setText(String.valueOf(tongcalocan));

                            }
                        });
                        break;
                    case R.id.radio_w2:
                        parameter = -250;
                        btnsetcalo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                caloinday=Integer.parseInt(daycalo.getText().toString());
                                tongcalocan=(int)(caloinday+parameter);
                                setcalo.setText(String.valueOf(tongcalocan));
                            }
                        });
                        break;
                    case R.id.radio_w3:
                        parameter = -500;
                        btnsetcalo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                caloinday=Integer.parseInt(daycalo.getText().toString());
                                tongcalocan=(int)(caloinday+parameter);
                                setcalo.setText(String.valueOf(tongcalocan));
                            }
                        });
                        break;
                    case R.id.radio_w4:
                        parameter = -1000;
                        btnsetcalo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                caloinday=Integer.parseInt(daycalo.getText().toString());
                                tongcalocan=(int)(caloinday+parameter);
                                setcalo.setText(String.valueOf(tongcalocan));
                            }
                        });
                        break;
                }

            }
        });
        btngocalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap<String, Object> daymap = new HashMap<>();


                daymap.put("calointoday",tongcalocan);

                DatabaseReference databaseReference;
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(firebaseAuth.getUid()).updateChildren(daymap);
            }
        });
        addControl();
        return  root;
    }
    private void addControl() {
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Calo");
        tab.addTab(tab1);
        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Menu");
        tab.addTab(tab2);
    }
    public BroadcastReceiver mMessageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalCalo=intent.getIntExtra("totalCalo",0);
            calomenu2.setText(String.valueOf(totalCalo));
        }
    };
}