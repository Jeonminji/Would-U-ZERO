package com.example.woulduzero.Offline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.woulduzero.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OfflineshopInfo extends AppCompatActivity implements OnMapReadyCallback {
    ImageView iv_img1;
    ImageView iv_img2;
    ImageView iv_img3;

    int index = 0;

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offlineshop_info);

        Bundle bundle = getIntent().getExtras();
        OfflineShop of = bundle.getParcelable("offlineshop");

        // URL 이미지를 이미지뷰에 연결
        iv_img1 = findViewById(R.id.iv_1);
        Glide.with(this).load(of.getImg1()).into(iv_img1);
        iv_img2 = findViewById(R.id.iv_2);
        Glide.with(this).load(of.getImg2()).into(iv_img2);
        iv_img3 = findViewById(R.id.iv_3);
        Glide.with(this).load(of.getImg3()).into(iv_img3);

        TextView tv_strname = (TextView) findViewById(R.id.tv_strname);
        tv_strname.setText("[ " + of.getStore_name() + " ]");

        ImageButton ib_insta = (ImageButton) findViewById(R.id.ib_insta);
        ib_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(of.getLink()));

                c.startActivity(intent);
            }
        });

        TextView tv_ophours = (TextView) findViewById(R.id.tv_ophours);
        tv_ophours.setText(of.getOpening_hours());

        TextView tv_ad = (TextView) findViewById(R.id.tv_ad);
        tv_ad.setText(of.getAddress());

        TextView tv_type = (TextView) findViewById(R.id.tv_type);
        tv_type.setText(of.getStore_type());

        TextView tv_pn = (TextView) findViewById(R.id.tv_pn);
        tv_pn.setText(of.getStore_phone_number());
        tv_pn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + of.getStore_phone_number().replace("-", "")));

                c.startActivity(intent);
            }
        });

        TextView tv_info = (TextView) findViewById(R.id.tv_info);
        tv_info.setText(of.getOther_info().equals(" ") ? "없음" : of.getOther_info());


        // 구글 맵 관련 코드
        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);
    }

    public void onArrowRightClicked(View v) {
        index++;
        index %= 3;

        if(index == 0) {
            iv_img1.setVisibility(View.VISIBLE);
            iv_img2.setVisibility(View.INVISIBLE);
            iv_img3.setVisibility(View.INVISIBLE);
        } else if(index == 1) {
            iv_img1.setVisibility(View.INVISIBLE);
            iv_img2.setVisibility(View.VISIBLE);
            iv_img3.setVisibility(View.INVISIBLE);
        } else if(index == 2){
            iv_img1.setVisibility(View.INVISIBLE);
            iv_img2.setVisibility(View.INVISIBLE);
            iv_img3.setVisibility(View.VISIBLE);
        }
    }

    public void onArrowLeftClicked(View v) {
        index += 2;
        index %= 3;

        if(index == 0) {
            iv_img1.setVisibility(View.VISIBLE);
            iv_img2.setVisibility(View.INVISIBLE);
            iv_img3.setVisibility(View.INVISIBLE);
        } else if(index == 1) {
            iv_img1.setVisibility(View.INVISIBLE);
            iv_img2.setVisibility(View.VISIBLE);
            iv_img3.setVisibility(View.INVISIBLE);
        } else if(index == 2){
            iv_img1.setVisibility(View.INVISIBLE);
            iv_img2.setVisibility(View.INVISIBLE);
            iv_img3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Bundle bundle = getIntent().getExtras();
        OfflineShop of = bundle.getParcelable("offlineshop");

        LatLng location = new LatLng(of.getLat(), of.getLng()); // 해당 매장 위치에 마커 생성
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(of.getStore_name());
        markerOptions.snippet(of.getStore_type());
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}