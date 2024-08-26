package org.globant.view;

import org.globant.controller.user.UserController;
import org.globant.repository.ScannerRepository;
import java.util.Scanner;

public class ConsoleView {
    Scanner scanner = ScannerRepository.getInstance().getScanner();
    UserController userController = new UserController();
    HomeView homeView = new HomeView();

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
                userController.usersScreen();
//                loginView();
                homeView.home();
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
    private void loginView(){
        String email;
        String password;
        System.out.println("WELCOME AGAIN");
        email = emailUserInput();
        password = passwordUserInput();
        userController.loginUserSystem(email, password);
        System.out.println(userController.userLoginScreen());
        homeView.home();
    }
    private void registerView(){
        System.out.println("WELCOME, PLEASE ENTER THE NEXT DATA");
        String name = nameUserInput();
        String email = emailUserInput();
        String password = passwordUserInput();
        userController.registerUserRepository(name, email, password);
    }

    private String nameUserInput (){
        System.out.println("Insert your name:");
        System.out.print("Name: ");
        return scanner.nextLine();
    }

    private String emailUserInput (){
        System.out.println("Insert your e-amil:");
        System.out.print("E-mail: ");
        return scanner.nextLine();
    }

    private String passwordUserInput (){
        System.out.println("Insert your password:");
        System.out.print("Password: ");
        return scanner.nextLine();
    }
}
