package com.aram.banktree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class EachBookAdapter extends PagerAdapter {
    private ArrayList<String> content;
    private ArrayList<String> color;
    private Context context;

    public EachBookAdapter(Context context, ArrayList<String> content, ArrayList<String> color){
        this.content=content;
        this.color=color;
        this.context=context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.eachbookpage, null);
        ImageView eachbook_background=view.findViewById(R.id.eachbook_background);
        TextView eachbook_content=view.findViewById(R.id.eachbook_content);
        TextView eachbook_page=view.findViewById(R.id.eachbook_page);
        String tmp=color.get(position);
        int sub=0;
        System.out.println(tmp);
        if(tmp.substring(0, 1).equals("-")){
            tmp=tmp.substring(1);
            sub=1;
        }
        if(tmp.substring(1, 2).equals("-")){
            tmp=tmp.substring(2);
            sub=1;
        }
        int temp=Integer.parseInt(tmp);
        if(sub==1){
            temp=(-1)*temp;
        }
        eachbook_background.setBackgroundColor(temp);
        if(content!=null&&content.size()!=0&&content.get(position)!=null&&content.get(position)!=""&&content.get(position).length()!=0&&content.get(position)!=" "){
            eachbook_content.setText(content.get(position));
        }
        eachbook_page.setText(Integer.toString(position+1));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return color==null?0:color.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(View)object);
    }
}
