package com.aram.banktree;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aram.banktree.R;

import java.util.ArrayList;


public class Fragment3 extends Fragment {
    RecyclerView recyclerView;
    private ArrayList<ChatListData> mArrayList;
    private ChatListAdapter chatListAdapter;

    public Fragment3(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment3, container, false);

        recyclerView=(RecyclerView)v.findViewById(R.id.chatlist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mArrayList=new ArrayList<ChatListData>();
        chatListAdapter=new ChatListAdapter(mArrayList);
        recyclerView.setAdapter(chatListAdapter);
        return v;
    }
    public void settingchat(String want, String me){

    }
}
