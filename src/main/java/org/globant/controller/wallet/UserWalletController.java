package org.globant.controller.wallet;

import org.globant.enums.Cryptos;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.services.walletservice.userwallet.UserWalletPortServiceImpl;

public class UserWalletController {

    UserMemoryRepository userMemoryRepository = UserMemoryRepository.getInstance();
    private final LoginUserRepository loginUserRepository = LoginUserRepository.getInstance();
    ExchangeWalletRepository exchangeWalletRepository = ExchangeWalletRepository.getInstance();
    UserWalletPortServiceImpl userWalletService = new UserWalletPortServiceImpl(loginUserRepository,userMemoryRepository,exchangeWalletRepository);

    public boolean depositUserWallet (Cryptos cryptos, String amount){
        if(userWalletService.allowedUserFunds(cryptos, amount)){
            return userWalletService.depositCrypto(cryptos, amount);
        } else
            return false;
    }

    public String depositFiat(String amount){
        try {
            if (Double.parseDouble(amount)>0) {
                userWalletService.depositFiat(amount);
                return "Fiat deposit in your wallet";
            } else
                return "Enter an amount greater than 0 ";
        } catch (NumberFormatException e){
            return "Enter a valid number";
        }
    }


}
