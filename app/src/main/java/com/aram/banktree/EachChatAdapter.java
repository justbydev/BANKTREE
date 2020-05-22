package com.aram.banktree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EachChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ArrayList<ChatMessage> mList=null;

    public EachChatAdapter(ArrayList<ChatMessage> mList) {
        this.mList=mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.eachchat, parent, false);
        //EachChatAdapter eachChatAdapter=new EachChatAdapter(v);
        //return eachChatAdapter;
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
