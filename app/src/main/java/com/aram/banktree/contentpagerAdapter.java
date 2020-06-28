package com.aram.banktree;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
//전자책 추가 당시 새로운 페이지를 추가할 때 사용하는 viewpager를 위한 Adapter
//또한 추가될 때 화면 하나 하나는 Anotherpage class를 통해 정보를 저장한다(내용, 색, 페이지)
public class contentpagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mData=new ArrayList<>();
    public contentpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mData.add(new Anotherpage());
    }
    public void addFragment(Fragment fragment){
        mData.add(fragment);
        //notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public Fragment getfragment(int position){
        return mData.get(position);
    }

}
