package org.globant.controller.wallet;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.services.userServices.LoginUserPort;
import org.globant.services.userServices.RegisterUserAccountPort;
import org.globant.services.userServices.UserServiceImpl;
import org.globant.services.userWalletService.UserWalletPortServiceImpl;

import java.math.BigDecimal;
import java.util.Map;

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

    public void depositFiat(String amount){
        try {
            Double.parseDouble(amount);
            userWalletService.depositFiat(amount);
        } catch (NumberFormatException e){
            System.out.println("Enter a valid number");
        }
    }

}
