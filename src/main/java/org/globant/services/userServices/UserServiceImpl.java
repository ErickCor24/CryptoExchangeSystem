package org.globant.services.userServices;

import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.model.user.User;

import java.util.Map;

public class UserServiceImpl implements RegisterUserAccountPort, DepositUserAccountPort, WithdrawUserAccountPort, LoginUserPort {
    UserMemoryRepository userMemoryRepository;
    LoginUserRepository loginUserRepository;

    public UserServiceImpl(UserMemoryRepository userMemoryRepository, LoginUserRepository loginUserRepository) {
        this.userMemoryRepository = userMemoryRepository;
        this.loginUserRepository = loginUserRepository;
    }

    @Override
    public User userRegister(int id,String name, String email, String password) {
        var users = userMemoryRepository.getUsers();
        User user = new User (name, email, password);
        users.put(id,user);
        return user;
    }

    @Override
    public User searchUserAccount(String email, String password) {
        var users = userMemoryRepository.getUsers();
        for(User user : users.values()){
            if(user.getEmail().equalsIgnoreCase(email)){
                if (user.getPassword().equalsIgnoreCase(password)){
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public Integer searchIdByUser(User user) {
        var users = userMemoryRepository.getUsers();
        for(Map.Entry<Integer, User> entry : users.entrySet()){
            if(entry.getValue().equals(user)){
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public void enterUserSystem(int id, User user) {
        loginUserRepository.changeUserLogin(id,user);
    }
}
