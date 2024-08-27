package org.globant.repository;

import org.globant.model.wallet.ExchangeWallet;

public class ExchangeWalletRepository {
    private static ExchangeWalletRepository instance;
    private final ExchangeWallet exchangeWallet;

    private ExchangeWalletRepository() {
        this.exchangeWallet = new ExchangeWallet("1800","8500", "7000");
    }

    public static ExchangeWalletRepository getInstance(){
        if(instance == null){
            instance = new ExchangeWalletRepository();
        }
        return instance;
    }

    public ExchangeWallet getExchangeWallet (){
        return exchangeWallet;
    }
}
