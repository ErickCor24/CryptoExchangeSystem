package org.globant.view;

import org.globant.controller.user.UserController;
import org.globant.repository.ScannerRepository;

import java.util.Map;
import java.util.Scanner;

public class HomeView {

    UserController userController = new UserController();
    Scanner scanner = ScannerRepository.getInstance().getScanner();

    public HomeView() {
        home();
    }

    public void home (){
        System.out.println("Welcome " + userController.userNameLogin());
        System.out.println("Select a option");
        System.out.println("1. My wallet");
        System.out.println("2. Cryptocurrency price");
        System.out.println("3. Market");
        System.out.println("4. Deposit fiat");
        System.out.println("0. Logout");
        int option = enterNumber();
        scanner.nextLine();
        blankSpace();
        if (option >= 0){
            switch (option){
                case 1:
                    userWalletView();
                    break;
                case 2:
                    cryptocurrenciesValue();
                    break;
                case 3:
                    MarketView marketView = new MarketView();
                    break;
                case 4:
                    depositFiatView();
                    home();
                    break;
                case 0:
                    ConsoleView consoleView = new ConsoleView();
                    break;
                default:
                    home();
                    break;
            }
        }
        else {
            home();
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
        blankSpace();
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
        blankSpace();
    }

    private void depositFiatView(){
        System.out.println("Deposit to user account");
        String value = scanner.nextLine();
        userController.depositFiat(value.trim());
        blankSpace();
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
