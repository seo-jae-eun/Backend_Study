package com.example.day03.team;

import com.example.day03.student.Student;
import com.example.day03.student.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final StudentRepository studentRepository;

    public TeamService(TeamRepository teamRepository, StudentRepository studentRepository) {
        this.teamRepository = teamRepository;
        this.studentRepository = studentRepository;
    }

    public void create() {
        List<String> team_fiiiiive = Arrays.asList("파아아아아아이브", "강혜정", "박성준", "박종성", "송나경", "정수연");
        List<String> team_404x = Arrays.asList("404x", "김다윤", "오규림", "이가은", "장유정");
        List<String> team_gamja = Arrays.asList("감자", "김동욱", "김은선", "이재룡", "지연희", "차윤슬");
        List<String> team_ketchop = Arrays.asList("케챱", "구은주", "안준홍", "최정완", "한별");
        List<String> team_dopamines = Arrays.asList("도파민즈", "곽동현", "서시현", "유송연", "최수빈", "최승은");
        List<String> team_fourtrees = Arrays.asList("포트리스", "강태성", "김우혁", "도지민", "서재은");

        List<List<String>> teams = Arrays.asList(team_fiiiiive, team_404x, team_gamja, team_ketchop, team_dopamines, team_fourtrees);

        for (List<String> team : teams) {
            Team newTeam = new Team();
            newTeam.setTeamName(team.get(0));
            teamRepository.save(newTeam);
            for (int i = 1; i < team.size(); i++) {
                Student newStudent = new Student();
                newStudent.setName(team.get(i));
                newStudent.setTeam(newTeam);
                studentRepository.save(newStudent);
            }
        }
    }

    // SELECT * FROM team LEFT JOIN student ON team.idx = student.team_idx WHERE team.teamName=? 예상
    public void read1(String teamName) {
        Optional<Team> result = teamRepository.findByTeamName(teamName);
        Team team = result.get();


        for (Student student : team.getStudents()) {
            System.out.println(team.getTeamName()
                    + ", " + student.getName());
        }
    }

    // SELECT * FROM student LEFT JOIN team ON student.team_idx = team.idx WHERE student.name=? 예상
    public void read2(String studentName) {
        Optional<Student> result = studentRepository.findByName(studentName);
        Student student = result.get();
        for (Student getStudent : student.getTeam().getStudents()) {
            System.out.println(student.getTeam().getTeamName() + ", " + getStudent.getName());
        }
    }


    // SELECT * FROM team LEFT JOIN student ON team.idx = student.team_idx 예상
    public void read3() {
        System.out.println("팀을 통해 학생을 받아와서 출력");
        List<Team> teamList = teamRepository.findAll();
        // 팀을 통해 학생을 받아와서 출력
        for (Team team : teamList) {
            for (Student student : team.getStudents()) {
                System.out.println(team.getTeamName() + ", " + student.getName());
            }
        }

    }

    // SELECT * FROM student LEFT JOIN team ON student.team_idx = team.idx 예상
    public void read4() {
        // 학생을 통해 팀을 받아와서 출력
        System.out.println("학생을 통해 팀을 받아와서 출력");
        List<Student> studentList = studentRepository.findAll();

        for (Student student : studentList) {
            Team team = student.getTeam();
            System.out.println(team.getTeamName() + ", " + student.getName());
        }
    }


    public void update() {
    }

    public void delete() {
    }
}
