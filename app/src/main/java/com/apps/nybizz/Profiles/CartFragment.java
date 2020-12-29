package com.apps.nybizz.Profiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nybizz.Activities.ConfirmOrderActivity;
import com.apps.nybizz.Adapters.CartAdapter;
import com.apps.nybizz.Adapters.SuggestionAdapter;
import com.apps.nybizz.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private RecyclerView recycle_view,recycle_view_suggestion,recycle_view_bought;
    View view;
    private ArrayList<String> arrayList = new ArrayList<>();
    CartAdapter adapters;
    Button button;
    SuggestionAdapter suggestionAdapter,suggestrviewBought;
    private LinearLayout layout_price;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cart_fragment, null);
        init();
        return view;
    }



    public void showBottomSheetDialogFragment() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }


    public void init(){
        recycle_view = view.findViewById(R.id.recycle_view);
        recycle_view_suggestion = view.findViewById(R.id.recycle_view_suggestion);
        recycle_view_bought = view.findViewById(R.id.recycle_view_bought);
        layout_price = view.findViewById(R.id.layout_price);

        adapters = new CartAdapter(getContext(),arrayList);
        suggestionAdapter = new SuggestionAdapter(getContext(),arrayList);

        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                startActivity(intent);
            }
        });
        layout_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialogFragment();
            }
        });

        for (int i =0; i < 2;i++){
            arrayList.add("");
        }

        LinearLayoutManager HorizontalLayout112
                = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view.setLayoutManager(HorizontalLayout112);
        recycle_view.setItemAnimator(new DefaultItemAnimator());
        adapters.notifyDataSetChanged();
        recycle_view.setAdapter(adapters);




        LinearLayoutManager HorizontalLayout1123
                = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view_suggestion.setLayoutManager(HorizontalLayout1123);
        recycle_view_suggestion.setItemAnimator(new DefaultItemAnimator());
        suggestionAdapter.notifyDataSetChanged();
        recycle_view_suggestion.setAdapter(suggestionAdapter);



        suggestrviewBought = new SuggestionAdapter(getContext(),arrayList);
        LinearLayoutManager HorizontalLayout1121
                = new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.HORIZONTAL,
                false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);/
        recycle_view_bought.setLayoutManager(HorizontalLayout1121);
        recycle_view_bought.setItemAnimator(new DefaultItemAnimator());
        suggestrviewBought.notifyDataSetChanged();
        recycle_view_bought.setAdapter(suggestrviewBought);


    }
}
