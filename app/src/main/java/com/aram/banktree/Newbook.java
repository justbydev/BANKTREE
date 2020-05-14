package com.aram.banktree;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    ImageView book_content;
    EditText content_write;
    public static Context newbookcontext;
    int contentdefaultcolor;
    public int costset=0;
    public String cst=null;
    int commutset=0;
    int mentoragree=0;
    static final int REQUEST_IMAGE_CODE=1001;
    static final int REQUEST_CAMERA_CODE=1002;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbook);
        newbookcontext=this;
        color=(Button)findViewById(R.id.color);
        image=(Button)findViewById(R.id.image);
        picture=(Button)findViewById(R.id.picture);
        costset_button=(Button)findViewById(R.id.costset_button);
        commuteset_button=(Button)findViewById(R.id.commuteset_button);
        mentoragree_button=(Button)findViewById(R.id.mentoragree_button);
        cancel=(Button)findViewById(R.id.cancel);
        share=(Button)findViewById(R.id.share);
        book_title=(EditText)findViewById(R.id.book_title);
        book_content=(ImageView)findViewById(R.id.book_content);
        content_write=(EditText)findViewById(R.id.content_write);

        contentdefaultcolor= ContextCompat.getColor(this, R.color.design_default_color_secondary_variant);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MenuActivity)MenuActivity.menucontext).which=0;
                finish();
            }
        });
        image.setOnClickListener(buttonClickListener);
        picture.setOnClickListener(buttonClickListener);
        color.setOnClickListener(buttonClickListener);
        costset_button.setOnClickListener(buttonClickListener);
        commuteset_button.setOnClickListener(buttonClickListener);
        mentoragree_button.setOnClickListener(buttonClickListener);

    }//end of oncreate

    private View.OnClickListener buttonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.image:
                    if(Build.VERSION.SDK_INT>=23){
                        int permissionReadStorage=ContextCompat.checkSelfPermission(Newbook.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                        int permissionWriteStorage=ContextCompat.checkSelfPermission(Newbook.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if(permissionReadStorage== PackageManager.PERMISSION_DENIED || permissionWriteStorage==PackageManager.PERMISSION_DENIED){
                            ActivityCompat.requestPermissions(Newbook.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_CODE);
                        }
                        else{
                            Intent imageintent=new Intent(Intent.ACTION_PICK);
                            imageintent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                            imageintent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(imageintent, REQUEST_IMAGE_CODE);
                        }
                    }
                    break;
                case R.id.picture:
                    if(Build.VERSION.SDK_INT>=23){
                        int permissioncamera=ContextCompat.checkSelfPermission(Newbook.this, Manifest.permission.CAMERA);
                        if(permissioncamera==PackageManager.PERMISSION_DENIED){
                            ActivityCompat.requestPermissions(Newbook.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
                        }
                    }
                    break;
                case R.id.color:
                    openColorPicker();
                    return;
                case R.id.costset_button:
                    CostCustomDialog customDialog=new CostCustomDialog(Newbook.this);
                    customDialog.callFunction(costset_button);
                    return;
                case R.id.commuteset_button:
                    if(commutset==0){
                        commutset=1;
                        commuteset_button.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
                    }
                    else if(commutset==1){
                        commutset=0;
                        commuteset_button.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled));
                    }
                    return;
                case R.id.mentoragree_button:
                    if(mentoragree==0){
                        mentoragree=1;
                        mentoragree_button.setBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
                    }
                    else if(mentoragree==1){
                        mentoragree=0;
                        mentoragree_button.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled));
                    }
                    return;
                default:
                    break;
            }
        }
    };

    public void openColorPicker(){
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(this, contentdefaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                contentdefaultcolor=color;
                book_content.setImageBitmap(null);
                book_content.setBackgroundColor(contentdefaultcolor);
            }
        });
        colorPicker.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_IMAGE_CODE:
                int cnt=0;
                for(int i=0; i<permissions.length; i++){
                    String permission=permissions[i];
                    int grantResult=grantResults[i];
                    if(permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                    if(permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                }
                if(cnt==2){
                    Intent imageintent=new Intent(Intent.ACTION_PICK);
                    imageintent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                    imageintent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(imageintent, REQUEST_IMAGE_CODE);
                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(Newbook.this);
                    builder.setTitle("알림");
                    builder.setMessage("[설정]->[권한]에서\n권한을 허용해주세요.\n");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.create().show();
                }
                break;
            case REQUEST_CAMERA_CODE:
                String permission=permissions[0];
                int grantResult=grantResults[0];
                int cameraflag=0;
                if(permission.equals(Manifest.permission.CAMERA)){
                    if(grantResult==PackageManager.PERMISSION_GRANTED){
                        cameraflag=1;
                    }
                }
                if (cameraflag == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Newbook.this);
                    builder.setTitle("알림");
                    builder.setMessage("[설정]->[권한]에서\n권한을 허용해주세요.\n");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.create().show();
                }
                return;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_IMAGE_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Uri image = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                        book_content.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_light));
                        book_content.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case REQUEST_CAMERA_CODE:
                break;
            default:
                break;
        }
    }
}
