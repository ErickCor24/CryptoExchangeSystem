package org.globant.repository;

import org.globant.model.wallet.Crypto;
import org.globant.model.wallet.ExchangeWallet;

import java.math.RoundingMode;

public class ExchangeWalletRepository {
    private static ExchangeWalletRepository instance;
    private final ExchangeWallet exchangeWallet;

    private final Crypto bitcoin = new Crypto("62782.76","1800","Bitcoin","BTC",1);
    private final Crypto ethereum = new Crypto("2679.51","8500","Ethereum","ETH",2);
    private final Crypto unisWap = new Crypto("6.27","7000","UnisWap","UNI",3);

    private ExchangeWalletRepository() {
        this.exchangeWallet = new ExchangeWallet(bitcoin,ethereum,unisWap);
    }

    /**
     * Get instance of ExchangeWalletRepository
     * @return instance
     */
    public static ExchangeWalletRepository getInstance(){
        if(instance == null){
            instance = new ExchangeWalletRepository();
        }
        return instance;
    }

    public ExchangeWallet getExchangeWallet (){
        return exchangeWallet;
    }

    /**
     * Get price of bitcoin
     * @return price
     */
    public String getBitcoinPrice() {
        return bitcoin.getPriceCrypto().setScale(2, RoundingMode.HALF_UP).toString();
    }

    /**
     * Get price of ethereum
     * @return price
     */
    public String getEthereumPrice() {
        return ethereum.getPriceCrypto().setScale(2, RoundingMode.HALF_UP).toString();
    }

    /**
     * Get price of unisWap
     * @return price
     */
    public String getUnisWapPrice() {
        return unisWap.getPriceCrypto().setScale(2, RoundingMode.HALF_UP).toString();
    }

}
