import java.util.Objects;

public class 로또 {
    public static void main(String[] args) {
        System.out.println("로또 번호 생성기");

        // 6번 반복
        //      랜덤한 숫자 생성 (1 ~ 45)
        //      생성한 숫자 출력 (중복 없이)
        // ->
        // 숫자를 6개 저장할 수 있는 배열 생성
        // 6번 반복
        //      랜덤한 숫자 생성(1 ~ 45)
        //      현재 반복 횟수만큼 반복
        //          만약에 랜덤한 숫자랑 현재 반복 순서의 배열의 값이 같으면
        //              랜덤한 숫자 생성(1 ~ 45)
        //              현재 반복 순서를 -1로 변경 (맨 처음부터 비교해봐야하니까)
        //      현재 반복 순서 배열에 생성한 랜ㄴ덤 숫자를 저장
        //  배열에 저장된 모든 숫자 출력

        Integer[] numbers = new Integer[6];
        for(int i = 0; i < 6; i++) {
            Integer number = (int)(Math.random() * 45)+ 1;
            for (int j = 0; j < i; j++) {
                if (number == numbers[j]) {
                    number = (int)(Math.random() * 45)+ 1;
                    j = -1;
                }
            }
            numbers[i] = number;
            System.out.print(numbers[i] + " ");
        }
    }
}
