package com.apps.nybizz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<String> productList;
    SearchAdapter.OnItemClickListener mItemClickListener;
    Context context;

    public SearchAdapter(Context context, List<String> productList) {
        this.context = context;
        this.productList = productList;

    }


    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_adapter, null);
        SearchAdapter.ViewHolder viewHolder = new SearchAdapter.ViewHolder(itemLayoutView);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchAdapter.ViewHolder holder, final int position) {



      /*  holder.txt_like_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });

*/
    }
    public void setOnItemClickListener(final SearchAdapter.OnItemClickListener mItemClickListener) {
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
