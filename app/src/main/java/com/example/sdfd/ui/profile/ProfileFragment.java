package com.example.sdfd.ui.profile;

import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.sdfd.R;
import com.example.sdfd.models.UserClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    FirebaseStorage storage;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    ImageView imguser;
    EditText name,email,phone,pass;
    TextView namefiled,emailfiled;
    Button updateprofile;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile,container,false);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        imguser=root.findViewById(R.id.profile_img);
        name=root.findViewById(R.id.name_profile);
        email=root.findViewById(R.id.email_profile);
        phone=root.findViewById(R.id.phoneNumber_profile);
        pass=root.findViewById(R.id.password_profile);
        namefiled=root.findViewById(R.id.fullname_field);
        emailfiled=root.findViewById(R.id.email_field);
        updateprofile=root.findViewById(R.id.update_profile);

        firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserClass user =snapshot.getValue(UserClass.class);
                        namefiled.setText(user.getName());
                        emailfiled.setText(user.getEmail());
                        name.setText(user.getName());
                        phone.setText(user.getPhone());
                        email.setText(user.getEmail());
                        pass.setText(user.getPass());

                        Glide.with(getContext()).load(user.getProfileImg()).into(imguser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        imguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("imame/*");
                startActivityForResult(intent,33);
            }
        });


        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });
        return root;

    }

    private void updateUserProfile() {
        final HashMap<String, Object> profilemap = new HashMap<>();
        profilemap.put("name",name.getText().toString());
        profilemap.put("email",email.getText().toString());
        profilemap.put("phone",phone.getText().toString());
        profilemap.put("pass",pass.getText().toString());
        DatabaseReference databaseReference;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(firebaseAuth.getUid()).updateChildren(profilemap);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            Uri profileUri=data.getData();
            imguser.setImageURI(profileUri);
            final StorageReference reference=storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}