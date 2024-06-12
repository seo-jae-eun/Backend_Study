package b;

import tree.TreePrinter;

public class BTreeMain {
    public static void main(String[] args) {
        BTree btree = new BTree();
        btree.insert(8);
        btree.insert(9);
        BTreePrinter.print(btree.head);

        btree.insert(10);
        BTreePrinter.print(btree.head);

        btree.insert(15);
        BTreePrinter.print(btree.head);

        btree.insert(20);
        BTreePrinter.print(btree.head);

        btree.insert(11);
        BTreePrinter.print(btree.head);

    }
}
