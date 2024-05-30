import java.sql.*;

public class DbTest {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.141.100:3306/web",
                    "sje", "qwer1234"
            );
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM board");
            // executeUpdate -> insert, update, delete

            while (rs.next()) {
                String idx = rs.getString("idx");
                String title = rs.getString("title");
                String contents = rs.getString("contents");

                System.out.println(idx);
                System.out.println(title);
                System.out.println(contents);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
