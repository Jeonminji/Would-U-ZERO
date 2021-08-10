package com.example.woulduzero.Offline;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woulduzero.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Offline extends Fragment {
    private CustomAdapter adapter;
    private ArrayList<OfflineShop> offlineShops, filteredList;

    public Fragment_Offline() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_fragment2, container, false);

        // 리사이클러뷰 초기화
        RecyclerView recyclerView = layout.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        offlineShops = new ArrayList<>(); // OfflineShop 객체를 담을 어레이 리스트 (어댑터쪽으로)
        filteredList = new ArrayList<>();
        adapter = new CustomAdapter(offlineShops, this.getContext());
        recyclerView.setAdapter(adapter);

        TextView tv_strcount = layout.findViewById(R.id.tv_strcount);//매장 수 출력 변수

        FirebaseDatabase database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        DatabaseReference databaseReference = database.getReference("OfflineShop"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터 받아오는 곳
                offlineShops.clear(); // 기존 배열리스트 방지 차원에서 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    OfflineShop offlineShop = snapshot.getValue(OfflineShop.class); // 오프라인 매장 객체에 데이터 담는다
                    offlineShops.add(offlineShop); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                tv_strcount.setText("총 " + offlineShops.size() + "개의 매장");
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // DB 가져오는 과정에서 에러 발생 시
                Log.e("Fragment_Offline", String.valueOf(error.toException()));
            }
        });

        Button btn_cafe = layout.findViewById(R.id.btn_cafe);
        Button btn_refill = layout.findViewById(R.id.btn_refill);
        Button btn_zero = layout.findViewById(R.id.btn_zero);

        @SuppressLint("NonConstantResourceId") View.OnClickListener clickListener = v -> {
            switch (v.getId()) {
                case R.id.btn_cafe: {
                    clickedButtonColor(btn_cafe);
                    unclickedButtonColor(btn_refill);
                    unclickedButtonColor(btn_zero);
                    searchFilter("카페", tv_strcount);
                    break;
                }
                case R.id.btn_refill: {
                    unclickedButtonColor(btn_cafe);
                    clickedButtonColor(btn_refill);
                    unclickedButtonColor(btn_zero);
                    searchFilter("리필", tv_strcount);
                    break;
                }
                case R.id.btn_zero: {
                    unclickedButtonColor(btn_cafe);
                    unclickedButtonColor(btn_refill);
                    clickedButtonColor(btn_zero);
                    searchFilter("제로", tv_strcount);
                    break;
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

    @SuppressLint("SetTextI18n")
    public void searchFilter(String searchText, TextView tv) {
        filteredList.clear();

        for (int i = 0; i < offlineShops.size(); i++) {
            if (offlineShops.get(i).getStore_type().contains(searchText))
                filteredList.add(offlineShops.get(i));
        }

        tv.setText("총 " + filteredList.size() + "개의 매장");

        adapter.filterList(filteredList);
    }

    public void clickedButtonColor(Button b) {
        b.setTextColor(Color.parseColor("#FF98D15F"));
        b.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
    }

    public void unclickedButtonColor(Button b) {
        b.setTextColor(Color.parseColor("#FFFFFFFF"));
        b.setBackgroundColor(Color.parseColor("#FF98D15F"));
    }
}
