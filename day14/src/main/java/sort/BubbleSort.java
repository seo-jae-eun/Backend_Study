package sort;

import java.util.Arrays;

public class BubbleSort {
    public void sort(Integer[] arr) {
        System.out.println("0회전: " + Arrays.toString(arr));
        // 정렬하는 코드
        Integer tmp = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            System.out.println(i + 1 + "회전: " + Arrays.toString(arr));
        }

        // 정렬된 데이터 출력
    }
}
