public class Factorial {

    public Integer calc(Integer num) {
        int result = 1;
        for(int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
    public Integer calcRecur(Integer num) {
//        calc(num); // 재귀함수 : 메모리 공간을 많이 차지함
        if(num == 1) {
            return 1;
        } else {
            return num * calc(num - 1);
        }
    }
}
