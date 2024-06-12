package avl;

import tree.TreePrinter;

public class AVLMain {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(8);
        tree.insert(9);
        TreePrinter.print(tree.root);

        tree.insert(10);
        TreePrinter.print(tree.root);

        tree.insert(15);
        TreePrinter.print(tree.root);

        tree.insert(20);
        TreePrinter.print(tree.root);

        tree.insert(17);
        TreePrinter.print(tree.root);

        tree.insert(25);
        TreePrinter.print(tree.root);

    }
}
