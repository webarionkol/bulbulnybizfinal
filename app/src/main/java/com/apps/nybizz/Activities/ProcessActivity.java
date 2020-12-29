package com.apps.nybizz.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.nybizz.R;

public class ProcessActivity extends AppCompatActivity {
    TextView txt_heading;
    ImageView img_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prosess_activity);
        txt_heading = findViewById(R.id.txt_heading);
        txt_heading.setText(getIntent().getStringExtra("name"));
        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
