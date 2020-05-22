package com.aram.banktree;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class EachChat extends AppCompatActivity {
    TextView want;
    EditText message_edit;
    Button send_message;
    RecyclerView message_recycler_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eachchat);

        message_edit=(EditText)findViewById(R.id.message_edit);
        send_message=(Button)findViewById(R.id.send_button);
        message_recycler_view=(RecyclerView)findViewById(R.id.message_recycler_view);

        want=(TextView)findViewById(R.id.want);
        Intent intent=getIntent();
        want.setText(intent.getExtras().getString("want"));
    }
}
