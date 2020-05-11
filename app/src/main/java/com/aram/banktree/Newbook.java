package com.aram.banktree;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
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
    int costset=0;
    int commutset=0;
    int mentoragree=0;
    int REQUEST_IMAGE_CODE=1001;
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
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageintent=new Intent(Intent.ACTION_PICK);
                imageintent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                imageintent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imageintent, REQUEST_IMAGE_CODE);
            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        costset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(costset==0){
                    costset=1;
                    costset_button.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
                }
                else if(costset==1){
                    costset=0;
                    costset_button.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled));
                }
            }
        });
        commuteset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commutset==0){
                    commutset=1;
                    commuteset_button.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
                }
                else if(commutset==1){
                    commutset=0;
                    commuteset_button.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled));
                }
            }
        });
        mentoragree_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mentoragree==0){
                    mentoragree=1;
                    mentoragree_button.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
                }
                else if(mentoragree==1){
                    mentoragree=0;
                    mentoragree_button.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled));
                }
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
