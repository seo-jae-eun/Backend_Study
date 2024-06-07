import java.lang.reflect.Array;
import java.util.Arrays;

public class Stack<T> {
    // 숫자 배열을 저장할 수 있는 변수
    private T[] stackArray;
    // 어디까지 저장했는지를 기억할 변수
    private int top;

    // 생성자
    // 숫자 10개 저장할 수 있는 배열을 생성해서 변수에 저장
    // 어디까지 저장했는지를 기억할 변수에 -1을 저장
    public Stack(Class<T> clazz, int length) {
        this.stackArray = (T[]) Array.newInstance(clazz, length);
        this.top = -1;
    }


    // isEmpty
    public Boolean isEmpty() {
        return top == -1;
    }
    // isFull
    public Boolean isFull() {
        return top == stackArray.length - 1;
    }
    // peek
    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return stackArray[top];
    }

    // push 기능
    //      저장할 데이터를 전달받고
    //      스택이 가득차 있지 않으면
    //      top을 1증가하고 해당 인덱스 번호의 배열에 데이터 저장
    public void push(T number) {
        if(!isFull()) {
            top++;
            stackArray[top] = number;
        }
        else {
            System.out.println("스택이 가득 참");
        }
    }

    // pop 기능
    //  스택이 비어있지 않으면
    //      top 인덱스 번호의 배열에 값을 꺼내고
    //      top 인덱스 번호의 배열에 값을 비워주고
    //      top을 1 감소
    //      꺼낸 값을 반환
    //  스택이 비어있으면
    //      null을 반환
    public T pop() {
        if(!isEmpty()) {
            T result = stackArray[top];
            stackArray[top] = null;
            top--;
            return result;
        }
        else {
            System.out.println("스택이 비어있음");
            return null;
        }
    }

    // display
    public void display() {
        System.out.println(Arrays.toString(stackArray));
    }

    // 데이터를 거꾸로 정렬하는 기능
    // reverse
    //  배열을 전달 받고
    //  거꾸로 정렬된 배열을 반환
    public Integer[] reverse(Integer[] array) {
        // 2 4 1 5 3
        Stack reverseStack = new Stack(Integer.class, array.length);
        for(int i = 0; i < array.length; i++) {
            reverseStack.push(array[i]);
        }
        for(int i = 0; i < array.length; i++) {
            array[i] = (Integer)reverseStack.pop();
        }
        return array;
    }
    

    // 후위표기법으로 변경하는 기능
    //  "3+2*4-9/3" 중위표기법    "324*+93/-" 후위표기법 -> 8
    //  "3+2-4*9/3" 중위표기법    "32+49*3/-" 후위표기법 -> -7

    //  숫자가 오면 그냥 바로 출력
    //  연산자가 오면 스택에 집어넣는다.
    //  스택에 넣을 때 기존에 스택에 있는 연산자와 우선순위를 비교
    //  현재 연산자가 더 높으면 그냥 넣고
    //  현재 연산자가 낮거나 같으면 기존에 있는 연산자를 빼고 넣는다.

    public String postfix(String infixStr) {
        String result = "";
        Stack postfixStack = new Stack(Character.class, infixStr.length());


        for(int i = 0; i < infixStr.length(); i++) {
            int order = 0;
            if(infixStr.charAt(i) >= '1' && infixStr.charAt(i) <= '9') {
                result += Character.toString(infixStr.charAt(i));
            }
            else {
                if(!isEmpty()) {
                    if (postfixStack.peek() != null && order(infixStr.charAt(i)) > order((char)postfixStack.peek())) {
                        postfixStack.push(infixStr.charAt(i));
                    }
                    else {
                        while(postfixStack.peek() != null && order(infixStr.charAt(i)) <= order((char)postfixStack.peek())) { // -> -1??????
                            result += Character.toString((char)postfixStack.pop());
                        }
                        postfixStack.push(infixStr.charAt(i));
                    }

                }
                else {
                    postfixStack.push(infixStr.charAt(i));
                    top++;
                }
            }
        }
        while(postfixStack.peek() != null) {
            result += Character.toString((char)postfixStack.pop());

        }
        return result;
    }

    // 연산자 우선순위 비교
    private Integer order(char c) {
        if(c == '+' || c == '-') {
            return 1;
        }
        else if(c == '*' || c == '/') {
            return 2;
        }
        return 0;
    }


    // 후위표기법으로 사칙연산식 계산
    //  3+2*4-9/3 중위표기법    324*+93/- 후위표기법 -> 8
    //  3+2-4*9/3 중위표기법    32+49*3/- 후위표기법 -> -7
    //  앞에서부터 차례대로 스택에 넣는다.
    //  연산자를 넣을 때가 되면 스택에 저장된 데이터 2개(1번, 2번)를 꺼내서
    //  연산자로 (2번 연산자 1번) 순으로 계산 후 계산 결과를 스택에 넣는다.
    //  계산 결과를 반환

    public Integer postfixCalc(String postfixStr) {
        Stack calcStack = new Stack(Integer.class, postfixStr.length());

        for(int i = 0; i < postfixStr.length(); i++) {
            if(postfixStr.charAt(i) >= '1' && postfixStr.charAt(i) <= '9') {
                calcStack.push(Integer.parseInt(""+postfixStr.charAt(i)));
            } else {
                Integer num1 = Integer.parseInt(""+calcStack.pop());
                Integer num2 = Integer.parseInt(""+calcStack.pop());
                if(postfixStr.charAt(i) == '+') {
                    calcStack.push(num2 + num1);
                }
                else if(postfixStr.charAt(i) == '-') {
                    calcStack.push(num2 - num1);
                }
                else if(postfixStr.charAt(i) == '*') {
                    calcStack.push(num2 * num1);
                }
                else if(postfixStr.charAt(i) == '/') {
                    calcStack.push(num2 / num1);
                }
            }
        }
        return (Integer)calcStack.pop();

    }


}
