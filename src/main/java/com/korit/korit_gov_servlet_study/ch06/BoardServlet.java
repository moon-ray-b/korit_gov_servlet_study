package com.korit.korit_gov_servlet_study.ch06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ch06/boards")
public class BoardServlet extends HttpServlet {

    private Gson gson;
    private BoardRepository boardRepository;

    @Override
    public void init() throws ServletException {
        boardRepository = BoardRepository.getInstance();
        gson = new GsonBuilder().create();
    }

    // 게시글 작성 (POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 인코딩은 필터에서 UTF-8 설정됨
        resp.setContentType("application/json;charset=UTF-8");

        // JSON 바디 -> Board 객체로 변환
        Board board = gson.fromJson(req.getReader(), Board.class);
        System.out.println("요청으로 들어온 게시글 = " + board);  // 콘솔 확인용

        // 저장
        boardRepository.addBoard(board);

        // 응답 JSON
        SuccessResponse<Void> successResponse = SuccessResponse.<Void>builder()
                .message("게시글 작성 완료")
                .build();

        String json = gson.toJson(successResponse);
        resp.getWriter().write(json);
    }

    // 게시글 전체 조회 (GET)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");

        List<Board> boards = boardRepository.find();

        String json = gson.toJson(boards);   // 과제 요구대로 리스트만 반환
        resp.getWriter().write(json);
    }
}
