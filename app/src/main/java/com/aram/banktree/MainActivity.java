package com.aram.banktree;

import android.app.ProgressDialog;//로딩 시 메시지 뜨도록
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aram.banktree.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //define view objects
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextPassword2;
    EditText editName;
    RadioGroup genderradio;
    DatePicker birthpicker;
    Button buttonSignup;
    Button validateemailbutton;
    TextView textviewSingin;
    TextView textviewMessage;
    TextView emailerrortext;
    ProgressDialog progressDialog;
    //define firebase object
    FirebaseAuth firebaseAuth;
    int emailcheck=0;

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
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class)); //추가해 줄 ProfileActivity
        }
        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword2=(EditText)findViewById(R.id.editTextPassword2);
        editName=(EditText)findViewById(R.id.editTextName);
        genderradio=(RadioGroup)findViewById(R.id.genderradio);
        birthpicker=(DatePicker)findViewById(R.id.birthpicker);
        textviewSingin= (TextView) findViewById(R.id.textViewSignin);
        textviewMessage = (TextView) findViewById(R.id.textviewMessage);
        emailerrortext=(TextView)findViewById(R.id.emailerrortext);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        validateemailbutton=(Button)findViewById(R.id.validateemailbutton);
        progressDialog = new ProgressDialog(this);

        //사용자가 입력하는 email, password, name, gender, birth를 가져온다.
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString();
        final String repassword=editTextPassword2.getText().toString();
        final String name=editName.getText().toString().trim();
        String gender=null;
        final String realgender;
        if(genderradio.getCheckedRadioButtonId()!=-1){
            RadioButton rd=(RadioButton)findViewById(genderradio.getCheckedRadioButtonId());
            gender=rd.getText().toString().trim();
        }
        realgender=gender;
        final int year=birthpicker.getYear();
        final int month=birthpicker.getMonth()+1;
        final int day=birthpicker.getDayOfMonth();
        validateemailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailcheck=checkemail(email);
            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_empty(email, password, repassword, name, realgender)){
                    if(checkpassword(password, repassword)){
                        if(emailcheck==1) {
                            //email과 password가 제대로 입력되어 있다면 계속 진행된다.
                            progressDialog.setMessage("등록중입니다. 기다려 주세요...");
                            progressDialog.show();
                            firebaseAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                DatabaseReference memberreference=FirebaseDatabase.getInstance().getReference("Member");
                                                Member member= new Member();
                                                member.setEmail(email);
                                                member.setName(name);
                                                member.setGender(realgender);
                                                member.setYear(year);
                                                member.setMonth(month);
                                                member.setDay(day);
                                                memberreference.child("mem").push().setValue(member);
                                                finish();
                                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                            }
                                            else{
                                                textviewMessage.setText("서버 에러\n");
                                            }
                                            progressDialog.dismiss();
                                        }
                                    });
                        }
                        else{
                            textviewMessage.setText("이메일 인증을 받으셔야 합니다.\n");
                        }
                    }
                }
            }
        });

        //button click event

        textviewSingin.setOnClickListener(this);
    }
    private boolean check_empty(String email, String password, String repassword, String name, String gender){
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
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "성명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
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
    private boolean checkpassword(String password, String repassword){
        if(password.length()<8||!Pattern.matches("^([a-zA-Z0-9])$", password)){
            textviewMessage.setText("비밀번호는 영문자, 숫자 포함 8자리 이상입니다.\n");
            return false;
        }
        if(!password.equals(repassword)){
            textviewMessage.setText("비밀번호가 일치하지 않습니다.\n");
            return false;
        }
        return true;
    }
    private int checkemail(String email){
        if(!Pattern.matches("^([a-zA-Z0-9]+@[a-zA-Z0-9]+)$", email)){
            textviewMessage.setText("제대로 된 이메일 형식이 아닙니다.\n");
            return 0;
        }
        return 1;
    }
}