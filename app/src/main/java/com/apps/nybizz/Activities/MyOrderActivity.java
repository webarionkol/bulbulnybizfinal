package com.apps.nybizz.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.apps.nybizz.Profiles.ORderFragment;
import com.apps.nybizz.Profiles.RefunderOrderFragment;
import com.apps.nybizz.R;

public class MyOrderActivity extends AppCompatActivity {
    ImageView img_back;
    View view_2,view_1;
    RelativeLayout layout_order_refunded,layout_order;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order_activity);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        view_2 = findViewById(R.id.view_2);
        view_1 = findViewById(R.id.view_1);
        layout_order_refunded = findViewById(R.id.layout_order_refunded);
        layout_order = findViewById(R.id.layout_order);

        if (getIntent().getStringExtra("order").equals("1")){
            pushFragment(new ORderFragment(),"order");
        }else {
            pushFragment(new RefunderOrderFragment(),"re");
        }

        layout_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new ORderFragment(),"order");
            }
        });

        layout_order_refunded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushFragment(new RefunderOrderFragment(),"re");
            }
        });

    }


    public boolean pushFragment(Fragment fragment, String tag) {
        //   FrameLayout viewPager_old = (FrameLayout)findViewById(R.id.viewPager_old);
        //    viewPager_old.setVisibility(View.VISIBLE);
//        viewPager.setVisibility(View.GONE);

        if (tag.equals("re")){
            view_1.setVisibility(View.GONE);
            view_2.setVisibility(View.VISIBLE);
        }else {
            view_1.setVisibility(View.VISIBLE);
            view_2.setVisibility(View.GONE);
        }

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
