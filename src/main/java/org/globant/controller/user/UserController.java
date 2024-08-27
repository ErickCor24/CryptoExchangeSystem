package org.globant.controller.user;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;
import org.globant.model.user.UserRegisterException;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.services.userServices.LoginUserPort;
import org.globant.services.userServices.RegisterUserAccountPort;
import org.globant.services.userServices.UserServiceImpl;
import org.globant.services.userWalletService.UserWalletPortServiceImpl;

import java.util.Map;

public class UserController {

    UserMemoryRepository userMemoryRepository = UserMemoryRepository.getInstance();
    private final Map<Integer,User> users = userMemoryRepository.getUsers();
    private final LoginUserRepository loginUserRepository = LoginUserRepository.getInstance();
    private User user;

    RegisterUserAccountPort registerUserAccount = new UserServiceImpl(userMemoryRepository, loginUserRepository);
    LoginUserPort loginUser = new UserServiceImpl(userMemoryRepository, loginUserRepository);

    public void registerUserRepository(String name, String email, String password) {
        try {
            int id = lastUserId();
            user = registerUserAccount.userRegister(id,name, email, password);
            System.out.println("User register Successful :)\nYour username is: " + user.getUserName());
            System.out.println(user.toString());
        } catch (UserRegisterException e){
            System.out.println("A problem occurred while registering your user ");
        }
    }

    public boolean loginUserSystem (String email, String password){
        var userLogin= loginUser.searchUserAccount(email, password);
        if(userLogin != null){
            int userId = loginUser.searchIdByUser(userLogin);
            if (userId >= 0){
                loginUser.enterUserSystem(userId, userLogin);
                return true;
            }
            else {
                System.out.println("User id account not found :(");
                return false;
            }
        }
        else {
            System.out.println("User account not found :(");
            return false;
        }
    }

    public String userLoginScreen(){
        return loginUserRepository.toString();
    }

    public String userNameLogin (){
        return loginUserRepository.getUserNameLogin();
    }

    public void usersScreen (){
        for(Map.Entry <Integer, User > entry : users.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private int lastUserId (){
        if(users.isEmpty()){
            return 0;
        }
        return users.size();
    }
}

