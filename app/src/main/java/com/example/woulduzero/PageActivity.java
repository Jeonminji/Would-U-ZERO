package com.example.woulduzero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.woulduzero.Offline.Fragment_Offline;
import com.example.woulduzero.Online.Fragment_Online;

public class PageActivity extends AppCompatActivity implements View.OnClickListener{

    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;

    private Button bt_tab1, bt_tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        // 위젯에 대한 참조
        bt_tab1 = (Button)findViewById(R.id.bt_tab1);
        bt_tab2 = (Button)findViewById(R.id.bt_tab2);

        // 탭 버튼에 대한 리스너 연결
        bt_tab1.setOnClickListener(this);
        bt_tab2.setOnClickListener(this);

        String button = getIntent().getStringExtra("Button");

        if(button != null && button.equals("offline")) {
            //첫 페이지에서 '오프라인 매장' 클릭 시 오프라인 페이지 이동
            callFragment(FRAGMENT2);
        } else {
            //첫 페이지에서 '온라인 상품' 클릭 시 온라인 페이지 이동
            callFragment(FRAGMENT1);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_tab1:
                // '버튼1' 클릭 시 '프래그먼트1' 호출
                callFragment(FRAGMENT1);
                break;

            case R.id.bt_tab2 :
                // '버튼2' 클릭 시 '프래그먼트2' 호출
                callFragment(FRAGMENT2);
                break;
        }
    }

    private void callFragment(int fragment_no){

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment_no){
            case 1:
                // '프래그먼트1' 호출
                Fragment_Online fragment1 = new Fragment_Online();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                // '프래그먼트2' 호출
                Fragment_Offline fragment2 = new Fragment_Offline();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;
        }
    }
}