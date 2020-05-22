package com.aram.banktree;

<<<<<<< HEAD


import android.app.ProgressDialog;

import android.content.Context;

import android.icu.text.Collator;

import android.os.Bundle;



import androidx.annotation.NonNull;

=======
import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.Collator;
import android.os.Bundle;

import androidx.annotation.NonNull;
>>>>>>> master
import androidx.fragment.app.Fragment;



import android.text.Layout;
<<<<<<< HEAD

import android.text.TextUtils;

import android.view.LayoutInflater;

import android.view.Menu;

=======
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
>>>>>>> master
import android.view.View;

import android.view.ViewGroup;

import android.widget.AdapterView;

import android.widget.Button;
<<<<<<< HEAD

import android.widget.GridLayout;

=======
import android.widget.GridLayout;
>>>>>>> master
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
<<<<<<< HEAD

    LinearLayout new_book;

    GridLayout random_book;

    ArrayList<Totalbook> totalbook=new ArrayList<Totalbook>();

    //ProgressDialog progressDialog;

    public Fragment1(){}

=======
    LinearLayout new_book;
    GridLayout random_book;
    ArrayList<Totalbook> totalbook=new ArrayList<Totalbook>();
    //ProgressDialog progressDialog;
    public Fragment1(){}
>>>>>>> master
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
<<<<<<< HEAD

        View v=inflater.inflate(R.layout.fragment1, container, false);

        new_book=(LinearLayout)v.findViewById(R.id.new_book);

        random_book=(GridLayout)v.findViewById(R.id.random_book);

        //progressDialog=new ProgressDialog(getContext());

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Content");

        //progressDialog.setMessage("로딩중입니다...");

        //progressDialog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                    String json=snapshot.getValue().toString();

                    json=json.replaceAll("\\s", "&");

                    System.out.println(json);

                    try {

                        JSONObject jsonObject=new JSONObject(json);

                        System.out.println(jsonObject);

                        String t=jsonObject.getString("title");

                        String w=jsonObject.getString("&writer");

                        //System.out.println("================================");

                        //System.out.println(t);

                        //System.out.println(w);

                        //System.out.println("================================");

                        t=t.replaceAll("&", " ");

                        w=w.replaceAll("&", " ");

                        totalbook.add(new Totalbook(t, w));

                        addnewbook(t, w);

                    } catch (JSONException e) {

                        e.printStackTrace();

                    }

                }

            }



            @Override

            public void onCancelled(@NonNull DatabaseError databaseError) {



            }

        });

        //progressDialog.dismiss();

        return v;

    }

    public void addnewbook(String title, String writer){

        View childview=getLayoutInflater().inflate(R.layout.book_cover, new_book, false);

        View childview2=getLayoutInflater().inflate(R.layout.randombookcover, random_book, false);

        TextView childwriter=(TextView)childview.findViewById(R.id.writer);

        TextView childtitle=(TextView)childview.findViewById(R.id.title);

        Button childbutton=(Button)childview.findViewById(R.id.chatbutton);//버튼 태그 설정 위한 것

        TextView childwriter2=(TextView)childview2.findViewById(R.id.writer);

        TextView childtitle2=(TextView)childview2.findViewById(R.id.title);

        childwriter.setText(writer);

        childtitle.setText("제목: "+title);

        childbutton.setTag(writer);//버튼 태그를 writer 아이디로 설정, 일종의 꼼수

        childbutton.setOnClickListener(buttonClickListener);

        childwriter2.setText(writer);

        childtitle2.setText("제목: "+title);

        new_book.addView(childview);

        random_book.addView(childview2);

        /*Context context=getContext().getApplicationContext();

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.book_cover, new_book, true);

        LayoutInflater gridinflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        gridinflater.inflate(R.layout.book_cover, random_book, true);*/

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
=======
        View v=inflater.inflate(R.layout.fragment1, container, false);
        new_book=(LinearLayout)v.findViewById(R.id.new_book);
        random_book=(GridLayout)v.findViewById(R.id.random_book);
        //progressDialog=new ProgressDialog(getContext());
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Content");
        //progressDialog.setMessage("로딩중입니다...");
        //progressDialog.show();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String json=snapshot.getValue().toString();
                    json=json.replaceAll("\\s", "&");
                    System.out.println(json);
                    try {
                        JSONObject jsonObject=new JSONObject(json);
                        System.out.println(jsonObject);
                        String t=jsonObject.getString("title");
                        String w=jsonObject.getString("&writer");
                        //System.out.println("================================");
                        //System.out.println(t);
                        //System.out.println(w);
                        //System.out.println("================================");
                        t=t.replaceAll("&", " ");
                        w=w.replaceAll("&", " ");
                        totalbook.add(new Totalbook(t, w));
                        addnewbook(t, w);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //progressDialog.dismiss();
        return v;
    }
    public void addnewbook(String title, String writer){
        View childview=getLayoutInflater().inflate(R.layout.book_cover, new_book, false);
        View childview2=getLayoutInflater().inflate(R.layout.randombookcover, random_book, false);
        TextView childwriter=(TextView)childview.findViewById(R.id.writer);
        TextView childtitle=(TextView)childview.findViewById(R.id.title);
        Button childbutton=(Button)childview.findViewById(R.id.chatbutton);//버튼 태그 설정 위한 것
        TextView childwriter2=(TextView)childview2.findViewById(R.id.writer);
        TextView childtitle2=(TextView)childview2.findViewById(R.id.title);
        childwriter.setText(writer);
        childtitle.setText("제목: "+title);
        childbutton.setTag(writer);//버튼 태그를 writer 아이디로 설정, 일종의 꼼수
        childbutton.setOnClickListener(buttonClickListener);
        childwriter2.setText(writer);
        childtitle2.setText("제목: "+title);
        new_book.addView(childview);
        random_book.addView(childview2);
        /*Context context=getContext().getApplicationContext();
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.book_cover, new_book, true);
        LayoutInflater gridinflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        gridinflater.inflate(R.layout.book_cover, random_book, true);*/
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
>>>>>>> master
