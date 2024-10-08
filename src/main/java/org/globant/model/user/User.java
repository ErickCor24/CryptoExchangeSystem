package org.globant.model.user;

import org.globant.model.history.TransactionHistory;
import org.globant.model.wallet.UserWallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private final String name;
    private final String email;
    private final String password;
    private final String userName;
    private final UserWallet userWallet;
    private final List<TransactionHistory> transactionHistories;


    /**
     * Constructor of User
     * @param name name of user
     * @param email email of user
     * @param password pass of user
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.userName = userNameGenerator();
        this.email = email;
        this.password = password;
        this.userWallet = new UserWallet();
        this.transactionHistories = new ArrayList<>();
    }

    /**
     * Create a username for a user
     * @return username
     */
    public String userNameGenerator(){
        Random random = new Random();
        String [] parts = this.name.split(" ");
        return "@" + parts[0].toLowerCase() + random.nextInt(1,5000);
    }

    public List<TransactionHistory> getAllTransactions() {
        return transactionHistories;
    }

    /**
     * Add history to user
     * @param transactionHistory history
     */
    public void addTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistories.add(transactionHistory);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public UserWallet getUserWallet() {
        return userWallet;
    }

    @Override
    public String toString() {
        return "Name:" + name + ", E-mail:" + email +", Password:'" + password +
                ", User Name: '" + userName + userWallet.toString();
    }
}
