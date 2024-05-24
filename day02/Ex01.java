import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner sc;
        sc = new Scanner(System.in);

        System.out.print("글자 입력해라 : ");
        String str = sc.nextLine();
        System.out.println(str);

        System.out.print("숫자 입력해라 : ");
        Integer num = sc.nextInt();
        System.out.println(num);
    }
}
