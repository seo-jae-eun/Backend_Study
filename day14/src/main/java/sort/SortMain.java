package sort;

public class SortMain {
    public static void main(String[] args) {
        Integer[] arr = {59 ,34 ,80 ,27, 61, 90, 55, 37, 25, 43};

        System.out.println("===================선택 정렬===================");
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);

        System.out.println("\n\n===================버블 정렬===================");
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(arr);

        System.out.println("\n\n===================삽입 정렬===================");
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(arr);

        System.out.println("\n\n===================퀵 정렬===================");
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
    }
}
