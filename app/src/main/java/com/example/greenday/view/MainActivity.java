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
 *
 * Splash Activity 만들까
 *
 * 레포지토리가 좀 복잡한데 데이터 소스 뽑을까
 *
 * iTunes API 실행 할 때 마다 다른 값을 주고, 가끔 겹치는걸 주네 - 정렬시켜서 가져오면 될라나
 *
 * ListAdapter가 더 좋아보이니 적용
 * 아이템 삭제 애니메이션 추가
 * Diff로 notifydatachange안 부르게
 *
 * NavigationUI는 여러 설정을 자동으로 처리해주는 만큼, 커스텀이 안 되니(Animation, Frag 재사용...) 변경
 *
 * Favorite Button Animation을  코드로 짰음. XML으로 설정하는게 좋아보이니 API 19에서 가능한 방법 조사
 *
 * 개발하다 보니, 자료도 죄다 코틀린이고, 롬복이랑 KAPT도 호환 안 되는데 Kotlin으로 갈아엎을까
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