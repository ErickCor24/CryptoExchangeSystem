package org.globant.services.userservices;

import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;
import org.globant.model.history.TransactionHistory;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.model.user.User;

import java.math.BigDecimal;
import java.util.Map;

public class UserServiceImpl implements RegisterUserAccountPort, LoginUserPort, HistoryTransactionPort {
    UserMemoryRepository userMemoryRepository;
    LoginUserRepository loginUserRepository;
    ExchangeWalletRepository exchangeWalletRepository;

    public UserServiceImpl(UserMemoryRepository userMemoryRepository, LoginUserRepository loginUserRepository, ExchangeWalletRepository exchangeWalletRepository) {
        this.userMemoryRepository = userMemoryRepository;
        this.loginUserRepository = loginUserRepository;
        this.exchangeWalletRepository = exchangeWalletRepository;
    }

    @Override
    public User userRegister(int id, String name, String email, String password) {
        User user = null;
        boolean loop = true;
        var users = userMemoryRepository.getUsers();
        if (id > 0) {
            for (User flag : users.values()) {
                if (flag.getEmail().equals(email)) {
                    loop = false;
                    break;
                }
            }
        }
        if(loop){
            user = new User(name.trim(), email.trim(), password.trim());
            users.put(id, user);
        }
        return user;
    }

    @Override
    public User searchUserAccount(String email, String password) {
        var users = userMemoryRepository.getUsers();
        for(User user : users.values()){
            if(user.getEmail().equals(email)){
                if (user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public Integer searchIdByUser(User user) {
        var users = userMemoryRepository.getUsers();
        for(Map.Entry<Integer, User> entry : users.entrySet()){
            if(entry.getValue().equals(user)){
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public void enterUserSystem(int id, User user) {
        loginUserRepository.changeUserLogin(id,user);
    }

    @Override
    public void addHistory(Cryptos cryptos, BigDecimal priceAmount ,BigDecimal cryptoAmount, Transaction transaction) {
        String cryptoName = "";
        BigDecimal price = null;
        String typeTransaction = "";
        TransactionHistory transactionHistory;

        switch (cryptos) {
            case BITCOIN -> {
                cryptoName = "Bitcoin";
                price = new BigDecimal(exchangeWalletRepository.getBitcoinPrice());
            }
            case ETHEREUM -> {
                cryptoName = "Ethereum";
                price = new BigDecimal(exchangeWalletRepository.getBitcoinPrice());
            }
            case UNISWAP -> {
                cryptoName = "UnisWap";
                price = new BigDecimal(exchangeWalletRepository.getBitcoinPrice());
            }
        }

        switch (transaction) {
            case DIRECT_BUY -> typeTransaction = " Buy direct to exchange";
            case BUY_ORDER -> typeTransaction = " Buy in market P2P";
            case SELL_ORDER -> typeTransaction = " Shell in market P2P";
        }

        if(transaction == Transaction.DIRECT_BUY){
            transactionHistory = new TransactionHistory(cryptoName,cryptoAmount, cryptoAmount.multiply(price), typeTransaction);
        } else
            transactionHistory = new TransactionHistory(cryptoName,cryptoAmount, priceAmount, typeTransaction);

        userMemoryRepository.getUsers().get(loginUserRepository.getUserId()).addTransactionHistory(transactionHistory);
    }
}
