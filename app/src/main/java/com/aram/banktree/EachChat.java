package com.aram.banktree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EachChat extends AppCompatActivity {
    TextView want;
    EditText message_edit;
    Button send_message;
    String me;
    String other;
    private RecyclerView message_recycler_view;
    private EachChatAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

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

        // specify an adapter (see also next example)
        mList=new ArrayList<>();
        mAdapter = new EachChatAdapter(mList);
        message_recycler_view.setAdapter(mAdapter);

        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=message_edit.getText().toString();
                ChatMessage chatMessage=new ChatMessage();
                chatMessage.setName(me);
                chatMessage.setText(text);
                mList.add(chatMessage);
                mAdapter.notifyDataSetChanged();
                message_edit.setText("");
            }
        });

    }
}
