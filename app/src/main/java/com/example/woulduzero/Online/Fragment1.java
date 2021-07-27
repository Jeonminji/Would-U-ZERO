package com.example.woulduzero.Online;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woulduzero.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    private FirebaseDatabase database;

    //이미지 슬라이드
    private RecyclerView slideView;
    private RecyclerView.Adapter slideAdapter;
    private RecyclerView.LayoutManager slideLayoutManager;
    private ArrayList<ImageSlide> slideArrayList;
    private DatabaseReference slideDatabaseReference;

    //전체상품 그리드
    // 리사이클러 뷰
    private RecyclerView recyclerView;
    private RecyclerView.Adapter productAdapter;
    private RecyclerView.LayoutManager productLayoutManager;
    private ArrayList<Product> productArrayList;
    private DatabaseReference productDatabaseReference;


    //그리드뷰
    /*
    private GridView gridView;
    private GridAdapterProduct gridAdapter;
    private ArrayList<Product> productArrayList;
    private DatabaseReference productDatabaseReference;

     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);

        database = FirebaseDatabase.getInstance(); //firebase 연동

        //이미지 슬라이드
        slideView = v.findViewById(R.id.recyclerView_slide_img);
        slideView.setHasFixedSize(true);

        //레이아웃 매니저 설정
        slideLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        slideView.setLayoutManager(slideLayoutManager);
        slideArrayList = new ArrayList<>();

        //db연동
        slideDatabaseReference = database.getReference("Recommend");
        slideDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스의 데이터를 받아오는 곳
                slideArrayList.clear(); //기존 배열 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 data추출
                    ImageSlide image = snapshot.getValue(ImageSlide.class); //만들어 뒀던 ImageSlide 객체에 담는다
                    slideArrayList.add(image); //담은 데이터를 배열에 넣고 리사이클러 뷰에 넣을 준비
                }
                slideAdapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비 가져오다가 에러 발생시
                Log.e("Recommend", String.valueOf(databaseError.toException())); //에러 출력
            }
        });

        //어댑터 설정
        slideAdapter = new SliderAdapter(slideArrayList, getContext());
        slideView.setAdapter(slideAdapter);


        //전체상품
        recyclerView = v.findViewById(R.id.recycler_product); //리사이클러뷰 레이아웃 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화

        //레이아웃매니저 설정
        productLayoutManager = new GridLayoutManager(getContext(),2); //MainActicity에서는 this 로 사용
        recyclerView.setLayoutManager(productLayoutManager);
        productArrayList = new ArrayList<>(); //Product 객체 담을 arrayList (adapter 쪽으로)

        //db 연동
        productDatabaseReference = database.getReference("Product");
        productDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스의 데이터를 받아오는 곳
                productArrayList.clear(); //기존 배열 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 data추출
                    Product product = snapshot.getValue(Product.class); //만들어 뒀던 Product 객체에 담는다
                    productArrayList.add(product); //담은 데이터를 배열에 넣고 리사이클러 뷰에 넣을 준비
                }
                productAdapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비 가져오다가 에러 발생시
                Log.e("Product", String.valueOf(databaseError.toException())); //에러 출력
            }
        });



        //어댑터 설정
        productAdapter = new ProductAdatper(productArrayList, getContext()); //MainActivity 에서는 둘째 argument가 this였음
        recyclerView.setAdapter(productAdapter); //리사이클러뷰에 어댑터 연결

        return v;
    }



}
