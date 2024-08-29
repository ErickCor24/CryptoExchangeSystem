package org.globant.view;

import org.globant.controller.history.HistoryController;
import org.globant.controller.user.UserController;
import org.globant.controller.wallet.ExchangeWalletController;
import org.globant.controller.wallet.UserWalletController;

import java.util.Scanner;

public class HomeView {

    ExchangeWalletController exchangeWalletController;
    HistoryController historyController;
    UserController userController;
    UserWalletController userWalletController;
    MarketView marketView;
    ColorView color;
    Scanner scanner;

    public HomeView(MarketView marketView, HistoryController historyController, ExchangeWalletController exchangeWalletController,UserController userController, UserWalletController userWalletController, Scanner scanner, ColorView color) {
        this.marketView = marketView;
        this.historyController = historyController;
        this.exchangeWalletController = exchangeWalletController;
        this.userController = userController;
        this.userWalletController = userWalletController;
        this.scanner = scanner;
        this.color = color;
    }

    public void home (){
        boolean loop = true;
        do {
            blankSpace();
            color.blueColor("Welcome " + userController.userNameLogin());
            color.greenColor("Select a option");
            System.out.println("1. My wallet");
            System.out.println("2. Cryptocurrency price");
            System.out.println("3. Market");
            System.out.println("4. Deposit fiat");
            System.out.println("5. View transactions history");
            System.out.println("0. Logout");
            int option = enterNumber();
            scanner.nextLine();
            blankSpace();
            if (option >= 0) {
                switch (option) {
                    case 1:
                        userWalletView();
                        break;
                    case 2:
                        cryptocurrenciesValue();
                        break;
                    case 3:
                        marketView.marketHome();
                        break;
                    case 4:
                        depositFiatView();
                        break;
                    case 5:
                        historyController.screenHistory();
                        break;
                    case 0:
                        System.out.println("exit logout");
                        loop = false;
                        break;
                    default:
                        color.redColor("Error: Enter a number of the list");
                        break;
                }
            }
        } while (loop);
    }

    private void cryptocurrenciesValue() {
        System.out.println(exchangeWalletController.screenCryptosPrice());
        color.greenColor("\nPress any key to return");
        scanner.nextLine();
        blankSpace();
    }

    private void userWalletView(){
        userController.userWalletScreen();
        color.greenColor("\nPress any key to return");
        scanner.nextLine();
        blankSpace();
    }

    private void depositFiatView(){
        System.out.println("Deposit to user account");
        String value = scanner.nextLine();
        userWalletController.depositFiat(value.trim());
        blankSpace();
    }

    private int enterNumber(){
        System.out.print(">> ");
        if(scanner.hasNextInt())
            return scanner.nextInt();
        else {
            System.out.println("Enter a valid number");
            return -1;
        }
    }

    private void blankSpace (){
        System.out.println();
    }
}
