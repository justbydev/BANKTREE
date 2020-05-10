package com.aram.banktree;

import android.app.ProgressDialog;//로딩 시 메시지 뜨도록
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aram.banktree.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //define view objects
    EditText editTextEmail;
    TextView emailerror;
    EditText editTextPassword;
    TextView passwordchecktext;
    EditText editTextPassword2;
    TextView passwordequaltext;
    EditText editName;
    RadioGroup genderradio;
    DatePicker birthpicker;
    Button buttonSignup;
    Button validateemailbutton;
    TextView textviewSingin;
    TextView textviewMessage;
    ProgressDialog progressDialog;
    private String email;
    private String password;
    private String repassword;
    //define firebase object
    FirebaseAuth firebaseAuth;
    String emailValidation="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String passwordValidation="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}.$";
    int emailcheck=0;
    int emailequal=0;
    int passwordcheck=0;
    int passwordequal=0;
    int emailvalidatebutton=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializig firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //이미 로그인 되었다면 이 액티비티를 종료함
            finish();
            //그리고 profile 액티비티를 연다.
            startActivity(new Intent(getApplicationContext(), MenuActivity.class)); //추가해 줄 ProfileActivity
        }
        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        emailerror=(TextView)findViewById(R.id.emailerror);
        validateemailbutton=(Button)findViewById(R.id.validateemailbutton);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        passwordchecktext=(TextView)findViewById(R.id.passwordchecktext);
        editTextPassword2=(EditText)findViewById(R.id.editTextPassword2);
        passwordequaltext=(TextView)findViewById(R.id.passwordequaltext);
        editName=(EditText)findViewById(R.id.editTextName);
        genderradio=(RadioGroup)findViewById(R.id.genderradio);
        birthpicker=(DatePicker)findViewById(R.id.birthpicker);
        textviewSingin= (TextView) findViewById(R.id.textViewSignin);
        textviewMessage = (TextView) findViewById(R.id.textviewMessage);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);

        //이메일 형식에 맞는지 체크, 이메일 형식에 맞으면 파란색으로 보임
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                email = editTextEmail.getText().toString().trim();
                if(email.matches(emailValidation)){
                    editTextEmail.setTextColor(Color.BLUE);
                    emailcheck=1;
                    emailequal=0;
                    emailvalidatebutton=0;
                }
                else{
                    editTextEmail.setTextColor(Color.BLACK);
                    emailcheck=0;
                    emailvalidatebutton=0;
                    emailequal=0;
                }
            }
        });
        validateemailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email==null || email.length()==0){
                    emailerror.setText("이메일을 입력하세요\n");
                }
                else{
                    DatabaseReference checkequal=FirebaseDatabase.getInstance().getReference("Memberemail");
                    checkequal.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                if(snapshot.getValue()!=null){
                                    if(snapshot.getValue().equals(email)){
                                        emailequal=1;
                                    }
                                    System.out.println(snapshot.getValue());
                                    if(emailequal==1){
                                        emailerror.setText("이미 가입된 이메일입니다\n");
                                        break;
                                    }
                                }
                            }
                            if(emailequal==0){
                                emailerror.setText("인증 완료\n");
                                emailvalidatebutton=1;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        //비밀번호 형식에 맞는지 체크, 맞으면 올바른 비밀번호라고 알려줌
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                passwordchecktext.setText("영문자, 숫자, 특수문자 포함 8자리 이상으로 해주세요\n");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                password = editTextPassword.getText().toString();
                if(password.matches(passwordValidation)){
                    passwordchecktext.setText("올바른 비밀번호 형식입니다\n");
                    passwordcheck=1;
                }
                else{
                    passwordchecktext.setText("영문자, 숫자, 특수문자 포함 8자리 이상으로 해주세요\n");
                    passwordcheck=0;
                }
            }
        });
        editTextPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                passwordequaltext.setText("비밀번호가 일치하지 않습니다\n");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                repassword=editTextPassword2.getText().toString();
                if(password.equals(repassword)){
                    passwordequaltext.setText("비밀번호가 일치합니다\n");
                    passwordequal=1;
                }
                else{
                    passwordequaltext.setText("비밀번호가 일치하지 않습니다\n");
                    passwordequal=0;
                }
            }
        });


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=editName.getText().toString().trim();
                String gender=null;
                if(genderradio.getCheckedRadioButtonId()!=-1){
                    RadioButton rd=(RadioButton)findViewById(genderradio.getCheckedRadioButtonId());
                    gender=rd.getText().toString().trim();
                }
                final int year=birthpicker.getYear();
                final int month=birthpicker.getMonth()+1;
                final int day=birthpicker.getDayOfMonth();
                if(check_empty(email, password, repassword, name, gender)){
                    if(passwordcheck==1 && passwordequal==1){
                        if(emailcheck==1 && emailequal==0 && emailvalidatebutton==1) {
                            //email과 password가 제대로 입력되어 있다면 계속 진행된다.
                            progressDialog.setMessage("등록중입니다. 기다려 주세요...");
                            progressDialog.show();
                            final String finalGender = gender;
                            firebaseAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                DatabaseReference memberreference=FirebaseDatabase.getInstance().getReference("Member");
                                                DatabaseReference emailreference=FirebaseDatabase.getInstance().getReference("Memberemail");
                                                Member member= new Member();
                                                Memberemail EM=new Memberemail();
                                                member.setEmail(email);
                                                member.setName(name);
                                                member.setGender(finalGender);
                                                member.setYear(year);
                                                member.setMonth(month);
                                                member.setDay(day);
                                                EM.setEmail(email);
                                                memberreference.push().setValue(member);
                                                emailreference.push().setValue(email);
                                                firebaseAuth.signOut();
                                                finish();
                                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                            }
                                            else{
                                                textviewMessage.setText("이미 가입된 이메일입니다\n");
                                            }
                                            progressDialog.dismiss();
                                        }
                                    });
                        }
                        else{
                            textviewMessage.setText("이메일 인증을 받으셔야 합니다.\n");
                        }
                    }
                    else{
                        textviewMessage.setText("올바른 비밀번호를 입력해 주세요\n");
                    }
                }
            }
        });

        //button click event

        textviewSingin.setOnClickListener(this);
    }
    private boolean check_empty(String email, String password, String repassword, String name, String gender){
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "성명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(repassword)){
            Toast.makeText(this, "Password를 재입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "성별을 체크해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //button click event
    @Override
    public void onClick(View view) {
        if(view == textviewSingin) {
            //TODO
            startActivity(new Intent(this, LoginActivity.class)); //추가해 줄 로그인 액티비티
        }
    }
}