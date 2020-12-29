package com.apps.nybizz.Profiles;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.apps.nybizz.Activities.ProductViewActivity;
import com.apps.nybizz.Activities.VendorProfileActivity;
import com.apps.nybizz.Adapters.HomeVideoAdapter;
import com.apps.nybizz.Network.SharedPrefsUtils;
import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.ProductDetailsresponse;
import com.apps.nybizz.Utils.ApiUtils;
import com.apps.nybizz.VideosResponse;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoPostFragment extends AppCompatActivity implements Player.EventListener {

    int swipe_count = 0;
    Activity mActivity;
    SwipeRefreshLayout swiperefresh;
    LinearLayoutManager layoutManager;
    WebApi webAPI;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    int page = 1, total_record, total_page;
    String url;
    List<ProductDetailsresponse.Data> videoList = new ArrayList<>();
 //   ArrayList<VideosResponse.Row> data_list = new ArrayList<>();
    //    @BindView(R.id.viewMain)
//    LinearLayout viewMain;
    int currentPage = -1;
    String id;
    RecyclerView rvHomeVideos;
    HomeVideoAdapter homeVideoAdapter;
    ProgressBar p_bar;
    RelativeLayout txt_for_you;

    ImageView img_categories;
    LinearLayout img_search;
    RelativeLayout txt_for_you_explore;
    TextView txt_for_you_1_explore, txt_for_you_1;
    RelativeLayout layout_back;
    ProgressDialog progressDialog;

    public VideoPostFragment() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_fragment);
        id = SharedPrefsUtils.getSharedPreferenceString(getApplicationContext(),"id");

        rvHomeVideos =findViewById(R.id.rvHomeVideos);

        getVideoDetails();
        p_bar =findViewById(R.id.p_bar);
        txt_for_you_1_explore =findViewById(R.id.txt_for_you_1_explore);
        txt_for_you_1 =findViewById(R.id.txt_for_you_1);
        layout_back =findViewById(R.id.layout_back);
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* SharedPrefreances.setSharedPreferenceString(getApplicationContext(), "VideoPostActive", "0");
                ((MainActivity) getApplicationContext()).onBackPressed();*/

              /*  Flipfragment flipfragment = new Flipfragment();
                flipfragment.findViews();
                flipfragment.animation();*/
                // getApplicationContext().finish();

                privious_player.stop();
                //((MainActivity)getApplicationContext()).pushFragment(new Flipfragment(),"flip");

                // findViews();
                // animation();
            }
        });
        img_search =findViewById(R.id.img_search);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txt_for_you =findViewById(R.id.txt_for_you);
//        txt_following =findViewById(R.id.txt_following);

        img_categories =findViewById(R.id.img_categories);


        txt_for_you_explore =findViewById(R.id.txt_for_you_explore);
        txt_for_you_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        txt_for_you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getVideoAPI();
            }
        });


        rvHomeVideos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //here we find the current item number
                final int scrollOffset = recyclerView.computeVerticalScrollOffset();
                final int height = recyclerView.getHeight();
                int page_no = scrollOffset / height;

                if (page_no != currentPage) {
                    currentPage = page_no;

                    Release_Privious_Player();
                    Set_Player(currentPage);

                }
            }
        });

        swiperefresh =findViewById(R.id.swiperefresh);
        swiperefresh.setProgressViewOffset(false, 0, 200);

        swiperefresh.setColorSchemeResources(R.color.black);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                txt_for_you_1_explore.setTextColor(getApplicationContext().getResources().getColor(R.color.white));
                txt_for_you_1.setTextColor(getApplicationContext().getResources().getColor(R.color.text_gray));
