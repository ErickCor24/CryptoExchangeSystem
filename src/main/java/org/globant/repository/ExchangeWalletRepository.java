package org.globant.repository;

import org.globant.model.wallet.ExchangeWallet;

public class ExchangeWalletRepository {
    private static ExchangeWalletRepository instance;
    private final ExchangeWallet exchangeWallet;

    private String bitcoinPrice = "62782.76";
    private String ethereumPrice = "2679.51";
    private  String unisWapPrice = "6.27";

    private ExchangeWalletRepository() {
        this.exchangeWallet = new ExchangeWallet("1800","8500", "7000", bitcoinPrice, ethereumPrice, unisWapPrice);
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

    public String getBitcoinPrice() {
        return bitcoinPrice;
    }

    public void setBitcoinPrice(String bitcoinPrice) {
        this.bitcoinPrice = bitcoinPrice;
    }

    public String getEthereumPrice() {
        return ethereumPrice;
    }

    public void setEthereumPrice(String ethereumPrice) {
        this.ethereumPrice = ethereumPrice;
    }

    public String getUnisWapPrice() {
        return unisWapPrice;
    }

    public void setUnisWapPrice(String unisWapPrice) {
        this.unisWapPrice = unisWapPrice;
    }

}
