package Board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idx = req.getParameter("idx");

        System.out.println(idx);


        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.141.100:3306/web",
                    "sje", "qwer1234"
            );
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM board WHERE idx=" + idx);

            if (rs.next()) {
                BoardDto boardDto = new BoardDto(rs.getInt("idx"), rs.getString("title"), rs.getString("contents"));

                PrintWriter out = resp.getWriter();
                out.println(boardDto.getIdx());
                out.println(boardDto.getTitle());
                out.println(boardDto.getContents());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
