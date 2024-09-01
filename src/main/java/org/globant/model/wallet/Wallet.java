package org.globant.model.wallet;

public class Wallet {

    protected Crypto bitcoinCrypto;
    protected Crypto ethereumCrypto;
    protected Crypto unisWapCrypto;

    /**
     * Constructor of Wallet
     * @param bitcoinCrypto crypto object fo btc
     * @param ethereumCrypto crypto object fo eth
     * @param unisWapCrypto crypto object for uni
     */
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


    public Crypto getEthereumCrypto() {
        return ethereumCrypto;
    }


    public Crypto getUnisWapCrypto() {
        return unisWapCrypto;
    }

}
