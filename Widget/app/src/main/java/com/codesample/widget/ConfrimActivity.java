package com.codesample.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codesample.widget.databinding.ActivityConfrimBinding;
import com.codesample.widget.databinding.ActivityMainBinding;

public class ConfrimActivity extends AppCompatActivity {
    private ActivityConfrimBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityConfrimBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        print();
        setResult(Activity.RESULT_CANCELED);
        binding.buttonSMS.setOnClickListener(v->{
            Intent smsIntent= new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(Uri.parse("smsto:010-1234-5678"));
            if(smsIntent.resolveActivity(getPackageManager()) !=null){
                startActivity(smsIntent);
            }
        });
    }
    public void print(){
        String name=getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String Class = getIntent().getStringExtra("class");
        binding.textViewName.setText(name);
        binding.textViewPhone.setText(phone);
        binding.textViewClass.setText(Class);
    }
    public void onButton(View v){
        if (v.getId()==R.id.buttonOk){
            Intent intent =new Intent();
            intent.putExtra("message","User confirmed");
            setResult(Activity.RESULT_OK, intent);

        }
        finish();
    }
}
