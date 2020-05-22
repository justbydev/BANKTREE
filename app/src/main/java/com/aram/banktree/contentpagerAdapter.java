package com.aram.banktree;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

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
