package com.example.homeshef;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;


public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewgroup=(ViewGroup)inflater.inflate(R.layout.fragment1, container, false);
        Button button=viewgroup.findViewById(R.id.edit_button);
        final LinearLayout listView=viewgroup.findViewById(R.id.scroll_container);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=getContext().getApplicationContext();
                LayoutInflater inflater2=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                inflater2.inflate(R.layout.cook_text, listView, true);
            }
        });
        return viewgroup;
    }
}
