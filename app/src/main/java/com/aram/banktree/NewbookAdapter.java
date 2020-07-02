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
        ImageView cover_image;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            writer=itemView.findViewById(R.id.writer);
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
                intent= new Intent(context, BookInfo.class);
                intent.putExtra("title",mList.get(position).getTitle());
                intent.putExtra("writer",mList.get(position).getWriter());
                intent.putStringArrayListExtra("content", mList.get(position).getContent());
                intent.putStringArrayListExtra("color", mList.get(position).getColor());
                intent.putExtra("page", mList.get(position).getPage());
                intent.putExtra("date", mList.get(position).getDate());
                context.startActivity(intent);
            }
        });
        myViewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(context, BookInfo.class);
                intent.putExtra("title", mList.get(position).getTitle());
                intent.putExtra("writer", mList.get(position).getWriter());
                intent.putStringArrayListExtra("content", mList.get(position).getContent());
                intent.putStringArrayListExtra("color", mList.get(position).getColor());
                intent.putExtra("page", mList.get(position).getPage());
                intent.putExtra("date", mList.get(position).getDate());
                context.startActivity(intent);
            }
        });
        //myViewHolder.chatbutton.setTag(mList.get(position).getWriter());



        //newbook에는 각각 채팅 버튼이 생성되어 있고 그 채팅 버튼을 눌렀을 때 작동하는 것
        /*myViewHolder.chatbutton.setOnClickListener(new View.OnClickListener() {
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
                    //내가 아닌 다른 사람과의 채팅 버튼을 누르면 우선
                    //menuactivity의 changechat 함수를 호출하게 된다
                    //그러면 changechat에서는 우선 채팅 화면이 있는 fragment3를 생성해서 add하고
                    //fragment3의 settingchat()을 호출하게 된다
                    ((MenuActivity)MenuActivity.menucontext).changechat(want, me);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
