package com.korit.korit_gov_servlet_study.prt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {
    private List<User> users;


    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String username= req.getParameter("username");
        String password= req.getParameter("password");
        String email= req.getParameter("email");
        String name= req.getParameter("name");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding(StandardCharsets.UTF_8.name());

    }

}
