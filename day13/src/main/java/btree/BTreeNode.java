package b;

public class BTreeNode implements BTreePrinter.PrintableNode {
    public Integer nodeType;
    public Integer s, l;
    public BTreeNode small, middle, large, parent;

    public BTreeNode(Integer nodeType, Integer s, Integer l, BTreeNode parent) {
        this.nodeType = nodeType;
        this.s = s;
        this.l = l;
        this.small = null;
        this.middle = null;
        this.large = null;
        this.parent = parent;
    }

    @Override
    public BTreePrinter.PrintableNode getLeft() {
        return small;
    }

    @Override
    public BTreePrinter.PrintableNode getMiddle() {
        return middle;
    }

    @Override
    public BTreePrinter.PrintableNode getRight() {
        return large;
    }

    @Override
    public String getText() {
        String str = "[ " + s + "," + l + " ]";
        return str;
    }
}