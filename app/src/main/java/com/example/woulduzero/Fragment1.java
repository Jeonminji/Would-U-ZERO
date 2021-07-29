package com.example.woulduzero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment1 extends Fragment {

    private DrawerLayout drawerLayout;
    private View drawerView;

    List<String> mainCategory;
    List<String> subCategory;
    Map<String, List<String>> categoryList;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment1, container, false);

        //네비게이션 드로어 구현(온라인 카테고리 메뉴 창)
        drawerLayout = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        drawerView = (View)view.findViewById(R.id.drawer);

        ImageView btn_open = (ImageView)view.findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        Button btn_close = (Button)view.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        //서랍 창에 메인, 서브 카테고리 목록 구현
        createMainCategoryList();
        createList();
        expandableListView = view.findViewById(R.id.category);
        expandableListAdapter = new MyExpandableAdapter(view.getContext(), mainCategory, categoryList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
                    expandableListView.collapseGroup(lastExpandedPosition);
                lastExpandedPosition = groupPosition;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selected = expandableListAdapter.getChild(groupPosition, childPosition).toString();
                return true;
            }
        });

        return view;
    }

    private void createList() {
        String[] list1 = {"세제", "수세미", "행주", "솔", "기타"};
        String[] list2 = {"헤어", "바디", "구강", "기타"};
        String[] list3 = {"상의", "하의", "신발", "기타"};
        String[] list4 = {"백팩", "크로스/숄더백", "토트백", "클러치", "기타"};
        String[] list5 = {"악세사리", "지갑", "케이스", "기타"};
        String[] list6 = {"기초", "색조", "기타"};

        categoryList = new HashMap<String, List<String>>();

        for(String main : mainCategory) {
            if(main.equals("주방"))
                loadSub(list1);
            else if (main.equals("욕실"))
                loadSub(list2);
            else if (main.equals("의류"))
                loadSub(list3);
            else if (main.equals("가방"))
                loadSub(list4);
            else if (main.equals("잡화"))
                loadSub(list5);
            else
                loadSub(list6);

            categoryList.put(main, subCategory);
        }

    }

    private void loadSub(String[] subList) {
        subCategory = new ArrayList<>();
        for(String sub : subList)
            subCategory.add(sub);
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