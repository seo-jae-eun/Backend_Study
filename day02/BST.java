public class BST {
    Integer[] bstArray;
    Integer data;

    void insert() {
        int index = 1;
        while(true) {
            if(bstArray[index] == null) {
                bstArray[index] = data;
                break;
            }
            else {
                if(bstArray[index] < data) {
                    index = index * 2 + 1; // 오른쪽 이동
                }
                else {
                    index = index * 2; // 왼쪽 이동
                }

            }
        }


    }

    void print() {
        for(int i = 0; i < 20; i++) {
            System.out.print(bstArray[i] + " ");
        }
    }


}
