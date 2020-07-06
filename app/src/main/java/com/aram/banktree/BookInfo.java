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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookInfo extends AppCompatActivity {
    private Intent intent;

    ImageView imageView;
    TextView Text_title;
    TextView Text_writer;
    TextView Text_writerinfo;
    Button Writerpagebutton;
    Button chat_button;
    Button read_button;
    Button read_cancel;
    ImageView profile_image;
    String title;
    String writer;
    String page;
    String date;
    ArrayList<String> content;
    ArrayList<String> color;
    int chat;
    String me, want;

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
        read_button=findViewById(R.id.read_button);
        read_cancel=findViewById(R.id.read_cancel);
        chat_button=findViewById(R.id.chat_button);


        Bundle extras = getIntent().getExtras();



        if(extras!=null){
            title=extras.getString("title", "nothing");
            writer=extras.getString("writer", "nothing");
            page=extras.getString("page");
            content=extras.getStringArrayList("content");
            color=extras.getStringArrayList("color");
            date=extras.getString("date");
            chat=extras.getInt("chat");
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
        read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BookInfo.this, EachBook.class);
                intent.putExtra("title", title);
                intent.putExtra("page", page);
                intent.putStringArrayListExtra("content", content);
                intent.putStringArrayListExtra("color", color);
                startActivity(intent);
            }
        });
        read_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuActivity)MenuActivity.menucontext).which=0;
                finish();
            }
        });
        me= ManageTotalbook.getInstance().getFakename();
        want=writer;
        if(want.equals(me)){
            chat_button.setText("나의책");
            chat_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BookInfo.this, "내와의 채팅은 불가합니다", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            if(chat==0){
                chat_button.setText("채팅불가");
                chat_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(BookInfo.this, "채팅이 불가합니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if(chat==1){
                chat_button.setText("채팅");
                chat_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MenuActivity)MenuActivity.menucontext).changechat(want, me);
                    }
                });
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((MenuActivity)MenuActivity.menucontext).which=0;
        finish();
    }
}











