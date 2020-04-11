package com.example.homeshef;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;


public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewgroup=(ViewGroup)inflater.inflate(R.layout.fragment1, container, false);
        Button button=viewgroup.findViewById(R.id.edit_button);
        Spinner s=viewgroup.findViewById(R.id.filter_spinner);
        final LinearLayout listView=viewgroup.findViewById(R.id.scroll_container);
        button.setOnClickListener(new View.OnClickListener() {//button 클릭시의 리스너
            @Override
            public void onClick(View v) {
                Context context=getContext().getApplicationContext();//context는 activity 전반적 정보 담김
                //getcontext()는 fragment,adapter,view 위한 것, getapplicationcontext()는 activity위한 것, 여기서는 getSystemService를 사용하기 위해 사용함
                LayoutInflater inflater2=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//따로 만든 레이아웃을 메모리에 inflate 하기 위한 것
                inflater2.inflate(R.layout.cook_text, listView, true);//cook_text layout을 inflater2에 inflate하고 attachtoRoot를 true로 해서
            }                                                                      //아래에 이어 붙여서 쓸 수 있도록 설정(listView 자리에 쌓기)
        });
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){//spinner의 select listener, position에 각각의 index가 담김
                    case 0://필터 없음
                        return;
                    case 1://거리순
                        return;
                    case 2://추천순
                        return;
                    case 3://가격순
                        return;
                    default:
                        return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return viewgroup;
    }
}
