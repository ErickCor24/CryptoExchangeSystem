package org.globant.model.wallet;

import java.math.BigDecimal;

public class Crypto {
    private int idCrypto;
    private final String nameCrypto;
    private final String abbreviationCrypto;
    private BigDecimal amountCrypto;
    private BigDecimal priceCrypto;

    public Crypto(String nameCrypto, String abbreviationCrypto) {
        this.amountCrypto = BigDecimal.ZERO;
        this.nameCrypto = nameCrypto;
        this.abbreviationCrypto = abbreviationCrypto;
    }

    public Crypto(String priceCrypto, String amountCrypto, String nameCrypto, String abbreviationCrypto, int idCrypto) {
        this.priceCrypto = new BigDecimal(priceCrypto);
        this.amountCrypto = new BigDecimal(amountCrypto);
        this.nameCrypto = nameCrypto;
        this.abbreviationCrypto = abbreviationCrypto;
        this.idCrypto = idCrypto;
    }

    public String getAbbreviationCrypto() {
        return abbreviationCrypto;
    }

    public BigDecimal getAmountCrypto() {
        return amountCrypto;
    }

    public void addAmountCrypto(BigDecimal amountCrypto) {
        this.amountCrypto = this.amountCrypto.add(amountCrypto);
    }

    public boolean removeAmountCrypto (BigDecimal amountCrypto){
        if(this.amountCrypto.compareTo(amountCrypto) >= 0){
            this.amountCrypto = this.amountCrypto.subtract(amountCrypto);
            return true;
        }
        else {
            return false;
        }
    }

    public BigDecimal getPriceCrypto() {
        return priceCrypto;
    }

    public void addPriceCrypto(BigDecimal priceCrypto) {
        this.priceCrypto = this.priceCrypto.add(priceCrypto);
    }

    public void subtractPriceCrypto(BigDecimal priceCrypto) {
        if(this.priceCrypto.compareTo(priceCrypto) >= 0){
            this.priceCrypto = this.priceCrypto.subtract(priceCrypto);
        }
    }

    @Override
    public String toString() {
        return "Crypto{" +
                "idCrypto=" + idCrypto +
                ", nameCrypto='" + nameCrypto + '\'' +
                ", amountCrypto=" + amountCrypto +
                ", priceCrypto=" + priceCrypto +
                '}';
    }
}
