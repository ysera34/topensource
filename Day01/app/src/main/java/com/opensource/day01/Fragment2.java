package com.opensource.day01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by yoon on 2017. 1. 23..
 */

public class Fragment2 extends Fragment {

    public static Fragment2 newInstance() {

        Bundle args = new Bundle();

        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, Fragment1.newInstance())
//                        .commit();
                ((MainActivity) getActivity()).onChangeFragment(1);
            }
        });
        return rootView;
    }
}
