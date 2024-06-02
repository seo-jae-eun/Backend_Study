package org.example.board;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.DataSourceConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDao {
    HikariDataSource dataSourceConfig;

    BoardDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

    public BoardDto read(String idx) {
        System.out.println(dataSourceConfig);
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = dataSourceConfig.getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM board WHERE idx='"+idx+"'");

            if (rs.next()) {
                BoardDto dto = new BoardDto(
                        rs.getInt("idx"),
                        rs.getString("title"),
                        rs.getString("contents")
                );
                return dto;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }
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
        return null;
    }


    Boolean save(BoardDto dto) {
        Connection connection = null;
        Statement stmt = null;
        Integer result = null;
        try {
            connection = dataSourceConfig.getConnection();
            stmt = connection.createStatement();
            result = stmt.executeUpdate("INSERT INTO web.board (title, contents) VALUES ('"+dto.getTitle()+"', '"+dto.getContents()+"')");

            if (result > 0) {
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
