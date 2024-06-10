public class Recur {
    // 재귀함수
    //  메소드가 자기 자신을 실행하는 형태
    public static void main(String[] args) {
        // 1. 팩토리얼 계산
        Factorial factorial = new Factorial();
        System.out.println(factorial.calcRecur(5));
        System.out.println(factorial.calc(5));
        
        // 2. 하노이 탑
        Hanoi hanoi = new Hanoi();
        hanoi.move(3, 3, 1);
    }
}
