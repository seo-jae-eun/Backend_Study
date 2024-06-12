package avl;

import tree.TreePrinter;

public class AVLNode implements TreePrinter.PrintableNode {
    Integer data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(Integer data) {
        this.data = data;
        height = 0;
    }

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return "["+data+"]";
    }
}
