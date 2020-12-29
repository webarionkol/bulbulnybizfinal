package com.apps.nybizz.Adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Activities.VendorProfileActivity;
import com.apps.nybizz.Network.WebApi;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.ProductDetailsresponse;
import com.apps.nybizz.Utils.ApiUtils;
import com.apps.nybizz.Utils.CircularProgressBar;
import com.apps.nybizz.VideosResponse;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.ViewHolder>{

    Context context;
    List<ProductDetailsresponse.Data> videoList;
    private WebApi webAPI;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    OnItemClickListener mItemClickListener;
    mItemClickListener_1 mItemClickListener_1;
    mItemClickListener_2 mItemClickListener_2;


    public HomeVideoAdapter(Context context, List<ProductDetailsresponse.Data> videoList) {
        this.context = context;
        this.videoList = videoList;
        webAPI = ApiUtils
                .getClient().create(WebApi.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_videos_item, null);
        itemLayoutView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT));
        //ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return new ViewHolder(itemLayoutView);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //final VideosResponse.Row model = videoList.get(position);

        holder.layout_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mItemClickListener_1 != null)
                    mItemClickListener_1.onItemClick(position);

            }
        });


        holder.txt_name.setText(videoList.get(position).getVendor().toString());
        //holder.txt_name.setText(videoList.get(position).getli().toString());


        holder.cardview_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });

        holder.img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener_2 != null)
                    mItemClickListener_2.onItemClick(position);
            }
        });

      /*  holder.ivPlay.setVisibility(View.GONE);

        // setting data
        holder.tvUsername.setText("@" + model.getUser().getUsername());
        holder.tvDesc.setText(model.getDescription());
        holder.tvLikes.setText(String.valueOf(model.getLikes()));
        holder.tvComments.setText(String.valueOf(model.getComments()));
//        holder.musicName.setText(model.getAudioDetail().getDescription());
        if (model.getHashtags() != null && model.getHashtags().size() > 0) {
            StringBuilder hashTagText = new StringBuilder();
            for (String tag : model.getHashtags()) {
                hashTagText.append("#").append(tag).append(" ");
            }

            // setting hashtag
            holder.hashtags.setText(hashTagText.toString());
        } else {
            holder.hashtags.setVisibility(View.GONE);
        }



        if (videoList.get(position).getIsFriend() > 0) {
            //  holder.ivUser.setImageResource(R.drawable.ic_heart_icon);
            holder.img_plus.setVisibility(View.GONE);

            holder.txt_follow.setText("Unfollow");
        } else {
            holder.img_plus.setVisibility(View.VISIBLE);
            holder.txt_follow.setText("Follow");
        }*/


        // performing clicks

     /*   holder.videoView.setVideoPath(WebAPI.VIDEO_BASE_URL + model.getUrl());
        Log.w("ravi","video stage 1");
        holder.videoView.start();
        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                holder.videoView.start();
            }
        });
        holder.videoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            @Override
            public void onScaleChange(boolean isFullscreen) {
                Log.w("ravi","video stage 2");
            }

            @Override
            public void onPause(MediaPlayer mediaPlayer) {
                //holder.ivPlay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStart(MediaPlayer mediaPlayer) {
                hidePrd(holder.cpb);
            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {

            }

            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {

            }
        });*/

        /*holder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (holder.videoView.isPlaying()) {
                    holder.videoView.pause();
                    holder.ivPlay.setVisibility(View.VISIBLE);
                } else {
                    holder.videoView.start();
                    holder.ivPlay.setVisibility(View.GONE);
                }
                return false;
            }
        });*/

       /* holder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setMessage("Create Duo, Trio or Download video")
                        .setPositiveButton("Make Trio", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new DownloadFile(true, false, model.getThumbnail()).execute(model.getVideoURL());
                            }
                        })
                        .setNegativeButton("Make Duo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new DownloadFile(false, true, model.getThumbnail()).execute(model.getVideoURL());
                            }
                        }).setNeutralButton("Download Video", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DownloadFile(false, false, model.getThumbnail()).execute(model.getVideoURL());
                    }
                }).show();
            }
        });*/


      /*  holder.ivDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // context.startActivity(new Intent(context, DiscoverActivity.class));
            }
        });




        holder.view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName, thumbnail;
        private String folder;
        private String videoFile = "";
        private boolean isDuo, isTrio = false;

        public DownloadFile(boolean isTrio, boolean isDuo, String thumbnail) {
            this.isTrio = isTrio;
            this.isDuo = isDuo;
            this.thumbnail = thumbnail;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Downloading....");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            OutputStream output = null;
            InputStream input = null;

            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                input = new BufferedInputStream(url.openStream(), 8192);

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());

                //External directory path to save file
                folder = Environment.getExternalStorageDirectory() + File.separator + "Meest/";

                //Create androiddeft folder if it does not exist
                File directory = new File(folder);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                videoFile = folder + fileName;

                File requiredFile = new File(videoFile);

                if(requiredFile.exists() && requiredFile.length() > 0) {
                    return "Exists at: " + folder + fileName;
                }

                // Output stream to write file
                output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    // Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded at: " + folder + fileName;

            } catch (Exception e) {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                Log.e("Error: ", e.getMessage());
            }
            return "Video not Found";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded

        }
    }

    private void openOptionDialog(int position, ViewHolder holder) {
        /*LayoutInflater factory = LayoutInflater.from(context);
        final View view = factory.inflate(R.layout.dialog_video_options, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(view);
        LinearLayout viewDownloadVideo = view.findViewById(R.id.viewDownloadVideo);
        viewDownloadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Permission.hasPermissions(((Activity) context), permissions)) {
                    checkFile(WebAPI.VIDEO_BASE_URL + videoList.get(position).getUrl(), videoList.get(position).getUrl(), holder);
                } else {
                    Permission.requestPermissions(((Activity) context), permissions, 101);
                }
                dialog.dismiss();
            }
        });

        dialog.show();*/


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardview_data;
        TextView txt_name,txt_whatsapp,txt_like,txt_share;
        CircleImageView img_profile;
        BottomSheetBehavior sheetBehavior;
        LinearLayout layoutBottomSheet,layout_name;
    /*    CircleImageView ivUser;
        TextView tvUsername, tvLikes, tvComments, tvDesc, hashtags, musicName, txt_follow;
        ImageView ivPlay, ivDiscover, ivLike, img_plus, ivMore, ivMusic;
        LinearLayout viewLike, viewComment, viewShare, view_report;
        RelativeLayout main;
        // CircularProgressBar cpb;

        CircularProgressBar downloadProgressbar;
        ScrollView scrollView;*/
    CircularProgressBar downloadProgressbar;
        RelativeLayout viewProgressbar;
        ImageView img_back;
        public ViewHolder(View view) {
            super(view);
            txt_share = view.findViewById(R.id.txt_share);
            txt_like = view.findViewById(R.id.txt_like);

            txt_whatsapp = view.findViewById(R.id.txt_whatsapp);

            txt_name = view.findViewById(R.id.txt_name);
            img_profile = view.findViewById(R.id.img_profile);


            cardview_data = view.findViewById(R.id.cardview_data);
            layout_name = view.findViewById(R.id.layout_name);
            img_back = view.findViewById(R.id.img_back);

          /*  ivUser = view.findViewById(R.id.ivUser);
            tvUsername = view.findViewById(R.id.tvUsername);
            tvDesc = view.findViewById(R.id.tvDesc);
            ivMore = view.findViewById(R.id.ivMore);
            ivMusic = view.findViewById(R.id.ivMusic);
            txt_follow = view.findViewById(R.id.txt_follow);
            ivPlay = view.findViewById(R.id.ivPlay);
            tvLikes = view.findViewById(R.id.tvLikes);
            tvComments = view.findViewById(R.id.tvComments);
            //videoView = view.findViewById(R.id.videoView);
            viewLike = view.findViewById(R.id.viewLike);
            viewComment = view.findViewById(R.id.viewComment);
            viewShare = view.findViewById(R.id.viewShare);
            hashtags = view.findViewById(R.id.hashtags);
            view_report = view.findViewById(R.id.view_report);
            musicName = view.findViewById(R.id.musicName);
            //cpb = view.findViewById(R.id.cpb);
            main = view.findViewById(R.id.main);

            downloadProgressbar = view.findViewById(R.id.downloadProgressbar);
            ivDiscover = view.findViewById(R.id.ivDiscover);
            ivLike = view.findViewById(R.id.ivLike);
            scrollView = view.findViewById(R.id.scrollView);
            img_plus = view.findViewById(R.id.img_plus);*/

            downloadProgressbar = view.findViewById(R.id.downloadProgressbar);
            viewProgressbar = view.findViewById(R.id.viewProgressbar);

        }
    }



    /*private void openPopUpMenu(final int position, ImageView ivOptions) {
        PopupMenu popup = new PopupMenu(context, ivOptions);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_deactive_video:
                        openConfirmDialog(position);
                        return true;
                }
                return false;
            }
        });
        popup.inflate(R.menu.pop_up_video);
        popup.show();
    }*/

    private void openConfirmDialog(final int selected_position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deactive Video");
        builder.setMessage("Are you sure deactive this video?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                deactiveVideoAPI(selected_position);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deactiveVideoAPI(final int selected_position) {
        /*Call<DeactivateVideo> api = webAPI.deactivateVideo(videoList.get(selected_position).getId(), WebAPI.WS_KEY);
        api.enqueue(new Callback<DeactivateVideo>() {
            @Override
            public void onResponse(Call<DeactivateVideo> call, Response<DeactivateVideo> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        Utils.showToast(context, response.body().getMsg());
                        videoList.remove(selected_position);
                        notifyDataSetChanged();
                    } else {
                        Utils.showErrorToast(context);
                    }
                } else {
                    Utils.showErrorToast(context);
                }
            }

            @Override
            public void onFailure(Call<DeactivateVideo> call, Throwable t) {
                Utils.showErrorToast(context);
            }
        });*/
    }

    private void shareVideo(File videoFile) {
        Uri videoURI = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                ? FileProvider.getUriForFile(context, context.getPackageName(), videoFile)
                : Uri.fromFile(videoFile);
        ShareCompat.IntentBuilder.from(((Activity) context))
                .setStream(videoURI)
                .setType("video/mp4")
                .setChooserTitle("Share video...")
                .startChooser();
    }

    private void hideDownloadProgessbar(ViewHolder holder) {
        holder.viewProgressbar.setVisibility(View.GONE);
    }

    class DownloadFileTask extends AsyncTask<Void, Void, String> {

        String url;
        File file;
        ViewHolder holder;

        public DownloadFileTask(String url, File file, ViewHolder holder) {
            this.url = url;
            this.file = file;
            this.holder = holder;
        }

        @Override
        protected String doInBackground(Void... voids) {
            downloadFile(url, file, holder);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideDownloadProgessbar(holder);
            if (file.exists()) {
                shareVideo(file);
            } else {
                // Utils.showToast(context, "File path is incorrect.", R.color.msg_fail);
            }
        }

    }

    public void downloadFile(String fileURL, File directory, ViewHolder holder) {
        int count;
        try {
            URL url = new URL(fileURL);
            URLConnection conection = url.openConnection();
            conection.connect();

            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            OutputStream output = new FileOutputStream(directory);
            byte data[] = new byte[1024];
            long total = 0;
            int lenghtOfFile = conection.getContentLength();
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
                int progress = (int) ((total * 100) / lenghtOfFile);
                holder.downloadProgressbar.setProgress(progress);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener_1(final mItemClickListener_1 mItemClickListener_1) {
        this.mItemClickListener_1 = mItemClickListener_1;
    }

    public interface mItemClickListener_1 {
        void onItemClick(int position);
    }

    public void setOnItemClickListener_2(final mItemClickListener_2 mItemClickListener_2) {
        this.mItemClickListener_2 = mItemClickListener_2;
    }

    public interface mItemClickListener_2 {
        void onItemClick(int position);
    }

}
