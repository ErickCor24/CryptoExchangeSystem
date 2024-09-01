package org.globant.controller.history;

import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;
import org.globant.model.history.TransactionHistory;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.services.userservices.HistoryTransactionPort;
import org.globant.services.userservices.UserServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class HistoryController {
    private final UserMemoryRepository userMemoryRepository = UserMemoryRepository.getInstance();
    private final LoginUserRepository loginUserRepository = LoginUserRepository.getInstance();
    private final ExchangeWalletRepository exchangeWalletRepository = ExchangeWalletRepository.getInstance();

    HistoryTransactionPort historyTransaction = new UserServiceImpl(userMemoryRepository,loginUserRepository,exchangeWalletRepository);

    public void addTransactionToUser(Cryptos crypto, BigDecimal cryptoPriceNegotiated, BigDecimal cryptoAmount, Transaction transaction){
        historyTransaction.addHistory(crypto, cryptoPriceNegotiated, cryptoAmount, transaction);
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

    public String btcPrice(){
        return "Bitcoin price: $"+exchangeWalletRepository.getBitcoinPrice();
    }

    public String ethPrice(){
        return "Ethereum price: $"+exchangeWalletRepository.getEthereumPrice();
    }

    public String uniPrice(){
        return "UnisWap price: $"+exchangeWalletRepository.getUnisWapPrice();
    }
}
