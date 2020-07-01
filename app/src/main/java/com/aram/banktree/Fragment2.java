package com.aram.banktree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aram.banktree.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
//마이페이지를 위한 fragment

public class Fragment2 extends Fragment {
    TextView writerid;
    TextView writer_bookcount;
    RecyclerView history_recyclerview;
    RecyclerView lib_recyclerview;
    String writer;
    ArrayList<Totalbook> mybook;

    RecyclerView.LayoutManager gridlayoutManager;
    RandombookAdapter randombookAdapter;
    RecyclerView.LayoutManager linearlayoutManager;
    HistoryAdapter historyAdapter;
    public Fragment2(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.activity_writerpage, container, false);

        writerid=v.findViewById(R.id.writerid);
        writer_bookcount=v.findViewById(R.id.writer_bookcount);
        history_recyclerview=v.findViewById(R.id.history_recyclerview);
        lib_recyclerview=v.findViewById(R.id.lib_recyclerview);

        String me=FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();

        writerid.setText(me);
        mybook=new ArrayList<>();
        mybook=ManageTotalbook.getInstance().getwantwriter(me);

        writer_bookcount.setText("보유 권수: "+Integer.toString(mybook.size())+"권");

        lib_recyclerview.setHasFixedSize(true);
        gridlayoutManager=new GridLayoutManager(getContext(), 3);
        lib_recyclerview.setLayoutManager(gridlayoutManager);
        randombookAdapter=new RandombookAdapter(mybook, 1);
        lib_recyclerview.setAdapter(randombookAdapter);

        history_recyclerview.setHasFixedSize(true);
        linearlayoutManager=new LinearLayoutManager(getContext());
        history_recyclerview.setLayoutManager(linearlayoutManager);
        historyAdapter=new HistoryAdapter(mybook, 1);
        history_recyclerview.setAdapter(historyAdapter);

        ((MenuActivity)MenuActivity.menucontext).which=3;

        return v;
    }
}
