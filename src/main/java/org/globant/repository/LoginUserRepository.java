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

    /**
     * Get instance of LoginUserRepository
     * @return instance
     */
    public static LoginUserRepository getInstance(){
        if(instance == null){
            instance = new LoginUserRepository();
        }
        return instance;
    }

    /**
     *
     * @return user login in system
     */
    public User getUserLogin() {
        return userLogin;
    }

    /**
     * get username of user login
     * @return user name
     */
    public String getUserNameLogin (){
        return this.userLogin.getUserName();
    }

    /**
     * Change the user login in the system
     * @param userId user id
     * @param userLogin user
     */
    public void changeUserLogin(int userId, User userLogin){
        this.userId = userId;
        this.userLogin = userLogin;
    }

    /**
     * Get id of user login
     * @return user id
     */

    public int getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        return userLogin.toString() + ", userId:" + userId;
    }
}
