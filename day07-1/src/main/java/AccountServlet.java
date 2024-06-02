import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userName = req.getParameter("userName");
        Integer money = Integer.parseInt(req.getParameter("money"));


        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.141.100:3306/web",
                    "sje", "qwer1234"
            );
            stmt = connection.createStatement();
            synchronized (this) {
                rs = stmt.executeQuery("SELECT * FROM accounts WHERE username='"+ userName +"'");
                if(rs.next()) {
                    Integer myMoney = rs.getInt("money");

                    if(action.equals("입금")) {
                        myMoney += money;
                        stmt.executeUpdate("UPDATE accounts SET money = '" + myMoney + "' WHERE username='" + userName + "'");
                    } else {
                        myMoney -= money;
                        stmt.executeUpdate("UPDATE accounts SET money = '" + myMoney + "' WHERE username='" + userName + "'");
                    }

                    PrintWriter out = resp.getWriter();
                    out.println(myMoney);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
