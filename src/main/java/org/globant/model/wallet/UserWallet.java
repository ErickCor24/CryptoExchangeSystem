package org.globant.model.wallet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserWallet extends Wallet{
    private BigDecimal fiat;

    /**
     * Constructor extends of Wallet
     */
    public UserWallet() {
        this.fiat = BigDecimal.ZERO;
    }

    public BigDecimal getFiat() {
        return fiat;
    }

    /**
     * Add fiat to user wallet
     * @param amount fiat amount
     */
    public void addFiat(BigDecimal amount) {
        this.fiat = this.fiat.add(amount);
    }

    /**
     * Remove fiat to user wallet
     * @param amount fiat amount
     */
    public void removeFiat(BigDecimal amount){
        if(this.fiat.compareTo(amount)>= 0){
            this.fiat = this.fiat.subtract(amount);
        }
    }

    /**
     * Obtain wallet of user
     * @return user wallet
     */
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
