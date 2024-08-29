package org.globant.view;

import org.globant.controller.user.UserController;

import java.util.Scanner;

public class ConsoleView {

    ColorView color;
    Scanner scanner;
    UserController userController;
    HomeView homeView;

    public ConsoleView(HomeView homeView, UserController userController, Scanner scanner, ColorView color) {
        this.homeView = homeView;
        this.userController = userController;
        this.scanner = scanner;
        this.color = color;
    }

    public void execute() {
        do {
            blankSpace();
            color.blueColor("CRYPTO EXCHANGE SYSTEM\n");
            System.out.println("Select a option");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. To shut down the system");
            blankSpace();
            int flag = enterNumber();
            scanner.nextLine();
            blankSpace();
            if (flag != -1) {
                switch (flag) {
                    case 1:
                        userController.usersScreen();
                        boolean loginFlag = loginView();
                        if (loginFlag) {
                            homeView.home();
                        }
                        break;
                    case 2:
                        registerView();
                        break;
                    case 0:
                        color.yellowColor("Good Bye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        color.redColor("Error: Enter a number of the list");
                        break;
                }
            }
        } while (true);
    }

    private boolean loginView() {
        String email;
        String password;
        color.blueColor("USER LOGIN");
        email = emailUserInput();
        password = passwordUserInput();
        boolean flag = userController.loginUserSystem(email, password);
        if (flag) {
            System.out.println(userController.userLoginScreen());
        }
        blankSpace();
        return flag;
    }

    private void registerView() {
        color.blueColor("USER REGISTER");
        String name = nameUserInput();
        String email = emailUserInput();
        String password = passwordUserInput();
        blankSpace();
        userController.registerUserRepository(name, email, password);
    }

    private String nameUserInput() {
        System.out.println("Insert your name:");
        System.out.print(">> ");
        return scanner.nextLine();
    }

    private String emailUserInput() {
        System.out.println("Insert your e-amil:");
        System.out.print(">> ");
        return scanner.nextLine();
    }

    private String passwordUserInput() {
        System.out.println("Insert your password:");
        System.out.print(">> ");
        return scanner.nextLine();
    }

    private int enterNumber() {
        System.out.print(">> ");
        if (scanner.hasNextInt())
            return scanner.nextInt();
        else {
            blankSpace();
            color.redColor("Error: Enter a valid number");
            return -1;
        }
    }

    private void blankSpace() {
        System.out.println();
    }
}
