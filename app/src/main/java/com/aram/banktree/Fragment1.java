package com.aram.banktree;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.aram.banktree.R;
import com.google.api.Distribution;


public class Fragment1 extends Fragment {
    LinearLayout new_book;
    GridLayout random_book;
    public Fragment1(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1, container, false);
        new_book=(LinearLayout)v.findViewById(R.id.new_book);
        random_book=(GridLayout)v.findViewById(R.id.random_book);

        return v;
    }
    public void addnewbook(String title, String writer){
        View childview=getLayoutInflater().inflate(R.layout.book_cover, new_book, false);
        TextView childwriter=(TextView)childview.findViewById(R.id.writer);
        TextView childtitle=(TextView)childview.findViewById(R.id.title);
        childwriter.setText(writer);
        childtitle.setText("제목: "+title);
        childtitle.setSingleLine(true);
        childtitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        childtitle.setSelected(true);
        new_book.addView(childview);
    }
}
