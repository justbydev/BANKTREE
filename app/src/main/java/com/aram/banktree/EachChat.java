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
        other=intent.getExtras().getString("want");
        want.setText(other);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        me=firebaseAuth.getCurrentUser().getEmail();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        message_recycler_view.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        message_recycler_view.setLayoutManager(layoutManager);

        othertemp=other.replace('.', '-');
        metemp=me.replace('.', '-');
        mList=new ArrayList<ChatMessage>();

        mAdapter = new EachChatAdapter(mList, me);
        message_recycler_view.setAdapter(mAdapter);

        myRef=firebaseDatabase.getReference("Chat").child(metemp).child(othertemp);

        /*myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String now=snapshot.getValue().toString();
                    now=now.replace(" ", "&");
                    now=now.replace("\n", "$");
                    System.out.println("=============================================");
                    System.out.println(now);
                    try{
                        JSONObject json=new JSONObject(now);
                        String name=json.optString("name", null);
                        if(name==null){
                            name=json.getString("&name");
                        }
                        String txt=json.optString("text", null);
                        if(txt==null){
                            txt=json.getString("&text");
                        }
                        ChatMessage chat=new ChatMessage();
                        name=name.replace("&", " ");
                        txt=txt.replace("&", " ");
                        txt=txt.replace("$", "\n");
                        chat.setName(name);
                        chat.setText(txt);
                        mList.add(chat);
                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                }
                // specify an adapter (see also next example)
                mAdapter = new EachChatAdapter(mList);
                message_recycler_view.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=message_edit.getText().toString();
                if(text!=null||text.length()>0){
                    ChatMessage chatMessage=new ChatMessage();
                    chatMessage.setName(me);
                    chatMessage.setText(text);
                    message_edit.setText("");
                    myRef=firebaseDatabase.getReference("Chat").child(othertemp).child(metemp);
                    myRef.push().setValue(chatMessage);
                    myRef=firebaseDatabase.getReference("Chat").child(metemp).child(othertemp);
                    myRef.push().setValue(chatMessage);
                }
            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatMessage chat=dataSnapshot.getValue(ChatMessage.class);
                mList.add(chat);
                mAdapter.notifyDataSetChanged();
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
}
