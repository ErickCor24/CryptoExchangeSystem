package org.globant;

import org.globant.controller.user.UserController;
import org.globant.controller.wallet.ExchangeWalletController;
import org.globant.controller.wallet.UserWalletController;
import org.globant.repository.ScannerRepository;
import org.globant.view.ColorView;
import org.globant.view.ConsoleView;
import org.globant.view.HomeView;
import org.globant.view.MarketView;

import java.util.Scanner;

public class CryptoExchangeApp {
    public static void main(String[] args) {


        ExchangeWalletController exchangeWalletController = new ExchangeWalletController();
        UserController userController = new UserController();
        UserWalletController userWalletController = new UserWalletController();

        Scanner scanner = ScannerRepository.getInstance().getScanner();

        ColorView colors = new ColorView();
        MarketView marketView = new MarketView(exchangeWalletController,userController,userWalletController, scanner, colors);
        HomeView homeView = new HomeView(marketView,exchangeWalletController, userController, userWalletController, scanner, colors);
        ConsoleView view = new ConsoleView(homeView, userController, scanner, colors);
        view.execute();
    }
}