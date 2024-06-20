package com.example.day02.board;

import com.example.day02.board.model.BoardCreateReq;
import com.example.day02.board.model.BoardCreateRes;
import com.example.day02.board.model.BoardReadRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<BoardCreateRes> create(@RequestBody BoardCreateReq dto){
        BoardCreateRes response = boardService.create(dto);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<BoardReadRes> read(int idx){
        BoardReadRes response = boardService.read(idx);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<BoardReadRes>> list(){
        List<BoardReadRes> response = boardService.list();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ResponseEntity<String> delete(int idx){
        String response = boardService.delete(idx);
        return ResponseEntity.ok(response);
    }
}
