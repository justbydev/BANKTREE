package com.aram.banktree;

<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
=======
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
>>>>>>> master
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
<<<<<<< HEAD
    private ArrayList<ChatListData> mList;
    ChatListAdapter(ArrayList<ChatListData> mList){
        this.mList=mList;
    }
    public static class ChatListViewHolder extends RecyclerView.ViewHolder{
        TextView nickname;
        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            nickname=(TextView)itemView.findViewById(R.id.nickname);
=======

    private static ArrayList<ChatListData> mList;
    private static Context context;
    ChatListAdapter(ArrayList<ChatListData> mList, Context context){
        this.mList=mList;
        this.context=context;
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
                        Intent intent=new Intent(context, EachChat.class);
                        intent.putExtra("want", mList.get(pos).getNickname());
                        context.startActivity(intent);
                    }
                }
            });
            nickname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        Intent intent=new Intent(context, EachChat.class);
                        intent.putExtra("want", mList.get(pos).getNickname());
                        context.startActivity(intent);
                    }
                }
            });
>>>>>>> master
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
<<<<<<< HEAD
=======

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
>>>>>>> master
}
