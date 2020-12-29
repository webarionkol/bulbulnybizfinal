package com.apps.nybizz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.nybizz.Network.SharedPrefsUtils;
import com.apps.nybizz.R;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                    if (SharedPrefsUtils.getSharedPreferenceString(getApplicationContext(),"login").equals("1")){
                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }


                //   edt_pin.setVisibility(View.VISIBLE);
                //   btn.setVisibility(View.VISIBLE);
               /* Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();*/

            }
        }, 2000);



    }
}
