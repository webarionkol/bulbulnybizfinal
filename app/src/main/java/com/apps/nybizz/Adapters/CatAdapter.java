package com.apps.nybizz.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Activities.CatVideoListingActivity;
import com.apps.nybizz.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {

    List<String> productList;
    CatAdapter.OnItemClickListener mItemClickListener;
    Context context;

    public CatAdapter(Context context, List<String> productList) {
        this.context = context;
        this.productList = productList;

    }


    @NonNull
    @Override
    public CatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_adapter, null);
        CatAdapter.ViewHolder viewHolder = new CatAdapter.ViewHolder(itemLayoutView);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CatAdapter.ViewHolder holder, final int position) {


        holder.layout_product_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);

            }
        });

      /*  holder.txt_like_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });

*/
    }
    public void setOnItemClickListener(final CatAdapter.OnItemClickListener mItemClickListener) {
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
        RelativeLayout layout_product_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_product_view = itemView.findViewById(R.id.layout_product_view);
        }

    }


}
