package org.globant.model.wallet;

import java.math.RoundingMode;

public class ExchangeWallet extends Wallet{

    public ExchangeWallet(Crypto bitcoinCrypto, Crypto ethereumCrypto, Crypto unisWapCrypto) {
        super(bitcoinCrypto, ethereumCrypto, unisWapCrypto);
    }

    public String CryptosPrice (){
        return "BitCoin: $" + this.bitcoinCrypto.getPriceCrypto().toString() +
                " - Ethereum: $" + this.ethereumCrypto.getPriceCrypto().toString() +
                " - UnisWap: $" + this.unisWapCrypto.getPriceCrypto().toString();
    }

    @Override
    public String toString() {
        return "ExchangeWallet{" +
                "bitcoinPrice=" + bitcoinCrypto.getPriceCrypto().setScale(2, RoundingMode.HALF_UP) +
                ", ethereumPrice=" + ethereumCrypto.getPriceCrypto().setScale(2, RoundingMode.HALF_UP) +
                ", unisWapPrice=" + unisWapCrypto.getPriceCrypto().setScale(2, RoundingMode.HALF_UP) +
                ", bitCoin=" + bitcoinCrypto.getAmountCrypto() +
                ", ethereum=" + ethereumCrypto.getAmountCrypto() +
                ", unisWap=" + unisWapCrypto.getAmountCrypto() +
                '}';
    }
}
