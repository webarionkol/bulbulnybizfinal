package com.apps.nybizz.Profiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Adapters.NotificationAdapter;
import com.apps.nybizz.Adapters.OrderAdapter;
import com.apps.nybizz.R;

import java.util.ArrayList;

public class ORderFragment extends Fragment {

    OrderAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>();
    RecyclerView recycle_view;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_fragment, null);
        init();

        return view;
    }

    private void init(){
        recycle_view = view.findViewById(R.id.recycle_view);

        for (int i = 0; i < 4; i++) {
            arrayList.add("Item " + i);
        }
        adapter = new OrderAdapter(getActivity(),arrayList);


        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(HorizontalLayout112);
        //   recycle_view.setItemAnimator(new DefaultItemAnimator());

        recycle_view.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }


}
