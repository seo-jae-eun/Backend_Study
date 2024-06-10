public class Hanoi {
    // 기둥 번호는 1~3번
    // 원반은 가장 작은 원반이 1 큰 원반일수록 숫자를 1씩 증가

    // 이동 (원반 번호와 from에서 to로 옮길지를 전달받는다.)
    public void move(Integer number, Integer from, Integer to) {
        // 원반 번호가 1보다 크면 원반 번호 -1을 from에서 from과 to가 아닌 곳으로 이동
        if(number > 1) {
            move(number - 1, from, 6 - from - to);
        }
        // 원반 번호를 from에서 to로 옮겼다고 출력
        System.out.println(number  + "을(를) " + from + "에서 " + to + "로 올겼습니다.");
        // 원반 번호가 1보다 크면 원반 번호 -1을 from과 to가 아닌 곳에서 to로 이동
        if(number > 1) {
            move(number - 1, 6 - from - to, to);
        }
    }
}
