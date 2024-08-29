package org.globant.services.walletservice.exchangewallet;

import org.globant.enums.Cryptos;

public interface WithdrawExchangeWalletPort {
    boolean withdraw(Cryptos cryptos, String var);
}
