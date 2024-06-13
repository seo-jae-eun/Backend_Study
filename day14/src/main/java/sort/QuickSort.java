package sort;

public class QuickSort {

    private void swap(Integer[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void quickSort(Integer[] arr, Integer left, Integer right) {
        // 정렬 수행하고 피봇과 바뀐 위치를 찾는다.
        // 바뀐 위치를 기준으로 왼쪽 배열을 다시 퀵정렬 메소드 실행
        // 바뀐 위치를 기준으로 오른쪽 배열을 다시 퀵정렬 메소드 실행
    }
    public void sort(Integer[] arr) {
        // 정렬하는 코드
        quickSort(arr, 0, arr.length - 1);
        // 퀵정렬 메소드 실행
        
        // 1번의 회전이 끝나면 배열의 데이터를 전부 출력
        
    }
}
