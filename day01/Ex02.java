public class Ex02 {
    public static void main(String[] args) {
        // 연산자
        // 산술 연산자  +, -, *, /, %
        // 비교 연산자  >, <, >=, <=, ==, !=
        // 논리 연산자  &&, ||
        // 대입 연산자  왼쪽 = 오른쪽  오른쪽에 있는 걸 왼쪽에 저장

        Integer num01;
        num01 = 20;

        Integer num02;
        num02 = 10;

        Integer result;
        result = num01 / num02;

        System.out.println( result );

        Boolean result2;
        result2 = num01 == num02 && num01 > num02;

        System.out.println(result2);



    }
}