//                txt_following_1.setTextColor(getApplicationContext().getResources().getColor(R.color.gray));


                currentPage = -1;
                getVideoAPI();
            }
        });



    }

  
    private void initUI() {
        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        webAPI = ApiUtils.getClient().create(WebApi.class);
        homeVideoAdapter = new HomeVideoAdapter(mActivity, videoList);
//        rvHomeVideos.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false));
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);




        rvHomeVideos.setLayoutManager(layoutManager);
        rvHomeVideos.setHasFixedSize(false);
        homeVideoAdapter.setHasStableIds(true);
        rvHomeVideos.setAdapter(homeVideoAdapter);
        SnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rvHomeVideos);

     /*   rvHomeVideos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                homeVideoAdapter.notifyDataSetChanged();
                return false;
            }
        });*/


        homeVideoAdapter.setOnItemClickListener(new HomeVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BottomSheetFragmentAddtoCard bottomSheetFragment = new BottomSheetFragmentAddtoCard();
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });

        homeVideoAdapter.setOnItemClickListener_1(new HomeVideoAdapter.mItemClickListener_1() {
            @Override
            public void onItemClick(int position) {
                SharedPrefsUtils.setSharedPreferenceString(getApplicationContext(),"v_id",videoList.get(position).getVendor().toString());
                Intent intent = new Intent(VideoPostFragment.this,VendorProfileActivity.class);
                intent.putExtra("v_id",videoList.get(position).getId());
                startActivity(intent);
            }
        });




        homeVideoAdapter.setOnItemClickListener_2(new HomeVideoAdapter.mItemClickListener_2() {
            @Override
            public void onItemClick(int position) {
                finish();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        getVideoAPI();
    }




    private void getVideoAPI() {

        //showPrd();
//        Call<GetVideo> api;
        //String type = SharedPrefsUtils.getSharedPreferenceString(getApplicationContext(),"videi_type");

//        api = webAPI.getVideo(String.valueOf(page), "",
//                "", "", "ht1PY/msDN69L16hj3ARuE0XIA+ekntA",
//                "",
//                "3",
//                "followers");

/*        Map<String, String> header = new HashMap<>();
        header.put("x-token",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJJZCI6IjFkMzYxYzc5LTZlM2UtNDUxNy04ODQzLTdlZTZhYjkzOWJiYSIsImVtYWlsIjoiIiwiY3JlYXRlZEF0IjoiMjAyMC0xMS0yNFQwMzoxNDo1MC40MDBaIn0sImlhdCI6MTYwNjE4NzY5MH0.LRekY3QBtcmTmYOBUZqzkC1djYVj5KRW9B2FtW1wG7g");
        Call<VideosResponse> call = webAPI.getAllVideos(header);

       *//* Call<GetVideo> api = webAPI.getVideo(String.valueOf(page), "",
                "", "", WebAPI.WS_KEY,
                SharedPrefsUtils.getSharedPreferenceString(mActivity,SharedPrefsUtils.USER_ID),
                SharedPrefsUtils.getSharedPreferenceString(getApplicationContext(),"lan_id"));*//*
        //Utils.showLog("==== getVideoAPI " + page + " " + WebAPI.WS_KEY);
        call.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                swiperefresh.setRefreshing(false);
                if (response.body() != null) {

                    if (response.body().getSuccess()) {
                        if (page == 1) {
                            videoList.clear();
                        }
                        total_record = Integer.parseInt(String.valueOf(response.body().getData().getCount()));
                        int tempTotal = total_record % 30;
                        if (tempTotal != 0) {
                            total_page = (total_record / 30) + 1;
                        } else {
                            total_page = (total_record / 30);
                        }
                        videoList.addAll(response.body().getData().getRows());
                        //homeVideoPagerAdapter.notifyDataSetChanged();
                        homeVideoAdapter.notifyDataSetChanged();

                        data_list.addAll(response.body().getData().getRows());
                    } else {
                    //    Utilss.showToast(getApplicationContext(), "Server Error", R.color.msg_fail);
                    }
                } else {
                  //  Utilss.showErrorToast(mActivity);
                }
                //hidePrd();
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {
                swiperefresh.setRefreshing(false);
               // Utilss.showErrorToast(mActivity);
                //hidePrd();
            }
        });*/
    }


    /*   private void hidePrd() {
           if (cpb != null) {
               cpb.setVisibility(View.GONE);
               ((CircularProgressDrawable) cpb.getIndeterminateDrawable()).progressiveStop();
           }
       }

       private void showPrd() {
           if (cpb != null) {
               cpb.setVisibility(View.VISIBLE);
               ((CircularProgressDrawable) cpb.getIndeterminateDrawable()).start();
           }
       }
   */
    // when we swipe for another video this will relaese the privious player
    SimpleExoPlayer privious_player;

    public void Release_Privious_Player() {
        if (privious_player != null) {
            privious_player.removeListener(this);
            privious_player.release();

        }
    }


    public void Set_Player(final int currentPage) {

        final ProductDetailsresponse.Data item = videoList.get(currentPage);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        final SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), trackSelector);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(),
                Util.getUserAgent(getApplicationContext(), "tunemist"));

        // for mp4 media
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url + videoList.get(currentPage).getVideo()));

        // for hls media
