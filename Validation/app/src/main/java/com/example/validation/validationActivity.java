package com.example.validation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.se.omapi.Session;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class validationActivity extends AppCompatActivity {

    final private String id= "jyj98071@gmail.com";
    final private String password = "owner9807";



    TextView time_counter;
    TextView validation_num;
    TextView validation_button;
    TextView start_counter;

    LayoutInflater dialog;
    View dialogLayout;
    Dialog authdialog;


    final int limitTime = 180 * 1000;
    final int countDownInterval = 1000;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);



        Intent intent =getIntent();

        final String mailAdress = intent.getExtras().getString("mailAddress");
        final String randNum = intent.getExtras().getString("random");


        time_counter = (TextView) findViewById(R.id.emailTime);
        validation_num = (TextView) findViewById(R.id.userEmail);
        validation_button = (TextView) findViewById(R.id.sendValidation);
        validation_num.setText(randNum);
        countDownTimer();

        validation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String user_answer = validation_num.getText().toString();
               if(user_answer.equals(randNum)){
                   Toast.makeText(getApplicationContext(),"Email 인증 성공",Toast.LENGTH_SHORT).show();


                Intent validation =new Intent(validationActivity.this,OnOff.class);
                validation.putExtra("userEmail",mailAdress);
                validationActivity.this.startActivity(validation);
               }
               else{
                   Toast.makeText(getApplicationContext(),"Email 인증 실패",Toast.LENGTH_LONG).show();
               }

        }

        });
    }

    public void countDownTimer() {


        countDownTimer = new CountDownTimer(limitTime, countDownInterval) {
            @Override
            public void onTick(long limitTime) {
                long emailCount = limitTime / 1000;

                if ((emailCount - ((emailCount / 60) * 60)) >= 10) {
                    time_counter.setText((emailCount / 60) + ":" + (emailCount - ((emailCount / 60) * 60)));
                } else {
                    time_counter.setText((emailCount / 60) + ":0" + (emailCount - ((emailCount) / 60) * 60));
                }

            }

            @Override
            public void onFinish() {
               time_counter.setText("기간이 만료되었습니다");
                try {
                    wait(800);
                } catch (InterruptedException e) {

                }
                Intent unvalidation = new Intent(validationActivity.this, LoginActivity.class);
                validationActivity.this.startActivity(unvalidation);
            }
        }.start();


    }

}
