package org.globant.view;

import org.globant.controller.user.UserController;
import org.globant.controller.wallet.ExchangeWalletController;
import org.globant.enums.Cryptos;
import org.globant.repository.ScannerRepository;

import java.util.Scanner;

public class MarketView {

    ExchangeWalletController exchangeWalletController = new ExchangeWalletController();
    UserController userController = new UserController();
    Scanner scanner = ScannerRepository.getInstance().getScanner();

    public void marketHome(){
        System.out.println("1. Buy cryptocurrencies on exchange");
        System.out.println("2. Create a purchase order");
        System.out.println("3. Create a sales order");
        System.out.println("0. Return to Home");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                buyDirectView();
                returnHome();
                break;
            case 2:
                buyOrderView();
                break;
            case 3:
                salesOrderView();
                break;
            case 0:
                returnHome();
                break;
        }
    }

    private void buyDirectView(){
        System.out.println("BUY DIRECT TO EXCHANGE");
        System.out.println("Select the crypto to buy: \n1. Bitcoin \n2. Ethereum \n3. UnisWap \n0. Return to home ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                cryptoBuy(Cryptos.BITCOIN, "Bitcoin");
                break;
            case 2:
                cryptoBuy(Cryptos.ETHEREUM, "Ethereum");
                break;
            case 3:
                cryptoBuy(Cryptos.UNISWAP, "UnisWap");
                break;
            case 0:
                marketHome();
                break;
        }
    }

    private void buyOrderView (){
        System.out.println("PURCHASE ORDER");
    }

    private void salesOrderView(){
        System.out.println("SALES ORDER");
    }

    private void returnHome(){
        HomeView homeView = new HomeView();
        homeView.home();
    }

    private String amountEnter (){
        return scanner.nextLine();
    }

    private void cryptoBuy(Cryptos cryptos, String cryptoType){
        System.out.println("Enter the amount to buy");
        String amount = amountEnter();
        if(exchangeWalletController.withdrawExchange(cryptos, amount)){
            userController.depositUserWallet(cryptos, amount);
            System.out.println(cryptoType + " sell successfully");
            System.out.println(exchangeWalletController.screenExchangeWallet());
        }
        else {
            System.out.println("No funds available on Exchange :( \n");
        }
    }
}

