package com.aram.banktree;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter {
    ArrayList<Totalbook> mList;
    static Context context;
    private Intent intent;
    int cs;//cs==0이면 책읽기에서 누른 것, cs==1이면 마이페이지에서 누른 것
    public HistoryAdapter(ArrayList<Totalbook> mList, int cs){
        this.mList=mList;
        this.cs=cs;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView his_date;
        TextView his_title;
        LinearLayout his_total;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            his_date=itemView.findViewById(R.id.his_date);
            his_title=itemView.findViewById(R.id.his_title);
            his_total=itemView.findViewById(R.id.his_total);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.his_date.setText(mList.get(position).getDate()+"-");
        System.out.println(mList.get(position).getDate());
        System.out.println("date=========================================");
        myViewHolder.his_title.setText(mList.get(position).getTitle());
        final int pos=position;
        myViewHolder.his_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(context, EachBook.class);
                intent.putExtra("title",mList.get(pos).getTitle());
                intent.putStringArrayListExtra("content", mList.get(pos).getContent());
                intent.putStringArrayListExtra("color", mList.get(pos).getColor());
                intent.putExtra("page", mList.get(pos).getPage());
                context.startActivity(intent);
            }
        });
        myViewHolder.his_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(context, EachBook.class);
                intent.putExtra("title",mList.get(pos).getTitle());
                intent.putStringArrayListExtra("content", mList.get(pos).getContent());
                intent.putStringArrayListExtra("color", mList.get(pos).getColor());
                intent.putExtra("page", mList.get(pos).getPage());
                context.startActivity(intent);
            }
        });
        myViewHolder.his_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(context, EachBook.class);
                intent.putExtra("title",mList.get(pos).getTitle());
                intent.putStringArrayListExtra("content", mList.get(pos).getContent());
                intent.putStringArrayListExtra("color", mList.get(pos).getColor());
                intent.putExtra("page", mList.get(pos).getPage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
