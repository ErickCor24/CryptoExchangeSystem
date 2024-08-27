package org.globant.services.walletService;

import org.globant.enums.Cryptos;

public interface WithdrawExchangeWallet {
    boolean withdraw(Cryptos cryptos, String var);
}
