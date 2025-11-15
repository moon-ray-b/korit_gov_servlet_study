package com.korit.korit_gov_servlet_study.ch06;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;


public class BoardRepository {
    private List<Board> boards;

    private static BoardRepository instance;
    private BoardRepository() {
        boards = new ArrayList<>();
    }
    public static BoardRepository getInstance() {
        if (instance == null) {
            instance = new BoardRepository();
        }
        return instance;
    }
    public Board addBoard(Board board) {
        boards.add(board);
        return board;
    }
    public List<Board> find(){
        return boards;
    }
}