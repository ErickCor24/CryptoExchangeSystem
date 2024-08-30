package org.globant.controller.history;

import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;
import org.globant.model.history.TransactionHistory;
import org.globant.model.user.User;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.services.userservices.HistoryTransactionPort;
import org.globant.services.userservices.UserServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class HistoryController {
    private final UserMemoryRepository userMemoryRepository = UserMemoryRepository.getInstance();
    private final Map<Integer, User> users = userMemoryRepository.getUsers();
    private final LoginUserRepository loginUserRepository = LoginUserRepository.getInstance();
    private final ExchangeWalletRepository exchangeWalletRepository = ExchangeWalletRepository.getInstance();

    HistoryTransactionPort historyTransaction = new UserServiceImpl(userMemoryRepository,loginUserRepository,exchangeWalletRepository);

    public void addTransactionToUser(Cryptos crypto, BigDecimal cryptoAmount, Transaction transaction){
        String typeTransaction = "";
        switch (transaction) {
            case DIRECT_BUY -> typeTransaction = " Buy direct to exchange";
            case BUY_ORDER -> typeTransaction = " Buy in market P2P";
            case SELL_ORDER -> typeTransaction = " Shell in market P2P";
        }
        historyTransaction.addHistory(crypto, cryptoAmount, typeTransaction);
    }

    public void screenHistory (){
        List<TransactionHistory> histories = loginUserRepository.getUserLogin().getAllTransactions();
        if (!histories.isEmpty()) {
            for (TransactionHistory transactionHistory : histories) {
                System.out.println(transactionHistory);
            }
        }else
            System.out.println("There are no transactions in your account");
    }
}
