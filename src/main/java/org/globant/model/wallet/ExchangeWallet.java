package org.globant.model.wallet;

import java.math.BigDecimal;

public class ExchangeWallet extends Wallet{
    private final BigDecimal bitcoinPrice;
    private final BigDecimal ethereumPrice;
    private final BigDecimal unisWapPrice;

    public ExchangeWallet(String bitcoin, String ethereum, String unisWap, String bitcoinPrice, String ethereumPrice, String unisWapPrice) {
        super(bitcoin, ethereum, unisWap);
        this.bitcoinPrice = new BigDecimal(bitcoinPrice);
        this.ethereumPrice = new BigDecimal(ethereumPrice);
        this.unisWapPrice = new BigDecimal(unisWapPrice);
    }

    public String CryptosPrice (){
        return "BitCoin: $" + this.bitcoinPrice + " - Ethereum: $" + this.ethereumPrice + " - UnisWap: $" + this.unisWapPrice;
    }

    @Override
    public String toString() {
        return "ExchangeWallet{" +
                "bitcoinPrice=" + bitcoinPrice +
                ", ethereumPrice=" + ethereumPrice +
                ", unisWapPrice=" + unisWapPrice +
                ", bitCoin=" + bitCoin +
                ", ethereum=" + ethereum +
                ", unisWap=" + unisWap +
                '}';
    }
}
