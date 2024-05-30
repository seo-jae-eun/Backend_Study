package org.example.Board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/boardSelect")
public class SelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BoardDto boardDto = new BoardDto(req.getParameter("id"), req.getParameter("pw"));

        BoardDao boardDao = new BoardDao();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        int index = 0;
        while(index < boardDao.read().length) {
            out.println(boardDao.read()[index].getIdx() + " " + boardDao.read()[index].getTitle() + "      " + boardDao.read()[index].getContents() + "<br>");
            index++;
        }
    }
}
