package org.globant.controller.user;

import org.globant.model.user.User;
import org.globant.model.user.UserRegisterException;
import org.globant.services.UserMemoryService;

import java.util.List;

public class RootUser implements UserRegister {

    private List<User> users = UserMemoryService.getInstance().getUsers();
    private User user;

    @Override
    public void userRegister(String name, String email, String password) {
        try {
            user = new User(name, email, password);
            System.out.println("User register Successful");
        } catch (UserRegisterException e){
            System.out.println("A problem occurred while registering your user ");
        }

    }
}

