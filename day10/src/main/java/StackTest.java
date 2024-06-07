import java.util.Arrays;

public class StackTest {
    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack()<Integer>();

        Stack<Integer> stack = new Stack<>(Integer.class,3);
//        stack.display();
//
//        stack.push(10);
//        stack.display();
//        stack.push(20);
//        stack.display();
//        stack.push(30);
//        stack.display();
//        stack.push(40);
//        stack.display();
//
//        stack.pop();
//        stack.display();
//        stack.pop();
//        stack.display();
//        stack.pop();
//        stack.display();
//        stack.pop();
//        stack.display();
//
//        stack.push(50);
//        stack.display();
//
//        System.out.println(stack.peek());
        
        
        // 데이터를 거꾸로 정렬
//        2 4 1 5 3
        Integer[] array = new Integer[]{2, 4, 1, 5, 3};
        System.out.println(Arrays.toString(stack.reverse(array)));


        System.out.println(stack.postfix("3+2*4-9/3"));
        System.out.println(stack.postfix("3+2-4*9/3"));
        System.out.println(stack.postfixCalc(stack.postfix("3+2*4-9/3")));
        System.out.println(stack.postfixCalc(stack.postfix("3+2-4*9/3")));

//        System.out.println(stack.postfix("2+3∗4−5"));
//        System.out.println(stack.postfix("6+2∗3/2−1+4"));
//        System.out.println(stack.postfixCalc(stack.postfix("2+3∗4−5"))); // 여기서 에러남
//        System.out.println(stack.postfixCalc(stack.postfix("6+2∗3/2−1+4")));

    }
}
