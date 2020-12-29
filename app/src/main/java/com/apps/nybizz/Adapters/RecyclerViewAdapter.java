package com.apps.nybizz.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Activities.ProductViewActivity;
import com.apps.nybizz.Network.SharedPrefsUtils;
import com.apps.nybizz.Profiles.VideoPostFragment;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.GetAllVideoResponbse;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {


    List<GetAllVideoResponbse.Datum> itemList;
    String url_demo;

    Context context;
    Activity activity;
    public RecyclerViewAdapter(List<GetAllVideoResponbse.Datum> itemList, Context context,String url_demo,Activity activity) {
        this.itemList = itemList;
        this.activity= activity;
        this.context = context;
        this.url_demo = url_demo;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_video_adapter, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ItemViewHolder myViewHolder, int position) {
       // myViewHolder.tvItem.setText(itemList.get(position));

        myViewHolder.layout_product_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsUtils.setSharedPreferenceString(context,"id",itemList.get(position).getId().toString());
                context.startActivity(new Intent(context, VideoPostFragment.class));
            }
        });
        myViewHolder.layout_product_view.setVisibility(View.VISIBLE);

//        myViewHolder.txt_name.setText(itemList.get(position).getVendor());

/*
        Glide.with(context)
                .load(url_demo + itemList.get(position).getThumbnail())
                .placeholder(R.drawable.profile_loc_ic)
                .error(R.drawable.profile_loc_ic)
                .into(  myViewHolder.img_profile);
*/

        if(itemList.get(position).getThumbnail() != null){



            Glide.with(context)
                    .load(url_demo + itemList.get(position).getThumbnail())
                    .placeholder(R.drawable.profile_loc_ic)
                    .error(R.drawable.profile_loc_ic)
                    .into(  myViewHolder.img_1);

            Log.w("image",url_demo + itemList.get(position).getThumbnail());

        }



        //myViewHolder.img_1.setBackground(context.getResources().getDrawable());
  /*      if (position  == 0){

            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)  myViewHolder.img_1.getLayoutParams();

            params.height = 520;
// existing height is ok as is, no need to edit it
            myViewHolder.img_1.setLayoutParams(params);


            myViewHolder.img_1.setImageDrawable(context.getResources().getDrawable(R.drawable.one));
        }else if (position == 1){
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)  myViewHolder.img_1.getLayoutParams();

            params.height = 480;
// existing height is ok as is, no need to edit it
            myViewHolder.img_1.setLayoutParams(params);

            myViewHolder.img_1.setImageDrawable(context.getResources().getDrawable(R.drawable.two));
        }else if (position == 2){
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)  myViewHolder.img_1.getLayoutParams();

            params.height = 450;
// existing height is ok as is, no need to edit it
            myViewHolder.img_1.setLayoutParams(params);

            myViewHolder.img_1.setImageDrawable(context.getResources().getDrawable(R.drawable.three));
        }else if (position == 3){

            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)  myViewHolder.img_1.getLayoutParams();

            params.height = 540;
// existing height is ok as is, no need to edit it
            myViewHolder.img_1.setLayoutParams(params);

            myViewHolder.img_1.setImageDrawable(context.getResources().getDrawable(R.drawable.four));
        }else if (position == 4){

            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)  myViewHolder.img_1.getLayoutParams();

            params.height = 490;
// existing height is ok as is, no need to edit it
            myViewHolder.img_1.setLayoutParams(params);

            myViewHolder.img_1.setImageDrawable(context.getResources().getDrawable(R.drawable.five));
        }*/
     /*   if (position%2==0){
            myViewHolder.layout_product_view.setVisibility(View.VISIBLE);
            myViewHolder.layout_product_view_1.setVisibility(View.GONE);
        }else {
            myViewHolder.layout_product_view.setVisibility(View.GONE);
            myViewHolder.layout_product_view_1.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public int getItemCount() {
        return  itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

       // TextView tvItem;
       RelativeLayout layout_product_view,layout_product_view_1,layout_product_view_2;
       TextView txt_name;
       ImageView img_1,img_profile;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_product_view = itemView.findViewById(R.id.layout_product_view);
            layout_product_view_1 = itemView.findViewById(R.id.layout_product_view_1);
            layout_product_view_2 = itemView.findViewById(R.id.layout_product_view_2);
            img_profile = itemView.findViewById(R.id.img_profile);
            txt_name = itemView.findViewById(R.id.txt_name);
            img_1 = itemView.findViewById(R.id.img_1);

          //  tvItem = itemView.findViewById(R.id.tvItem);
        }
    }
}
