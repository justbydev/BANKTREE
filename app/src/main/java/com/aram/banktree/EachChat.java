package com.aram.banktree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//각각의 채팅방
public class EachChat extends AppCompatActivity {
    TextView want;
    EditText message_edit;
    Button send_message;
    String me;
    String other;
    String othertemp;
    String metemp;
    int first=0;
    int flag=0;
    private RecyclerView message_recycler_view;
    private EachChatAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    private ArrayList<ChatMessage> mList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eachchat);

        message_edit=(EditText)findViewById(R.id.message_edit);
        send_message=(Button)findViewById(R.id.send_button);
        message_recycler_view=(RecyclerView)findViewById(R.id.message_recycler_view);

        want=(TextView)findViewById(R.id.want);
        Intent intent=getIntent();
        other=intent.getStringExtra("want");
        want.setText(other);
        firebaseDatabase=FirebaseDatabase.getInstance();
        me=ManageTotalbook.getInstance().getFakename();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        message_recycler_view.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        message_recycler_view.setLayoutManager(layoutManager);


        mList=new ArrayList<ChatMessage>();

        //각각 채팅방은 대화 하나당 하나의 목록으로써 recyclerview를 사용하고
        //이 recyclerview는 EachChatAdapter를 사용한다

        //채팅창 열때 초반 채팅창 recyclerview는 비어 있는 상태로 연결
        mAdapter = new EachChatAdapter(mList, me);
        message_recycler_view.setAdapter(mAdapter);

        System.out.println(me+other+"======+++++++++++=================");
        System.out.println("EachChat java");

        myRef=firebaseDatabase.getReference("Chat").child(me).child(other);
        //채팅 대화를 전송할 때의 method로써 ChatMessage class를 이용해서 firebase에 저장
        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=message_edit.getText().toString().trim();
                if(text!=null&&text.length()>0&&text!=""&&text!=" "){
                    ChatMessage chatMessage=new ChatMessage();
                    chatMessage.setName(me);
                    String t=message_edit.getText().toString();
                    chatMessage.setText(t);
                    myRef=firebaseDatabase.getReference("Chat").child(other).child(me);
                    myRef.push().setValue(chatMessage);
                    myRef=firebaseDatabase.getReference("Chat").child(me).child(other);
                    myRef.push().setValue(chatMessage);
                }

                message_edit.setText("");
            }
        });
        //채팅 목록을 가져오기 위한 코드
        //채팅은 계속 치는 것이므로 그 변화에 대한 것을 실시간으로 적용시키기 위해서
        //addChildEventListener를 사용하고 onChildAdded를 사용한다

        //위의 send_message에서 채팅을 전송하면 우선 firebase에 저장되고 그럼
        //addChildEventListener를 이용해서 실시간 데이터 베이스에서 실시간으로 변화를
        //감지하고 다시 EachChatAdapter에 추가한다
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatMessage chat=dataSnapshot.getValue(ChatMessage.class);
                mList.add(chat);
                mAdapter.notifyDataSetChanged();
                message_recycler_view.scrollToPosition(mAdapter.getItemCount()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((MenuActivity)MenuActivity.menucontext).which=2;
        finish();
    }
}
