package org.globant.view;

import org.globant.repository.ScannerRepository;

import java.util.Map;
import java.util.Scanner;

public class HomeView {

    Scanner scanner = ScannerRepository.getInstance().getScanner();
    MarketView marketView = new MarketView();
    public void home (){
        System.out.println("Welcome *name user*");
        System.out.println("Select a option");
        System.out.println("1. My wallet");
        System.out.println("2. Cryptocurrency price");
        System.out.println("3. Market");
        System.out.println("4. Deposit fiat");
        System.out.println("0. Logout");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
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
            case 0:
                ConsoleView consoleView = new ConsoleView();
                break;
        }
    }

    private void cryptocurrenciesValue (){
        System.out.println("Bitcoin: 5000");
        System.out.println("Ethereum: 5000");
        System.out.println("UnisWap: 5000");
        System.out.println("Press any key to return");
        String flag = scanner.nextLine();
        if(flag != null){
            home();
        }
    }

    private void userWalletView(){
        System.out.println("Bitcoin: 5000");
        System.out.println("Ethereum: 5000");
        System.out.println("UnisWap: 5000");
        System.out.println("Fiat: $5000");
        System.out.println("Press any key to return");
        String flag = scanner.nextLine();
        if(flag != null){
            home();
        }
    }

    private void depositFiatView(){
        System.out.println("Deposit to user account");
        String value = scanner.nextLine();
    }

}
