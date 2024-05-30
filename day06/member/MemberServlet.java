package member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = new UserDto(req.getParameter("id"), req.getParameter("pw"));

        UserDao userDao = new UserDao();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(userDao.read(userDto)) {
            out.println("로그인 성공!!!");
        } else {
            out.println("로그인 실패!!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto = new UserDto(req.getParameter("id"), req.getParameter("pw"), req.getParameter("name"));

        UserDao userDao = new UserDao();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(userDao.create(userDto)) {
            out.println("회원가입 성공!!!");
        } else {
            out.println("회원가입 실패!!!");
        }
    }
}
