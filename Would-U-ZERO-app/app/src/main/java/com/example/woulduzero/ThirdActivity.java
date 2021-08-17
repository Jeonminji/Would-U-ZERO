package com.example.woulduzero; //첫 페이지

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    Button btn_online;
    Button btn_offline;
    Button btn_moreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //more info button
        btn_moreInfo = (findViewById(R.id.btn_moreinfo));
        btn_moreInfo.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://3.36.96.127/Would-U-ZERO/WEB/about.html"));
            startActivity(intent);
        });

        btn_online = (Button)findViewById(R.id.btn_online);
        btn_offline = (Button)findViewById(R.id.btn_offline);

        btn_online.setOnClickListener(v -> {
            Intent intent = new Intent(this, PageActivity.class);
            intent.putExtra("Button", "online");
            startActivity(intent);
        });

        btn_offline.setOnClickListener(v -> {
            Intent intent = new Intent(this, PageActivity.class);
            intent.putExtra("Button", "offline");
            startActivity(intent);
        });
    }
}