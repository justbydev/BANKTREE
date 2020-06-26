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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_book_info);
        ArrayList<Totalbook> mList = null;
        imageView = findViewById(R.id.bookimage);
        Text_title = findViewById(R.id.text_title);
        Text_title.setText("제목: " + extras.getString("title"));
        Text_writer = findViewById(R.id.text_writer);
        Text_writer.setText("작가: " + extras.getString("writer"));
        Text_writerinfo = findViewById(R.id.text_writerinfo);
        Text_writerinfo.setText(extras.getString("writer"));
        Writerpagebutton = (Button) findViewById(R.id.writerpage);
        this.Writerpagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        writerpage.class);
                intent.putExtra("wr",extras.getString("writer"));

                startActivity(intent);

            }

        });
    }
}











