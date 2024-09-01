package org.globant.view;

import org.globant.controller.history.HistoryController;
import org.globant.controller.order.OrderController;
import org.globant.enums.Cryptos;

import java.util.Scanner;

public class OrderView {

    ColorView color;
    Scanner scanner;
    OrderController orderController;
    HistoryController historyController;

    public OrderView(HistoryController historyController, OrderController orderController, ColorView color, Scanner scanner) {
        this.historyController = historyController;
        this.color = color;
        this.scanner = scanner;
        this.orderController = orderController;
    }

    public void buyOrderView() {
        color.blueColor("BUY ORDER");
        Cryptos crypto = cryptoSelect();
        priceCryptoView(crypto);
        System.out.println("Enter the amount of crypto to buy");
        String cryptoAmount = amountEnter();
        System.out.println("Enter the maximum price to pay");
        String maximumAmount = amountEnter();
        blankSpace();
        color.yellowColor(orderController.makeBuyOrder(crypto,cryptoAmount,maximumAmount));
    }

    public void salesOrderView() {
        color.blueColor("SELL ORDER");
        Cryptos crypto = cryptoSelect();
        priceCryptoView(crypto);
        System.out.println("Enter the amount of crypto to sell");
        String cryptoAmount = amountEnter();
        System.out.println("Enter the price to sell");
        String maximumAmount = amountEnter();
        blankSpace();
        color.yellowColor(orderController.makeSalesOrder(crypto,cryptoAmount,maximumAmount));
    }

    private void priceCryptoView (Cryptos cryptos){
        switch (cryptos){
            case BITCOIN -> color.yellowColor(historyController.btcPrice());
            case ETHEREUM -> color.yellowColor(historyController.ethPrice());
            case UNISWAP -> color.yellowColor(historyController.uniPrice());
        }
    }

    private Cryptos cryptoSelect(){
        do {
            System.out.println("Select the crypto to buy: \n1. Bitcoin \n2. Ethereum \n3. UnisWap ");
            int option = enterNumber();
            scanner.nextLine();
            switch (option){
                case 1:
                    return Cryptos.BITCOIN;
                case 2:
                    return Cryptos.ETHEREUM;
                case 3:
                    return Cryptos.UNISWAP;
                default:
                    System.out.println("Select a number of the list");
            }
        }while (true);
    }

    private String amountEnter() {
        System.out.print(">> ");
        return scanner.nextLine();
    }

    private int enterNumber(){
        System.out.print(">> ");
        if(scanner.hasNextInt())
            return scanner.nextInt();
        else {
            blankSpace();
            color.redColor("Enter a valid number");
            blankSpace();
            return -1;
        }
    }

    private void blankSpace() {
        System.out.println();
    }
}
