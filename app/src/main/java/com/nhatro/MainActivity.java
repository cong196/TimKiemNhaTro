package com.nhatro;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

public class MainActivity extends AppCompatActivity {


    AHBottomNavigation bottomNavigation;
    final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
    HomeFragment homeFragment = new HomeFragment();
    NotifyFragment notifyFragment = new NotifyFragment();
    PostFragment postFragment = new PostFragment();
    AccountFragment accountFragment = new AccountFragment();
    tim_o_ghep tim_o_ghep = new tim_o_ghep();
    Fragment active = homeFragment;
    private int t1, t2, t3 = 0;

    @Override
    public void onBackPressed() {
        if(active != homeFragment) {
            bottomNavigation.setCurrentItem(0, false);
            fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
            active = homeFragment;
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.home);
        actionBar.setDisplayUseLogoEnabled(true);
        //actionBar.hide();

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        /////////////
         // Test
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setForceTint(true);

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        //////////


        final AHBottomNavigationItem item1 = new AHBottomNavigationItem("Tìm phòng", R.drawable.home);
        bottomNavigation.addItem(item1);

        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Tìm ở ghép", R.drawable.news);
        bottomNavigation.addItem(item2);

        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Thông báo", R.drawable.notify);
        bottomNavigation.addItem(item3);

        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Tài khoản", R.drawable.account);
        bottomNavigation.addItem(item4);
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setNotification("5", 2);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#1594E9"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0) {
                    bottomNavigation.setCurrentItem(0, false);
                    if (active != homeFragment) {
                        fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                        active = homeFragment;
                    }
                } else {
                    if (position == 1) {
                        // Active item ở vị trí 1
                        bottomNavigation.setCurrentItem(1, false);
                        if (t1 == 1) { // Đã add Fragment post
                            // Nếu post fragment không phải đang active
                            if (active != tim_o_ghep) {
                                fragmentManager.beginTransaction().hide(active).show(tim_o_ghep).commit();
                                //Set active là post Fragment
                                active = tim_o_ghep;
                            }
                            //active = postFragment;
                        } else { // chưa add Fragment post
                            t1 = 1; // Set là đã add
                            // Add vào
                            fragmentManager.beginTransaction().add(R.id.frame, tim_o_ghep).commit();
                            fragmentManager.beginTransaction().hide(active).show(tim_o_ghep).commit();
                            active = tim_o_ghep;
                        }
                    } else {
                        if (position == 2) {
                            bottomNavigation.setCurrentItem(2, false);
                            if (t2 == 1) { // Đã add Fragment thông báo
                                // Nếu notify fragment không phải đang active
                                if (active != notifyFragment) {
                                    fragmentManager.beginTransaction().hide(active).show(notifyFragment).commit();
                                    //Set active là notify Fragment
                                    active = notifyFragment;
                                }
                            } else { // chưa add Fragment notify
                                t2 = 1; // Set là đã add
                                // Add vào
                                fragmentManager.beginTransaction().add(R.id.frame, notifyFragment).commit();
                                fragmentManager.beginTransaction().hide(active).show(notifyFragment).commit();
                                active = notifyFragment;
                            }
                        } else {
                            bottomNavigation.setCurrentItem(3, false);
                            if (t3 == 1) { // Đã add Fragment account
                                // Nếu account fragment không phải đang active
                                if (active != accountFragment) {
                                    fragmentManager.beginTransaction().hide(active).show(accountFragment).commit();
                                    //Set active là account Fragment
                                    active = accountFragment;
                                }
                            } else { // chưa add Fragment account
                                t3 = 1; // Set là đã add
                                // Add vào
                                fragmentManager.beginTransaction().add(R.id.frame, accountFragment).commit();
                                fragmentManager.beginTransaction().hide(active).show(accountFragment).commit();
                                active = accountFragment;
                            }
                        }
                    }
                }
                return false;
            }
        });
        fragmentManager.beginTransaction().add(R.id.frame, homeFragment).commit(); // Add và hiện thị fragment home khi khởi động

    }
}
