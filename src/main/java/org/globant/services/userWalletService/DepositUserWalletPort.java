package org.globant.services.userWalletService;

import org.globant.enums.Cryptos;

public interface DepositUserWalletPort {
    void depositFiat (String amount);
    boolean depositCrypto(Cryptos cryptos, String amount);
    boolean allowedUserFunds(Cryptos cryptos,String amount);
}
