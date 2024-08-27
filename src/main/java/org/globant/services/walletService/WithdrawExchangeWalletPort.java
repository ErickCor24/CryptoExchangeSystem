package org.globant.services.walletService;

import org.globant.enums.Cryptos;

public interface WithdrawExchangeWalletPort {
    boolean withdraw(Cryptos cryptos, String var);
}
