package org.globant.services.walletservice.exchangewallet;

import org.globant.enums.Cryptos;
import org.globant.repository.ExchangeWalletRepository;

import java.math.BigDecimal;

public class WalletPortServiceImpl implements WithdrawExchangeWalletPort {

    ExchangeWalletRepository exchangeWalletRepository;

    public WalletPortServiceImpl() {
        this.exchangeWalletRepository = ExchangeWalletRepository.getInstance();
    }

    @Override
    public boolean withdraw(Cryptos cryptos, String var) {
        BigDecimal amount = new BigDecimal(var);
        switch (cryptos){
            case BITCOIN -> { return exchangeWalletRepository.getExchangeWallet().getBitcoinCrypto().removeAmountCrypto(amount); }
            case ETHEREUM -> { return exchangeWalletRepository.getExchangeWallet().getEthereumCrypto().removeAmountCrypto(amount); }
            case UNISWAP -> { return exchangeWalletRepository.getExchangeWallet().getUnisWapCrypto().removeAmountCrypto(amount); }
            default -> {
                return false;
            }
        }
    }
}
