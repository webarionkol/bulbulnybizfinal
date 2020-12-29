package com.apps.nybizz.Profiles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.apps.nybizz.Activities.NotificationActivity;
import com.apps.nybizz.Activities.SearchActivity;
import com.apps.nybizz.Activities.VendorProfileActivity;
import com.apps.nybizz.Adapters.HomeAdapters;
import com.apps.nybizz.Adapters.RecyclerViewAdapter;
import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.GetAllVideoResponbse;
import com.apps.nybizz.Response.HomeResponse;
import com.apps.nybizz.Utils.ApiUtils;
import com.apps.nybizz.data.ModelSmoolider;
import com.apps.nybizz.data.SmooliderAdapter;

import com.astritveliu.boom.Boom;
import com.av.smoothviewpager.Smoolider.SmoothViewpager;
import com.av.smoothviewpager.utils.Txt_Factory;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.av.smoothviewpager.utils.Smoolider_Utils.autoplay_viewpager;
import static com.av.smoothviewpager.utils.Smoolider_Utils.openWebPage;
import static com.av.smoothviewpager.utils.Smoolider_Utils.stop_autoplay_ViewPager;

public class HomeFragment extends Fragment {

    private HomeAdapters adapters;
    private RecyclerView recycle_view;
    private ArrayList<HomeResponse.Category> arrayList = new ArrayList<>();
    private ArrayList<GetAllVideoResponbse.Datum> videos = new ArrayList<>();
    private RecyclerView recycle_view_videos;
    View view;
    private ProgressDialog progressDialog;
    String url_demo;
    private final int[] pics = {R.drawable.img01, R.drawable.img02, R.drawable.img01, R.drawable.img02, R.drawable.img01};


    private final String[] car_hp = {"S 65", "400 4Matic", "GT 63S", "G 63", "C 63S"};
    private final String[] position = {"1 / 5", "2 / 5", "3 / 5", "4 / 5", "5 / 5"};

    private TextSwitcher txt_swithcher_position;
    private TextView txt_title;
    private TextView txt_subtitle;
    private boolean is_autoplay = false;
    private int currentPosition;
    private SmoothViewpager viewPager;
    private ImageView img_github;
    private LottieAnimationView animationView;
    private List<ModelSmoolider> feedItemList;
    private RelativeLayout layout_vendor_click;
    private ImageView img_notification;
    private ImageView img_search;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<String> arrayList1 = new ArrayList<>();


    int[] animationList = {R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down, R.anim.layout_animation_up_to_down};

