package com.aram.banktree;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

//새로은 책 추가 눌렀을 때 새로운 페이지 추가해서 fragment 추가하는 역할
public class Viewpagerbase extends Fragment {
    public Viewpagerbase(){}
    private ViewPager viewPager;
    private contentpagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.viewpager_base, container, false);
        viewPager=(ViewPager)v.findViewById(R.id.viewpager_content);
        adapter=new contentpagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(50);
        return v;
    }
    /*public void changecolor(int color){
        book_content.setImageBitmap(null);
        book_content.setBackgroundColor(color);
    }*/
    public void addpage(String page){
        Bundle bundle=new Bundle();
        bundle.putString("page", page);
        Anotherpage anotherpage=new Anotherpage();
        anotherpage.setArguments(bundle);
        adapter.addFragment(anotherpage);
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(Integer.parseInt(page));
    }

    public void changecolor(int color){
        int nowposition=viewPager.getCurrentItem();
        Anotherpage anotherpage=(Anotherpage)adapter.getfragment(nowposition);
        Bundle bundle=new Bundle();
        bundle.putInt("color", color);
        anotherpage.setArguments(bundle);
        anotherpage.changecolor();
    }
    public int gettotalpage(){
        return adapter.getCount();
    }
    public String getcontent(int position){
        Anotherpage an=(Anotherpage)adapter.getfragment(position);
        return an.getcontent();
    }
    public int getcolor(int position){
        Anotherpage an=(Anotherpage)adapter.getfragment(position);
        return an.getcolor();
    }
}
