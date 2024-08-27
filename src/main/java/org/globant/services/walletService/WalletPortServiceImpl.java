package org.globant.services.walletService;

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
            case BITCOIN -> { return exchangeWalletRepository.getExchangeWallet().removeBitcoin(amount); }
            case ETHEREUM -> { return exchangeWalletRepository.getExchangeWallet().removeEthereum(amount); }
            case UNISWAP -> { return exchangeWalletRepository.getExchangeWallet().removeUnisWap(amount); }
            default -> {
                return false;
            }
        }
    }
}
