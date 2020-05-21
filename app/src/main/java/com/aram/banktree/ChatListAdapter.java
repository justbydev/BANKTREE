package com.aram.banktree;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatListData> mList;
    ChatListAdapter(ArrayList<ChatListData> mList){
        this.mList=mList;
    }
    public static class ChatListViewHolder extends RecyclerView.ViewHolder{
        TextView nickname;
        LinearLayout chatlayout;
        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname=(TextView)itemView.findViewById(R.id.nickname);
            chatlayout=(LinearLayout)itemView.findViewById(R.id.chatlayout);
            chatlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){

                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate=LayoutInflater.from(parent.getContext());
        View v=inflate.inflate(R.layout.chatitem, parent, false);
        ChatListViewHolder chatListViewHolder=new ChatListViewHolder(v);
        return chatListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatListViewHolder chatListViewHolder=(ChatListViewHolder)holder;
        chatListViewHolder.nickname.setText(mList.get(position).getNickname());

    }

    @Override
    public int getItemCount() {
        return mList==null? 0:mList.size();
    }

    public int getwantposition(String want){
        int position=-1;
        for(int i=0; i<mList.size(); i++){
            if(mList.get(i).getNickname().equals(want)){
                position=i;
                return position;
            }
        }
        return position;
    }
}
