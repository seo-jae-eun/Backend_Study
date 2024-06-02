import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


// 로직을 수정해서 SQL 인젝션 방어
@WebServlet("/member2")
public class MemberServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (action.equals("login")) {
            // 로그인 작업 수행
            Connection connection = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                connection = DriverManager.getConnection("jdbc:mariadb://192.168.141.100:3306/web", "sje", "qwer1234");
                stmt = connection.createStatement();
                rs = stmt.executeQuery("SELECT * FROM member WHERE id='" + id + "'");

                if (rs.next()) {
                    if(pw.equals(rs.getString("pw"))) {
                        req.getSession().setAttribute("isLogin", true);
                        out.println("로그인 성공");
                    } else {
                        out.println("로그인 실패");
                    }
                } else {
                    out.println("로그인 실패");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("check")) {
            // 로그인이 되어있는지 아닌지 확인해서 알려주는 작업 수행
            if (req.getSession().getAttribute("isLogin") != null && (Boolean) req.getSession().getAttribute("isLogin")) {
                out.println("로그인 한 사람");
            } else {
                out.println("로그인 안 한 사람");
            }
        }


    }


}
