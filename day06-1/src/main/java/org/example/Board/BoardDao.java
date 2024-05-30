package org.example.Board;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;


public class BoardDao {
    public BoardDto[] read() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://192.168.141.100:3306/web");
        config.setUsername("sje");
        config.setPassword("qwer1234");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);

        try (HikariDataSource dataSource = new HikariDataSource(config);
             Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM board")) {

            rs.last();
            int count = rs.getRow();
            rs.beforeFirst();

            BoardDto[] boardDtos = new BoardDto[count];
            int index = 0;
            while (rs.next()) {
                boardDtos[index++] = new BoardDto(rs.getInt("idx"), rs.getString("title"), rs.getString("contents"));
            }
            return boardDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean create(BoardDto dto) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://192.168.141.100:3306/web");
        config.setUsername("sje");
        config.setPassword("qwer1234");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);

        try (HikariDataSource dataSource = new HikariDataSource(config);
             Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()) {

            int rs = stmt.executeUpdate("INSERT INTO web.board (title, contents) VALUES ('" + dto.getTitle() + "', '" + dto.getContents() + "')");

            return rs > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

