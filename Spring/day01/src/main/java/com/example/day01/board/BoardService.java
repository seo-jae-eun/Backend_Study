package com.example.day01.board;

import com.example.day01.board.model.BoardCreateReq;
import com.example.day01.board.model.BoardCreateRes;
import com.example.day01.board.model.BoardReadRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 서비스
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 요청 모델을 받아서 응답 모델을 반환하는 게시글 저장 메소드
    public BoardCreateRes create(BoardCreateReq boardCreateReq) {
        // 받은 제목에 '저장됨'이라는 글자를 추가해서 응답 모델에 저장 후 응답 모델 반환
        String titleResult = boardCreateReq.getTitle()+" 저장됨";
        return new BoardCreateRes(titleResult);
    }
    
    // 숫자를 1개 전달 받아서 응답 모델을 반환하는 게시글 조회 메소드
    public BoardReadRes read(int number) {
        // 받은 숫자가 1이면 제목을 "1번 제목" 내용을 "1번 내용"으로 응답 모델에 저장 후 응답 모델 반환
        // 받은 숫자가 2이면 제목을 "2번 제목" 내용을 "2번 내용"으로 응답 모델에 저장 후 응답 모델 반환
        String titleResult = number + "번 제목";
        String contentResult = number + "번 내용";

        return new BoardReadRes(titleResult, contentResult);
    }

    // 숫자를 1개 전달 받아서 글자를 반환하는 게시글 삭제 메소드
    public String delete(int number) {
        // 받은 숫자가 1이면 글자를 "1번 삭제"를 변수에 저장 후 반환
        String result = number + "번 삭제";
        return result;
    }
}
