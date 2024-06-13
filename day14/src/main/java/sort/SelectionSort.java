package sort;

import java.util.Arrays;

public class SelectionSort {

    public void sort(Integer[] arr) {
        System.out.println("0회전: " + Arrays.toString(arr));
        // 정렬하는 코드
        Integer index = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            Integer min = Integer.MAX_VALUE;
            for(int j = i; j < arr.length; j++) {
                if(arr[j] < min) {
                    min = arr[j];
                    index = j;
                }
            }
            arr[index] = arr[i];
            arr[i] = min;
            System.out.println(i + 1 + "회전: " + Arrays.toString(arr));
        }
        // 정렬된 데이터 출력
    }
}
