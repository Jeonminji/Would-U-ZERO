package com.example.woulduzero; //메인 다음 로딩 페이지

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SubActivity extends AppCompatActivity {

    ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        loading = findViewById(R.id.loading);

        Glide.with(this).load(R.drawable.loading).into(loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SubActivity.this, ThirdActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}