//        MediaSource mediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(item.getVideoURL()));

        Log.v("harsh", "video url == " + url + item.getVideo());

        player.prepare(mediaSource);
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        player.addListener(this);


        View layout = layoutManager.findViewByPosition(currentPage);
        final PlayerView playerView = layout.findViewById(R.id.videoView);


        playerView.setPlayer(player);
        playerView.setUseController(false);

        player.setPlayWhenReady(true);
        privious_player = player;


        // final RelativeLayout mainlayout = layout.findViewById(R.id.mainlayout);
        playerView.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    super.onFling(e1, e2, velocityX, velocityY);
                    float deltaX = e1.getX() - e2.getX();
                    float deltaXAbs = Math.abs(deltaX);
                    // Only when swipe distance between minimal and maximal distance value then we treat it as effective swipe
                    if ((deltaXAbs > 100) && (deltaXAbs < 1000)) {
                        if (deltaX > 0) {
                            //OpenProfile(item,true);
                        }
                    }


                    return true;
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    super.onSingleTapUp(e);
                    if (!player.getPlayWhenReady()) {
                        privious_player.setPlayWhenReady(true);
                    } else {
                        privious_player.setPlayWhenReady(false);
                    }


                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);
                    //Show_video_option(item);

                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {

                    if (!player.getPlayWhenReady()) {
                        privious_player.setPlayWhenReady(true);
                    }


//                    if(SharedPrefsUtils.getSharedPreferenceBoolean(getApplicationContext(), SharedPrefsUtils.IS_LOGIN, false)) {
//                        Show_heart_on_DoubleTap(item, mainlayout, e);
//                        Like_Video(currentPage, item);
//                    }else {
//                        Toast.makeText(getApplicationContext(), "Please Login into app", Toast.LENGTH_SHORT).show();
//                    }
                    return super.onDoubleTap(e);

                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        //TextView desc_txt=layout.findViewById(R.id.desc_txt);
     /*   HashTagHelper.Creator.create(getApplicationContext().getResources().getColor(R.color.maincolor), new HashTagHelper.OnHashTagClickListener() {
            @Override
            public void onHashTagClicked(String hashTag) {

                onPause();
                OpenHashtag(hashTag);

            }
        }).handle(desc_txt);*/



/*        LinearLayout soundimage = (LinearLayout)layout.findViewById(R.id.sound_image_layout);
        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.d_clockwise_rotation);
        soundimage.startAnimation(aniRotate);*/


/*        if(SharedPrefsUtils.getSharedPreferenceBoolean(getApplicationContext(), SharedPrefsUtils.IS_LOGIN, false))
            // Functions.Call_Api_For_update_view(getApplicationContext(),item.video_id);


            swipe_count++;
        if(swipe_count>4){
            //Show_add();
            swipe_count=0;
        }*/

    }




    @Override
    public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == Player.STATE_BUFFERING) {
            p_bar.setVisibility(View.VISIBLE);
        } else if (playbackState == Player.STATE_READY) {
            p_bar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public void onStop() {
        super.onStop();
        //Toast.makeText(getApplicationContext(),"ge",Toast.LENGTH_SHORT).show();
        if (privious_player != null) {
            privious_player.stop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //Toast.makeText(getApplicationContext(),"demo",Toast.LENGTH_SHORT).show();
        if (privious_player != null) {
            privious_player.stop();
        }
    }

    public void Delete_file_no_watermark(String item) {
        File file = new File(Environment.getExternalStorageDirectory() + "/tunemist/" + item + "no_watermark" + ".mp4");
        if (file.exists()) {
            file.delete();
        }
    }

    public void Scan_file(String item) {
        MediaScannerConnection.scanFile(getApplicationContext(),
                new String[]{Environment.getExternalStorageDirectory() + "/tunemist/" + item + ".mp4"},
                null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {

                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);


                    }
                });
    }


    private void shareVideo(File videoFile) {
        Uri videoURI = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                ? FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName(), videoFile)
                : Uri.fromFile(videoFile);
        ShareCompat.IntentBuilder.from(((Activity) getApplicationContext()))
                .setStream(videoURI)
                .setType("video/mp4")
                .setChooserTitle("Share video...")
                .startChooser();

        Toast.makeText(getApplicationContext(), "demo5", Toast.LENGTH_LONG).show();

    }



    public interface BackToFlip{



    }


    private void getVideoDetails(){
        progressDialog = new ProgressDialog(VideoPostFragment.this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
        WebApi webApi = ApiUtils.getClientNew(getApplicationContext());
        webApi.getproductDetails(id).enqueue(new Callback<ProductDetailsresponse>() {
            @Override
            public void onResponse(Call<ProductDetailsresponse> call, Response<ProductDetailsresponse> response) {
                progressDialog.dismiss();
                if (response.body().getStatus() == 200){
                    videoList.add(response.body().getData());
                    url = response.body().getUrl();
                    initUI();
                }else {
                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductDetailsresponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();

            }
        });
    }


   /* private void init(){
        cardview_data = findViewById(R.id.cardview_data);
        layout_name = findViewById(R.id.layout_name);





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
                Intent intent = new Intent(VideoPostFragment.this, VendorProfileActivity.class);
                startActivity(intent);
            }
        });
    }*/

}
