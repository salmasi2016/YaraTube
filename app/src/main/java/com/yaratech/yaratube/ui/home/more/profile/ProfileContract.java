package com.yaratech.yaratube.ui.home.more.profile;

import com.yaratech.yaratube.data.source.local.db.entity.User;

public interface ProfileContract {

    interface View {

        void showProgress();

        void hideProgress();

        void saveUser();

        void showErrorMessage(String message);
    }

    interface Presenter {

        void postUser(User user);
    }
}