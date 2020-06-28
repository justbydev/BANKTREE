package com.aram.banktree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookInfo extends AppCompatActivity {
    private Intent intent;

    ImageView imageView;
    TextView Text_title;
    TextView Text_writer;
    TextView Text_writerinfo;
    Button Writerpagebutton;
    ImageView profile_image;
    String title;
    String writer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        ArrayList<Totalbook> mList = null;
        imageView = findViewById(R.id.bookimage);
        Text_title = findViewById(R.id.text_title);
        Text_writer = findViewById(R.id.text_writer);
        Text_writerinfo = findViewById(R.id.text_writerinfo);
        profile_image=findViewById(R.id.profile_image);

        Bundle extras = getIntent().getExtras();



        if(extras!=null){
            title=extras.getString("title", "nothing");
            writer=extras.getString("writer", "nothing");
        }
        Text_title.setText("제목: " + title);
        Text_writer.setText("작가: " + writer);
        Text_writerinfo.setText(writer);

        Writerpagebutton = (Button) findViewById(R.id.writerpage);

        Writerpagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        BookInfo.this,
                        writerpage.class);
                intent.putExtra("wr",writer);

                startActivity(intent);

            }

        });

    }
}











