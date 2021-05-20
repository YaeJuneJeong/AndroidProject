package com.codesample.foodrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import com.codesample.foodrecorder.data.FoodRecord;
import com.codesample.foodrecorder.data.FoodRecordDatabase;
import com.codesample.foodrecorder.data.FoodRecordOpenHelper;
import com.codesample.foodrecorder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SharedPreferences preferences;
//    private FoodRecordOpenHelper helper;
    private FoodRecordDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        helper = new FoodRecordOpenHelper(this,"db",null,1);
//        ArrayList<FoodRecord> list = helper.getRecords();




        preferences=getSharedPreferences("food", Context.MODE_PRIVATE);
        String lastFood = preferences.getString("food",null);
        String lastTime = preferences.getString("time",null);
        displayRecord(lastFood,lastTime);
        binding.buttonRecord.setOnClickListener(onSave);
        binding.buttonShowAll.setOnClickListener(v->{     startActivity(new Intent(this, RecordActivity.class)); });
    }

    public void displayRecord(String lastfood , String savedTime){





//            LocalDateTime end = LocalDateTime.now();
//            ZonedDateTime zonedDateTime=end.atZone(ZoneId.of("Asia/Seoul"));
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd hh:mm");
//            zonedDateTime.format(formatter);
//            binding.textViewRecord.setText(zonedDateTime.toString());


        if (lastfood ==null){
            binding.textViewRecord.setText("저장된 기록이 없습니다");
            binding.textViewElapsed.setText("경과 시간이 없습니다");
        }
        else {
            LocalDateTime startTime = LocalDateTime.parse(savedTime);
            LocalDateTime endTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm",Locale.KOREA);
            String timeStr = startTime.format(formatter);
            String foodMessage = String.format("%s - %s",timeStr,lastfood);
            binding.textViewRecord.setText(foodMessage);
            long hours = ChronoUnit.HOURS.between(startTime,endTime);
            long minute = ChronoUnit.MINUTES.between(startTime,endTime);

            minute-=hours*60;
            binding.textViewElapsed.setText(String.format(Locale.KOREA,"%d 시간 %02d분 ",hours,minute));
        }




    }
    public View.OnClickListener onSave=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences.Editor editor = preferences.edit();
            String food = binding.editText.getText().toString();
            if(!food.isEmpty()){
                editor.putString("food",food);
                String now=LocalDateTime.now().toString();
                editor.putString("time",now);
                editor.apply();
//                helper.addRecord(new FoodRecord(food,now));
                save(new FoodRecord(food,now));
            }

        }
    };
    private void save(FoodRecord record)
    {
        new Thread(()->db.foodRecordDAO().addRecord(record)).start();
    }
    private void getList(){
        new Thread(()-> {List<FoodRecord> result = db.foodRecordDAO().getRecords();
        for (FoodRecord e:result)
            Log.i("Main", e.time+e.food);
        }).start();
    }
}
