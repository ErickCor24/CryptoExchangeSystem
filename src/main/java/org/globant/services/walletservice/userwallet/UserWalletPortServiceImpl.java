package org.globant.services.walletservice.userwallet;

import org.globant.enums.Cryptos;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;

import java.math.BigDecimal;

public class UserWalletPortServiceImpl implements DepositUserWalletPort {

    LoginUserRepository loginUserRepository;
    ExchangeWalletRepository exchangeWalletRepository;
    UserMemoryRepository userMemoryRepository;

    public UserWalletPortServiceImpl(LoginUserRepository loginUserRepository, UserMemoryRepository userMemoryRepository, ExchangeWalletRepository exchangeWalletRepository) {
        this.loginUserRepository = loginUserRepository;
        this.userMemoryRepository = userMemoryRepository;
        this.exchangeWalletRepository = exchangeWalletRepository;
    }

    /**
     * Deposit fiat in user wallet login repository
     * @param amount amount to deposit
     */
    @Override
    public void depositFiat(String amount) {
        BigDecimal fiatAmount = new BigDecimal(amount);
        userMemoryRepository.getUsers().get(loginUserRepository.getUserId()).getUserWallet().addFiat(fiatAmount);
    }

    /**
     * Deposit crypto in user wallet login repository
     * @param cryptos enum crypto
     * @param amount amount to deposit
     * @return boolean
     */
    @Override
    public boolean depositCrypto(Cryptos cryptos, String amount) {
        boolean flag = false;
        BigDecimal cryptoAmount = new BigDecimal(amount);
        var user  = userMemoryRepository.getUsers().get(loginUserRepository.getUserId());
        BigDecimal cryptoPrice;
        switch (cryptos){
            case BITCOIN -> {
                if(allowedUserFunds(cryptos, amount)){
                    cryptoPrice = new BigDecimal(exchangeWalletRepository.getBitcoinPrice());
                    user.getUserWallet().removeFiat(cryptoPrice.multiply(cryptoAmount));
                    user.getUserWallet().getBitcoinCrypto().addAmountCrypto(cryptoAmount);
                    flag = true;
                }
            }
            case ETHEREUM -> {
                if (allowedUserFunds(cryptos, amount)){
                    cryptoPrice = new BigDecimal(exchangeWalletRepository.getEthereumPrice());
                    user.getUserWallet().removeFiat(cryptoPrice.multiply(cryptoAmount));
                    user.getUserWallet().getEthereumCrypto().addAmountCrypto(cryptoAmount);
                    flag = true;
                }
            }
            case UNISWAP -> {
                if (allowedUserFunds(cryptos, amount)){
                    cryptoPrice = new BigDecimal(exchangeWalletRepository.getUnisWapPrice());
                    user.getUserWallet().removeFiat(cryptoPrice.multiply(cryptoAmount));
                    user.getUserWallet().getUnisWapCrypto().addAmountCrypto(cryptoAmount);
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * @param cryptos enum crypto
     * @param amount amount fiat
     * @return boolean
     */
    @Override
    public boolean allowedUserFunds(Cryptos cryptos,String amount) {
        boolean flag = false;
        BigDecimal fiatAmount = userMemoryRepository.getUsers().get(loginUserRepository.getUserId()).getUserWallet().getFiat();
        BigDecimal cryptoAmount = new BigDecimal(amount);
        BigDecimal cryptoPrice;
        switch (cryptos){
            case BITCOIN -> {
                cryptoPrice = new BigDecimal(exchangeWalletRepository.getBitcoinPrice());
                flag = fiatAmount.compareTo(cryptoPrice.multiply(cryptoAmount)) >= 0;
            }
            case UNISWAP -> {
                cryptoPrice = new BigDecimal(exchangeWalletRepository.getUnisWapPrice());
                flag = fiatAmount.compareTo(cryptoPrice.multiply(cryptoAmount)) >= 0;
            }
            case ETHEREUM -> {
                cryptoPrice = new BigDecimal(exchangeWalletRepository.getEthereumPrice());
                flag = fiatAmount.compareTo(cryptoPrice.multiply(cryptoAmount)) >= 0;
            }
        }
        return flag;
    }
}