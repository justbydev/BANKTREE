package com.aram.banktree;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//각각의 페이지에 대한 정보를 저장하기 위한 class
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

        content_write.addTextChangedListener(new TextWatcher() {
            String previous;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previous=s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(content_write.getLineCount()>=16){
                    content_write.setText(previous);
                    content_write.setSelection(content_write.length());
                    Toast.makeText(getContext(), "페이지당 최대 15줄입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
    public String getcontent(){
        String content=content_write.getText().toString();
        return content;
    }
    public int getcolor(){
        ColorDrawable drawable=(ColorDrawable)book_content.getBackground();
        int color=drawable.getColor();
        return color;
    }
}
