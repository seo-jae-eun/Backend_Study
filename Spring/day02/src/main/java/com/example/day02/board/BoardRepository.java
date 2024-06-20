package com.example.day02.board;

import com.example.day02.board.model.BoardCreateReq;
import com.example.day02.board.model.BoardReadRes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BoardRepository {

    private JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(BoardCreateReq dto) {
        return jdbcTemplate.update("INSERT INTO board (title, contents, member_idx) VALUES (?, ?, ?)",
                dto.getTitle(),
                dto.getContents(),
                1
        );
    }

    public List<BoardReadRes> findAll() {
        List<BoardReadRes> boardReadRes = jdbcTemplate.query("SELECT * FROM board LEFT JOIN member ON board.member_idx = member.idx",
                // 실행 결과를 DTO로 옮기는 코드 (익명함수, 람다식)
                (rs, rowNum) -> {
                    BoardReadRes response = new BoardReadRes(rs.getString("title"), rs.getString("contents"), rs.getString("name"));
                    return response;
                }
        );
        return boardReadRes;
    }


    public BoardReadRes findById(int idx) {
        // queryForObject은 1개일 때 (여러 개일 땐 query())
        BoardReadRes boardReadRes = jdbcTemplate.queryForObject("SELECT * FROM board LEFT JOIN member ON board.member_idx = member.idx WHERE board.idx=?",
                // 실행 결과를 DTO로 옮기는 코드 (익명함수, 람다식)
                (rs, rowNum) -> {
                    BoardReadRes response = new BoardReadRes(rs.getString("title"), rs.getString("contents"), rs.getString("name"));
                    return response;
                },
                idx
        );
        return boardReadRes;
    }

    public void deleteById(int idx) {
        System.out.println("게시글 하나 삭제 실행");
    }

}
