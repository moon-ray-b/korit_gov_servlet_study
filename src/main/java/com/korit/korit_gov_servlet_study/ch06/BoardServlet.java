package com.korit.korit_gov_servlet_study.ch06;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.korit.korit_gov_servlet_study.ch03.SuccessResponse;
import com.korit.korit_gov_servlet_study.ch03.Todo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ch06/login")
public class BoardServlet extends HttpServlet {
    private Gson gson;
    private BoardRepository boardRepository;

    @Override
    public void init() throws ServletException {
        BoardRepository boardRepository = BoardRepository.getInstance();
        Gson gson = new GsonBuilder().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setContentType("text/plain");
        //List<Board> Board = BoardRepository.find();
        SuccessResponse<List<Board>> successResponse = SuccessResponse.<List<Todo>>builder()
                .message("전체 출력")
                .build();
        String json = gson.toJson(successResponse);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String content = req.getParameter("content");
        String title = req.getParameter("title");
    }

}
