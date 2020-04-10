package com.example.homeshef;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
        fragment3=new Fragment3();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment3).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int select=item.getItemId();
                switch(select){
                    case R.id.menu_cook:
                        if(fragment1==null){

                            fragment1=new Fragment1();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
                        }
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
