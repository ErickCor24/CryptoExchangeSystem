package org.globant.services.userWalletService;

import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;

import java.math.BigDecimal;

public class UserWalletServiceImpl implements DepositUserWallet {

    LoginUserRepository loginUserRepository;
    UserMemoryRepository userMemoryRepository;

    public UserWalletServiceImpl(LoginUserRepository loginUserRepository, UserMemoryRepository userMemoryRepository) {
        this.loginUserRepository = loginUserRepository;
        this.userMemoryRepository = userMemoryRepository;
    }

    @Override
    public void depositFiat(String amount) {
        BigDecimal fiatAmount = new BigDecimal(amount);
        userMemoryRepository.getUsers().get(loginUserRepository.getUserId()).getUserWallet().addFiat(fiatAmount);
    }
}
