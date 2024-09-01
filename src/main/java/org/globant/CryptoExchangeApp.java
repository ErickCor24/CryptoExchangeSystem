package org.globant;

import org.globant.controller.history.HistoryController;
import org.globant.controller.order.OrderController;
import org.globant.controller.user.UserController;
import org.globant.controller.wallet.CryptoVariation;
import org.globant.controller.wallet.ExchangeWalletController;
import org.globant.controller.wallet.UserWalletController;
import org.globant.repository.ScannerRepository;
import org.globant.view.*;

import java.util.Scanner;

public class CryptoExchangeApp {
    public static void main(String[] args) {

        ExchangeWalletController exchangeWalletController = new ExchangeWalletController();
        UserController userController = new UserController();
        UserWalletController userWalletController = new UserWalletController();
        HistoryController historyController = new HistoryController();
        OrderController orderController = new OrderController(historyController);

        Scanner scanner = ScannerRepository.getInstance().getScanner();

        ColorView color = new ColorView();
        OrderView orderView = new OrderView(historyController,orderController,color,scanner);
        MarketView marketView = new MarketView(orderView,exchangeWalletController,historyController,userWalletController, scanner, color);
        HomeView homeView = new HomeView(marketView,historyController, exchangeWalletController, userController, userWalletController, scanner, color);
        ConsoleView view = new ConsoleView(homeView, userController, scanner, color);
        CryptoVariation cryptoVariation = new CryptoVariation();
        new Thread(cryptoVariation).start();
        view.execute();
    }
}