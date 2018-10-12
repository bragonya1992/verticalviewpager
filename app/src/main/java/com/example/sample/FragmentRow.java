package com.example.sample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRow extends Fragment {
    String text;
    private static final String TEXT = "text";
    private RecyclerView myRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> stringList = new ArrayList<>();
    private MyAdapterTwo mAdapter;

    public FragmentRow() {
        // Required empty public constructor
    }


    public static FragmentRow newInstance(String data) {
        FragmentRow fragment = new FragmentRow();
        Bundle bundle = new Bundle(1);
        bundle.putString(TEXT, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.text = getArguments().getString(TEXT);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.row_multi_cam, container, false);
        myRecycler = view.findViewById(R.id.myRecycler);
        mLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager.setAutoMeasureEnabled(true);
        myRecycler.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapterTwo(stringList);
        myRecycler.setNestedScrollingEnabled(false);
        myRecycler.setAdapter(mAdapter);

        addData();
        return view;
    }

    private void addData() {
        for (int i = 0; i < 8; i++) {
            stringList.add(String.valueOf(i));
        }
    }
}
