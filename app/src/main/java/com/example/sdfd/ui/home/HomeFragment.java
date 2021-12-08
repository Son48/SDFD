package com.example.sdfd.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import com.example.sdfd.R;
import com.example.sdfd.ViewAllActivity;
import com.example.sdfd.adapter.DiabetesAdapter;
import com.example.sdfd.adapter.HearAdapter;
import com.example.sdfd.adapter.HomeCategoryAdapter;
import com.example.sdfd.adapter.PopularAdapter;
import com.example.sdfd.adapter.ViewAllAdapter;
import com.example.sdfd.adapter.WeightlossAdapter;
import com.example.sdfd.databinding.FragmentHomeBinding;
import com.example.sdfd.models.DiabetesModel;
import com.example.sdfd.models.HearModel;
import com.example.sdfd.models.HomeCategory;
import com.example.sdfd.models.PopularModel;
import com.example.sdfd.models.ViewAllModel;
import com.example.sdfd.models.WeightlossModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularRec,homeCatRec,diabetesRec,weightlossRec,hearRec;
    FirebaseFirestore db;

    private FragmentHomeBinding binding;

    ///search view popular
    EditText searchbox;
    private RecyclerView recyclerViewSearch;
    private List<ViewAllModel> viewAllModelList;
    private ViewAllAdapter viewAllAdapter;

    //popular item
    PopularAdapter popularAdapter;
    List<PopularModel> popularModelList;

    //category item
    HomeCategoryAdapter homeCategoryAdapter;
    List<HomeCategory> homeCategories;

    //diabetes item
    DiabetesAdapter diabetesAdapter;
    List<DiabetesModel> diabetesModelList;

    //weightloss item
    WeightlossAdapter weightlossAdapter;
    List<WeightlossModel> weightlossModelList;

    //hear item
    HearAdapter hearAdapter;
    List<HearModel> hearModelList;

    //all item
    TextView popularShowAll;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db= FirebaseFirestore.getInstance();

        popularRec =root.findViewById(R.id.poprec);
        homeCatRec =root.findViewById(R.id.category_rec);
        diabetesRec=root.findViewById(R.id.diabetics_rec);
        weightlossRec=root.findViewById(R.id.weightloss_rec);
        hearRec=root.findViewById(R.id.heart_rec);
        popularShowAll=root.findViewById(R.id.view_all_popular);

        //view all popular
        popularShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ViewAllActivity.class);
                startActivity(intent);
            }
        });

        //popular item
        popularRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList =new ArrayList<>();
        popularAdapter=new PopularAdapter(getActivity(),popularModelList);
        popularRec.setAdapter(popularAdapter);

        db.collection("PopularDish")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String Popularid=document.getId();
                                PopularModel popularModel=document.toObject(PopularModel.class);
                                popularModel.setId(Popularid);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // category item
        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeCategories =new ArrayList<>();
        homeCategoryAdapter =new HomeCategoryAdapter(getActivity(),homeCategories);
        homeCatRec.setAdapter(homeCategoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory=document.toObject(HomeCategory.class);
                                homeCategories.add(homeCategory);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // diabetes item
        diabetesRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        diabetesModelList =new ArrayList<>();
        diabetesAdapter =new DiabetesAdapter(getActivity(),diabetesModelList);
        diabetesRec.setAdapter(diabetesAdapter);

        db.collection("Diabetes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DiabetesModel diabetesModel=document.toObject(DiabetesModel.class);
                                diabetesModelList.add(diabetesModel);
                                diabetesAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // weightloss item
        weightlossRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        weightlossModelList =new ArrayList<>();
        weightlossAdapter =new WeightlossAdapter(getActivity(),weightlossModelList);
        weightlossRec.setAdapter(weightlossAdapter);

        db.collection("weight-loss")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               WeightlossModel weightlossModel=document.toObject(WeightlossModel.class);
                                weightlossAdapter.notifyDataSetChanged();
                                String Weightlossid=document.getId();
                                weightlossModel.setId(Weightlossid);
                                weightlossModelList.add(weightlossModel);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        // hear item
        hearRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        hearModelList =new ArrayList<>();
        hearAdapter =new HearAdapter(getActivity(),hearModelList);
        hearRec.setAdapter(hearAdapter);

        db.collection("Heart-related diseaes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HearModel hearModel =document.toObject(HearModel.class);
                                hearModelList.add(hearModel);
                                hearAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        recyclerViewSearch=root.findViewById(R.id.search_rec);
        searchbox=root.findViewById(R.id.search_box);
        viewAllModelList =new ArrayList<>();
        viewAllAdapter=new ViewAllAdapter(getContext(),viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        searchbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().isEmpty()){
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }
                else{
                    searchproduct(editable.toString());
                }
            }
        });
        return root;
    }

    private void searchproduct(String name) {
        if(!name.isEmpty()){
            db.collection("ViewAllItem").orderBy("name").whereGreaterThanOrEqualTo("name", name)
                    .whereLessThanOrEqualTo("name", name + "\uf8ff").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful() && task.getResult()!=null){
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc: task.getResult().getDocuments()){
                                    ViewAllModel viewAllModel=doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}