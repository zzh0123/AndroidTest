package com.zzh.androidtest.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzh.androidtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    TextView tv;
    View rootView;
    private ViewStub vs_buttons;
    private LinearLayout ll_operation_buttons;
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i=0; i<3000; i++){
            Log.i("--i--", "--i--" + i);
//            tv.setText(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_second, container, false);
        tv = rootView.findViewById(R.id.tv);
        vs_buttons = (ViewStub) rootView.findViewById(R.id.vs_buttons);
        ll_operation_buttons = (LinearLayout) vs_buttons.inflate();

        Log.i("--i--", "--onCreateView--");
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        for (int i=0; i<3000; i++){
//            Log.i("--i--", "--i--" + i);
////            tv.setText(i);
//        }
    }
}
