package org.globant.controller.user;

import org.globant.model.user.User;
import org.globant.model.user.UserRegisterException;
import org.globant.repository.ExchangeWalletRepository;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.services.userservices.LoginUserPort;
import org.globant.services.userservices.RegisterUserAccountPort;
import org.globant.services.userservices.UserServiceImpl;

import java.util.Map;

public class UserController {

    private final UserMemoryRepository userMemoryRepository = UserMemoryRepository.getInstance();
    private final Map<Integer,User> users = userMemoryRepository.getUsers();
    private final LoginUserRepository loginUserRepository = LoginUserRepository.getInstance();
    private final ExchangeWalletRepository exchangeWalletRepository = ExchangeWalletRepository.getInstance();

    private final RegisterUserAccountPort registerUserAccount = new UserServiceImpl(userMemoryRepository, loginUserRepository,exchangeWalletRepository);
    private final LoginUserPort loginUser = new UserServiceImpl(userMemoryRepository, loginUserRepository,exchangeWalletRepository);

    /**
     *  Method for register a user in the system (user repository)
     * @param name name of user
     * @param email email of user with @
     * @param password pass of user for enter in the system
     * @return message information
     */
    public String registerUserRepository(String name, String email, String password) {
        try {
            if (!(name.isEmpty() || email.isEmpty() || password.isEmpty())) {
                User user;
                if (email.contains("@")) {
                    int id = lastUserId();
                    user = registerUserAccount.userRegister(id, name, email, password);
                } else
                    return "The email format is invalid";
                if (user != null) {
                    return "User register Successful :)\nYour username is: " + user.getUserName();
                } else {
                    return "The email is already register";
                }
            } else {
                return "You cannot enter empty fields";
            }
        } catch (UserRegisterException e) {
            return "A problem occurred while registering your user ";
        }
    }

    /**
     * Method to login user register in te system
     * @param email email of user register
     * @param password pass of user register
     * @return boolean
     */
    public boolean loginUserSystem (String email, String password){
        if(!(email.isEmpty() || password.isEmpty())) {
            var userLogin = loginUser.searchUserAccount(email, password);
            if (userLogin != null) {
                int userId = loginUser.searchIdByUser(userLogin);
                if (userId >= 0) {
                    loginUser.enterUserSystem(userId, userLogin);
                    return true;
                } else {
                    System.out.println("\nUser id account not found :(");
                    return false;
                }
            } else {
                System.out.println("\nUser account not found :(");
                return false;
            }
        } else {
            System.out.println("\nYou cannot enter empty fields");
            return false;
        }
    }

    /**
     * method for present de user name of user
     * @return message
     */
    public String userNameLogin (){
        return loginUserRepository.getUserNameLogin();
    }

    /**
     * Method for present the wallet of user
     */
    public void userWalletScreen(){
        if(!loginUserRepository.getUserLogin().getUserWallet().toString().isEmpty()){
            System.out.println(loginUserRepository.getUserLogin().getUserWallet());
        } else {
            System.out.println("Your wallet not contains founds");
        }

    }

    /**
     * method for register the id in a new account
     * @return int id
     */
    private int lastUserId (){
        if(users.isEmpty()){
            return 0;
        }
        return users.size();
    }

}

