package org.globant.services.userservices;

import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;

import java.math.BigDecimal;

public interface HistoryTransactionPort {
    void addHistory (Cryptos crypto, BigDecimal priceAmount, BigDecimal cryptoAmount, Transaction transaction);
}
