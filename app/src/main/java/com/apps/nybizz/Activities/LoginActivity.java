package com.apps.nybizz.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.percentlayout.widget.PercentLayoutHelper;
import androidx.percentlayout.widget.PercentRelativeLayout;

import com.apps.nybizz.Network.SharedPrefsUtils;
import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.LoginResponse;
import com.apps.nybizz.Response.RegistrationResponse;
import com.apps.nybizz.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity   implements View.OnClickListener{

    private boolean isSigninScreen = true;
    private TextView tvSignupInvoker;
    private LinearLayout llSignup;
    private TextView tvSigninInvoker;
    private LinearLayout llSignin;
    private Button btnSignup;
    private Button btnSignin;
    LinearLayout llsignin,llsignup;

    private EditText edit_email,edit_password;
    private EditText reg_edit_mobilenumber,reg_edit_password,reg_edit_email;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);
        llSignin.setOnClickListener(this);
        //LinearLayout singnin =(LinearLayout)findViewById(R.id.signin);
        llsignup =(LinearLayout)findViewById(R.id.llSignup);
        llsignup.setOnClickListener(this);
        tvSignupInvoker = (TextView) findViewById(R.id.tvSignupInvoker);
        tvSigninInvoker = (TextView) findViewById(R.id.tvSigninInvoker);

        btnSignup= (Button) findViewById(R.id.btnSignup);
        btnSignin= (Button) findViewById(R.id.btnSignin);

        edit_email = findViewById(R.id.edit_email);

        reg_edit_mobilenumber = findViewById(R.id.reg_edit_mobilenumber);
        reg_edit_password = findViewById(R.id.reg_edit_password);
        reg_edit_email = findViewById(R.id.reg_edit_email);

        edit_password = findViewById(R.id.edit_password);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reg_edit_email.getText().toString().isEmpty()){
                    reg_edit_email.setError("Enter Email");
                }else if (reg_edit_password.getText().toString().isEmpty()){
                    reg_edit_password.setError("Enter Password");
                }else if (reg_edit_mobilenumber.getText().toString().isEmpty()){
                    reg_edit_mobilenumber.setError("Enter Mobile");
                }else {
                    progressDialog.show();
                    WebApi webApi = ApiUtils.getClient().create(WebApi.class);
                    Call<RegistrationResponse> call = webApi.register(reg_edit_email.getText().toString(),reg_edit_mobilenumber.getText().toString(),reg_edit_password.getText().toString(),reg_edit_password.getText().toString());
                    call.enqueue(new Callback<RegistrationResponse>() {
                        @Override
                        public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                            progressDialog.dismiss();
                            if (response.code() == 200){
                               // SharedPrefsUtils.setSharedPreferenceString(getApplicationContext(),"access_token",response.body().getAccessToken());
                               // SharedPrefsUtils.setSharedPreferenceString(getApplicationContext(),"login","1");
                                Intent intent = new Intent(LoginActivity.this,SplashScreenActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_email.getText().toString().isEmpty()){
                    edit_email.setError("Enter Email");
                }else if (edit_password.getText().toString().isEmpty()){
                    edit_password.setError("Enter Password");
                }else {
                    progressDialog.show();
                    WebApi webApi = ApiUtils.getClient().create(WebApi.class);
                    Call<LoginResponse> call = webApi.login(edit_email.getText().toString(),edit_password.getText().toString());
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            progressDialog.dismiss();
                            if (response.code() == 200){
                                SharedPrefsUtils.setSharedPreferenceString(getApplicationContext(),"access_token",response.body().getAccessToken());
                                SharedPrefsUtils.setSharedPreferenceString(getApplicationContext(),"login","1");
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }
        });
        llSignup = (LinearLayout) findViewById(R.id.llSignup);
        llSignin = (LinearLayout) findViewById(R.id.llSignin);

        tvSignupInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = false;
                showSignupForm();
            }
        });

        tvSigninInvoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigninScreen = true;
                showSigninForm();
            }
        });
        showSigninForm();

 /*       btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
                if(isSigninScreen)
                    btnSignup.startAnimation(clockwise);
            }
        });*/
    }

    private void showSignupForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.15f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.85f;
        llSignup.requestLayout();

        tvSignupInvoker.setVisibility(View.GONE);
        tvSigninInvoker.setVisibility(View.VISIBLE);
        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_right_to_left);
        llSignup.startAnimation(translate);

        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_right_to_left);
        btnSignup.startAnimation(clockwise);

    }

    private void showSigninForm() {
        PercentRelativeLayout.LayoutParams paramsLogin = (PercentRelativeLayout.LayoutParams) llSignin.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoLogin = paramsLogin.getPercentLayoutInfo();
        infoLogin.widthPercent = 0.85f;
        llSignin.requestLayout();


        PercentRelativeLayout.LayoutParams paramsSignup = (PercentRelativeLayout.LayoutParams) llSignup.getLayoutParams();
        PercentLayoutHelper.PercentLayoutInfo infoSignup = paramsSignup.getPercentLayoutInfo();
        infoSignup.widthPercent = 0.15f;
        llSignup.requestLayout();

        Animation translate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_left_to_right);
        llSignin.startAnimation(translate);

        tvSignupInvoker.setVisibility(View.VISIBLE);
        tvSigninInvoker.setVisibility(View.GONE);
        Animation clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_left_to_right);
        btnSignin.startAnimation(clockwise);
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.llSignin || v.getId() ==R.id.llSignup){
            // Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
          try {
              InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
              methodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
          }catch (Exception e){
              e.printStackTrace();
          }

        }

    }


}
