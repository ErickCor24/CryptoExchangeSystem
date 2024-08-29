package org.globant.model.history;

import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TransactionHistory {
    private final LocalDateTime transactionDate;
    private final String cryptoName;
    private final BigDecimal cryptoAmount;
    private final BigDecimal fiatAmount;
    private final String typeTransaction;

    public TransactionHistory(String cryptoName, BigDecimal cryptoAmount, BigDecimal fiatAmount, String typeTransaction) {
        this.transactionDate = LocalDateTime.now();
        this.cryptoName = cryptoName;
        this.cryptoAmount = cryptoAmount;
        this.fiatAmount = fiatAmount;
        this.typeTransaction = typeTransaction;
    }

    @Override
    public String toString() {
        return ">> Date: " + transactionDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))+
                ", crypto:" + cryptoName +
                ", crypto amount: " + cryptoAmount +
                ", fiat: " + fiatAmount +
                ", transaction type:" + typeTransaction;
    }
}
