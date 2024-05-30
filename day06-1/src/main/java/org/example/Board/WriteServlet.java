package org.example.Board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/boardWrite")
public class WriteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        BoardDto boardDto = new BoardDto(req.getParameter("title"), req.getParameter("contents"));
        BoardDao boardDao = new BoardDao();


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(boardDao.create(boardDto)) {
            out.println("게시글 등록 성공!!!");
        } else {
            out.println("게시글 등록 실패!!!");
        }
    }
}
