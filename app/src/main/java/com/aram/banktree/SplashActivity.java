package com.aram.banktree;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
//데이터를 받아오기 위해 앱을 켜면 보이는 화면
//background에서 서버로부터(firebase) 데이터를 받아온다
public class SplashActivity extends AppCompatActivity {
    public static Context context;
    ImageView logo_image;
    TextView loading;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        logo_image=(ImageView)findViewById(R.id.logo_image);
        loading=(TextView)findViewById(R.id.loading);
        context=this;


        new getData().execute();

    }
    private class getData extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            //ManageTotalbook은 firebase로부터 저장된 책 데이터를 저장하기 위한 class
            //getTotalbook() method를 통해 데이터를 받아오기 까지
            //SystemClock.sleep(6000)을 통해 6초간 시스템을 sleep 시킨다
            ManageTotalbook.getInstance().getTotalbook();
            SystemClock.sleep(6000);
            return null;
            //ManageTotalbook은 Singleton class
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //doInBackground가 끝나면 onPostExecute로 넘어와서 메인 화면으로 넘어간다
            //우선 LoginActivity로 넘어감
            super.onPostExecute(aVoid);
            Intent intent=new Intent(context, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
