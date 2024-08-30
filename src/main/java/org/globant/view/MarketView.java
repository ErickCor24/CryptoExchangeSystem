package org.globant.view;

import org.globant.controller.history.HistoryController;
import org.globant.controller.wallet.ExchangeWalletController;
import org.globant.controller.wallet.UserWalletController;
import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;

import java.math.BigDecimal;
import java.util.Scanner;

public class MarketView {

    private final ColorView color;
    private final ExchangeWalletController exchangeWalletController;
//    private final UserController userController;
    private final UserWalletController userWalletController;
    private final HistoryController historyController;
    private final Scanner scanner;
    private final OrderView orderView;

    public MarketView(OrderView orderView, ExchangeWalletController exchangeWalletController,HistoryController historyController, UserWalletController userWalletController, Scanner scanner, ColorView color) {
        this.orderView = orderView;
        this.exchangeWalletController = exchangeWalletController;
        this.historyController = historyController;
        this.userWalletController = userWalletController;
        this.scanner = scanner;
        this.color = color;
    }

    public void marketHome(){
        boolean loop = true;
        do{
            blankSpace();
            color.greenColor("Select a option");
            System.out.println("1. Buy cryptocurrencies on exchange");
            System.out.println("2. Create a purchase order");
            System.out.println("3. Create a sales order");
            System.out.println("0. Return to Home");
            int option = enterNumber();
            scanner.nextLine();
            blankSpace();
            if (option >= 0) {
                switch (option) {
                    case 1:
                        buyDirectView();
                        loop = false;
                        break;
                    case 2:
                        orderView.buyOrderView();
                        loop = false;
                        break;
                    case 3:
                        orderView.salesOrderView();
                        loop = false;
                        break;
                    case 0:
                        orderView.screenOrders();
                        loop = false;
                        break;
                    default:
                        color.yellowColor("Select a option of the list");
                        break;
                }
            }
        } while (loop);
    }

    private void buyDirectView() {
        blankSpace();
        color.blueColor("BUY DIRECT TO EXCHANGE");
        boolean loop = true;
        do {
            System.out.println("Select the crypto to buy: \n1. Bitcoin \n2. Ethereum \n3. UnisWap \n0. Return ");
            int option = enterNumber();
            scanner.nextLine();
            blankSpace();
            if (option >= 0) {
                switch (option) {
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
                        loop = false;
                        break;
                    default:
                        System.out.println("Insert a number of the list");
                        break;
                }
            }
            blankSpace();
        } while (loop);
    }

    private void cryptoBuyView(Cryptos cryptos, String cryptoType){
        color.yellowColor("Enter the amount to buy");
        String amount = amountEnter();
        blankSpace();
        if (exchangeWalletController.changeStringDouble(amount)){
            if(exchangeWalletController.withdrawExchange(cryptos, amount)){
                if(userWalletController.depositUserWallet(cryptos, amount)){
                    historyController.addTransactionToUser(cryptos, new BigDecimal(amount), Transaction.DIRECT_BUY);
                    color.greenColor(cryptoType + " sell successfully");
                    System.out.println(exchangeWalletController.screenExchangeWallet());
                }
                else {
                    color.redColor("Your account not have necessary fiat");
                }
            }
            else {
                color.redColor("No funds available on Exchange :(");
            }
        } else {
            color.redColor("Error: Enter a correct number");
        }
        blankSpace();
    }

    private String amountEnter (){
        System.out.print(">> ");
        return scanner.nextLine();
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

