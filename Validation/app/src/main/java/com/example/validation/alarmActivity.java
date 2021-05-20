package com.example.validation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class alarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        TextView logout = findViewById(R.id.logout);
        ImageView alarm = findViewById(R.id.user);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutbutton = new Intent(alarmActivity.this,LoginActivity.class);
                startActivity(logoutbutton);
            }
        });
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                ColorDrawable colors = (ColorDrawable) alarm.getBackground();
                int color = colors.getColor();
                int basic = getResources().getColor(R.color.colorAccent);
                if(color == basic)
                {
                    alarm.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    Toast.makeText(getApplicationContext(),"기록 ON",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    alarm.setBackgroundColor(basic);
                    Toast.makeText(getApplicationContext(),"기록 OFF",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
