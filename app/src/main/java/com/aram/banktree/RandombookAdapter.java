package com.aram.banktree;

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

    public RandombookAdapter(ArrayList<Totalbook> mList){
        this.mList=mList;
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
        myViewHolder.random_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(v.getContext(), BookInfo.class);
                intent.putExtra("number",position);
                v.getContext().startActivity(intent);
<<<<<<< HEAD

=======
                
>>>>>>> ac615cd20ca96c223658f40cfa20ee6bb262db23
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
