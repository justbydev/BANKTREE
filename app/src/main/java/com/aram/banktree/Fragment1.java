package com.aram.banktree;

import android.content.Context;
import android.icu.text.Collator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.aram.banktree.R;
import com.google.api.Distribution;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment1 extends Fragment {
    LinearLayout new_book;
    GridLayout random_book;
    ArrayList<Totalbook> totalbook=new ArrayList<Totalbook>();
    public Fragment1(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1, container, false);
        new_book=(LinearLayout)v.findViewById(R.id.new_book);
        random_book=(GridLayout)v.findViewById(R.id.random_book);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Content");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    //String w=snapshot.getValue().toString();
                    //String t=snapshot.getValue().toString();
                    //totalbook.add(new Totalbook(t, w));
                    String json=snapshot.getValue().toString();
                    try {
                        JSONObject jsonObject=new JSONObject(json);
                        String t=jsonObject.getString("title");
                        String w=jsonObject.getString("writer");
                        System.out.println("============================================================");
                        System.out.println(t);
                        System.out.println(w);
                        System.out.println("============================================================");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(snapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }
    public void addnewbook(String title, String writer){
        View childview=getLayoutInflater().inflate(R.layout.book_cover, new_book, false);
        TextView childwriter=(TextView)childview.findViewById(R.id.writer);
        TextView childtitle=(TextView)childview.findViewById(R.id.title);
        childwriter.setText(writer);
        childtitle.setText("제목: "+title);
        childtitle.setSingleLine(true);
        childtitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        childtitle.setSelected(true);
        new_book.addView(childview);
    }
}
