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

            ManageTotalbook.getInstance().getTotalbook();
            SystemClock.sleep(6000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent=new Intent(context, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
