import java.util.Scanner;

public class 숫자야구 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int[] numbers;
        int count = 0;

        // 3자리 숫자 생성
        while(true) {
            num = (int)(Math.random() * 1000);

            numbers = new int[3];
            numbers[0] = num / 100;
            numbers[1] = (num % 100) / 10;
            numbers[2] = (num % 100) % 10;
            if(numbers[0] != numbers[1] && numbers[0] != numbers[2] && numbers[1] != numbers[2]) {
                break;
            }
        }
        System.out.println("-------------------------");
        System.out.printf("           %d%d%d\n", numbers[0], numbers[1], numbers[2]);
        System.out.println("-------------------------");


        // 숫자 3개 생성
//        Integer[] numbers = new Integer[3];
//        for(int i = 0; i < 3; i++) {
//            Integer number = (int)(Math.random() * 9);
//            for (int j = 0; j < i; j++) {
//                if (number == numbers[j]) {
//                    number = (int)(Math.random() * 9);
//                    j = -1;
//                }
//            }
//            numbers[i] = number;
//        }
//        int num = numbers[0] * 100 + numbers[1] * 10 + numbers[2];
//        System.out.println(num);


        while(true) {
            count++;
            int sCount = 0;
            int bCount = 0;
            int oCount = 0;
            System.out.print("숫자입력 : ");
            int inputNum = sc.nextInt();

            if(num == inputNum) {
                System.out.println("-----------성공-----------");
                System.out.println("           " + count + "번");
                System.out.println("--------------------------");
                break;
            }
            else {
                int[] inputNums = new int[3]; // 672
                inputNums[0] = inputNum / 100; // 470
                inputNums[1] = (inputNum % 100) / 10;
                inputNums[2] = (inputNum % 100) % 10;
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        if(numbers[i] == inputNums[j]) {
                            if(i == j) {
                                sCount++;
                            }
                            else {
                                bCount++;
                            }
                        }

                    }
                    oCount = 3 - sCount - bCount;
                }
                System.out.println(sCount + "s, " + bCount + "b, " + oCount + "o");
            }
        }

    }
}
