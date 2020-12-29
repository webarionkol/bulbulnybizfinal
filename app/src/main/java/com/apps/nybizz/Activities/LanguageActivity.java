package com.apps.nybizz.Activities;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Adapters.LanguageAdapter;
import com.apps.nybizz.R;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity {
    RecyclerView recycle_view;
    ArrayList<String> arrayList = new ArrayList<>();
    LanguageAdapter adapters;
    ImageView img_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activity);
        init();
        data();
    }

    private void init(){
        recycle_view = findViewById(R.id.recycle_view);
        img_back = findViewById(R.id.img_back);
    }

    private void data(){

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        adapters = new LanguageAdapter(getApplicationContext(),arrayList);
        for (int i =0; i < 10;i++){
            arrayList.add("");
        }

        RecyclerView.LayoutManager HorizontalLayout112 = new GridLayoutManager(getApplicationContext(), 2);

        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(HorizontalLayout112);
        recycle_view.setItemAnimator(new DefaultItemAnimator());
        adapters.notifyDataSetChanged();
        recycle_view.setAdapter(adapters);


    }

}
