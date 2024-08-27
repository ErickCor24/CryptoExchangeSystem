package org.globant.model.wallet;

import java.math.BigDecimal;

public class UserWallet extends Wallet{
    BigDecimal fiat;

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
            value = value +"Cash: " + fiat;
        }
        if (bitCoin.compareTo(BigDecimal.ZERO) > 0){
            value = value +" - Bitcoin: " + bitCoin;
        }
        if (ethereum.compareTo(BigDecimal.ZERO) > 0){
            value = value +" - Ethereum:  " + ethereum;
        }
        if (unisWap.compareTo(BigDecimal.ZERO) > 0){
            value = value +" - UnisWap: " + unisWap;
        }
        return value;
    }
}
