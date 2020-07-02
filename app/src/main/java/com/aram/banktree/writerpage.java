package com.aram.banktree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class writerpage extends AppCompatActivity {
    TextView writerid;
    TextView writer_bookcount;
    RecyclerView history_recyclerview;
    RecyclerView lib_recyclerview;
    String writer;
    ArrayList<Totalbook> mybook;

    RecyclerView.LayoutManager gridlayoutManager;
    RandombookAdapter randombookAdapter;
    RecyclerView.LayoutManager linearlayoutManager;
    HistoryAdapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_writerpage);
        writerid=findViewById(R.id.writerid);
        writer_bookcount=findViewById(R.id.writer_bookcount);
        history_recyclerview=findViewById(R.id.history_recyclerview);
        lib_recyclerview=findViewById(R.id.lib_recyclerview);

        writerid.setText(extras.getString("wr")+"님");
        writer=extras.getString("wr");

        mybook=new ArrayList<>();
        mybook=ManageTotalbook.getInstance().getwantwriter(writer);

        writer_bookcount.setText("보유 권수: "+Integer.toString(mybook.size())+"권");

        lib_recyclerview.setHasFixedSize(true);
        gridlayoutManager=new GridLayoutManager(this, 2);
        lib_recyclerview.setLayoutManager(gridlayoutManager);
        randombookAdapter=new RandombookAdapter(mybook, 1);
        lib_recyclerview.setAdapter(randombookAdapter);

        history_recyclerview.setHasFixedSize(true);
        linearlayoutManager=new LinearLayoutManager(this);
        history_recyclerview.setLayoutManager(linearlayoutManager);
        historyAdapter=new HistoryAdapter(mybook, 1);
        history_recyclerview.setAdapter(historyAdapter);

    }
}
