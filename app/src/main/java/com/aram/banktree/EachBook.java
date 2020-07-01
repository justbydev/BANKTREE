package com.aram.banktree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class EachBook extends AppCompatActivity {
    TextView eachbook_title;
    ViewPager eachbook_viwepager;
    Button eachbook_cancel;
    private Intent intent;
    String title;
    String page;
    ArrayList<String> content;
    ArrayList<String> color;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eachbook);

        eachbook_title=(TextView)findViewById(R.id.eachbook_title);
        eachbook_viwepager=(ViewPager)findViewById(R.id.eachbook_viewpager);
        eachbook_cancel=(Button)findViewById(R.id.eachbook_cancel);

        intent=getIntent();
        title=intent.getStringExtra("title");
        page=intent.getStringExtra("page");
        content=intent.getStringArrayListExtra("content");
        color=intent.getStringArrayListExtra("color");


        eachbook_title.setText(title);

        eachbook_viwepager.setAdapter(new EachBookAdapter(this, content, color));

        eachbook_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
