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

    // 내꺼
//    // 팀 저장 메소드
//    // 모든 팀 저장
//    // 각 팀의 팀원들에게 팀을 지정해서 팀원들 저장
//    public void create() {
//        Team team1 = new Team();
//        team1.setTeamName("4TREES");
//        Team team2 = new Team();
//        team2.setTeamName("파아아아아아이브");
//        Team team3 = new Team();
//        team3.setTeamName("404x");
//        Team team4 = new Team();
//        team4.setTeamName("감자");
//        Team team5 = new Team();
//        team5.setTeamName("케챱");
//        Team team6 = new Team();
//        team6.setTeamName("도파민즈");
//        teamRepository.save(team1);
//        teamRepository.save(team2);
//        teamRepository.save(team3);
//        teamRepository.save(team4);
//        teamRepository.save(team5);
//        teamRepository.save(team6);
//
//        Student student1 = new Student("서재은");
//        student1.setTeam(team1);
//        studentRepository.save(student1);
//        Student student2 = new Student("강태성");
//        student2.setTeam(team1);
//        studentRepository.save(student2);
//        Student student3 = new Student("도지민");
//        student3.setTeam(team1);
//        studentRepository.save(student3);
//        Student student4 = new Student("김우혁");
//        student4.setTeam(team1);
//        studentRepository.save(student4);
//    }
//
//    // 팀 이름을 입력받아서 팀 이름 및 팀원 조회 메소드
//    // 팀에 해당하는 팀원들 이름을 출력				출력은 팀 이름, 팀원 이름  형식으로 출력
//    public void read(String teamName) {
//        Long teamIdx = teamRepository.findByTeamName(teamName).get().getIdx();
//
//        List<Student> studentList = studentRepository.findByTeamIdx(teamIdx);
//        System.out.println(teamName);
//        for(Student student : studentList) {
//            System.out.println(student.getName());
//        }
//    }
//
//
//
//    // 학생 이름을 입력받아서 팀 이름 및 팀원 조회 메소드
//    // 팀이름과 팀에 해당하는 팀원들 이름을 출력				출력은 팀 이름, 팀원 이름  형식으로 출력
//    public void read2(String studentName) {
//    }

    // 강사님꺼
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
