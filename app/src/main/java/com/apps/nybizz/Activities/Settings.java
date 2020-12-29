package com.apps.nybizz.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.nybizz.Network.SharedPrefsUtils;
import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.RegistrationResponse;
import com.apps.nybizz.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settings extends AppCompatActivity {
    ImageView img_back;
    RelativeLayout layout_languages,layout_about,layout_contact_us,layout_privacy_policy,layout_terms_condtion,layout_refund,shipping_policy,layout_payment_policy,layout_logout,layout_process_and_logistics;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        init();
        onClick();
    }

    private void init(){
        progressDialog = new ProgressDialog(Settings.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");

        img_back = findViewById(R.id.img_back);
        layout_languages = findViewById(R.id.layout_languages);

        layout_about = findViewById(R.id.layout_about);
        layout_privacy_policy = findViewById(R.id.layout_privacy_policy);
        layout_terms_condtion = findViewById(R.id.layout_terms_condtion);
        layout_refund = findViewById(R.id.layout_refund);
        shipping_policy = findViewById(R.id.shipping_policy);
        layout_payment_policy = findViewById(R.id.layout_payment_policy);
        layout_logout = findViewById(R.id.layout_logout);
        layout_contact_us = findViewById(R.id.layout_contact_us);
        layout_process_and_logistics = findViewById(R.id.layout_process_and_logistics);
    }

    private void onClick(){
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        layout_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ContactUsActivity.class);
                intent.putExtra("name","Refund Policy");
                startActivity(intent);
            }
        });

        layout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                WebApi webApi = ApiUtils.getClientNew(getApplicationContext());
                webApi.logout().enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        progressDialog.dismiss();
                        if (response.code() == 200){
                            SharedPrefsUtils.setSharedPreferenceString(getApplicationContext(),"login","0");
                            Intent intent = new Intent(Settings.this,SplashScreenActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"Somthing is wrong",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        layout_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ContainActivity.class);
                intent.putExtra("name","Refund Policy");
                startActivity(intent);
            }
        });
        layout_process_and_logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ProcessActivity.class);
                intent.putExtra("name","Process flow to purchase the product");
                startActivity(intent);
            }
        });
        shipping_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ContainActivity.class);
                intent.putExtra("name","Shipping Policy");
                startActivity(intent);
            }
        });


        layout_payment_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ContainActivity.class);
                intent.putExtra("name","Payment Policy");
                startActivity(intent);
            }
        });


        layout_terms_condtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ContainActivity.class);
                intent.putExtra("name","Terms and Condition");
                startActivity(intent);
            }
        });

        layout_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ContainActivity.class);
                intent.putExtra("name","Privacy Policy");
                startActivity(intent);
            }
        });
        layout_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,ContainActivity.class);
                intent.putExtra("name","About us");
                startActivity(intent);
            }
        });

        layout_languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this,LanguageActivity.class);
                startActivity(intent);
            }
        });



    }
}
