package com.example.day02.board;

import com.example.day02.board.model.BoardCreateReq;
import com.example.day02.board.model.BoardCreateRes;
import com.example.day02.board.model.BoardReadRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardCreateRes create(BoardCreateReq dto) {
        // 처리
        // DB에 저장
        int result = boardRepository.save(dto);
        if(result > 0) return new BoardCreateRes(dto.getTitle() + " 저장됨");
        else return new BoardCreateRes(dto.getTitle() + " 저장안됨");
    }

    public BoardReadRes read(int idx) {
        // 처리
        // DB에서 조회
        BoardReadRes boardReadRes = boardRepository.findById(idx);
        return boardReadRes;
    }

    // readAll
    public List<BoardReadRes> list() {
        // 처리
        // DB에서 조회
        List<BoardReadRes> boardReadResList = boardRepository.findAll();
        return boardReadResList;
    }

    public String delete(int idx) {
        return idx + "삭제";
    }
}
