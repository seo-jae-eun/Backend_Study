package org.example.postquery;


import lombok.RequiredArgsConstructor;
import org.example.postquery.model.response.ReadPostRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/query/post")
public class PostQueryController {
    private final PostQueryService postService;

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<ReadPostRes> read(Long idx) {
        ReadPostRes response= postService.read(idx);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<ReadPostRes>> list() {
        List<ReadPostRes> response= postService.list();
        return ResponseEntity.ok(response);
    }
}
