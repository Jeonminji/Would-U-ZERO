package com.example.woulduzero.Online;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woulduzero.MyExpandableAdapter;
import com.example.woulduzero.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_Online extends Fragment {

    //서랍
    private DrawerLayout drawerLayout;
    private View drawerView;

    List<String> mainCategory;
    List<String> subCategory;
    Map<String, List<String>> categoryList;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    //화면
    private SliderAdapter slideAdapter;
    private ArrayList<ImageSlide> slideArrayList, recommendList, filteredRecommend, filteredView;

    private ProductAdapter productAdapter;
    private ArrayList<Product> productArrayList, filteredList;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance(); //firebase 연동

        //전체상품
        //이미지 슬라이드
        RecyclerView slideView = v.findViewById(R.id.recyclerView_slide_img);
        slideView.setHasFixedSize(true);

        //레이아웃 매니저 설정
        RecyclerView.LayoutManager slideLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        slideView.setLayoutManager(slideLayoutManager);
        slideArrayList = new ArrayList<>();
        recommendList = new ArrayList<>();

        //db연동
        DatabaseReference slideDatabaseReference = database.getReference("Product");
        slideDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스의 데이터를 받아오는 곳
                slideArrayList.clear(); //기존 배열 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 data추출
                    ImageSlide image = snapshot.getValue(ImageSlide.class); //만들어 뒀던 ImageSlide 객체에 담는다
                    slideArrayList.add(image); //담은 데이터를 배열에 넣고 리사이클러 뷰에 넣을 준비
                }
                recommendList.clear();
                for(int i = 0; i < 5; i++) {
                    int genNum = (int) ((Math.random() * 100) % 908);
                    recommendList.add(slideArrayList.get(genNum));
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
        slideAdapter = new SliderAdapter(recommendList, getContext());
        slideView.setAdapter(slideAdapter);


        // 리사이클러 뷰
        RecyclerView recyclerView = v.findViewById(R.id.recycler_product); //리사이클러뷰 레이아웃 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화

        //레이아웃매니저 설정
        RecyclerView.LayoutManager productLayoutManager = new GridLayoutManager(getContext(), 2); //MainActicity에서는 this 로 사용
        recyclerView.setLayoutManager(productLayoutManager);
        productArrayList = new ArrayList<>(); //Product 객체 담을 arrayList (adapter 쪽으로)

        //db 연동
        DatabaseReference productReference = database.getReference("Product");
        productReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
        productAdapter = new ProductAdapter(productArrayList, this.getContext()); //MainActivity 에서는 둘째 argument가 this였음
        recyclerView.setAdapter(productAdapter); //리사이클러뷰에 어댑터 연결

        //서랍
        //네비게이션 드로어 구현(온라인 카테고리 메뉴 창)
        drawerLayout = v.findViewById(R.id.drawer_layout);
        drawerView = v.findViewById(R.id.drawer);

        ImageView btn_open = v.findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v1 -> drawerLayout.openDrawer(drawerView));

        Button btn_close = v.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(v2 -> drawerLayout.closeDrawers());

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener((v3, event) -> true);

        //서랍 창에 메인, 서브 카테고리 목록 구현
        createMainCategoryList();
        createList();
        expandableListView = v.findViewById(R.id.category);
        expandableListAdapter = new MyExpandableAdapter(v.getContext(), mainCategory, categoryList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
                    expandableListView.collapseGroup(lastExpandedPosition);
                lastExpandedPosition = groupPosition;
            }
        });

        expandableListView.setOnChildClickListener((parent, v12, groupPosition, childPosition, id) -> {
            filteredList = new ArrayList<>();
            filteredRecommend = new ArrayList<>();
            String mainCategory = expandableListAdapter.getGroup(groupPosition).toString();
            String subCategory = expandableListAdapter.getChild(groupPosition, childPosition).toString();
            Log.d(getTag(), mainCategory + subCategory);

            searchFilter(mainCategory,subCategory);
            recommendFilter(mainCategory, subCategory);
            drawerLayout.close();

            return true;
        });

        //맨 위로, 맨 아래로 버튼
        FloatingActionButton btn_top = v.findViewById(R.id.top);
        btn_top.setOnClickListener(v1 -> {
            recyclerView.smoothScrollToPosition(0);
        });

        FloatingActionButton btn_bottom = v.findViewById(R.id.bottom);
        btn_top.setOnClickListener(v1 -> {
            recyclerView.smoothScrollToPosition(productArrayList.size());
        });

        return v;
    }

    public void searchFilter(String main_category, String sub_category) {
        filteredList.clear();

        for(int i = 0; i < productArrayList.size(); i++) {
            if(productArrayList.get(i).getMain_category().contains(main_category)
                    && productArrayList.get(i).getSub_category().contains(sub_category))
                filteredList.add(productArrayList.get(i));
        }

        productAdapter.filterList(filteredList);
    }

    public void recommendFilter(String main_category, String sub_category) {
        filteredRecommend.clear();
        filteredView = new ArrayList<>();

        for(int i = 0; i < slideArrayList.size(); i++) {
            if(slideArrayList.get(i).getMain_category().contains(main_category)
                    && slideArrayList.get(i).getSub_category().contains(sub_category))
                filteredRecommend.add(slideArrayList.get(i));
        }

        for(int i = 0; i < 5; i++) {
            int randNum = (int) ((Math.random() * 100) % filteredRecommend.size());
            filteredView.add(filteredRecommend.get(randNum));
        }
        slideAdapter.filterList(filteredView);
    }

    private void createList() {
        String[] list1 = {"세제", "수세미", "행주", "솔", "기타"};
        String[] list2 = {"헤어", "바디", "구강", "기타"};
        String[] list3 = {"상의", "하의", "신발", "기타"};
        String[] list4 = {"백팩", "크로스/숄더백", "토트백", "클러치", "기타"};
        String[] list5 = {"악세사리", "지갑", "케이스", "기타"};
        String[] list6 = {"기초", "색조", "기타"};

        categoryList = new HashMap<>();

        for (String main : mainCategory) {
            switch (main) {
                case "주방":
                    loadSub(list1);
                    break;
                case "욕실":
                    loadSub(list2);
                    break;
                case "의류":
                    loadSub(list3);
                    break;
                case "가방":
                    loadSub(list4);
                    break;
                case "잡화":
                    loadSub(list5);
                    break;
                default:
                    loadSub(list6);
                    break;
            }

            categoryList.put(main, subCategory);
        }

    }

    private void loadSub(String[] subList) {
        subCategory = new ArrayList<>();
        Collections.addAll(subCategory, subList);
    }

    private void createMainCategoryList() {
        mainCategory = new ArrayList<>();
        mainCategory.add("주방");
        mainCategory.add("욕실");
        mainCategory.add("의류");
        mainCategory.add("가방");
        mainCategory.add("잡화");
        mainCategory.add("화장품");
    }

    //네비게이션 드로어 구현
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };


}