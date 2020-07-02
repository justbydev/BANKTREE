package com.aram.banktree;

import android.Manifest;
import android.app.ProgressDialog;//로딩 시 메시지 뜨도록
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    EditText writerName;
    RadioGroup genderradio;
    DatePicker birthpicker;
    Button buttonSignup;
    Button validateemailbutton;
    Button writerbutton;
    TextView writererror;
    TextView textviewSingin;
    TextView textviewMessage;
    ProgressDialog progressDialog;
    CheckBox agree_check;
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
    int writerequal=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializig firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //이미 로그인 되었다면 이 액티비티를 종료함
            finish();
            //그리고 MenuActivity를 연다.
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
        writerName=(EditText)findViewById(R.id.editWriterName);
        genderradio=(RadioGroup)findViewById(R.id.genderradio);
        birthpicker=(DatePicker)findViewById(R.id.birthpicker);
        textviewSingin= (TextView) findViewById(R.id.textViewSignin);
        textviewMessage = (TextView) findViewById(R.id.textviewMessage);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        agree_check=(CheckBox)findViewById(R.id.agree_check);
        writerbutton=(Button)findViewById(R.id.writernameequalbutton);
        writererror=(TextView)findViewById(R.id.writererror);
        progressDialog = new ProgressDialog(this);


        writerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tmp=writerName.getText().toString().trim();
                if(TextUtils.isEmpty(tmp)){
                    Toast.makeText(MainActivity.this, "필명을 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference wrequal=FirebaseDatabase.getInstance().getReference("writername");
                    wrequal.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int flag=0;
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                if(snapshot.getValue()!=null){
                                    if(snapshot.getValue().equals(tmp)){
                                        flag=1;
                                        break;
                                    }
                                }
                            }
                            if(flag==0){
                                writerequal=1;
                                writererror.setText("사용가능한 필명입니다\n");
                            }
                            else{
                                writerequal=0;
                                writererror.setText("이미 사용중인 필명입니다\n");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        //이메일 형식에 맞는지 체크, 이메일 형식에 맞으면 파란색으로 보임
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            //이메일 형식에 맞으면 emailcheck가 1이 된다.
            //여기서 체크한다는 것은 이메일을 다시 새로 쓴다는 것이니까 이메일 인증을 다시 해야함
            //따라서, emailequal, emailvalidatebutton을 0으로 초기화
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
                        //이미 가입되어 있는 이메일을 firebase를 통해 체크해서
                        //이미 가입되어 있으면 emailequal=1, 아니면 emailequal=0
                        //emailequal이 0이 되었을 때 인증 버튼을 누른 것이므로
                        //emailvalidatebutton을 1로 바꾼다
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
                passwordchecktext.setText("영문자, 숫자, 특수문자 포함 9자리 이상으로 해주세요\n");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                password = editTextPassword.getText().toString();
                //비밀번호가 올바른 형식이면 passwordcheck를 1로 바꾼다
                if(password.matches(passwordValidation)){
                    passwordchecktext.setText("올바른 비밀번호 형식입니다\n");
                    passwordcheck=1;
                }
                else{
                    passwordchecktext.setText("영문자, 숫자, 특수문자 포함 9자리 이상으로 해주세요\n");
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
            //비밀번호 재입력을 통해서 다시 한번 확인하는 것으로 처음 적은 비밀번호와 같다면
            //passwordequal은 1, 아니면 0이다
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
                final String writername=writerName.getText().toString().trim();
                final int year=birthpicker.getYear();
                final int month=birthpicker.getMonth()+1;
                final int day=birthpicker.getDayOfMonth();
                if(check_empty(email, password, repassword, name, gender, writername)){
                    if(writerequal==1){
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
                                                    DatabaseReference writerreference=FirebaseDatabase.getInstance().getReference("writername");
                                                    Member member= new Member();
                                                    Memberemail EM=new Memberemail();
                                                    member.setEmail(email);
                                                    member.setName(name);
                                                    member.setGender(finalGender);
                                                    member.setYear(year);
                                                    member.setMonth(month);
                                                    member.setDay(day);
                                                    member.setWritername(writername);
                                                    EM.setEmail(email);
                                                    memberreference.push().setValue(member);
                                                    emailreference.push().setValue(email);
                                                    String tmp=email.replace(".", "-");
                                                    writerreference.child(tmp).setValue(writername);
                                                    DatabaseReference t=FirebaseDatabase.getInstance().getReference(tmp);
                                                    t.push().setValue(writername);
                                                    //회원가입을 하면 우선 firebase에 회원에 대한 정보를 저장
                                                    //firebase 저장시 Member class를 사용
                                                    //회원가입 후 자동 로그인이 아니라 다시 한번 로그인하도록 유도하기 위해 signOut()시키고
                                                    //다시 LoginActivity로 이동시킨다
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
                    else{
                        textviewMessage.setText("필명 중복확인을 해주세요\n");
                    }
                }
            }
        });

        //button click event

        textviewSingin.setOnClickListener(this);
    }
    //모든 것을 입력해야 하므로 한 군데라도 빈 곳이 있으면 알려주는 method
    private boolean check_empty(String email, String password, String repassword, String name, String gender, String writername){
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "성명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(writername)){
            Toast.makeText(this, "필명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
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
        if(!agree_check.isChecked()){
            Toast.makeText(this, "개인정보 수집에 동의해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //이미 가입된 회원이라면 회원가입 창에서 다시 로그인 창으로 이동하는 버튼
    @Override
    public void onClick(View view) {
        if(view == textviewSingin) {
            //TODO

            startActivity(new Intent(this, LoginActivity.class)); //추가해 줄 로그인 액티비티
            finish();
        }
    }
}