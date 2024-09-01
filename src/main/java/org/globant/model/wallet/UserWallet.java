package org.globant.model.wallet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserWallet extends Wallet{
    private BigDecimal fiat;

    public UserWallet() {
        this.fiat = BigDecimal.ZERO;
    }

    public BigDecimal getFiat() {
        return fiat;
    }

    public void addFiat(BigDecimal amount) {
        this.fiat = this.fiat.add(amount);
    }

    public void removeFiat(BigDecimal amount){
        if(this.fiat.compareTo(amount)>= 0){
            this.fiat = this.fiat.subtract(amount);
        }
    }

    @Override
    public String toString() {
        String value = "";
        if(fiat.compareTo(BigDecimal.ZERO) > 0){
            value += "Cash: $" + fiat.setScale(2, RoundingMode.HALF_UP);
        }
        if (bitcoinCrypto.getAmountCrypto().compareTo(BigDecimal.ZERO) > 0){
            value += " - Bitcoin: "+ bitcoinCrypto.getAbbreviationCrypto()+ " " + bitcoinCrypto.getAmountCrypto();
        }
        if (ethereumCrypto.getAmountCrypto().compareTo(BigDecimal.ZERO) > 0){
            value += " - Ethereum: "+ ethereumCrypto.getAbbreviationCrypto()+ " "  + ethereumCrypto.getAmountCrypto();
        }
        if (unisWapCrypto.getAmountCrypto().compareTo(BigDecimal.ZERO) > 0){
            value += " - UnisWap: " + unisWapCrypto.getAbbreviationCrypto()+ " "  + unisWapCrypto.getAmountCrypto();
        }
        return value;
    }
}
