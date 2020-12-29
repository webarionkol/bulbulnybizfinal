package com.apps.nybizz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.R;

import java.util.List;

public class ConfirmOrderAdapter extends RecyclerView.Adapter<ConfirmOrderAdapter.ViewHolder> {

    List<String> productList;
    ConfirmOrderAdapter.OnItemClickListener mItemClickListener;
    Context context;

    public ConfirmOrderAdapter(Context context, List<String> productList) {
        this.context = context;
        this.productList = productList;

    }


    @NonNull
    @Override
    public ConfirmOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_adpter, null);
        ConfirmOrderAdapter.ViewHolder viewHolder = new ConfirmOrderAdapter.ViewHolder(itemLayoutView);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ConfirmOrderAdapter.ViewHolder holder, final int position) {



      /*  holder.txt_like_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });

*/

        holder.layout_qty.setVisibility(View.INVISIBLE);
    }
    public void setOnItemClickListener(final ConfirmOrderAdapter.OnItemClickListener mItemClickListener) {
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
    LinearLayout layout_qty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_qty = itemView.findViewById(R.id.layout_qty);
        }

    }


}
