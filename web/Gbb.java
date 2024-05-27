import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/gbb")
public class Gbb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자한테 input 변수에 가위 또는 바위 또는 보를 입력받는다.
        String input = req.getParameter("input");

        Integer computer = (int)(Math.random() * 3);
        System.out.println(computer);
        Integer user;
        if(input.equals("가위")) {
            user = 0;
        }
        else if(input.equals("바위")) {
            user = 1;
        }
        else {
            user = 2;
        }

        // 컴퓨터가 0~2까지 랜덤한 수 하나를 생성
        // 0 = 가위
        // 1 = 바위
        // 2 = 보


        // 사용자가 입력한 가위, 바위, 보랑 컴퓨터가 생성한 거랑 비교해서
        // 사용자가 이겼으면 사용자 웹 브라우저에 니가 이겼다! 출력
        // 사용자가 졌으면 사용자 웹 브라우저에 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ 출력
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(user == 0) {
            if(computer == 0) {
                out.println("비김요.");
            }
            else if(computer == 1) {
                out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
            }
            else {
                out.println("니가 이겼다!");
            }
        }
        else if(user == 1) {
            if(computer == 0) {
                out.println("니가 이겼다!");
            }
            else if(computer == 1) {
                out.println("비김요.");
            }
            else {
                out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
            }
        }
        else {
            if(computer == 0) {
                out.println("니가 이겼다!");
            }
            else if(computer == 1) {
                out.println("ㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
            }
            else {
                out.println("비김요.");
            }
        }

    }
}
