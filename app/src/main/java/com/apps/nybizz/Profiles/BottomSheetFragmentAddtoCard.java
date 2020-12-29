package com.apps.nybizz.Profiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Adapters.ColorAdapter;
import com.apps.nybizz.Adapters.SizeAdapter;
import com.apps.nybizz.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BottomSheetFragmentAddtoCard extends BottomSheetDialogFragment {

    SizeAdapter sizeAdapter;
    ColorAdapter colorAdapter;
    RecyclerView recycle_view_size,recycle_view_color;
    ArrayList<String> arrayList = new ArrayList<>();

    View view;
    public BottomSheetFragmentAddtoCard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.bottom_sheet_product_view, null);

        // Inflate the layout for this fragmentinflater.inflate(R.layout.bottom_sheet, container, false);
        View view = inflater.inflate(R.layout.bottom_sheet_product_view, container, false);
       // view = inflater.inflate(R.layout.bottom_sheet_product_view, null);

        recycle_view_size = view.findViewById(R.id.recycle_view_size);
        recycle_view_color = view.findViewById(R.id.recycle_view_color);

        for (int i =0;i < 5;i++){
            arrayList.add("");
        }
        sizeAdapter = new SizeAdapter(getContext(),arrayList);
        colorAdapter = new ColorAdapter(getContext(),arrayList);

        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view_size.setLayoutManager(HorizontalLayout112);
        recycle_view_size.setItemAnimator(new DefaultItemAnimator());
        sizeAdapter.notifyDataSetChanged();
        recycle_view_size.setAdapter(sizeAdapter);

        LinearLayoutManager HorizontalLayout1121
                = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view_color.setLayoutManager(HorizontalLayout1121);
        recycle_view_color.setItemAnimator(new DefaultItemAnimator());
        colorAdapter.notifyDataSetChanged();
        recycle_view_color.setAdapter(colorAdapter);


        return view;
    }
}