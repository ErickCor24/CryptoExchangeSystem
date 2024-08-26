package org.globant.view;

import org.globant.enums.Cryptos;
import org.globant.repository.ScannerRepository;

import java.util.Scanner;

public class MarketView {

    Scanner scanner = ScannerRepository.getInstance().getScanner();
    public void marketHome(){
        System.out.println("1. Buy cryptocurrencies on exchange");
        System.out.println("2. Create a purchase order");
        System.out.println("3. Create a sales order");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                buyDirectView();
                break;
            case 2:
                buyOrderView();
                break;
            case 3:
                salesOrderView();
                break;
            case 0:
                HomeView homeView = new HomeView();
                homeView.home();
        }
    }

    private void buyDirectView(){
        System.out.println("BUY DIRECT TO XCHANGE");
        System.out.println("Select the crypto to buy: \n1. Bitcoin \n2. Ethereum \n3. UnisWap \n0. Return to home ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
//                userController.buyCryptosToExchange(cryptos.BITCOIN, maximumMontEnter());
                System.out.println("Bitcoin sell successfully");
                break;
            case 2:
//                userController.buyCryptosToExchange(cryptos.ETHEREUM, maximumMontEnter());
                System.out.println("Ethereum sell successfully");
                break;
            case 3:
//                userController.buyCryptosToExchange(cryptos.UNISWAP, maximumMontEnter());
                System.out.println("UnisWap sell successfully");
                break;
            case 0:
                HomeView homeView = new HomeView();
                homeView.home();
                break;
        }
    }

    private void buyOrderView (){
        System.out.println("PURCHASE ORDER");
    }

    private void salesOrderView(){
        System.out.println("SALES ORDER");

    }

    private String maximumMontEnter (){
        return scanner.nextLine();
    }
}

