package com.aram.banktree;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aram.banktree.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);//처음 작동시킬 때 홈 화면 탭 버튼이 체크되도록
        fragment3=new Fragment3();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment3).commit();//처음 작동시킬 때 홈 화면 fragment가 보이도록
        //getSupportFragmentManager를 통해서 쉽게 fragment를 가져올 수 있다
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {//bottomnavigationview의 selected 리스너
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int select=item.getItemId();
                switch(select){
                    case R.id.menu_cook://각각의 하단 탭을 선택할 시, switch~case 문 사용, 5가지 모두 같은 방법을 사용
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
                        if(fragment5!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment5).commit();
                        }
                        return true;
                    case R.id.menu_user:
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
                        if(fragment5!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment5).commit();
                        }
                        return true;
                    case R.id.menu_home:
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
                        if(fragment5!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment5).commit();
                        }
                        return true;
                    case R.id.menu_chat:
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
                        if(fragment5!=null){
                            getSupportFragmentManager().beginTransaction().hide(fragment5).commit();
                        }
                        return true;
                    case R.id.menu_mypage:
                        if(fragment5==null){
                            fragment5=new Fragment5();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment5).commit();
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
                            getSupportFragmentManager().beginTransaction().hide(fragment4).commit();
                        }
                        if(fragment5!=null){
                            getSupportFragmentManager().beginTransaction().show(fragment5).commit();
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}
