package member;

import java.sql.*;

public class UserDao {
    public Boolean read(UserDto dto) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.141.100:3306/web",
                    "sje", "qwer1234"
            );
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM member WHERE id='"+dto.getId()+"' AND pw='"+dto.getPw()+"'");

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlEx) { } // ignore

                connection = null;
            }
        }

        return false;
    }

    public Boolean create(UserDto dto) {
        Connection connection = null;
        Statement stmt = null;
        Integer rs = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.141.100:3306/web",
                    "sje", "qwer1234"
            );
            stmt = connection.createStatement();
            rs = stmt.executeUpdate("INSERT INTO web.member (id, pw, name) VALUES ('"+dto.getId()+"', '"+dto.getPw()+"', '"+dto.getName()+"')");

            if (rs > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlEx) { } // ignore

                connection = null;
            }
        }
        return false;
    }
}
