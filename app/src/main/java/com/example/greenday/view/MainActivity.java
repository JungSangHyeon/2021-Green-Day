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
 * 1. REfactoring 이름, 패키지...
 * 2. Fragment 바꿀 때 마다 업뎃시키는것 없애기
 * 3. Favorite 변경 반영하는 좋은 방법 찾기.
 * 4. HILT
 * 5. Network RX Java
 * 5. 이쁜 UI
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FavoriteDatabase.init(this);

        // TODO 왠지 모르겠는데, 얘가 Fragment를 새로 만드네. 이전꺼 쓰는게 좋겠지?
        BottomNavigationView menu = this.findViewById(R.id.menu);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController mainNavController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(menu, mainNavController);
    }
}