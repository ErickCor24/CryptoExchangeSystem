package org.globant.controller.wallet;

import org.globant.enums.Cryptos;
import org.globant.model.wallet.ExchangeWallet;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.services.walletService.WalletPortServiceImpl;

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
            Double.parseDouble(var);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
