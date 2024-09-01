package org.globant.repository;

import org.globant.model.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserMemoryRepository {
    private static UserMemoryRepository instance;
    private final Map<Integer, User> users;

    private UserMemoryRepository() {
        this.users = new HashMap<>();
    }

    /**
     * return instance of UserMemoryRepository
     * @return instance
     */
    public static UserMemoryRepository getInstance(){
        if(instance == null){
            instance = new UserMemoryRepository();
        }
        return instance;
    }

    public Map<Integer,User> getUsers() {
        return users;
    }
}
