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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Newbook extends AppCompatActivity {
    Button color;
    //Button costset_button;
    Button commuteset_button;
    //Button mentoragree_button;
    Button cancel;
    Button share;
    Button page_add;
    EditText book_title;
    ImageView book_content;
    EditText content_write;
    Spinner category;
    public static Context newbookcontext;
    int contentdefaultcolor;
    public int costset=0;
    public String cst=null;
    int commutset=0;
    int mentoragree=0;
    static final int REQUEST_IMAGE_CODE=1001;
    static final int REQUEST_CAMERA_CODE=1002;

    /*ViewPager vp;
    contentpagerAdapter mAdapter;
    Anotherpage anp;
    Viewpagerbase bp;
    int nowpage=0;*/
    Viewpagerbase viewpagerbase;
    int nowpage=2;
    int cat=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbook);
        newbookcontext=this;
        color=(Button)findViewById(R.id.color);
        //costset_button=(Button)findViewById(R.id.costset_button);
        commuteset_button=(Button)findViewById(R.id.commuteset_button);
        //mentoragree_button=(Button)findViewById(R.id.mentoragree_button);
        cancel=(Button)findViewById(R.id.cancel);
        share=(Button)findViewById(R.id.share);
        page_add=(Button)findViewById(R.id.page_add);
        book_title=(EditText)findViewById(R.id.book_title);
        book_content=(ImageView)findViewById(R.id.book_content);
        content_write=(EditText)findViewById(R.id.content_write);
        category=(Spinner)findViewById(R.id.category);

        //글추가 눌렀을 때 옆으로 넘기게 fragment 추가되는 역할하는 viewpagerbase
        viewpagerbase=(Viewpagerbase)this.getSupportFragmentManager().findFragmentById(R.id.viewpagerbase);
        //vp=(ViewPager)findViewById(R.id.viewpager_content);
        //mAdapter=new contentpagerAdapter(getSupportFragmentManager());
        //vp.setAdapter(mAdapter);
        //Viewpagerbase fragment=(Viewpagerbase) mAdapter.getfragment(0);
        //fragment.setpagenumber(1);

        contentdefaultcolor= ContextCompat.getColor(this, R.color.design_default_color_secondary_variant);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        color.setOnClickListener(buttonClickListener);
        page_add.setOnClickListener(buttonClickListener);
        //costset_button.setOnClickListener(buttonClickListener);
        commuteset_button.setOnClickListener(buttonClickListener);
        //mentoragree_button.setOnClickListener(buttonClickListener);
        cancel.setOnClickListener(buttonClickListener);
        share.setOnClickListener(buttonClickListener);

    }//end of oncreate

    private View.OnClickListener buttonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                /*case R.id.image://앨범에서 이미지 선택
                    if(Build.VERSION.SDK_INT>=23){//버전이 23이상인 경우 권한 체크
                        int permissionReadStorage=ContextCompat.checkSelfPermission(Newbook.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                        int permissionWriteStorage=ContextCompat.checkSelfPermission(Newbook.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if(permissionReadStorage== PackageManager.PERMISSION_DENIED || permissionWriteStorage==PackageManager.PERMISSION_DENIED){
                            ActivityCompat.requestPermissions(Newbook.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_CODE);
                        }
                        else{//이미 권한 허락이 되어 있으면 갤러리로 가서 사진 가져옴
                            Intent imageintent=new Intent(Intent.ACTION_PICK);
                            imageintent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                            imageintent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(imageintent, REQUEST_IMAGE_CODE);
                        }
                    }
                    break;*/
                case R.id.color://단색 선택 연결
                    openColorPicker();
                    return;
                case R.id.page_add://새로운 페이지 추가하는 것, viewpagerbase 이용
                    if(nowpage>50){
                        Toast.makeText(Newbook.this, "최대 50페이지까지 가능합니다\n", Toast.LENGTH_LONG).show();
                    }
                    else{
                        viewpagerbase.addpage(nowpage+"");
                        nowpage=nowpage+1;
                    }
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
                case R.id.cancel:
                    ((MenuActivity)MenuActivity.menucontext).which=0;
                    finish();
                    return;
                case R.id.share://공유 버튼 누르는 경우 Bookcontent class 이용해서
                    //firebase에 저장하고 Menuactivity에서 which를 1로 만듬

                    if(cat==0){
                        Toast.makeText(Newbook.this, "카테고리를 선택해주세요", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DatabaseReference contentreference= FirebaseDatabase.getInstance().getReference("Content");
                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    String email=firebaseUser.getEmail();
                    String title=book_title.getText().toString();
                    int totalpage=viewpagerbase.gettotalpage();
                    String page=Integer.toString(totalpage);
                    Bookcontent bookcontent=new Bookcontent(ManageTotalbook.getInstance().getFakename(), totalpage);
                    bookcontent.setTitle(title);
                    ArrayList<String> content;
                    ArrayList<String> color;
                    content=new ArrayList<>();
                    color=new ArrayList<>();
                    //bookcontent.color=new int[totalpage];
                    for(int i=0; i<totalpage; i++){
                        bookcontent.setContent(viewpagerbase.getcontent(i));
                        content.add(viewpagerbase.getcontent(i));
                        bookcontent.setColor(viewpagerbase.getcolor(i));
                        color.add(Integer.toString(viewpagerbase.getcolor(i)));
                    }
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal=Calendar.getInstance();
                    bookcontent.setDate(simpleDateFormat.format(cal.getTime()));
                    String date=simpleDateFormat.format(cal.getTime());
                    bookcontent.setCat(cat);
                    bookcontent.setChat(commutset);
                    contentreference.push().setValue(bookcontent);
                    ((MenuActivity)MenuActivity.menucontext).title=title;
                    ((MenuActivity)MenuActivity.menucontext).writer=email;
                    ((MenuActivity)MenuActivity.menucontext).page=page;
                    ((MenuActivity)MenuActivity.menucontext).content=content;
                    ((MenuActivity)MenuActivity.menucontext).color=color;
                    ((MenuActivity)MenuActivity.menucontext).date=date;
                    ((MenuActivity)MenuActivity.menucontext).which=1;
                    ((MenuActivity)MenuActivity.menucontext).cat=cat;
                    ((MenuActivity)MenuActivity.menucontext).chat=commutset;
                    ((MenuActivity)MenuActivity.menucontext).fakename=ManageTotalbook.getInstance().getFakename();
                    finish();
                    return;
                default:
                    break;
            }
        }
    };

    //단색 체크하는 기능
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(this, contentdefaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                contentdefaultcolor=color;
                viewpagerbase.changecolor(contentdefaultcolor);
                //Viewpagerbase fragment=(Viewpagerbase) mAdapter.getfragment(1);
                //fragment.changecolor(contentdefaultcolor);
                //book_content.setImageBitmap(null);
                //book_content.setBackgroundColor(contentdefaultcolor);
            }
        });
        colorPicker.show();
    }

    //권한 체크 이후 들어오게 되는 override method
    /*@Override
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
            default:
                break;
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((MenuActivity)MenuActivity.menucontext).which=0;
        finish();
    }
}
