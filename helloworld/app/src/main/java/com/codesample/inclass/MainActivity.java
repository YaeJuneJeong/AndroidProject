package com.codesample.inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public TextView textView;
    public EditText editTextNum1, editTextNum2;
    public Button buttonAdd, buttonSub, buttonMul, buttonDiv;
//    private View.OnClickListener listener =new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            float num1=Float.parseFloat(editTextNum1.getText().toString());
//            float num2=Float.parseFloat(editTextNum2.getText().toString());
//            textView.setText(String.valueOf(num1*num2));
//        }
//    };
    @Override
    public void onClick(View v) {
        float first=0,second=0;
        int id=v.getId();
        try {
            first = Integer.parseInt(editTextNum1.getText().toString());
            second = Integer.parseInt(editTextNum2.getText().toString());
            }
        catch (NumberFormatException e){
            textView.setText("invalid result");
            return;
            }
        if(id==R.id.buttonAdd)
            {first+=second;}
        else if (id==R.id.buttonMul)
            {first*=second;}
        else if (id == R.id.buttonSub)
            {first-=second;}
        else if (id== R.id.buttonDiv)
            {if (second==0) {
            textView.setText("Divided by 0");
            return;
            }
            else first/=second;}
    textView.setText(String.valueOf(first));
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        진입
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setText("Simple calculator!");

        editTextNum1=findViewById(R.id.editTextNum1);
        editTextNum2=findViewById(R.id.editTextNum2);
//
//        buttonAdd=findViewById(R.id.buttonAdd);
//        buttonAdd.setOnClickListener(this);

//        buttonSub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                float num1=Float.parseFloat(editTextNum1.getText().toString());
//                float num2=Float.parseFloat(editTextNum2.getText().toString());
//                textView.setText(String.valueOf(num1-num2));
//            }
//        });
//        buttonSub.setOnClickListener((v)->{
//            float num1=Float.parseFloat(editTextNum1.getText().toString());
//            float num2=Float.parseFloat(editTextNum2.getText().toString());
//            textView.setText(String.valueOf(num1-num2));});
//        buttonMul=findViewById(R.id.buttonMul);
//        buttonMul.setOnClickListener(listener);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        buttonSub = findViewById(R.id.buttonSub);
        buttonSub.setOnClickListener(this);
        buttonMul = findViewById(R.id.buttonMul);
        buttonMul.setOnClickListener(this);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonDiv.setOnClickListener(this);
    }



}
