package com.aram.banktree;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Bookcover extends Fragment {
    TextView writer;
    TextView title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.book_cover, container, false);
        writer=(TextView)v.findViewById(R.id.writer);
        title=(TextView)v.findViewById(R.id.title);
        return v;
    }
    public void addwritertitle(String wr, String t){
        writer.setText(wr);
        title.setText(t);
    }
}
