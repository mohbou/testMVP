package com.mohbou.mvpapplication;

public interface LoginRepository {

    User getUser();
    void saveUser(User user);
}
