package com.korit.korit_gov_servlet_study.ch03;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private List<Todo> todos;

    private static TodoRepository instance;
    private TodoRepository() {
        todos = new ArrayList<>();
    }
    private Integer todoId = 1;
    public static TodoRepository getInstance() {
        if (instance == null) {
            instance = new TodoRepository();
        }
        return instance;
    }
    //여기까지 작성하고 다시 서블릿으로 넘어감
    public Todo addTodo(Todo todo) {
        todo.setTodoId(todoId++);
        todos.add(todo);
        return todo;
    }


    public Todo findByTodo(String title) {
        return todos.stream()
                .filter(user -> user.getUsername().equals(title))
                .findFirst()
                .orElse(null);
    }
    public List<Todo> find(){
        return todos;
    }

}
