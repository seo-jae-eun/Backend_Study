public class 연금복권 {
    public static void main(String[] args) {
        System.out.println("연금복권 번호 생성기");

        // 6번 반복
        //      랜덤한 숫자 생성 (0 ~ 9)
        //      생성한 숫자 출력
        for(int i = 0; i < 6; i++) {
            Integer number = (int)(Math.random() * 10);
            System.out.print(number + " ");
        }
    }
}
