package org.globant.model.wallet;

import java.math.BigDecimal;

public class Wallet {
    protected BigDecimal bitCoin;
    protected BigDecimal ethereum;
    protected BigDecimal unisWap;

    public Wallet() {
        this.bitCoin = BigDecimal.ZERO;
        this.ethereum = BigDecimal.ZERO;
        this.unisWap = BigDecimal.ZERO;
    }

    public Wallet(String bitcoin, String ethereum, String unisWap) {
        this.bitCoin = new BigDecimal(bitcoin);
        this.ethereum = new BigDecimal(ethereum);
        this.unisWap = new BigDecimal(unisWap);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "bitCoin=" + bitCoin +
                ", ethereum=" + ethereum +
                ", unisWap=" + unisWap +
                '}';
    }

    /* BITCOIN */

    public BigDecimal getBitCoin() {
        return bitCoin;
    }

    public void addBitCoin(BigDecimal amount) {
        this.bitCoin = this.bitCoin.add(amount);
    }

    public boolean removeBitcoin (BigDecimal amount){
        if(this.bitCoin.compareTo(amount)>= 0){
            this.bitCoin = this.bitCoin.subtract(amount);
            return true;
        }
        else {
            return false;
        }
    }

    /* ETHEREUM */

    public BigDecimal getEthereum() {
        return ethereum;
    }

    public void addEthereum(BigDecimal amount) {
        this.bitCoin = this.bitCoin.add(amount);
    }

    public boolean removeEthereum(BigDecimal amount){
        if(this.ethereum.compareTo(amount)>= 0){
            this.ethereum = this.ethereum.subtract(amount);
            return true;
        }
        else {
            return false;
        }
    }

    /* UNISWAP */

    public BigDecimal getUnisWap() {
        return unisWap;
    }

    public void addUnisWap(BigDecimal amount) {
        this.unisWap = this.unisWap.add(amount);
    }

    public boolean removeUnisWap(BigDecimal amount){
        if(this.unisWap.compareTo(amount)>= 0){
            this.unisWap = this.unisWap.subtract(amount);
            return true;
        }
        else {
            return false;
        }
    }

}
