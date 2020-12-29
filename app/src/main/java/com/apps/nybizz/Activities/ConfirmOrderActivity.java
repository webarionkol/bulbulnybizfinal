package com.apps.nybizz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Adapters.CartAdapter;
import com.apps.nybizz.R;

import java.util.ArrayList;

public class ConfirmOrderActivity extends AppCompatActivity {
    RecyclerView recycle_view;
    ArrayList<String> arrayList = new ArrayList<>();
    CartAdapter adapters;
    TextView txt_edit_address,txt_changed_address;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_order_activity);
        recycle_view = findViewById(R.id.recycle_view);
        txt_edit_address = findViewById(R.id.txt_edit_address);
        txt_changed_address = findViewById(R.id.txt_changed_address);
        txt_changed_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmOrderActivity.this,ChangeAddressActivity.class);
                startActivity(intent);
            }
        });
        txt_edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmOrderActivity.this,EditAddressActivity.class);
                startActivity(intent);
            }
        });
        adapters = new CartAdapter(getApplicationContext(),arrayList);

        for (int i =0; i < 2;i++){
            arrayList.add("");
        }

        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(HorizontalLayout112);
        recycle_view.setItemAnimator(new DefaultItemAnimator());
        adapters.notifyDataSetChanged();
        recycle_view.setAdapter(adapters);

    }
}
