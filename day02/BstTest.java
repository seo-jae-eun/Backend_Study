public class BstTest {
    public static void main(String[] args) {
        BST bst;
        bst = new BST();
        bst.bstArray = new Integer[20];

        bst.data = 10;
        bst.insert();

        bst.data = 15;
        bst.insert();

        bst.data = 5;
        bst.insert();

        bst.data = 12;
        bst.insert();

        bst.data = 18;
        bst.insert();

        bst.data = 7;
        bst.insert();

        bst.data = 19;
        bst.insert();


        bst.print();

    }
}
