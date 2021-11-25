package com.example.greenday.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.greenday.R;
import com.example.greenday.database.Favorite;
import com.example.greenday.database.FavoriteDatabase;
import com.example.greenday.viewmodel.TrackListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.schedulers.Schedulers;

/**
 * TODO
 * 5. 이쁜 UI - 애니메이션도, 리스트 삭제도
 *
 * 시간 남으면
 * 1. 화면 전환 할 때 Fragment 새로 안 만들고 계속 쓰는게 좋지 않을까? - 왜 Fra씨는 Launch Mode가 없지
 * 2. ListAdapter를 사용해서 페이버릿 notifydatachange 안 부르게 하기 - tasklist도 더 깔끔하게
 * 3. iTunes API 실행 할 때 마다 다른 값을 주고, 가끔 겹치는걸 주네 - 정렬시켜서 가져오면 될라나
 * 4. 개발하다 보니, 자료도 죄다 코틀린이고, 롬복이랑 KAPT씨도 호환 안 되는데 Kotlin으로 갈아엎을까
 * 5. 레포지토리가 좀 복잡한데 데이터 소스 뽑을까
 * 6. Splash Activity 만들까
 *
 * API 19는 XML에서 Animation 설정해줄 방법이 없을까?
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.black_light));
        }

        BottomNavigationView menu = this.findViewById(R.id.menu);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        NavController mainNavController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(menu, mainNavController);
    }
}