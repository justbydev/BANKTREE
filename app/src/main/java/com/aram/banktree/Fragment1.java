package com.aram.banktree;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.Collator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.text.Layout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.View;

import android.view.ViewGroup;

import android.widget.AdapterView;

import android.widget.Button;

import android.widget.GridLayout;

import android.widget.GridLayout;
import android.widget.LinearLayout;

import android.widget.ListView;

import android.widget.ScrollView;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.TextView;

import android.widget.Toast;



import com.aram.banktree.R;
import com.google.api.Distribution;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.google.api.Distribution;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.Query;

import com.google.firebase.database.ValueEventListener;



import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;



import java.util.ArrayList;





public class Fragment1 extends Fragment {
    RecyclerView new_book;
    RecyclerView random_book;
    Totalbook newbook;
    ArrayList<Totalbook> twenty;
    ArrayList<Totalbook> totalbook;
    LinearLayoutManager linearLayout;
    NewbookAdapter newbookAdapter;
    GridLayoutManager gridLayout;
    RandombookAdapter randombookAdapter;
    //ProgressDialog progressDialog;
    public Fragment1(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment1, container, false);
        new_book=(RecyclerView) v.findViewById(R.id.new_book);
        random_book=(RecyclerView) v.findViewById(R.id.random_book);
        twenty=ManageTotalbook.getInstance().gettwenty();
        totalbook=ManageTotalbook.getInstance().getTotalbook_total();

        new_book.setHasFixedSize(true);
        linearLayout=new LinearLayoutManager(getContext());
        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        new_book.setLayoutManager(linearLayout);

        newbookAdapter=new NewbookAdapter(twenty);
        new_book.setAdapter(newbookAdapter);

        random_book.setHasFixedSize(true);
        gridLayout=new GridLayoutManager(getContext(), 3);
        random_book.setLayoutManager(gridLayout);

        randombookAdapter=new RandombookAdapter(totalbook);
        random_book.setAdapter(randombookAdapter);

        return v;
    }
    public void addnewbooktorecycler(String title, String writer, String page, ArrayList<String> content, ArrayList<String> color){
        newbook=new Totalbook();
        newbook.setTitle(title);
        newbook.setWriter(writer);
        newbook.setPage(page);
        newbook.setContent(content);
        newbook.setColor(color);
        totalbook.add(newbook);
        twenty.add(newbook);
        newbookAdapter.notifyDataSetChanged();
        randombookAdapter.notifyDataSetChanged();
    }

    private View.OnClickListener buttonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.chatbutton:
                    String want=v.getTag().toString();
                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    String me=firebaseUser.getEmail();
                    if(want.equals(me)){
                        Toast.makeText(getContext(), "내가 쓴 글입니다", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ((MenuActivity)MenuActivity.menucontext).changechat(want, me);
                    }
                    return;
                default:
                    return;
            }
        }
    };
}