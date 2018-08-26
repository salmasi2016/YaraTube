package com.yaratech.yaratube.data.source.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yaratech.yaratube.data.source.local.db.entity.User;

@Dao
public interface daoUser {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT isLogin FROM User")
    int isLogin();
}