package com.apps.nybizz.Profiles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.apps.nybizz.Activities.EditProfileActivity;
import com.apps.nybizz.Activities.LanguageActivity;
import com.apps.nybizz.Activities.MyOrderActivity;
import com.apps.nybizz.Activities.NotificationActivity;
import com.apps.nybizz.Activities.Settings;
import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.ProfileResponse;
import com.apps.nybizz.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private RelativeLayout layout_notification,layout_settings;
    View view;
    Button btn_button;
    private LinearLayout layout_order_all,layout_order_r;
    private CardView cardview_data;

    private TextView txt_name,txt_email;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment, null);

        init();
        onclick();
        return view;
    }

    private void init(){

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);


        layout_notification = view.findViewById(R.id.layout_notification);
        layout_order_all = view.findViewById(R.id.layout_order_all);

        layout_order_r = view.findViewById(R.id.layout_order_r);
        txt_name = view.findViewById(R.id.txt_name);
        txt_email = view.findViewById(R.id.txt_email);

        cardview_data = view.findViewById(R.id.cardview_data);
        cardview_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileNumber = "1234567890";
                String message = "Help me";
                boolean installed = appInstalledOrNot("com.whatsapp");
                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+mobileNumber + "&text="+message));
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "Whats app not installed on your device", Toast.LENGTH_SHORT).show();
                }
            }
        });


        layout_settings = view.findViewById(R
        .id.layout_settings);

        btn_button = view.findViewById(R.id.btn_button);


        progressDialog.show();
        WebApi webApi = ApiUtils.getClientNew(getActivity());
        webApi.profile().enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200){
                    if (response.body().getData().getName() != null){
                        txt_name.setText(response.body().getData().getName().toString());
                    }

                    txt_email.setText(response.body().getData().getEmail().toString());
                }else {
                    Toast.makeText(getContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Server Error",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private boolean appInstalledOrNot(String url){
        PackageManager packageManager =getActivity().getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }


    private void onclick(){
        layout_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        btn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        layout_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Settings.class);
                startActivity(intent);
            }
        });


        layout_order_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("order","1");
                startActivity(intent);
            }
        });


        layout_order_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                intent.putExtra("order","2");
                startActivity(intent);
            }
        });
    }

}
