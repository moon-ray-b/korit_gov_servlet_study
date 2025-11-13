package com.korit.korit_gov_servlet_study.ch03;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/ch03/todos")
public class TodoServlet extends HttpServlet {
    private TodoRepository todoRepository;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        todoRepository = TodoRepository.getInstance();
        gson = new GsonBuilder().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        List<Todo> todos = todoRepository.find();
        SuccessResponse<List<Todo>>successResponse = SuccessResponse.<List<Todo>>builder()
                .status(200)
                .message("전체 출력")
                .body(todos)
                .build();
        String json = gson.toJson(successResponse);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Gson gson = new GsonBuilder().create();

        TodoDto todoDto = gson.fromJson(req.getReader(),TodoDto.class);//여기까지 하고 다시 레퍼지토리 로 돌아감

        Todo foundUser = todoRepository.findByTodo(todoDto.getTitle());

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        resp.setContentType("application/json");

        if (foundUser != null) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(400)
                    .message("이미 존재하는 title입니다.")
                    .build();
            resp.setStatus(400);
            String json = gson.toJson(errorResponse);
            resp.getWriter().write(json);
            return;
        }
        Todo todo = todoRepository.addTodo(todoDto.toEntity());

        SuccessResponse<Todo> successResponse = SuccessResponse.<Todo>builder()
                .status(200)
                .message("사용자 등록이 완료 되었습니다")
                .body(todo)
                .build();
        String json = gson.toJson(successResponse);
        resp.setStatus(200);
        resp.getWriter().write(json);
    }
}
