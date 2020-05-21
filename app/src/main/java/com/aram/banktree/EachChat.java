package com.aram.banktree;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EachChat extends AppCompatActivity {
    TextView want;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eachchat);

        want=(TextView)findViewById(R.id.want);
        Intent intent=getIntent();
        want.setText(intent.getExtras().getString("want"));
    }
}
