package com.apps.nybizz.Activities;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.apps.nybizz.Profiles.BottomSheetFragment;
import com.apps.nybizz.Profiles.BottomSheetFragmentAddtoCard;
import com.apps.nybizz.R;
import com.google.android.exoplayer2.Player;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.security.Permission;

public class ProductViewActivity extends AppCompatActivity {
    private CardView cardview_data;
    BottomSheetBehavior sheetBehavior;
    LinearLayout layoutBottomSheet,layout_name;
    com.google.android.exoplayer2.ui.PlayerView videoView;
    boolean btnBottomSheet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view_activity);

        init();
        onClick();
    }


    private void init(){
        cardview_data = findViewById(R.id.cardview_data);
        layout_name = findViewById(R.id.layout_name);

        videoView = findViewById(R.id.videoView);



    }

    private void onClick() {

        cardview_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragmentAddtoCard bottomSheetFragment = new BottomSheetFragmentAddtoCard();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        layout_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductViewActivity.this,VendorProfileActivity.class);
                startActivity(intent);
            }
        });
    }


}

