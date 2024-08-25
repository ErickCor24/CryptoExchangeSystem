package org.globant.services;

import org.globant.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserMemoryService {
    private static UserMemoryService instance;
    private final List<User> users;

    public UserMemoryService() {
        this.users = new ArrayList<>();
    }

    public static UserMemoryService getInstance(){
        if(instance == null){
            instance = new UserMemoryService();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }
}
