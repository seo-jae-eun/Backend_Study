package com.example.day03.team;

import com.example.day03.team.TeamService;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ResponseEntity<String> create() {
        teamService.create();
        return ResponseEntity.ok("생성");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read1")
    public ResponseEntity<String> read1(String teamName) {
        teamService.read1(teamName);
        return ResponseEntity.ok("조회1");
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read2")
    public ResponseEntity<String> read2(String studentName) {
        teamService.read2(studentName);
        return ResponseEntity.ok("조회2");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read3")
    public ResponseEntity<String> read3() {
        teamService.read3();
        return ResponseEntity.ok("조회3");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read4")
    public ResponseEntity<String> read4() {
        teamService.read4();
        return ResponseEntity.ok("조회4");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public ResponseEntity<String> update() {
        teamService.update();
        return ResponseEntity.ok("수정");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ResponseEntity<String> delete() {
        teamService.delete();
        return ResponseEntity.ok("삭제");
    }
}
