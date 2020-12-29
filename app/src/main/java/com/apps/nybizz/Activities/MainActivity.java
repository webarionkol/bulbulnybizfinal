package com.apps.nybizz.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.apps.nybizz.Profiles.CartFragment;
import com.apps.nybizz.Profiles.HelpFragment;
import com.apps.nybizz.Profiles.HomeFragment;
import com.apps.nybizz.Profiles.ProfileFragment;
import com.apps.nybizz.R;

import org.jetbrains.annotations.NotNull;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    private AnimatedBottomBar animatedBottomBar;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init(){
        animatedBottomBar = findViewById(R.id.animatedBottomBar);

        pushFragment(new HomeFragment(),"home");


        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NotNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment = null;
                switch (newTab.getId()) {
                    case R.id.menu_home:
                        pushFragment(new HomeFragment(),"home");
                        break;
                    case R.id.menu_cart:
                        pushFragment(new CartFragment(),"cart");
                        break;
                    case R.id.menu_hrlp:
                        pushFragment(new HelpFragment(),"help");
                        break;
                    case R.id.menu_profile:
                        pushFragment(new ProfileFragment(),"profile");
                        break;
                }


            }
        });
    }



    public boolean pushFragment(Fragment fragment, String tag) {
        //   FrameLayout viewPager_old = (FrameLayout)findViewById(R.id.viewPager_old);
        //    viewPager_old.setVisibility(View.VISIBLE);
//        viewPager.setVisibility(View.GONE);


        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    //.setCustomAnimations(R.anim.feed_in, R.anim.feed_out)
                    .replace(R.id.fragment_container, fragment, tag)
                    //.addToBackStack("fragment")
                    .commit();
            return true;
        }
        return false;
    }



}