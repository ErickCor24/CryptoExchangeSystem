package org.globant.services.userservices;

import org.globant.enums.Cryptos;

import java.math.BigDecimal;

public interface HistoryTransactionPort {
    void addHistory (Cryptos crypto, BigDecimal cryptoAmount, String transaction);
}
