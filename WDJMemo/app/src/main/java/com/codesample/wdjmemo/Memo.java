package com.codesample.wdjmemo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Memo {
    @PrimaryKey(autoGenerate = true)
    public int number;
    public String title;
    public String memo;
    public String time;
}
