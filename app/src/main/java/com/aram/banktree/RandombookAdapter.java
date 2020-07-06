package com.aram.banktree;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RandombookAdapter extends RecyclerView.Adapter {
    ArrayList<Totalbook> mList=null;
    private Intent intent;
    static Context context;
    int cs;
    //cs==1이면 마이페이지에서 누른 것, cs==0이면 그 이외
    public RandombookAdapter(ArrayList<Totalbook> mList, int cs){
        this.mList=mList;
        this.cs=cs;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView writer;
        ImageView random_image;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            writer=itemView.findViewById(R.id.writer);
            random_image=itemView.findViewById(R.id.random_image);
            title=itemView.findViewById(R.id.title);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.randombookcover, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.writer.setText(mList.get(position).getWriter());
        myViewHolder.title.setText(mList.get(position).getTitle());
        if(cs==0){
            myViewHolder.random_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent= new Intent(context, BookInfo.class);
                    intent.putExtra("title",mList.get(position).getTitle());
                    intent.putExtra("writer",mList.get(position).getWriter());
                    intent.putStringArrayListExtra("content", mList.get(position).getContent());
                    intent.putStringArrayListExtra("color", mList.get(position).getColor());
                    intent.putExtra("page", mList.get(position).getPage());
                    intent.putExtra("date", mList.get(position).getDate());
                    intent.putExtra("chat", mList.get(position).getChat());
                    context.startActivity(intent);

                }
            });
            myViewHolder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent= new Intent(context, BookInfo.class);
                    intent.putExtra("title",mList.get(position).getTitle());
                    intent.putExtra("writer",mList.get(position).getWriter());
                    intent.putStringArrayListExtra("content", mList.get(position).getContent());
                    intent.putStringArrayListExtra("color", mList.get(position).getColor());
                    intent.putExtra("page", mList.get(position).getPage());
                    intent.putExtra("date", mList.get(position).getDate());
                    intent.putExtra("chat", mList.get(position).getChat());
                    context.startActivity(intent);

                }
            });
        }
        else if(cs==1){
            myViewHolder.random_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent=new Intent(context, EachBook.class);
                    intent.putExtra("title",mList.get(position).getTitle());
                    intent.putStringArrayListExtra("content", mList.get(position).getContent());
                    intent.putStringArrayListExtra("color", mList.get(position).getColor());
                    intent.putExtra("page", mList.get(position).getPage());
                    intent.putExtra("choose", 3);
                    context.startActivity(intent);
                }
            });
            myViewHolder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent=new Intent(context, EachBook.class);
                    intent.putExtra("title",mList.get(position).getTitle());
                    intent.putStringArrayListExtra("content", mList.get(position).getContent());
                    intent.putStringArrayListExtra("color", mList.get(position).getColor());
                    intent.putExtra("page", mList.get(position).getPage());
                    intent.putExtra("choose", 3);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public void changedata(ArrayList<Totalbook> mList){
        if(this.mList!=null){
            this.mList=null;
        }
        this.mList=mList;
        notifyDataSetChanged();
    }
}
