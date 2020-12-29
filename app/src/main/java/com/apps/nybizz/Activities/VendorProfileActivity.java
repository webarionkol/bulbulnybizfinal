package com.apps.nybizz.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.VideoProfile;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.apps.nybizz.Adapters.HomeAdapters;
import com.apps.nybizz.Adapters.RecyclerViewAdapter;
import com.apps.nybizz.Adapters.RecyclerViewAdapterProfile;
import com.apps.nybizz.Adapters.RefundedOrderAdapter;
import com.apps.nybizz.Network.SharedPrefsUtils;
import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.Profiles.VideoPostFragment;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.VendorDetailsResponse;
import com.apps.nybizz.Utils.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorProfileActivity extends AppCompatActivity {
    private ImageView img_view;
    private ProgressDialog progressDialog;

    TextView experiencebtn,personalinfobtn,txt_name,txt_businessname,txt_total_like,txt_total_view,txt_bio_data;
    LinearLayout layout_bio,layout_videos;
    RecyclerViewAdapterProfile recyclerViewAdapter;
    RecyclerView recycle_view_videos;
    ArrayList<String> arrayList1 = new ArrayList<>();
    private final int[] pics = {R.drawable.img01, R.drawable.img02, R.drawable.img01, R.drawable.img02, R.drawable.img01};
    int[] animationList = {R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down};
    String v_id;
    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_profile);
        init();
        img_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        v_id = getIntent().getStringExtra("v_id");

    }

    public void init(){
        txt_name = findViewById(R
        .id.txt_name);
        txt_bio_data = findViewById(R.id.txt_bio_data);
        txt_total_like = findViewById(R.id.txt_total_like);
        txt_total_view = findViewById(R.id.txt_total_view);
        txt_businessname = findViewById(R.id.txt_businessname);
        img_view = findViewById(R.id.img_view);
        layout_videos = findViewById(R.id.layout_videos);
        recycle_view_videos = findViewById(R.id.recycle_view_videos);

        personalinfobtn = findViewById(R.id.personalinfobtn);
        experiencebtn = findViewById(R.id.experiencebtn);
        layout_bio = findViewById(R.id.layout_bio);

        layout_bio.setVisibility(View.VISIBLE);
        layout_videos.setVisibility(View.GONE);


        experiencebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experiencebtn.setTextColor(getApplicationContext().getResources().getColor(R.color.blue));

                layout_bio.setVisibility(View.GONE);
                layout_videos.setVisibility(View.VISIBLE);

                personalinfobtn.setTextColor(getApplicationContext().getResources().getColor(R.color.lightgrey));

            }
        });

        personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalinfobtn.setTextColor(getApplicationContext().getResources().getColor(R.color.blue));

                layout_bio.setVisibility(View.VISIBLE);
                layout_videos.setVisibility(View.GONE);

                experiencebtn.setTextColor(getApplicationContext().getResources().getColor(R.color.lightgrey));

            }
        });

        runAnimationAgain();


        progressDialog = new ProgressDialog(VendorProfileActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");

        getApi();
    }



    private void runAnimationAgain() {

        for (int i = 0; i < 5; i++) {
            arrayList1.add("Item " + i);
        }



        recyclerViewAdapter = new RecyclerViewAdapterProfile(arrayList1,getApplicationContext());




        //  RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);

        RecyclerView.LayoutManager manager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


        recycle_view_videos.setLayoutManager(manager);
//        recycle_view.setItemAnimator(new DefaultItemAnimator());




        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getApplicationContext(), animationList[i]);

        recycle_view_videos.setLayoutAnimation(controller);
        recycle_view_videos.setAdapter(recyclerViewAdapter);
        recycle_view_videos.scheduleLayoutAnimation();


        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapterProfile.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(VendorProfileActivity.this,VideoPostFragment.class);
                startActivity(intent);

            }
        });

    }


    private void getApi(){

        progressDialog.show();
        WebApi webApi = ApiUtils.getClientNew(getApplicationContext());
        webApi.getVendorDetails(SharedPrefsUtils.getSharedPreferenceString(getApplicationContext(),"v_id")).enqueue(new Callback<VendorDetailsResponse>() {
            @Override
            public void onResponse(Call<VendorDetailsResponse> call, Response<VendorDetailsResponse> response) {
                progressDialog.dismiss();
                if (response.body().getStatus() == 1){
                    txt_name.setText(response.body().getData().getVendorName());
                    txt_businessname.setText(response.body().getData().getBusinessName());

                    if (response.body().getData().getTotalLikes() != null){
                        txt_total_like.setText(response.body().getData().getTotalLikes().toString());
                    }

                    if (response.body().getData().getTotalViews() != null){
                        txt_total_view.setText(response.body().getData().getTotalViews().toString());
                    }

                    if (response.body().getData().getAboutMe() != null){
                        txt_bio_data.setText(response.body().getData().getAboutMe().toString());
                    }
                }else {
                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VendorDetailsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}
