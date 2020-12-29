package com.apps.nybizz.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.ProfileResponse;
import com.apps.nybizz.Response.RegistrationResponse;
import com.apps.nybizz.Utils.ApiUtils;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private EditText edit_name,edit_email,edit_mobile;
    private TextView txt_bod;
    private Button btn_profile;
    private LinearLayout layout_bod;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;


    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private ProgressDialog progressDialog;
    String sex = "Male";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_activity);

        init();
        layout_bod = findViewById(R.id.layout_bod);
        progressDialog = new ProgressDialog(EditProfileActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        layout_bod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    sex = rb.getText().toString();
                    Toast.makeText(EditProfileActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate() {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }



    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void init(){
        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_mobile = findViewById(R.id.edit_mobile);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);

        txt_bod = findViewById(R.id.txt_bod);
        btn_profile = findViewById(R.id.btn_profile);


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_name.getText().toString().isEmpty()){
                    edit_name.setError("Enter name");
                }else if (edit_email.getText().toString().isEmpty()){
                    edit_email.setError("Enter email");
                }else if (edit_mobile.getText().toString().isEmpty()){
                    edit_mobile.setError("Enter mobile");
                }else if (txt_bod.getText().toString().equals("Birthday")){
                    Toast.makeText(getApplicationContext(),"Enter Birthday",Toast.LENGTH_SHORT).show();
                }else {
                    updateProfile();
                }
            }
        });
    }

    private void showDate(int year, int month, int day) {
        txt_bod.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }


    private void updateProfile(){
        progressDialog.show();
        WebApi webApi = ApiUtils.getClientNew(getApplicationContext());
        webApi.profile(edit_name.getText().toString(),edit_email.getText().toString(),edit_mobile.getText().toString(),sex,txt_bod.getText().toString()).enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200 ){
                    Intent intent = new Intent(EditProfileActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Something wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(){
        progressDialog.show();
        WebApi webApi = ApiUtils.getClientNew(getApplicationContext());
        webApi.profile().enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200){
                    if (response.body().getData().getName() != null){
                        edit_name.setText(response.body().getData().getName().toString());
                    }

                    if (response.body().getData().getEmail() != null){
                        edit_email.setText(response.body().getData().getEmail().toString());
                    }

                    if (response.body().getData().getDob() != null){
                        edit_email.setText(response.body().getData().getDob().toString());
                    }

                    if (response.body().getData().getPhone() != null){
                        edit_email.setText(response.body().getData().getPhone().toString());
                    }



                }else {
                    Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
