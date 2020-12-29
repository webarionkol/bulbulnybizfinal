package com.apps.nybizz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.apps.nybizz.Adapters.CatAdapter;
import com.apps.nybizz.Adapters.NotificationAdapter;
import com.apps.nybizz.Adapters.RecyclerViewAdapter;
import com.apps.nybizz.R;
import com.apps.nybizz.data.ModelSmoolider;
import com.apps.nybizz.data.SmooliderAdapter;
import com.av.smoothviewpager.Smoolider.SmoothViewpager;
import com.av.smoothviewpager.utils.Txt_Factory;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static com.av.smoothviewpager.utils.Smoolider_Utils.autoplay_viewpager;
import static com.av.smoothviewpager.utils.Smoolider_Utils.openWebPage;
import static com.av.smoothviewpager.utils.Smoolider_Utils.stop_autoplay_ViewPager;

public class CatActivity extends AppCompatActivity {
    RecyclerView recycle_view;
    ImageView img_back;
    CatAdapter adapters;
    ArrayList<String> arrayList1 = new ArrayList<>();

    private final int[] pics = {R.drawable.img01, R.drawable.img02, R.drawable.img01, R.drawable.img02, R.drawable.img01};


    private final String[] car_hp = {"S 65", "400 4Matic", "GT 63S", "G 63", "C 63S"};
    private final String[] position = {"1 / 5", "2 / 5", "3 / 5", "4 / 5", "5 / 5"};

    private TextSwitcher txt_swithcher_position;
    private TextView txt_title;
    private TextView txt_subtitle;
    private boolean is_autoplay = false;
    private int currentPosition;
    private SmoothViewpager viewPager;
    private ImageView img_github;
    private LottieAnimationView animationView;
    private List<ModelSmoolider> feedItemList;
    private RelativeLayout layout_vendor_click;
    private ImageView img_notification;
    private ImageView img_search;
    RecyclerViewAdapter recyclerViewAdapter;

    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_activity);
        init();
        slider();
    }
    public void init(){
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recycle_view = findViewById(R.id.recycle_view);
        for (int i = 0; i < 2; i++) {
            arrayList1.add("Item " + i);
        }
        adapters = new CatAdapter(getApplicationContext(),arrayList1);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);

        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(manager);
        //   recycle_view.setItemAnimator(new DefaultItemAnimator());

        recycle_view.setAdapter(adapters);

        adapters.notifyDataSetChanged();


        adapters.setOnItemClickListener(new CatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CatActivity.this,CatVideoListingActivity.class);
                startActivity(intent);
            }
        });

    }

    private void populateData() {

        for (int i = 0; i < 2; i++) {
            arrayList1.add("Item " + i);
        }
    }

    private void initAdapter() {

    }

    private void slider(){

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        init_widgets();
        generate_items();

        viewPager.setAdapter( new SmooliderAdapter(feedItemList,getApplicationContext()));

        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manage_autoplay();
                Snackbar.make(view, " ▶ Autoplay :  "+is_autoplay, Snackbar.LENGTH_LONG).show();
            }
        });


        img_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(getApplicationContext(),"https://github.com/astrit-veliu");
            }
        });



        //use this method if you want to interact with other widgets
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {

            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(final int position) {
                // handle operations with current page
                manage_widgets_on_swipe(position);

            }
        });


    }


    private void init_widgets() {

        //getApplicationContext().getSupportActionBar().hide();

        viewPager = (SmoothViewpager)findViewById(R.id.smoolider);
        img_github = (ImageView)findViewById(R.id.img_github);
        txt_swithcher_position = (TextSwitcher)findViewById(R.id.txt_swithcher_position);
        txt_title = (TextView) findViewById(R.id.txt_title);
        txt_subtitle = (TextView) findViewById(R.id.txt_subtitle);
        animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.autoplay_animation);

        // new Boom(txt_title);
        // new Boom(txt_subtitle);

        txt_swithcher_position.setFactory(new Txt_Factory(R.style.CustomTextView, true,getApplicationContext()));
        txt_swithcher_position.setCurrentText(position[0]);
        //txt_title.setText("");

        manage_widgets_on_swipe(0);
    }

    private void generate_items(){
        feedItemList = new ArrayList<>();
        for(int i=0;i<pics.length;i++){
            ModelSmoolider gift = new ModelSmoolider();
            gift.setImage(pics[i]);
            gift.setName(1);
            gift.setUrl(1);
            gift.setDescription(1);
            gift.setPoints(car_hp[i]);

            feedItemList.add(gift);
        }
    }

    private void manage_widgets_on_swipe(int pos) {
        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        txt_swithcher_position.setInAnimation(getApplicationContext(), animH[0]);
        txt_swithcher_position.setOutAnimation(getApplicationContext(), animH[1]);
        txt_swithcher_position.setText(position[pos % position.length]);

        txt_title.setVisibility(View.GONE);
        txt_title.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        txt_title.setVisibility(View.GONE);
        txt_title.setText("1");

        txt_subtitle.setVisibility(View.GONE);
        txt_subtitle.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
        txt_subtitle.setVisibility(View.GONE);
        txt_subtitle.setText("1");

        currentPosition = pos;
    }


    private void manage_autoplay(){
        animationView.playAnimation();
        if(is_autoplay){
            is_autoplay = false;
            stop_autoplay_ViewPager();
        } else {
            is_autoplay = true;
            autoplay_viewpager(viewPager,feedItemList.size());
        }


    }
    
    




}
