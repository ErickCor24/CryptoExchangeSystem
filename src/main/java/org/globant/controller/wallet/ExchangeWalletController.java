package org.globant.controller.wallet;

import org.globant.enums.Cryptos;
import org.globant.model.wallet.ExchangeWallet;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.services.walletservice.exchangewallet.WalletPortServiceImpl;

public class ExchangeWalletController {
    ExchangeWallet exchangeWallet = ExchangeWalletRepository.getInstance().getExchangeWallet();
    WalletPortServiceImpl walletService = new WalletPortServiceImpl();

    /**
     * Withdraw of a user logged wallet
     * @param cryptos enum
     * @param amount amount
     * @return boolean
     */
    public boolean withdrawExchange(Cryptos cryptos, String amount){
            amount = amount.replace(",",".");
            return walletService.withdraw(cryptos, amount);
    }

    /**
     * Present the cryptos available on the exchange
     * @return message
     */
    public String screenExchangeWallet (){
        return this.exchangeWallet.toString();
    }


    /**
     * Present the price of cryptos
     * @return message
     */
    public String screenCryptosPrice (){
        return this.exchangeWallet.cryptosPrice();
    }

    /**
     * Check if the entry of an amount is correct
     * @param var amount
     * @return boolean
     */
    public boolean changeStringDouble (String var){
        try {
            var = var.replace(",",".");
            return Double.parseDouble(var) > 0;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
