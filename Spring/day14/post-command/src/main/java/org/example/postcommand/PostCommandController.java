package org.example.postcommand;


import lombok.RequiredArgsConstructor;
import org.example.postcommand.model.requset.CreatePostReq;
import org.example.postcommand.model.response.CreatePostRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/command/post")
public class PostCommandController {
    private final PostCommandService postCommandService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<CreatePostRes> create(@RequestBody CreatePostReq request) {

        CreatePostRes response= postCommandService.create(request);
        return ResponseEntity.ok(response);
    }

}
