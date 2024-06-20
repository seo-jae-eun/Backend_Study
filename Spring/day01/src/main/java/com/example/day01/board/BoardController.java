package com.example.day01.board;

import com.example.day01.board.model.BoardCreateReq;
import com.example.day01.board.model.BoardCreateRes;
import com.example.day01.board.model.BoardReadRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board")
public class BoardController {
    private BoardService boardService;

    // 서비스 객체의 의존성을 생성자로 주입받는다.
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 1. 게시글 작성 기능
    // POST로 요청 모델을 받아서 응답 모델을 ResponseEntity에 담아서 반환하는 게시글 저장 메소드
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BoardCreateRes> create(@RequestBody BoardCreateReq boardCreateReq) {

        // 서비스 게시글 저장 메소드 실행
        BoardCreateRes boardCreateRes = boardService.create(boardCreateReq);
        // 실행 결과 반환
        return ResponseEntity.ok(boardCreateRes);
    }

    // 2. 게시글 조회 기능
    // GET 숫자를 1개 전달 받아서 응답 모델을 ResponseEntity에 담아서 반환하는 게시글 조회 메소드
    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<BoardReadRes> read(int number) {
        // 서비스 게시글 조회 메소드 실행
        BoardReadRes boardReadRes = boardService.read(number);
        // 실행 결과 반환
        return ResponseEntity.ok(boardReadRes);
    }
    
    // 3. 게시글 삭제 기능
    //GET으로 숫자를 1개 전달 받아서 글자를 ResponseEntity에 담아서 반환하는 게시글 삭제 메소드
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ResponseEntity<String> delete(int number) {
        // 서비스 게시글 삭제 메소드 실행
        String boardDeleteRes = boardService.delete(number);
        // 실행 결과 반환
        return ResponseEntity.ok(boardDeleteRes);
    }

}
