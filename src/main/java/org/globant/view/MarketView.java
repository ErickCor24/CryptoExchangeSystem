package org.globant.view;

import org.globant.controller.user.UserController;
import org.globant.controller.wallet.ExchangeWalletController;
import org.globant.controller.wallet.UserWalletController;
import org.globant.enums.Cryptos;
import org.globant.repository.ScannerRepository;

import java.util.Scanner;

public class MarketView {

    ExchangeWalletController exchangeWalletController = new ExchangeWalletController();
    UserController userController = new UserController();
    UserWalletController userWalletController = new UserWalletController();
    Scanner scanner = ScannerRepository.getInstance().getScanner();

    public MarketView() {
        marketHome();
    }

    public void marketHome(){
        System.out.println("1. Buy cryptocurrencies on exchange");
        System.out.println("2. Create a purchase order");
        System.out.println("3. Create a sales order");
        System.out.println("0. Return to Home");
        int option = enterNumber();
        scanner.nextLine();
        blankSpace();
        if (option >= 0){
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
                default:
                        System.out.println("Select a option of the list");
                        blankSpace();
                        marketHome();
                    break;
            }
        } else
            marketHome();

    }

    private void buyDirectView(){
        System.out.println("BUY DIRECT TO EXCHANGE");
        System.out.println("Select the crypto to buy: \n1. Bitcoin \n2. Ethereum \n3. UnisWap \n0. Return to home ");
        int option = enterNumber();
        scanner.nextLine();
        blankSpace();
        if (option >= 0){
            switch (option){
                case 1:
                    cryptoBuyView(Cryptos.BITCOIN, "Bitcoin");
                    break;
                case 2:
                    cryptoBuyView(Cryptos.ETHEREUM, "Ethereum");
                    break;
                case 3:
                    cryptoBuyView(Cryptos.UNISWAP, "UnisWap");
                    break;
                case 0:
                    marketHome();
                    break;
                default:
                    System.out.println("Insert a number of the list");
                    blankSpace();
                    buyDirectView();
                    break;
            }
        }
        else
            buyOrderView();
    }

    private void buyOrderView (){
        System.out.println("PURCHASE ORDER");
    }

    private void salesOrderView(){
        System.out.println("SALES ORDER");
    }

    private void returnHome(){
        HomeView homeView = new HomeView();
    }

    private void cryptoBuyView(Cryptos cryptos, String cryptoType){
        System.out.println("Enter the amount to buy");
        String amount = amountEnter();
        blankSpace();
        if (exchangeWalletController.changeStringDouble(amount)){
            if(exchangeWalletController.withdrawExchange(cryptos, amount)){
                if(userWalletController.depositUserWallet(cryptos, amount)){
                    System.out.println(cryptoType + " sell successfully");
                    System.out.println(exchangeWalletController.screenExchangeWallet());
                }
                else {
                    System.out.println("Your account not have necessary fiat");
                }
            }
            else {
                System.out.println("No funds available on Exchange :( \n");
                marketHome();
            }
        } else {
            System.out.println("Error: Enter a correct number");
            buyDirectView();
        }
    }

    private String amountEnter (){
        return scanner.nextLine();
    }

    private int enterNumber(){
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

