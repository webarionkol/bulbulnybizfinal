package com.apps.nybizz.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Adapters.RecyclerViewAdapter;
import com.apps.nybizz.Adapters.RecyclerViewAdapterSearch;
import com.apps.nybizz.Adapters.SearchAdapter;
import com.apps.nybizz.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recycle_view,recycle_view_bought;
    ArrayList<String> arrayList = new ArrayList<>();
    RecyclerViewAdapterSearch recyclerViewAdapter;
    private SearchAdapter adapters;
    ArrayList<String> arrayList1 = new ArrayList<>();

    ImageView img_back;
    int[] animationList = {R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_fragment);
        init();
        data();
    }
    public void init(){
        recycle_view = findViewById(R.id.recycle_view);
        recycle_view_bought = findViewById(R.id.recycle_view_bought);

        adapters = new SearchAdapter(getApplicationContext(),arrayList);
        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void data(){

        for (int i =0; i < 5;i++){
            arrayList.add("");
        }
        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(HorizontalLayout112);
        recycle_view.setItemAnimator(new DefaultItemAnimator());
        adapters.notifyDataSetChanged();
        recycle_view.setAdapter(adapters);

        for (int i =0; i < 5;i++){
            arrayList1.add("");
        }

        recyclerViewAdapter = new RecyclerViewAdapterSearch(arrayList1,getApplicationContext());

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
        recycle_view_bought.setLayoutManager(manager);
//        recycle_view.setItemAnimator(new DefaultItemAnimator());




        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getApplicationContext(), animationList[0]);

        recycle_view_bought.setLayoutAnimation(controller);
        recycle_view_bought.setAdapter(recyclerViewAdapter);
        recycle_view_bought.scheduleLayoutAnimation();

    }


}
