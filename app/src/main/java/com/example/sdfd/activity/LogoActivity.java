package com.example.sdfd.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sdfd.R;

public class LogoActivity extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView img;
    TextView logo,sologan;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        img=findViewById(R.id.img_logo);
        logo=findViewById(R.id.txt_name);
        sologan=findViewById(R.id.txt_sologan);

        img.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        sologan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //tạo kết nối với login
                Intent it = new Intent(LogoActivity.this, LoginActivity.class);
                startActivity(it);
                finish();
//                tao hieu ung chuyen canh
                Pair[] pairs=new Pair[3];
                pairs[0]=new Pair<View,String>(img,"logog_imamge");
                pairs[1]=new Pair<View,String>(logo,"logo_text");
                pairs[2]=new Pair<View,String>(logo,"logo_text");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LogoActivity.this,pairs);
                    startActivity(it,options.toBundle());
                }


            }
        },5000);
    }
}