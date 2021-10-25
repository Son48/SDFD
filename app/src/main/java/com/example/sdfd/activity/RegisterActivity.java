package com.example.sdfd.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sdfd.R;
import com.example.sdfd.models.UserClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout mFullname,mEmail,mPhone,mPassword;
    Button btncallsignup;
    ProgressBar pb;
    FirebaseAuth fauth;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mFullname=findViewById(R.id.name);
        mEmail=findViewById(R.id.email);
        mPhone=findViewById(R.id.phoneNumber);
        mPassword=findViewById(R.id.password);
        btncallsignup=findViewById(R.id.callsignup);
        pb=findViewById(R.id.prg_bar1);
        fauth=FirebaseAuth.getInstance();
        btncallsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getEditText().getText().toString().trim();
                String pass=mPassword.getEditText().getText().toString().trim();
                String name=mFullname.getEditText().getText().toString().trim();
                String phone=mPhone.getEditText().getText().toString().trim();

                if(name.isEmpty()){
                    mFullname.setError("Full name cannot be blank!");
                    mFullname.requestFocus();
                    return;
                }
                if(email.isEmpty()){
                    mEmail.setError("Email cannot be blank!");
                    mEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mEmail.setError("Invalid email!");
                    mEmail.requestFocus();
                    return;
                }
                if(pass.isEmpty()){
                    mPassword.setError("Password cannot be blank!");
                    mPassword.requestFocus();
                    return;
                }
                if(pass.length()<8){
                    mPassword.setError("Password must be more than 8 characters!");
                    mPassword.requestFocus();
                    return;
                }

                if(phone.isEmpty()){
                    mPhone.setError("Phone number cannot be blank!");
                    mPhone.requestFocus();
                    return;

                }
                pb.setVisibility(View.GONE);
                fauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UserClass uc=new UserClass(name,email,phone,pass);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(uc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                        pb.setVisibility(View.VISIBLE);
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Failed to registered! Try again!", Toast.LENGTH_SHORT).show();
                                        pb.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to registered!", Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}