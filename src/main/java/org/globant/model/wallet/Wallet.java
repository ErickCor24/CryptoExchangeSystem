package org.globant.model.wallet;

public class Wallet {

    protected Crypto bitcoinCrypto;
    protected Crypto ethereumCrypto;
    protected Crypto unisWapCrypto;

    public Wallet(Crypto bitcoinCrypto, Crypto ethereumCrypto, Crypto unisWapCrypto) {
        this.bitcoinCrypto = bitcoinCrypto;
        this.ethereumCrypto = ethereumCrypto;
        this.unisWapCrypto = unisWapCrypto;
    }

    public Wallet(){
        this.bitcoinCrypto = new Crypto("Bitcoin", "BTC");
        this.ethereumCrypto = new Crypto("Ethereum", "ETH");
        this.unisWapCrypto = new Crypto("UnisWap", "UNI");
    }


    public Crypto getBitcoinCrypto() {
        return bitcoinCrypto;
    }

    public void setBitcoinCrypto(Crypto bitcoinCrypto) {
        this.bitcoinCrypto = bitcoinCrypto;
    }

    public Crypto getEthereumCrypto() {
        return ethereumCrypto;
    }

    public void setEthereumCrypto(Crypto ethereumCrypto) {
        this.ethereumCrypto = ethereumCrypto;
    }

    public Crypto getUnisWapCrypto() {
        return unisWapCrypto;
    }

    public void setUnisWapCrypto(Crypto unisWapCrypto) {
        this.unisWapCrypto = unisWapCrypto;
    }
}
