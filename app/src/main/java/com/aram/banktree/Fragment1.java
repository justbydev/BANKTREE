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
    Spinner home_spinner;

    //ProgressDialog progressDialog;
    public Fragment1(){}

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment1, container, false);
        new_book=(RecyclerView) v.findViewById(R.id.new_book);
        random_book=(RecyclerView) v.findViewById(R.id.random_book);
        home_spinner=(Spinner)v.findViewById(R.id.home_spinner);

        twenty=ManageTotalbook.getInstance().gettwenty();
        //twenty는 신규 책 목록을 위한 것으로 20개만 보이도록 하기 위해서 20개만 가지고 오는 것
        totalbook=ManageTotalbook.getInstance().getTotalbook_total();
        //totalbook은 랜덤 책을 위한 것으로 가지고 있는 전자책 전체를 가져온 데이터
        new_book.setHasFixedSize(true);
        linearLayout=new LinearLayoutManager(getContext());
        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayout.setReverseLayout(true);
        linearLayout.setStackFromEnd(true);
        new_book.setLayoutManager(linearLayout);

        //newbookAdapter는 신규 책을 보여주는 recyclerview를 위한 Adapter
        //newbook recyclerview는 Horizontal LinearLayout
        newbookAdapter=new NewbookAdapter(twenty);
        new_book.setAdapter(newbookAdapter);

        random_book.setHasFixedSize(true);
        gridLayout=new GridLayoutManager(getContext(), 3);
        random_book.setLayoutManager(gridLayout);

        //randombookAdapter는 랜덤 책 전체를 보여주는 recyclerview를 위한 Adapter
        //randombook recyclerview는 Gridlayout
        randombookAdapter=new RandombookAdapter(totalbook, 0);
        random_book.setAdapter(randombookAdapter);

        home_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    randombookAdapter.changedata(ManageTotalbook.getInstance().getTotalbook_total());
                }
                else{
                    randombookAdapter.changedata(ManageTotalbook.getInstance().getwantcat(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }
    //이 method는 새로운 전자책 등록시에 오는 method로 Totalbook class로 firebase에 등록
    //recyclerview에 새롭게 추가한다
    public void addnewbooktorecycler(String title, String writer, String page, ArrayList<String> content, ArrayList<String> color, String date, int cat){
        newbook=new Totalbook();
        newbook.setTitle(title);
        newbook.setWriter(writer);
        newbook.setPage(page);
        newbook.setContent(content);
        newbook.setColor(color);
        newbook.setDate(date);
        newbook.setCat(cat);
        totalbook.add(newbook);
        twenty.add(newbook);
        home_spinner.setSelection(0);
        newbookAdapter.notifyDataSetChanged();
        randombookAdapter.notifyDataSetChanged();
        //이 작업을 통해서 ManageTotalbook의 Totalbook_total에도 새로 추가된 항목이 add된다
        //그 이유는 fragment1의 totalbook을 받아올 때 ManageTotalbook.getInstance().getTotalbook()을 하게 되는데
        //이 method는 ManageTotalbook의 Totalbook_total 자체를 그냥 return하게 된다
        //그러면 ManageTotalbook은 singleton class이기 때문에 메모리 공간을 하나만 차지하게 되고 따라서 같은 메모리 주소를 가리키게 된다
        //따라서 totalbook 역시 같은 곳을 가리키게 되므로 totalbook.add를 하게 되면 동시에 ManageTotalbook의 Totalbook_total에도 add된다
    }

}