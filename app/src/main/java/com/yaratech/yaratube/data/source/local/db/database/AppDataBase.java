package com.yaratech.yaratube.data.source.local.db.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.yaratech.yaratube.data.source.local.db.dao.daoUser;
import com.yaratech.yaratube.data.source.local.db.entity.User;

@Database(entities = User.class,version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase appDatabase;

    public abstract daoUser daoUser();

    public static AppDataBase newInstance(Context context){
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "appDataBase")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }
}