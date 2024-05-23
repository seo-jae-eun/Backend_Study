// 현실 세상에 있는 학생을
// 컴퓨터 세상에 객체로 만들기 위한 틀
// 학생 클래스
// 학생 클래스로 여러 학생 객체를 생성
public class Student {
    String name;
    Integer age;
    Integer 코딩실력;

    Computer computer;


    // 메소드 : 무언가를 하는 행위, 기능, 행동
    //          일반적으로 속성을 변경
    // 메소드를 만드는 법
    // 반환클래스 메소드이름 () {
    //      메소드가 실행됐을 때 실행될 코드
    //      return 반환값;
    // }

    Integer 공부() {
        코딩실력 = 코딩실력 + 10;

        return 코딩실력;
    }

    Integer 술() {
        코딩실력 = 코딩실력 - 10;

        return 코딩실력;
    }

}
