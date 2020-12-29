package com.apps.nybizz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.R;

import java.util.List;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ViewHolder> {

    List<String> productList;
    HelpAdapter.OnItemClickListener mItemClickListener;
    Context context;

    public HelpAdapter(Context context, List<String> productList) {
        this.context = context;
        this.productList = productList;

    }


    @NonNull
    @Override
    public HelpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_adapter, null);
        HelpAdapter.ViewHolder viewHolder = new HelpAdapter.ViewHolder(itemLayoutView);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HelpAdapter.ViewHolder holder, final int position) {



      /*  holder.txt_like_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });

*/
    }
    public void setOnItemClickListener(final HelpAdapter.OnItemClickListener mItemClickListener) {
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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }


}
