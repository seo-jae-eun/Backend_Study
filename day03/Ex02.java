public class Ex02 {
    // 메소드 매개변수 연습문제
    public static void main(String[] args) {
        // 차 객체 저장할 변수 생성
        Car car01;
        // 차 객체 생성해서 변수에 저장
        car01 = new Car();

        car01.color = "흰색";
        car01.speed = 0;

        // 가속
        car01.accel(5, 4);
        // 감속
        car01.decel(10, 2);
        // 도색
        car01.paint("검정");
    }
}
