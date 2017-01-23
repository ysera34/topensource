package com.opensource.myfragment;

import android.content.Context;
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

public class Fragment1 extends Fragment {

    public interface OnChangeFragmentListener {
        void onChangeFragment(int position);
    }

    public static Fragment1 newInstance() {

        Bundle args = new Bundle();

        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, Fragment2.newInstance())
//                        .commit();

//                ((MainActivity) getActivity()).onChangeFragment(0);
                if (listener != null) {
                    listener.onChangeFragment(0);
                }
            }
        });
        return rootView;
    }

    OnChangeFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (OnChangeFragmentListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