    int i = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, null);
        init();

        slider();
        onClick();
        return view;
    }

    private void init(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        recycle_view = view.findViewById(R.id.recycle_view);
        adapters = new HomeAdapters(getActivity(),arrayList);
        layout_vendor_click = view.findViewById(R.id.layout_vendor_click);
        recycle_view_videos = view.findViewById(R.id.recycle_view_videos);
        img_notification = view.findViewById(R.id.img_notification);

        img_search = view.findViewById(R.id.img_search);
        populateData();
        initAdapter();
        getAllVideo();
        runAnimationAgain();
        data();
    }

    private void populateData() {

        for (int i = 0; i < 5; i++) {
            arrayList1.add("Item " + i);
        }
    }

    private void initAdapter() {

    }




    private void onClick(){
        layout_vendor_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VendorProfileActivity.class);
                startActivity(intent);
            }
        });


        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void slider(){

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        init_widgets();
        generate_items();

        viewPager.setAdapter( new SmooliderAdapter(feedItemList,getActivity()));

        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manage_autoplay();
                Snackbar.make(view, " ▶ Autoplay :  "+is_autoplay, Snackbar.LENGTH_LONG).show();
            }
        });


        img_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage(getActivity(),"https://github.com/astrit-veliu");
            }
        });



        //use this method if you want to interact with other widgets
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {

            }
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(final int position) {
                // handle operations with current page
                manage_widgets_on_swipe(position);

            }
        });


    }

    private void data(){


            getHome();



    }


    private void init_widgets() {

        //getActivity().getSupportActionBar().hide();

        viewPager = (SmoothViewpager)view.findViewById(R.id.smoolider);
        img_github = (ImageView)view.findViewById(R.id.img_github);
        txt_swithcher_position = (TextSwitcher)view.findViewById(R.id.txt_swithcher_position);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_subtitle = (TextView) view.findViewById(R.id.txt_subtitle);
        animationView = (LottieAnimationView) view.findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.autoplay_animation);

       // new Boom(txt_title);
       // new Boom(txt_subtitle);

        txt_swithcher_position.setFactory(new Txt_Factory(R.style.CustomTextView, true,getActivity()));
        txt_swithcher_position.setCurrentText(position[0]);
        //txt_title.setText("");

        manage_widgets_on_swipe(0);
    }

    private void generate_items(){
        feedItemList = new ArrayList<>();
        for(int i=0;i<pics.length;i++){
            ModelSmoolider gift = new ModelSmoolider();
            gift.setImage(pics[i]);
            gift.setName(1);
            gift.setUrl(1);
            gift.setDescription(1);
            gift.setPoints(car_hp[i]);

            feedItemList.add(gift);
        }
    }

    private void manage_widgets_on_swipe(int pos) {
        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        txt_swithcher_position.setInAnimation(getActivity(), animH[0]);
        txt_swithcher_position.setOutAnimation(getActivity(), animH[1]);
        txt_swithcher_position.setText(position[pos % position.length]);

        txt_title.setVisibility(View.GONE);
        txt_title.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
        txt_title.setVisibility(View.GONE);
        txt_title.setText("1");

        txt_subtitle.setVisibility(View.GONE);
        txt_subtitle.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
        txt_subtitle.setVisibility(View.GONE);
        txt_subtitle.setText("1");

        currentPosition = pos;
    }


    private void manage_autoplay(){
        animationView.playAnimation();
        if(is_autoplay){
            is_autoplay = false;
            stop_autoplay_ViewPager();
        } else {
            is_autoplay = true;
            autoplay_viewpager(viewPager,feedItemList.size());
        }


    }


    private void runAnimationAgain() {







      //  RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);

       /* RecyclerView.LayoutManager manager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


        recycle_view_videos.setLayoutManager(manager);
//        recycle_view.setItemAnimator(new DefaultItemAnimator());




        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), animationList[i]);

        recycle_view_videos.setLayoutAnimation(controller);
        recycle_view_videos.setAdapter(recyclerViewAdapter);
        recycle_view_videos.scheduleLayoutAnimation();*/

    }


    private void getAllVideo(){

        progressDialog.show();
        WebApi webApi = ApiUtils.getClientNew(getActivity());
        webApi.getproductlist().enqueue(new Callback<GetAllVideoResponbse>() {
            @Override
            public void onResponse(Call<GetAllVideoResponbse> call, Response<GetAllVideoResponbse> response) {
                progressDialog.dismiss();
                if (response.body().getStatus() == 1){
                    for (int i =0;i < response.body().getData().size();i++){
                        videos.add(response.body().getData().get(i));
                    }
                    url_demo = response.body().getUrl();

                    recyclerViewAdapter = new RecyclerViewAdapter(videos,getActivity(),url_demo,getActivity());

                    RecyclerView.LayoutManager manager =
                            new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


                    recycle_view_videos.setLayoutManager(manager);
//        recycle_view.setItemAnimator(new DefaultItemAnimator());




                    final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), animationList[i]);

                    recycle_view_videos.setLayoutAnimation(controller);
                    recycle_view_videos.setAdapter(recyclerViewAdapter);
                    recycle_view_videos.scheduleLayoutAnimation();
                   // recyclerViewAdapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAllVideoResponbse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Server Error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getHome(){
        progressDialog.show();
        WebApi webApi = ApiUtils.getClientNew(getActivity());
        webApi.gethome().enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response.body().getStatus() == 1){
                    for (int i =0; i < response.body().getData().getCategories().size();i++){
                        arrayList.add(response.body().getData().getCategories().get(i));
                    }

                    LinearLayoutManager HorizontalLayout112
                            = new LinearLayoutManager(
                            getActivity(),
                            LinearLayoutManager.HORIZONTAL,
                            false);
                    //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
                    recycle_view.setLayoutManager(HorizontalLayout112);
                    recycle_view.setItemAnimator(new DefaultItemAnimator());
                   // adapters.notifyDataSetChanged();
                    recycle_view.setAdapter(adapters);
                    adapters.notifyDataSetChanged();

                }else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),"Server Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Server Error",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
