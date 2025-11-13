package com.korit.korit_gov_servlet_study.ch02;
/*
 * List에 뚜두들 저장
 * 저장요청시 쿼리파라미터에서 값을 가져와 리스트에 저장
 * 저장 전에 3가지 필드가 다 채워져있는지 확인
 * 빈값있으면 map에 필드명과 빈값 일 수 없습니다 넣고 응답
 * 조회요청시 쿼리파라미터가 없으면 전체 조회
 * 있으면 title로 단건 조회
 * 해당 title 뚜두가 없으면 해당 뚜두가 없습니다. 응답
 * */

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.awt.SystemColor.text;

@WebServlet("/ch02/todos")
public class TodoServlet extends HttpMethodServlet {
    private List<Todo> todos;

    @Override
    public void init() throws ServletException {
        todos = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String username = req.getParameter("username");

        Todo todo = Todo.builder()
                .title(title)
                .content(content)
                .username(username)
                .build();

        Map<String,String> error = validTodo(todo);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (!error.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(error);
            return;
        }
        todos.add(todo);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Todos 등록완료");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String title = req.getParameter("title");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        if (title== null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(todos);
            return;
        }
        List<Todo> foundTodos = todos.stream()
                .filter(todo -> todo.getTitle().equals(title)).toList();
        if (foundTodos.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 title의 todo를 찾을 수 없습니다.");
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(foundTodos);
    }

    private Map<String, String> validTodo(Todo todo) {
        Map<String, String> error = new HashMap<>();
        Arrays.stream(todo.getClass().getDeclaredFields()).forEach(f -> {
            f.setAccessible(true);
            String fieldName = f.getName();
            System.out.println(fieldName);

            try {
                 Object fieldValue = f.get(todo);
                 System.out.println(fieldValue);

                 if (fieldValue == null) {
                     throw new RuntimeException();
                 }
                 if(fieldValue.toString().isEmpty()) {
                     throw new RuntimeException();
                 }
            }catch (IllegalAccessException e){
                System.out.println("접근 할수 없습니다.");
            }catch (RuntimeException e){
                error.put(fieldName, "빈 값일 수 없습니다");
            }
        });
        return error;
    }
}
