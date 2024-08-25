package org.globant.view;

import org.globant.controller.user.RootUser;
import org.globant.controller.user.UserRegister;
import org.globant.services.SingletonScanner;

import java.util.Scanner;

public class ConsoleView {
    Scanner scanner = SingletonScanner.getInstance().getScanner();
    UserRegister userRegister = new RootUser();

    public ConsoleView() {
        init();
    }

    public void init(){
        System.out.println("Crypto Exchange System");
        System.out.println("Select a option");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("Any other key to shut down the system");
        int flag = scanner.nextInt();
        scanner.nextLine();
        switch (flag){
            case 1:
                loginView();
                break;
            case 2:
                registerView();
                init();
                break;
            default:
                System.out.println("Good Bye!");
                break;
        }
    }

    public void loginView(){
        String email;
        String password;
        System.out.println("WELCOME AGAIN");
        System.out.println("Insert your e-amil:");
        System.out.print("E-mail: ");
        email = scanner.nextLine();
        System.out.println("Insert your password:");
        System.out.print("Password: ");
        password = scanner.nextLine();
    }

    public void registerView(){
        System.out.println("WELCOME, PLEASE ENTER THE NEXT DATA");
        System.out.println("Insert your name:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.println("Insert your e-amil:");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.println("Insert your password:");
        System.out.print("Password: ");
        String password = scanner.nextLine();
        userRegister.userRegister(name,email,password);
    }
}
