package com.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edPassword,edEmail,edConfirm;
    Button register,login;
    TextView tv;
    LottieAnimationView laview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = findViewById(R.id.RegLoginbtn);
        edEmail = findViewById(R.id.editTextRegEmail);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword2);
        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        register = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.already_registered);
        laview = findViewById(R.id.lottiAnim);
        laview.setAnimation(R.raw.registration_animation_3);
        laview.playAnimation();
        laview.loop(true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.setElevation(20.0f);
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm_password = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                int email_length = email.length();
                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm_password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please Enter all valid details",Toast.LENGTH_SHORT).show();
                }
                else if(email_length <= 10 || !email.substring(email_length-10,email_length).equals("@gmail.com")){
                    Toast.makeText(getApplicationContext(),"Please Enter a Valid E-mail",Toast.LENGTH_SHORT).show();
                }

                else if(!password.equals(confirm_password)){
                    Toast.makeText(getApplicationContext(),"Confirm password is wrong",Toast.LENGTH_SHORT).show();
                }
                else if(!isValid(password)){
                    Toast.makeText(getApplicationContext(),"Password length should be greater then 7 and it must contain a special character,upper-case and a digit",Toast.LENGTH_SHORT).show();
                }
                else {
                    db.register(username,email,password);
                    Toast.makeText(getApplicationContext(),"Register successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setElevation(20.0f);
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    public static boolean isValid(String passwordhere){
        int f1,f2,f3;
        f1=f2=f3=0;
        int length = passwordhere.length();
        if(length < 8) return false;
        for(int i = 0;i<length;i++){
            if(Character.isLetter(passwordhere.charAt(i))){
                f1 = 1;
                break;
            }
        }
        for(int i = 0;i<length;i++){
            if(Character.isDigit(passwordhere.charAt(i))){
                f2 = 1;
                break;
            }
        }
        for(int i = 0; i < length; i++){
            char c = passwordhere.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                f3 = 1;
                break;
            }
        }
        if(f1==1 && f2==1 && f3==1) return true;
        else return false;
    }

}
