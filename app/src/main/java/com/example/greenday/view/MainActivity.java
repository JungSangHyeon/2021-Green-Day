package com.example.greenday.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.greenday.R;
import com.example.greenday.database.FavoriteDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * TODO
 * 4. HILT
 * 5. Network RX Java
 * 5. 이쁜 UI
 *
 * ListAdapter를 사용해서 페이버릿 notifydatachange 안 부르게 하기
 *
 * 시간 남으면
 * 1. 화면 전환 할 때 Fragment 새로 안 만들고 계속 쓰는게 좋지 않을까? - 왜 Fra씨는 Launch Mode가 없지
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FavoriteDatabase.init(this);

        BottomNavigationView menu = this.findViewById(R.id.menu);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController mainNavController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(menu, mainNavController);
    }
}