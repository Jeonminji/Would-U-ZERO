package com.example.woulduzero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<OfflineShop> offlineShops, filteredList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_fragment2, container, false);

//        layout.findViewById(R.id.btn_cafe).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "wow", Toast.LENGTH_LONG).show();
//            }
//        });


        // 리사이클러뷰 초기화
        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        offlineShops = new ArrayList<>(); // OfflineShop 객체를 담을 어레이 리스트 (어댑터쪽으로)

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("OfflineShop"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터 받아오는 곳
                offlineShops.clear(); // 기존 배열리스트 방지 차원에서 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OfflineShop offlineShop = snapshot.getValue(OfflineShop.class); // 오프라인 매장 객체에 데이터 담는다
                    offlineShops.add(offlineShop); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // DB 가져오는 과정에서 에러 발생 시
                Log.e("Fragment2", String.valueOf(error.toException()));
            }
        });

        adapter = new CustomAdapter(offlineShops, this.getContext());
        recyclerView.setAdapter(adapter);


//        Button btn_cafe = (Button) getActivity().findViewById(R.id.btn_cafe);
//        btn_cafe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                adapter = new CustomAdapter(searchFilter("카페"), Fragment2.super.getContext());
//                recyclerView.setAdapter(adapter);
//            }
//        });

        View.OnClickListener clickListener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_cafe: {
                        searchFilter("카페");
                        break;
                    }
                    case R.id.btn_refill: {
                        Toast.makeText(v.getContext(), "refill", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.btn_zero: {
                        Toast.makeText(v.getContext(), "zero", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        };

        layout.findViewById(R.id.btn_cafe).setOnClickListener(clickListener);
        layout.findViewById(R.id.btn_refill).setOnClickListener(clickListener);
        layout.findViewById(R.id.btn_zero).setOnClickListener(clickListener);

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void searchFilter(String searchText) {
        filteredList.clear();

        for(int i = 0; i < offlineShops.size(); i++) {
            if(offlineShops.get(i).getStore_type().equals(searchText))
                filteredList.add(offlineShops.get(i));
        }

        adapter.filterList(filteredList);
    }

//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.btn_cafe:
//            {
//                adapter.notifyDataSetChanged();
//                adapter = new CustomAdapter(searchFilter("카페"), this.getContext());
//                recyclerView.setAdapter(adapter);
//            }
//            case R.id.btn_refill:
//            {
//                adapter = new CustomAdapter(searchFilter("리필 단일샵"), this.getContext());
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            }
//            case R.id.btn_zero:
//            {
//                adapter = new CustomAdapter(searchFilter("제로웨이스트 샵 & 리필"), this.getContext());
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            }
//        }
//    }
}
