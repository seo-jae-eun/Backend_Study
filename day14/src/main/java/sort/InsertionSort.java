package sort;

import java.util.Arrays;

public class InsertionSort {
    public void sort(Integer[] arr) {
        System.out.println("0회전: " + Arrays.toString(arr));
        // 정렬하는 코드
        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] < arr[j]) {
                    Integer number = arr[i];
                    for(int k = i; k >= j + 1; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = number;
                }

            }
            System.out.println(i + "회전: " + Arrays.toString(arr));
        }
    }
}
