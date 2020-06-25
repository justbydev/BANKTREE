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
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String now=snapshot.getValue().toString();
                    mArrayList.add(new ChatListData(now));
                }

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
    public void settingchat(String want, String me){
        //FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        //DatabaseReference myRef=firebaseDatabase.getReference("chat");
        int position=chatListAdapter.getwantposition(want);
        if(position==-1){
            String temp=me.replace('.', '-');
            int size=chatListAdapter.getItemCount();
            myRef=firebaseDatabase.getReference("Chatlist").child(temp);
            myRef.push().setValue(want);
            String wanttemp=want.replace('.', '-');
            myRef=firebaseDatabase.getReference("Chatlist").child(wanttemp);
            myRef.push().setValue(me);
            mArrayList.add(0, new ChatListData(want));
            chatListAdapter.notifyItemInserted(0);
        }
        else{
            ((MenuActivity) MenuActivity.menucontext).which=2;
            Intent intent=new Intent(getContext(), EachChat.class);
            intent.putExtra("want", want);
            startActivity(intent);
        }

    }
}
