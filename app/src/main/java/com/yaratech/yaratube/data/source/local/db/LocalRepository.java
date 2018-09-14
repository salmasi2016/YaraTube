package com.yaratech.yaratube.data.source.local.db;

import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.db.entity.User;

public class LocalRepository {

    private AppDataBase database;

    public LocalRepository(AppDataBase database) {
        this.database = database;
    }

    public void InsertUser(User user) {
        database.userDao().insert(user);
    }

    public void updateUser(User user) {
        database.userDao().update(user);
    }

    public User getUser() {
        return database.userDao().getUser();
    }

    public String getToken() {
        return database.userDao().getToken();
    }
}