package com.aram.banktree;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aram.banktree.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.ArrayList;


public class Fragment3 extends Fragment {
    RecyclerView recyclerView;
    private ArrayList<ChatListData> mArrayList;
    private ChatListAdapter chatListAdapter;

    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;

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


        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        String email=firebaseAuth.getCurrentUser().getEmail().toString();
        String temp=email.replace('.', '-');
        myRef=firebaseDatabase.getReference("Chatlist").child(temp);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j=0;
                //ChatListData class는 각 사람에 해당하는 채팅한 사람의 계정만
                //저장해 놓기 위한 class
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String now=snapshot.getValue().toString();
                    mArrayList.add(new ChatListData(now));
                }
                //여기는 채팅 하단 탭을 눌렀을 때 firebase에서 내가 채팅한 목록을 가져와서
                //ChatListAdapter를 통해서 recyclerview에 채팅 리스트를 보여주는 역할이다
                layoutManager=new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                chatListAdapter=new ChatListAdapter(mArrayList, getContext());
                recyclerView.setAdapter(chatListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }
    //이 method는 menuactivity의 fragment1에서 newbook의 채팅 버튼을 눌렀을 때
    //NewbookAdapter에서 호출한 changechat을 통해서 호출된 method
    //즉, 이 method는 신규 책 목록의 채팅 버튼을 눌렀을 때 호출된다
    public void settingchat(String want, String me){
        //FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        //DatabaseReference myRef=firebaseDatabase.getReference("chat");
        //chatListAdapter는 fragment3 화면에서 나의 채팅 목록을 보여주는 것
        int position=chatListAdapter.getwantposition(want);
        //getwantposition은 chatListAdapter class 속에 직접 작성한 method(override method 아님)
        if(position==-1){//만약 내가 채팅하고자 하는 사람과 처음 채팅하는 경우라면
            String temp=me.replace('.', '-');
            int size=chatListAdapter.getItemCount();
            myRef=firebaseDatabase.getReference("Chatlist").child(temp);
            myRef.push().setValue(want);
            String wanttemp=want.replace('.', '-');
            myRef=firebaseDatabase.getReference("Chatlist").child(wanttemp);
            myRef.push().setValue(me);
            //맨처음 생성된 것이기 때문에 firebase에 데이터를 추가하고 chatListAdapter를 업데이트한다
            mArrayList.add(0, new ChatListData(want));
            chatListAdapter.notifyItemInserted(0);
            Intent intent=new Intent(getContext(), EachChat.class);
            //EachChat은 각 채팅방을 의미
            intent.putExtra("want", want);
            startActivity(intent);
        }
        else{//이미 내가 채팅한 기록이 있는 경우
            Intent intent=new Intent(getContext(), EachChat.class);
            //EachChat은 각 채팅방을 의미
            intent.putExtra("want", want);
            startActivity(intent);
        }

    }
}
