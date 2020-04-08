package com.example.homeshef;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();
        fragment4=new Fragment4();
        fragment5=new Fragment5();
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int select=item.getItemId();
                switch(select){
                    case R.id.menu_cook:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return true;
                    case R.id.menu_user:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                        return true;
                    case R.id.menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                        return true;
                    case R.id.menu_chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();
                        return true;
                    case R.id.menu_mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment5).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}
