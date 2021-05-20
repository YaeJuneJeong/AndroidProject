package com.codesample.wdjmemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverter;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemoDao {
    @Insert
    long addMemo(Memo memo);

    @Query("select * from Memo")
    @TypeConverter
    List<Memo> getMemos();

    @Query("select * from Memo where number =:no")
    Memo getMemo(int no);

    @Update
    int updateMemo(Memo memo);

    @Delete
    int delete (Memo memo);
}
