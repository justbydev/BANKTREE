package com.aram.banktree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class writerpage extends AppCompatActivity {
TextView writerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_writerpage);
        writerid=findViewById(R.id.writerid);
        writerid.setText(extras.getString("wr")+"의 홈페이지");




    }
}
