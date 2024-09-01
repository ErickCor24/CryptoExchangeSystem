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

    /**
     * add crypto to a wallet
     * @param amountCrypto amount of crypto
     */
    public void addAmountCrypto(BigDecimal amountCrypto) {
        this.amountCrypto = this.amountCrypto.add(amountCrypto);
    }

    /**
     * remove crypto to a wallet
     * @param amountCrypto amount of crypto
     * @return boolean
     */
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

    /**
     * Add price to a crypto
     * @param priceCrypto price of crypto
     */
    public void addPriceCrypto(BigDecimal priceCrypto) {
        this.priceCrypto = this.priceCrypto.add(priceCrypto);
    }

    /**
     * Remove price to a crypto
     * @param priceCrypto price of crypto
     */
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
