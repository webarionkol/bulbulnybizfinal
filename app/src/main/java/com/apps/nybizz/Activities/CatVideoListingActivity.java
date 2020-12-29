package com.apps.nybizz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.apps.nybizz.Adapters.CatAdapter;
import com.apps.nybizz.Adapters.CatVideoListingAdapter;
import com.apps.nybizz.Profiles.VideoPostFragment;
import com.apps.nybizz.R;

import java.util.ArrayList;

public class CatVideoListingActivity extends AppCompatActivity {
    RecyclerView recycle_view;
    ImageView img_back;
    CatVideoListingAdapter adapters;
    ArrayList<String> arrayList1 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_video_listing_activity);
        init();

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
        for (int i = 0; i < 4; i++) {
            arrayList1.add("Item " + i);
        }
        adapters = new CatVideoListingAdapter(arrayList1,getApplicationContext());
        RecyclerView.LayoutManager manager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(manager);
        //   recycle_view.setItemAnimator(new DefaultItemAnimator());

        recycle_view.setAdapter(adapters);

        adapters.notifyDataSetChanged();

        adapters.setOnItemClickListener(new CatVideoListingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(CatVideoListingActivity.this, VideoPostFragment.class);
                startActivity(intent);
            }
        });

    }


}
