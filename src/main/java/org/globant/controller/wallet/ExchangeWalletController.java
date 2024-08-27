package org.globant.controller.wallet;

import org.globant.enums.Cryptos;
import org.globant.model.wallet.ExchangeWallet;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.services.userWalletService.UserWalletServiceImpl;
import org.globant.services.walletService.WalletServiceImpl;

public class ExchangeWalletController {
    ExchangeWallet exchangeWallet = ExchangeWalletRepository.getInstance().getExchangeWallet();
    WalletServiceImpl walletService = new WalletServiceImpl();

    public boolean withdrawExchange(Cryptos cryptos, String amount){
        amount = amount.replace(",",".");
        return walletService.withdraw(cryptos, amount);
    }

    public String screenExchangeWallet (){
        return this.exchangeWallet.toString();
    }
}
