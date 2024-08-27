package org.globant.repository;

import org.globant.model.user.User;

public class LoginUserRepository {
    private static LoginUserRepository instance;
    private User userLogin;
    private int userId;

    private LoginUserRepository() {
        this.userLogin = null;
        this.userId = 0;
    }

    public static LoginUserRepository getInstance(){
        if(instance == null){
            instance = new LoginUserRepository();
        }
        return instance;
    }

    public String getUserNameLogin (){
        return this.userLogin.getUserName();
    }

    public void changeUserLogin(int userId, User userLogin){
        this.userId = userId;
        this.userLogin = userLogin;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userLogin.toString() + ", userId:" + userId;
    }
}
