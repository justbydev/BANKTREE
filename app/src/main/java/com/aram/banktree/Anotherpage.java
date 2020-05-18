package com.aram.banktree;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Anotherpage extends Fragment {
    TextView page;
    ImageView book_content;
    EditText content_write;
    String nowpage=null;
    public Anotherpage(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.anotherpage, container, false);
        Bundle bundle=getArguments();
        if(bundle!=null){
            nowpage=bundle.getString("page");
        }
        book_content=(ImageView)v.findViewById(R.id.book_content);
        content_write=(EditText)v.findViewById(R.id.content_write);
        page=(TextView)v.findViewById(R.id.otherpage);
        if(nowpage==null || nowpage.length()==0){
            page.setText(1+"");
        }
        else{
            page.setText(nowpage);
        }
        return v;
    }
    public void changecolor(){
        Bundle bundle=getArguments();
        int color=0;
        if(bundle!=null){
            color=bundle.getInt("color");
            book_content.setImageBitmap(null);
            book_content.setBackgroundColor(color);
        }
    }
}
