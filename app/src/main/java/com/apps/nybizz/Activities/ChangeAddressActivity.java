package com.apps.nybizz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Adapters.ChangeAddressAdapter;
import com.apps.nybizz.Adapters.NotificationAdapter;
import com.apps.nybizz.R;

import java.util.ArrayList;

public class ChangeAddressActivity extends AppCompatActivity {
    ChangeAddressAdapter adapters;
    RecyclerView recycle_view;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ImageView img_back;
    Button btn_add_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_address_activity);

        init();
    }


    public void init(){
        img_back = findViewById(R.id.img_back);
        btn_add_button = findViewById(R.id.btn_add_button);
        btn_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeAddressActivity.this,AddNewAddressActivigty.class);
                startActivity(intent);
            }
        });
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
        adapters = new ChangeAddressAdapter(getApplicationContext(),arrayList1);


        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(HorizontalLayout112);
        //   recycle_view.setItemAnimator(new DefaultItemAnimator());

        recycle_view.setAdapter(adapters);

        adapters.notifyDataSetChanged();

    }


}
