package com.opensource.twork1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.opensource.twork1.MainActivity.EXTRA_SALES;

/**
 * Created by yoon on 2017. 1. 23..
 */

public class ChartFragment extends Fragment {

    public static ChartFragment newInstance() {

        Bundle args = new Bundle();

        ChartFragment fragment = new ChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayList<Sales> mSalesArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSalesArrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mSalesArrayList = (ArrayList<Sales>) savedInstanceState.getSerializable(EXTRA_SALES);
        }

        Log.i("onCreate", String.valueOf(mSalesArrayList.size()));

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
