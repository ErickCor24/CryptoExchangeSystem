package org.globant.model.history;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {
    private final LocalDateTime transactionDate;
    private final String cryptoName;
    private final BigDecimal cryptoAmount;
    private final BigDecimal fiatAmount;
    private final String typeTransaction;

    /**
     * Create object of Transaction history
     * @param cryptoName name of crypto
     * @param cryptoAmount amount of crypto
     * @param fiatAmount amount of fiat
     * @param typeTransaction name of type transaction
     */
    public TransactionHistory(String cryptoName, BigDecimal cryptoAmount, BigDecimal fiatAmount, String typeTransaction) {
        this.transactionDate = LocalDateTime.now();
        this.cryptoName = cryptoName;
        this.cryptoAmount = cryptoAmount;
        this.fiatAmount = fiatAmount;
        this.typeTransaction = typeTransaction;
    }

    /**
     *
     * @return message of transaction
     */
    @Override
    public String toString() {
        return ">> Transaction type:" + typeTransaction +
                ", crypto:" + cryptoName +
                ", crypto amount: " + cryptoAmount +
                ", price negotiated: $" + fiatAmount.setScale(2, RoundingMode.HALF_UP) +
                ", Date: " + transactionDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) ;
    }
}
