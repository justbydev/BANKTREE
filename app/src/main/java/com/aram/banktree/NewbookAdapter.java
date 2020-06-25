package com.aram.banktree;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewbookAdapter extends RecyclerView.Adapter {
    ArrayList<Totalbook> mList=null;
    private Intent intent;
    static Context context;
    public NewbookAdapter(ArrayList<Totalbook> mList){
        this.mList=mList;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView writer;
        Button chatbutton;
        ImageView cover_image;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            writer=itemView.findViewById(R.id.writer);
            chatbutton=itemView.findViewById(R.id.chatbutton);
            cover_image=itemView.findViewById(R.id.cover_image);
            title=itemView.findViewById(R.id.title);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_cover, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.writer.setText(mList.get(position).getWriter());
        myViewHolder.title.setText(mList.get(position).getTitle());
        myViewHolder.cover_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(v.getContext(), BookInfo.class);
                intent.putExtra("number",position);
                v.getContext().startActivity(intent);


            }
        });
        myViewHolder.chatbutton.setTag(mList.get(position).getWriter());
        myViewHolder.cover_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), BookInfo.class);
                intent.putExtra("number", position);
                v.getContext().startActivity(intent);
            }
        });




                myViewHolder.chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String want=v.getTag().toString();
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                String me=firebaseUser.getEmail();
                if(want.equals(me)){
                    Toast.makeText(context, "내가 쓴 글입니다", Toast.LENGTH_SHORT).show();
                }
                else{
                    ((MenuActivity)MenuActivity.menucontext).changechat(want, me);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
