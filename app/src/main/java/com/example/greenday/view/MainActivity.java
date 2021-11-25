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
 * 4. HILT로 DI - 네트워크, DB, 레포, 뷰모델
 * 5. 이쁜 UI
 *
 * 시간 남으면
 * 1. 화면 전환 할 때 Fragment 새로 안 만들고 계속 쓰는게 좋지 않을까? - 왜 Fra씨는 Launch Mode가 없지
 * 2. ListAdapter를 사용해서 페이버릿 notifydatachange 안 부르게 하기 - tasklist도 더 깔끔하게
 * 3. iTunes API 실행 할 때 마다 다른 값을 주고, 가끔 겹치는걸 주네 - 정렬시켜서 가져오면 될라나
 * 4. 개발하다 보니, 자료도 죄다 코틀린이고, 롬복이랑 KAPT씨도 호환 안 되는데 Kotlin으로 갈아엎을까
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