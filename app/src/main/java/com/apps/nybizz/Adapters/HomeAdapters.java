package com.apps.nybizz.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Activities.CatActivity;
import com.apps.nybizz.R;
import com.apps.nybizz.Response.HomeResponse;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapters  extends RecyclerView.Adapter<HomeAdapters.ViewHolder> {

    List<HomeResponse.Category> productList;
    OnItemClickListener mItemClickListener;
    Context context;

    public HomeAdapters(Context context, List<HomeResponse.Category> productList) {
        this.context = context;
        this.productList = productList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {



      /*  holder.txt_like_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });

*/

            holder.txt_name.setText(productList.get(position).getCategory());
           // holder.img_pro.setImageDrawable(context.getResources().getDrawable(R.drawable.cat_1));
        Glide.with(context)
                .load(" https://nybizz.clientdemo.cf" + productList.get(position).getImage())
                .placeholder(R.drawable.profile_loc_ic)
                .error(R.drawable.profile_loc_ic)
                .into(  holder.img_pro);


        holder.layout_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CatActivity.class));
            }
        });

    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        CircleImageView img_pro;
        RelativeLayout layout_main;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            img_pro = itemView.findViewById(R.id.img_pro);
            layout_main = itemView.findViewById(R.id.layout_main);
        }

    }


}
