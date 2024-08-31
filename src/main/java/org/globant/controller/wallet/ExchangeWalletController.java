package org.globant.controller.wallet;

import org.globant.enums.Cryptos;
import org.globant.model.wallet.ExchangeWallet;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.services.walletservice.exchangewallet.WalletPortServiceImpl;

public class ExchangeWalletController {
    ExchangeWallet exchangeWallet = ExchangeWalletRepository.getInstance().getExchangeWallet();
    WalletPortServiceImpl walletService = new WalletPortServiceImpl();

    public boolean withdrawExchange(Cryptos cryptos, String amount){
            amount = amount.replace(",",".");
            return walletService.withdraw(cryptos, amount);
    }

    public String screenExchangeWallet (){
        return this.exchangeWallet.toString();
    }

    public String screenCryptosPrice (){
        return this.exchangeWallet.CryptosPrice();
    }

    public boolean changeStringDouble (String var){
        try {
            var = var.replace(",",".");
            return Double.parseDouble(var) > 0;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
