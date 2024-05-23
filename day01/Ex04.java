public class Ex04 {
    public static void main(String[] args) {
        // 반복문

        // ~~ 하는 동안
        // 만약에 조건이 참이면 조건이 거짓이 될 때까지 다음을 반복한다.
        //      반복할 코드

        // while (조건) {
        //      반복할 코드
        //      조건을 변경해줄 코드
        // }
        Integer num01;
        num01 = 10;
        
        while(num01 < 100) {
            System.out.println("while 작다");
            num01 = num01 + 33;
        }


        // i가 ~부터 ~까지 ~씩 (증가 or 감소)하면서 반복
        // for (초기값, 조건, 증감식) {
        //      반복할 코드
        // }
        for (Integer i = 10; i < 100; i = i + 33) {
            System.out.println("for 작다");
        }

        // 숫자를 1~100까지 모두 더한 값을 출력

        // 만약에 숫자가 100보다 작거나 같으면 반복 (while)
        //      숫자를 더하고
        //      숫자를 1증가
        Integer num02;
        num02 = 1;
        Integer total;
        total = 0;
        while(num02 <= 100) {
            total = total + num02;
            num02 = num02 + 1;
        }
        System.out.println(total);

        // 숫자를 1부터 100까지 1씩 증가시키면서 반복 (for)
        //      숫자를 더한다
        total = 0;
        for(Integer i = 1; i <= 100; i++) {
            total = total + i;
        }
        System.out.println(total);

        // 숫자를 1~100까지 짝수면 더한 값을 출력
        num02 = 1;
        total = 0;
        while(num02 <= 100) {
            if(num02 % 2 == 0) {
                total = total + num02;
            }
            num02 = num02 + 1;
        }
        System.out.println(total);


        // 숫자를 1~100까지 출력
        // 3의 배수면 짝! 이라고 출력
        num02 = 1;
        while(num02 <= 100) {
            if(num02 % 3 == 0) {
                System.out.println("짝!");
            }
            else {
                System.out.println(num02);
            }
            num02 = num02 + 1;
        }
        for(Integer i = 1; i <= 100; i++) {
            if(i % 3 == 0) {
                System.out.println("짝!");
            }
            else {
                System.out.println(i);
            }
        }

        // break : 반복문 안에서 코드가 실행되면 반복문을 종료
        // continue : 반복문 안에서 코드가 실행되면 밑에 코드를 실행 안하고 다음 반복문을 실행
        for(Integer i = 1; i <= 10; i++) {
            if(i == 4) {
//                break;
                continue;
            }
            System.out.println(i);
        }
    }
}
