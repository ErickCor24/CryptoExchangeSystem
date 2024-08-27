package org.globant.model.user;

import org.globant.model.wallet.UserWallet;
import org.globant.model.wallet.Wallet;

import java.util.Random;

public class User {
    private String name;
    private String email;
    private String password;
    private final String userName;
    private UserWallet userWallet;

    public User(String name, String email, String password) {
        this.name = name;
        this.userName = userNameGenerator();
        this.email = email;
        this.password = password;
        this.userWallet = new UserWallet();
    }

    public String userNameGenerator(){
        Random random = new Random();
        String [] parts = this.name.split(" ");
        return "@" + parts[0].toLowerCase() + random.nextInt(1,5000);
    }

    @Override
    public String toString() {
        return "Name:'" + name + ", E-mail:" + email +", Password:'" + password +
                ", User Name: '" + userName + userWallet.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public UserWallet getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(UserWallet userWallet) {
        this.userWallet = userWallet;
    }
}
