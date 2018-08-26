package com.yaratech.yaratube.data.source.local;

import com.yaratech.yaratube.data.source.local.db.database.AppDataBase;
import com.yaratech.yaratube.data.source.local.db.entity.User;

public class LocalRepository {

    private AppDataBase database;

    public LocalRepository(AppDataBase database) {
        this.database = database;
    }

    public void loginUser(User user) {
        database.daoUser().insert(user);
    }

    public void updateUser(User user) {
        database.daoUser().update(user);
    }

    public boolean isLogin() {
        return database.daoUser().getUser().getToken() != null;
    }
}