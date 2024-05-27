import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    // TCP 소켓 서버 코드
    // HTTP 프로토콜
    // HTTP 해석해서 응답해주는 코드

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("클라이언트가 get으로 요청했을 때 실행되는 메소드");

        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        System.out.println(id);
        System.out.println(pw);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("클라이언트 웹 브라우저에 나올 내용");
    }
}
