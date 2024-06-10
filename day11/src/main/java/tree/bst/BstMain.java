package tree.bst;

import tree.TreePrinter;

public class BstMain {
    public static void main(String[] args) {
        Bst bst = new Bst();

//        bst.add(10);
//        bst.add(15);
//        bst.add(20);
//        bst.add(13);
//        bst.add(7);
//        bst.add(5);
        bst.add2(10);
        bst.add2(20);
        bst.add2(15);
        bst.add2(40);
        bst.add2(5);
        bst.add2(50);
        bst.add2(35);
        bst.add2(17);
        bst.add2(5);
        TreePrinter.print(bst.root);
    }
}
