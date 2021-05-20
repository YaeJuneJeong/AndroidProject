package com.codesample.foodrecorder.data;

import android.provider.BaseColumns;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "food")
public class FoodRecord implements BaseColumns {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "food")
    public String food;
    public String time;

    public int getId() {
        return id;
    }

    public String getFood() {
        return food;
    }

    public String getTime() {
        return time;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public FoodRecord(){

    }
    public FoodRecord(String food,String time)
    {
        this.food = food;
        this.time = time;
    }
    public FoodRecord(int id, String food, String time)
    {
        this.id = id;
        this.food=food;
        this.time=time;
    }
}
