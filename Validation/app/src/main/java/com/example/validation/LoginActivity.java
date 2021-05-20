package com.example.validation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class LoginActivity extends AppCompatActivity {

    static int randomNum = (int)(Math.random()*100000);
    String Emailregex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    EditText userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());


        TextView sendUserEmail = (TextView) findViewById(R.id.sendUserEmail);

        sendUserEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 userEmail =findViewById(R.id.user);
                if(Pattern.matches(Emailregex,userEmail.getText().toString()))
                {
                    Intent sendUserEmailintent = new Intent(LoginActivity.this, validationActivity.class);

                    sendUserEmailintent.putExtra("mailAddress", userEmail.getText().toString());
                    sendUserEmailintent.putExtra("random",Integer.toString(randomNum));
                    sendMail();
                    LoginActivity.this.startActivity(sendUserEmailintent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"이메일 양식이 유효하지 않습니다",Toast.LENGTH_LONG).show();
                }
            }






        });
    }
    public void sendMail() {
        String mail =userEmail.getText().toString().trim();


        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,"validation",Integer.toString(randomNum));

        javaMailAPI.execute();
    }
}
