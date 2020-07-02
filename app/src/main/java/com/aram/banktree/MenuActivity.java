package com.aram.banktree;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aram.banktree.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    TextView logbutton;
    BottomNavigationView bottomNavigationView;
    FirebaseAuth firebaseAuth;
    public int which=-1;
    public static Context menucontext;
    public String title;
    public String writer;
    public ArrayList<String> content;
    public ArrayList<String> color;
    public String page;
    public String date;
    public int cat;
    public String fakename;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);



        which=0;
        content=new ArrayList<>();
        color=new ArrayList<>();
        menucontext=this;
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        logbutton=findViewById(R.id.logbutton);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            logbutton.setText("로그아웃");
            String email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
            String temp=email.replace(".", "-");
            DatabaseReference d= FirebaseDatabase.getInstance().getReference(temp);
            d.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        ManageTotalbook.getInstance().setFakename(snapshot.getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            logbutton.setText("로그인");
        }
        //사실 로그인을 무조건 히야만 MenuActivity로 올 수 있기 때문에 로그아웃 기능만 있으면 된다
        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        bottomNavigationView.getMenu().getItem(0).setChecked(true);//처음 작동시킬 때 홈 화면 탭 버튼이 체크되도록
        fragment1=new Fragment1();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();//처음 작동시킬 때 홈 화면 fragment가 보이도록
        fragment3=new Fragment3();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment3).commit();
        //getSupportFragmentManager를 통해서 쉽게 fragment를 가져올 수 있다
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {//bottomnavigationview의 selected 리스너
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int select=item.getItemId();
                switch(select){
                    case R.id.menu_home://각각의 하단 탭을 선택할 시, switch~case 문 사용, 5가지 모두 같은 방법을 사용
                        if(fragment1==null){//아직 생성된 것이 아니면 new Fragment()를 통해서 객체 생성을 해야 하므로

                            fragment1=new Fragment1();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();//replace 대신 add를 사용,
                        }                                                                               //replace는 기존 Activity에 있던 fragment를 완전히 없애고 새로운 것을 얹기에 기존 데이터가 사라지지만
                        if(fragment1!=null){                                                    //add는 없애지 않고 fragment stack에 쌓아두는 것, 그래서 데이터가 유지 가능
                            getSupportFragmentManager().beginTransaction().show(fragment1).commit();//add는 각각 fragment마다 한번씩만 해서 stack overflow가 발생하지 않도록 하고 hide,show 사용
                        }                                                           //쌓아있는 fragment 중에서 보일 것은 show()를 하고 숨길 것은 hide()를 한다
                        if(fragment2!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
                        }
                        if(fragment3!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment3).commit();
                        }
                        if(fragment4!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
                        }
                        return true;
                    case R.id.menu_mentor:
                        if(fragment2==null){
                            fragment2=new Fragment2();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment2).commit();
                        }
                        if(fragment1!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
                        }
                        if(fragment2!=null){
                            getSupportFragmentManager().beginTransaction().show(fragment2).commit();
                        }
                        if(fragment3!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment3).commit();
                        }
                        if(fragment4!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
                        }
                        return true;
                    case R.id.menu_chat:
                        if(fragment3==null){
                            fragment3=new Fragment3();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment3).commit();
                        }
                        if(fragment1!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
                        }
                        if(fragment2!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
                        }
                        if(fragment3!=null){
                            getSupportFragmentManager().beginTransaction().show(fragment3).commit();
                        }
                        if(fragment4!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
                        }
                        return true;
                    case R.id.menu_mypage:
                        if(fragment4==null){
                            fragment4=new Fragment4();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment4).commit();
                        }
                        if(fragment1!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
                        }
                        if(fragment2!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
                        }
                        if(fragment3!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment3).commit();
                        }
                        if(fragment4!=null){
                            getSupportFragmentManager().beginTransaction().show(fragment4).commit();
                        }
                        return true;
                    case R.id.menu_plus:
                        //글 추가 기능만 Activity로써 Newbook Activity가 그 기능을 하게 된다
                        //다른 버튼은 전부 Fragment이다
                        startActivity(new Intent(MenuActivity.this, Newbook.class));
                        return true;
                    default:
                        return true;
                }
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(which==0){//글 추가 누른 후 그냥 취소하거나 back button 누르면 오게 되는 case, 평상이 back button 누르면 오는 case
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
            if(fragment1!=null){
                getSupportFragmentManager().beginTransaction().show(fragment1).commit();
            }
            if(fragment2!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
            }
            if(fragment3!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment3).commit();
            }
            if(fragment4!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
            }
        }
        else if(which==1){//글 추가 완료 후 오게 되는 case
            if(fragment1!=null){
                //fragment1.addnewbook(title, writer);
                //fragment1의 method를 사용하는 것으로 이것을 통해 recyclerview에 새로 등록된 책을 추가한다

                fragment1.addnewbooktorecycler(title, writer, page, content, color, date, cat);
                if(fragment2!=null){
                    fragment2.randombookAdapter.changedata(ManageTotalbook.getInstance().getwantwriter(ManageTotalbook.getInstance().getFakename()));
                    fragment2.historyAdapter.changedata(ManageTotalbook.getInstance().getwantwriter(ManageTotalbook.getInstance().getFakename()));
                }
            }
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
            if(fragment1!=null){
                getSupportFragmentManager().beginTransaction().show(fragment1).commit();
            }
            if(fragment2!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
            }
            if(fragment3!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment3).commit();
            }
            if(fragment4!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
            }
        }
        else if(which==2){//어떤 경우이든 채팅방을 열게 되면 EachChat activity로 가게 되는데 들어가는 순간 which를 2로 바꾼다
            //따라서, 개인 채팅방을 닫게 되면 이곳으로 오게 되는 case
            if(fragment3==null){
                fragment3=new Fragment3();
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment3).commit();
            }
            bottomNavigationView.getMenu().getItem(3).setChecked(true);
            if(fragment1!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
            }
            if(fragment2!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
            }
            if(fragment3!=null){
                getSupportFragmentManager().beginTransaction().show(fragment3).commit();
            }
            if(fragment4!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
            }
        }
        else if(which==3){//마이페이지에서 책 눌러서 본 후
            if(fragment2==null){
                fragment2=new Fragment2();
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragment2).commit();
            }
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
            if(fragment1!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
            }
            if(fragment2!=null){
                getSupportFragmentManager().beginTransaction().show(fragment2).commit();
            }
            if(fragment3!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment3).commit();
            }
            if(fragment4!=null){
                getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
            }
        }
    }

    //홈 화면의 신규 책 목록에 있는 채팅 버튼을 누른 경우 MenuActivity의 changechat method로 간 후
    //fragment3의 settingchat으로 가면 Eachchat activity를 호출해서 개인 채팅방이 열린다
    public void changechat(String want, String me){
        //bottomNavigationView.getMenu().getItem(3).setChecked(true);
        if(fragment3==null){
            fragment3=new Fragment3();
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment3).commit();
        }
        /*if(fragment1!=null){
            getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
        }
        if(fragment2!=null){
            getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
        }
        if(fragment3!=null){
            getSupportFragmentManager().beginTransaction().show(fragment3).commit();
        }
        if(fragment4!=null){
            getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
        }*/
        fragment3.settingchat(want, me);
    }
}