package com.aram.banktree;

import android.content.Context;
import android.drm.DrmStore;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class EachChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ArrayList<ChatMessage> mList=null;
    private String me;
    public EachChatAdapter(ArrayList<ChatMessage> mList, String me) {
        this.mList=mList;
        this.me=me;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView message_user;
        public TextView message_content;
        public MyViewHolder(View v) {
            super(v);
            message_user = v.findViewById(R.id.message_user);
            message_content=v.findViewById(R.id.message_content);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if(mList.get(position).getName().equals(me)){
            return 1;
        }
        else{
            return 2;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EachChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messageitem, parent, false);
        if(viewType==1){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.messageitem2, parent, false);
        }
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.message_user.setText(mList.get(position).getName());
        myViewHolder.message_content.setText(mList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

}
