package com.korit.korit_gov_servlet_study.ch03;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<User> findAll;
    private List<User> users;
    private Integer userId = 1;

    private static UserRepository instance;
    private UserRepository() {
        users = new ArrayList<>();
    }
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    public User addUser(User user) {
        user.setUserId(userId++);
        users.add(user);
        return user;
    }


    public User findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    public List<User> findByEmail(){
        return users;
    }

}
