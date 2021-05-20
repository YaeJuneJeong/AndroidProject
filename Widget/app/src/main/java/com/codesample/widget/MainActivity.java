package com.codesample.widget;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.codesample.widget.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
//    private EditText name;
//    private EditText phone;
//    private RadioButton adult;
//    private RadioButton student;
//    private CheckBox terms;
//    private ProgressBar progressBar;
//    private Button apply;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWidgets();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i("Main","before="+s+" start="+start+", count ="+count+",after ="+after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i("Main","before="+s+" start="+start+", before ="+before+", count ="+count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i("Main","after="+s.toString());
        update();
    }
    private void initWidgets(){
        binding.editTextName.addTextChangedListener(this);
        binding.editTextPhone.addTextChangedListener(this);
        binding.radioButtonAdult.setOnClickListener(this);
        binding.radioButtonStudent.setOnClickListener(this);
        binding.checkboxTerms.setOnCheckedChangeListener(this);

        binding.buttonApply.setOnClickListener(this);
    }
    private  void  update(){
        int progress = 0;

        if(binding.editTextName.getText().length()>0) progress++;
        if(binding.editTextPhone.getText().length()>0) progress++;
        if(binding.radioButtonAdult.isChecked() || binding.radioButtonStudent.isChecked())progress++;
        if(binding.checkboxTerms.isChecked())progress++;

        binding.progressBar.setProgress(progress);
        if(progress==4) binding.buttonApply.setVisibility(View.VISIBLE);
        else binding.buttonApply.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if (resultCode== Activity.RESULT_OK) {
                if (data != null) {
                    String message = data.getStringExtra("message");
                    if (message != null) Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
            }else{
                    Toast.makeText(this,"Canceled",Toast.LENGTH_SHORT).show();
                }
            }


        }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buttonApply) {
            Intent intent = new Intent(this, ConfrimActivity.class);
            intent.putExtra("name",binding.editTextName.getText().toString());
            intent.putExtra("phone",binding.editTextPhone.getText().toString());
            if(binding.radioButtonAdult.isChecked())
            intent.putExtra("class","adult");
            else if (binding.radioButtonStudent.isChecked())
                intent.putExtra("class","Student");
//            startActivity(intent);
            startActivityForResult(intent,1);
        }
        else {
            update();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    update();
    }
}
