package com.aram.banktree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aram.banktree.R;

//공지, 앱 설명을 위한 fragment
public class Fragment4 extends Fragment {
    public Fragment4(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment4, container, false);
    }
}
