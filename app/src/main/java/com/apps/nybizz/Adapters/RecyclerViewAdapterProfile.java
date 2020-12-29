package com.apps.nybizz.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Profiles.VideoPostFragment;
import com.apps.nybizz.R;

import java.util.List;

public class RecyclerViewAdapterProfile extends RecyclerView.Adapter<RecyclerViewAdapterProfile.ItemViewHolder> {

    RecyclerViewAdapterProfile.OnItemClickListener mItemClickListener;
    List<String> itemList;

    Context context;
    public RecyclerViewAdapterProfile(List<String> itemList,Context context) {
        this.itemList = itemList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewAdapterProfile.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_video_adapter, viewGroup, false);
        return new RecyclerViewAdapterProfile.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterProfile.ItemViewHolder myViewHolder, int position) {
        // myViewHolder.tvItem.setText(itemList.get(position));


        myViewHolder.layout_product_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });


/*        myViewHolder.layout_product_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        myViewHolder.layout_product_view.setVisibility(View.VISIBLE);

        if (position  == 0){

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
        }
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
        ImageView img_1;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_product_view = itemView.findViewById(R.id.layout_product_view);
            layout_product_view_1 = itemView.findViewById(R.id.layout_product_view_1);
            layout_product_view_2 = itemView.findViewById(R.id.layout_product_view_2);
            img_1 = itemView.findViewById(R.id.img_1);

            //  tvItem = itemView.findViewById(R.id.tvItem);
        }
    }

    public void setOnItemClickListener(final RecyclerViewAdapterProfile.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

}
