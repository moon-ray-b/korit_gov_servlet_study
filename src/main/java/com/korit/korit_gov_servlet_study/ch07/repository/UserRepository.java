package com.korit.korit_gov_servlet_study.ch07.repository;

import com.korit.korit_gov_servlet_study.ch07.dto.SignupReqDto;
import com.korit.korit_gov_servlet_study.ch07.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    //싱글톤 인스턴스 보관하는 변수.
    private static UserRepository instance;
    //유저 id 자동 증가 값.
    private int autoIncrement = 0;
    //임시db역할 하는 리스트
    private List<User> userList = new ArrayList<>();

    //외부에서 레버지토리를 못하게 막아둠
    private UserRepository() {}

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public User addUser(SignupReqDto signupReqDto) {
        User user = signupReqDto.toEntity();
        user.setUserId(autoIncrement++);
        userList.add(user);
        return user;
    }
}