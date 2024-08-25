package org.globant.model.wallet;

import java.math.BigDecimal;

public class Wallet {
    private BigDecimal bitCoin;
    private BigDecimal ethereum;
    private BigDecimal unisWap;

    public Wallet(BigDecimal bitCoin, BigDecimal ethereum, BigDecimal unisWap) {
        this.bitCoin = bitCoin;
        this.ethereum = ethereum;
        this.unisWap = unisWap;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "bitCoin=" + bitCoin +
                ", ethereum=" + ethereum +
                ", unisWap=" + unisWap +
                '}';
    }

    public BigDecimal getBitCoin() {
        return bitCoin;
    }

    public void setBitCoin(BigDecimal bitCoin) {
        this.bitCoin = bitCoin;
    }

    public BigDecimal getEthereum() {
        return ethereum;
    }

    public void setEthereum(BigDecimal ethereum) {
        this.ethereum = ethereum;
    }

    public BigDecimal getUnisWap() {
        return unisWap;
    }

    public void setUnisWap(BigDecimal unisWap) {
        this.unisWap = unisWap;
    }
}
