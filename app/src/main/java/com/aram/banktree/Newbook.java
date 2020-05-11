package com.aram.banktree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Newbook extends AppCompatActivity {
    Button color;
    Button image;
    Button picture;
    Button costset_button;
    Button commuteset_button;
    Button mentoragree_button;
    Button cancel;
    Button share;
    EditText book_title;
    EditText book_content;
    int contentdefaultcolor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbook);
        color=(Button)findViewById(R.id.color);
        image=(Button)findViewById(R.id.image);
        picture=(Button)findViewById(R.id.picture);
        costset_button=(Button)findViewById(R.id.costset_button);
        commuteset_button=(Button)findViewById(R.id.commuteset_button);
        mentoragree_button=(Button)findViewById(R.id.mentoragree_button);
        cancel=(Button)findViewById(R.id.cancel);
        share=(Button)findViewById(R.id.share);
        book_title=(EditText)findViewById(R.id.book_title);
        book_content=(EditText)findViewById(R.id.book_content);

        contentdefaultcolor= ContextCompat.getColor(this, R.color.design_default_color_secondary_variant);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuActivity)MenuActivity.menucontext).which=0;
                finish();
            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });
    }
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(this, contentdefaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                contentdefaultcolor=color;
                book_content.setBackgroundColor(contentdefaultcolor);
            }
        });
        colorPicker.show();
    }

}
